${pojo.getPackageDeclaration()}
// Generated ${date} by Hibernate Tools ${version}

<#assign classbody>
<#assign declarationName = pojo.importType(pojo.getDeclarationName())>/**
 * Data Access Object for domain model class ${declarationName}.
 * @see ${pojo.getQualifiedDeclarationName()}
 * @author Hibernate Tools
 */
public interface ${declarationName}DAO extends ${pojo.importType("org.springframework.data.jpa.repository.JpaRepository")}<${declarationName}, ${c2j.getJavaTypeName(clazz.identifierProperty, jdk5)}> {
}
</#assign>

${pojo.generateImports()}
${classbody}
