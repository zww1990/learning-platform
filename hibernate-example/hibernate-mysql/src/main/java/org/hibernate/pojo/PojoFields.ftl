<#foreach property in pojo.getAllPropertiesIterator()>
<#if pojo.getMetaAttribAsBool(property, "gen-property", true)>
<#include "Ejb3PropertyGetAnnotation.ftl"/>
    ${pojo.getFieldModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} ${c2j.keyWordCheck(property.name)}<#if pojo.hasFieldInitializor(property, jdk5)> = ${pojo.getFieldInitialization(property, jdk5)}</#if>;
</#if>
</#foreach>