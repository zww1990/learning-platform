<#if ejb3?if_exists>
	<#if pojo.isComponent()>
@${pojo.importType("javax.persistence.Embeddable")}
	<#else>
@${pojo.importType("javax.persistence.Entity")}
		<#assign uniqueConstraint=pojo.generateAnnTableUniqueConstraint()>
@${pojo.importType("javax.persistence.Table")}(name = "${clazz.table.name}"<#if uniqueConstraint?has_content>, uniqueConstraints = ${uniqueConstraint}</#if>)
	</#if>
</#if>