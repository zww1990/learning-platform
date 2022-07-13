${servicePackageName}
<#assign classbody>
<#assign declarationName = pojo.getDeclarationName()>

/**
 * Service for domain model class ${declarationName}<#if clazz.table.comment?exists  && clazz.table.comment?trim?length!=0> : ${clazz.table.comment}</#if>
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
public interface ${declarationName}Service {
}
</#assign>
${classbody}