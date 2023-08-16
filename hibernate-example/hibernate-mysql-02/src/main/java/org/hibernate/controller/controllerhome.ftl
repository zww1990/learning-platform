${controllerPackageName}

<#assign classbody>
<#assign declarationName = pojo.getDeclarationName()>
<#assign declarationCamelName = exporter.toCamelCase(declarationName)>
import javax.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ${servicePackageName}.${declarationName}Service;

/**
 * Controller for domain model class ${declarationName}<#if clazz.table.comment?exists  && clazz.table.comment?trim?length!=0> : ${clazz.table.comment}</#if>
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
@RestController
@RequestMapping("/${declarationCamelName}")
public class ${declarationName}Controller {
	private static final Logger log = LoggerFactory.getLogger(${declarationName}Controller.class);
	@Resource
	private ${declarationName}Service ${declarationCamelName}Service;

}
</#assign>
${classbody}