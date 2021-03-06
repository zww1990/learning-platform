<#foreach property in pojo.getAllPropertiesIterator()>
	<#if pojo.getMetaAttribAsBool(property, "gen-property", true)>
		<#if property.isComposite()>
    /**
     * @return 联合主键
     */
		<#else>
 			<#foreach column in property.getColumnIterator()>    
 				<#if column.comment?exists && column.comment?trim?length!=0>
    /**
     * @return ${column.comment}
     */
				</#if>
			</#foreach>
		</#if>
    ${pojo.getPropertyGetModifiers(property)} ${pojo.getJavaTypeName(property, jdk5)} ${pojo.getGetterSignature(property)}() {
        return this.${c2j.keyWordCheck(property.name)};
    }
		<#if property.isComposite()>
    /**
     * @param ${c2j.keyWordCheck(property.name)} 联合主键
     */
		<#else>
 			<#foreach column in property.getColumnIterator()>    
 				<#if column.comment?exists && column.comment?trim?length!=0>
    /**
     * @param ${c2j.keyWordCheck(property.name)} ${column.comment}
     */
				</#if>
			</#foreach>
		</#if>
    ${pojo.getPropertySetModifiers(property)} void set${pojo.getPropertyName(property)}(${pojo.getJavaTypeName(property, jdk5)} ${c2j.keyWordCheck(property.name)}) {
        this.${c2j.keyWordCheck(property.name)} = ${c2j.keyWordCheck(property.name)};
    }
	</#if>
</#foreach>