/**
 * domain model class ${pojo.getDeclarationName()}<#if clazz.table.comment?exists  && clazz.table.comment?trim?length!=0> : ${clazz.table.comment}</#if>
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
<#include "Ejb3TypeDeclaration.ftl"/>
@SuppressWarnings("serial")
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()} ${pojo.getExtendsDeclaration()} ${pojo.getImplementsDeclaration()}