package org.hibernate.cfg.reveng;

import java.sql.DatabaseMetaData;
import java.util.Iterator;
import java.util.Map;

import org.hibernate.JDBCException;
import org.hibernate.cfg.JDBCBinderException;
import org.hibernate.cfg.reveng.dialect.MetaDataDialect;
import org.hibernate.mapping.Column;
import org.hibernate.mapping.Table;
import org.hibernate.tool.util.TableNameQualifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BasicColumnProcessor {

	private static final Logger log = LoggerFactory.getLogger(BasicColumnProcessor.class);

	public static void processBasicColumns(
			MetaDataDialect metaDataDialect, 
			ReverseEngineeringStrategy revengStrategy, 
			String defaultSchema, String defaultCatalog, 
			Table table, 
			ProgressListener progress) {
		
		String qualify = TableNameQualifier.qualify(table.getCatalog(), table.getSchema(), table.getName() );
		Iterator<?> columnIterator = null;
		
		try {
			Map<?, ?> columnRs = null;
			log.debug("Finding columns for " + qualify );
			progress.startSubTask("Finding columns for " + qualify);
			columnIterator = metaDataDialect.getColumns(getCatalogForDBLookup(table.getCatalog(), defaultCatalog), getSchemaForDBLookup(table.getSchema(), defaultSchema), table.getName(), null);
			while (columnIterator.hasNext() ) {
				columnRs = (Map<?, ?>) columnIterator.next();
				String tableName = (String) columnRs.get("TABLE_NAME");
				int sqlType = ((Integer)columnRs.get("DATA_TYPE")).intValue();
				String columnName = (String) columnRs.get("COLUMN_NAME");
				String comment = (String) columnRs.get("REMARKS");
				
				TableIdentifier ti = RevEngUtils.createTableIdentifier(table, defaultCatalog, defaultSchema);
				if(revengStrategy.excludeColumn(ti, columnName)) {
					log.debug("Column " + ti + "." + columnName + " excluded by strategy");
					continue;
				}
				if(!tableName.equals(table.getName())) {
					log.debug("Table name " + tableName + " does not match requested " + table.getName() + ". Ignoring column " + columnName + " since it either is invalid or a duplicate" );
					continue;
				}
				
				int dbNullability = ((Integer)columnRs.get("NULLABLE")).intValue();
				boolean isNullable = true;
				switch (dbNullability) {
				case DatabaseMetaData.columnNullable:
				case DatabaseMetaData.columnNullableUnknown:
					isNullable = true;
					break;
				case DatabaseMetaData.columnNoNulls:
					isNullable = false;
					break;
				default:
					isNullable = true;
				}
				
				int size = ((Integer)columnRs.get("COLUMN_SIZE")).intValue();
				int decimalDigits = ((Integer)columnRs.get("DECIMAL_DIGITS")).intValue();
				
				Column column = new Column();
				column.setName(quote(columnName, metaDataDialect));
				Column existing = table.getColumn(column);
				if(existing!=null) {
					// TODO: should we just pick it up and fill it up with whatever we get from the db instead ?
					throw new JDBCBinderException(column + " already exists in " + qualify);
				}
								
				column.setComment(comment);
				column.setSqlTypeCode(sqlType );
        		column.setLength(size);
        		column.setPrecision(size); 
        		column.setScale(decimalDigits);
				
				column.setNullable(isNullable);

				// columnDefaultValue is useless for Hibernate
				// isIndexed  (available via Indexes)
				// unique - detected when getting indexes
				// isPk - detected when finding primary keys				
				
				table.addColumn(column);
			}
		}
		finally {
			
			if(columnIterator!=null) {
				try {
					metaDataDialect.close(columnIterator);
				} catch(JDBCException se) {
					log.warn("Exception while closing iterator for column meta data",se);
				}
			}
		}
				
	}

	private static String getCatalogForDBLookup(String catalog, String defaultCatalog) {
		return catalog==null?defaultCatalog:catalog;			
	}

	private static String getSchemaForDBLookup(String schema, String defaultSchema) {
		return schema==null?defaultSchema:schema;
	}


	private static String quote(String columnName, MetaDataDialect metaDataDialect) {
		   if(columnName==null) return columnName;
		   if(metaDataDialect.needQuote(columnName)) {
			   if(columnName.length()>1 && columnName.charAt(0)=='`' && columnName.charAt(columnName.length()-1)=='`') {
				   return columnName; // avoid double quoting
			   }
			   return "`" + columnName + "`";
		   } else {
			   return columnName;
		   }		
	}
}
