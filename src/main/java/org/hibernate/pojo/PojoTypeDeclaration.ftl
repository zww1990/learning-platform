/**
 * domain model class ${pojo.getDeclarationName()}.
 * @author Hibernate Tools ${version}
 * @date ${date}
 */
<#include "Ejb3TypeDeclaration.ftl"/>
@SuppressWarnings("serial")
${pojo.getClassModifiers()} ${pojo.getDeclarationType()} ${pojo.getDeclarationName()} ${pojo.getExtendsDeclaration()} ${pojo.getImplementsDeclaration()}