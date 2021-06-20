${serviceImplPackageName}

<#assign classbody>
<#assign declarationName = pojo.getDeclarationName()>
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ${daoPackageName}.${declarationName}DAO;
import ${servicePackageName}.${declarationName}Service;

/**
 * Service for domain model class ${declarationName}<#if clazz.table.comment?exists  && clazz.table.comment?trim?length!=0> : ${clazz.table.comment}</#if>
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
@Service
@Transactional
public class ${declarationName}ServiceImpl implements ${declarationName}Service {
	private static final Logger log = LoggerFactory.getLogger(${declarationName}ServiceImpl.class);
	@Resource
	private ${declarationName}DAO ${exporter.toCamelCase(declarationName)}DAO;

}
</#assign>
${classbody}