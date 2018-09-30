${daoPackageName}

<#assign classbody>
<#assign declarationName = pojo.getDeclarationName()>
<#assign qualifiedDeclarationName = pojo.getQualifiedDeclarationName()>
import ${qualifiedDeclarationName};

/**
 * Data Access Object for domain model class ${declarationName}<#if clazz.table.comment?exists  && clazz.table.comment?trim?length!=0> : ${clazz.table.comment}</#if>
 * @see ${qualifiedDeclarationName}
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
public interface ${declarationName}DAO extends ${pojo.importType("org.springframework.data.jpa.repository.JpaRepository")}<${declarationName}, ${pojo.importType(c2j.getJavaTypeName(clazz.identifierProperty, jdk5))}> {
}
</#assign>
${pojo.generateImports()}
${classbody}