package cn.org.atool.generator.javafile.template;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import javax.lang.model.element.Modifier;

import com.squareup.javapoet.AnnotationSpec;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.CodeBlock;
import com.squareup.javapoet.FieldSpec;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;
import com.squareup.javapoet.TypeSpec;

import cn.org.atool.fluent.mybatis.metadata.DbType;
import cn.org.atool.generator.database.config.impl.RelationConfig;
import cn.org.atool.generator.database.model.ConfigKey;
import cn.org.atool.generator.database.model.Naming;
import cn.org.atool.generator.database.model.TableField;
import cn.org.atool.generator.database.model.TableSetter;
import cn.org.atool.generator.util.ClassNames;
import cn.org.atool.generator.util.FluentMybatisKit;
import cn.org.atool.generator.util.GeneratorHelper;

/**
 * Entity代码生成构造
 *
 * @author wudarui
 */
@SuppressWarnings("rawtypes")
public class EntityFile extends AbstractTemplateFile {

	public EntityFile(TableSetter table) {
		super(table);
		this.packageName = entityPackage(table);
		this.klassName = entityClass(table);
	}

	public static TypeName entityName(TableSetter table) {
		return ClassName.get(entityPackage(table), entityClass(table));
	}

	public static String entityPackage(TableSetter table) {
		return table.gc().getBasePackage() + ".entity";
	}

	/**
	 * 返回Entity类名
	 *
	 * @param table TableSetter
	 * @return ignore
	 */
	public static String entityClass(TableSetter table) {
		String entityPrefix = table.getEntityPrefix();
		if (entityPrefix.endsWith(table.getEntitySuffix())) {
			return entityPrefix;
		} else {
			return entityPrefix + table.getEntitySuffix();
		}
	}

	@Override
	protected void build(TypeSpec.Builder spec) {
		spec.addAnnotation(AnnotationSpec.builder(SuppressWarnings.class)
				.addMember("value", "{$S, $S}", "rawtypes", "unchecked").build());
		this.lombok(spec).addAnnotation(this.fluentMybatisAnnotation())
				.addJavadoc("$T: 数据映射实体定义\n\n", super.className())
				.addJavadoc("$L", "@author Powered By Fluent Mybatis");

		Set<String> exists = this.addSuperClass(spec);
		this.addSuperInterface(spec, table.getEntityInterfaces());
		spec.addField(this.f_serialVersionUID());
		for (TableField field : table.getFields()) {
			if (exists.contains(field.getName())) {
				continue;
			}
			FieldSpec.Builder fb = FieldSpec.builder(field.getJavaType(), field.getName(), Modifier.PRIVATE);
			// fb.addJavadoc("$L", field.getComment());
			if (field.isPrimary() && !"user".equals(table.getSeqName())) {
				fb.addAnnotation(this.getTableIdAnnotation(field));
			} else {
				fb.addAnnotation(this.getTableFieldAnnotation(field));
			}
			setFieldTypeAnnotation(fb, field);
			spec.addField(fb.build());
			if (!table.gc().isLombok()) {
				String capital = Naming.capitalFirst(field.getName());
				spec.addMethod(this.m_getter(capital, field));
				spec.addMethod(this.m_setter(capital, field, table.gc().isSetterChain()));
			}
		}

		spec.addMethod(this.m_entityClass());
		for (RelationConfig relation : table.getRelations()) {
			spec.addMethod(this.m_relation(relation));
		}
	}

	private void setFieldTypeAnnotation(FieldSpec.Builder spec, TableField field) {
		String column = field.getColumnName();
		// 判断是否逻辑删除字段
		if (Objects.equals(table.getLogicDeleted(), column)) {
			spec.addAnnotation(ClassNames.FM_LogicDelete);
		}
		// 判断是否乐观锁字段
		if (Objects.equals(table.getVersionField(), column)) {
			spec.addAnnotation(ClassNames.FM_Version);
		}
		if (Objects.equals(table.getGmtCreate(), column)) {
			spec.addAnnotation(ClassNames.FM_GmtCreate);
		}
		if (Objects.equals(table.getGmtModified(), column)) {
			spec.addAnnotation(ClassNames.FM_GmtModified);
		}
	}

	private FieldSpec f_serialVersionUID() {
		return FieldSpec.builder(long.class, "serialVersionUID", ClassNames.PRIVATE_STATIC_FINAL)
				.initializer(CodeBlock.of("1L")).build();
	}

	private Set<String> addSuperClass(TypeSpec.Builder spec) {
		Class superEntity = table.getSuperEntity();
		Set<String> fields = new HashSet<>();
		if (superEntity != null) {
			if (FluentMybatisKit.hasEntityType(superEntity)) {
				spec.superclass(ParameterizedTypeName.get(ClassName.get(superEntity), super.className()));
			} else {
				spec.superclass(superEntity);
			}
			Class klass = superEntity;
			while (FluentMybatisKit.notBaseEntityClass(klass)) {
				fields.addAll(this.getFields(klass));
				klass = klass.getSuperclass();
			}
		} else if (table.gc().isRichEntity()) {
			spec.superclass(ClassNames.FM_RichEntity);
		} else {
			spec.superclass(ClassNames.FM_BaseEntity);
		}
		return fields;
	}

	private Set<String> getFields(Class superEntity) {
		Set<String> fields = new HashSet<>();
		for (Field field : superEntity.getDeclaredFields()) {
			int mod = field.getModifiers();
			if (java.lang.reflect.Modifier.isTransient(mod) || java.lang.reflect.Modifier.isStatic(mod)) {
				continue;
			}
			fields.add(field.getName());
		}
		return fields;
	}

	private static final ClassName Lombok_Getter = ClassName.get("lombok", "Getter");
	private static final ClassName Lombok_Setter = ClassName.get("lombok", "Setter");

	/**
	 * 设置 lombok 相关注解
	 *
	 * @param spec TypeSpec.Builder
	 */
	private TypeSpec.Builder lombok(TypeSpec.Builder spec) {
		if (!table.gc().isLombok()) {
			return spec;
		}
//		spec.addAnnotation(ClassNames.Lombok_Data);
		spec.addAnnotation(Lombok_Getter);
		spec.addAnnotation(Lombok_Setter);
		if (table.gc().isSetterChain()) {
			spec.addAnnotation(AnnotationSpec.builder(ClassNames.Lombok_Accessors).addMember("chain", "true").build());
		}
//		spec.addAnnotation(
//				AnnotationSpec.builder(ClassNames.Lombok_EqualsAndHashCode).addMember("callSuper", "false").build())
//				.addAnnotation(ClassNames.Lombok_AllArgsConstructor).addAnnotation(ClassNames.Lombok_NoArgsConstructor);
		if (table.gc().isLombokBuilder()) {
			spec.addAnnotation(ClassNames.Lombok_Builder);
		}
		return spec;
	}

	private MethodSpec m_getter(String capital, TableField field) {
		return MethodSpec.methodBuilder("get" + capital).addModifiers(Modifier.PUBLIC).returns(field.getJavaType())
				.addStatement("return this.$L", field.getName()).build();
	}

	private MethodSpec m_setter(String capital, TableField field, boolean setterChain) {
		MethodSpec.Builder spec = MethodSpec.methodBuilder("set" + capital).addModifiers(Modifier.PUBLIC);
		if (setterChain) {
			spec.returns(this.className());
		}
		spec.addParameter(field.getJavaType(), field.getName()).addStatement("this.$L = $L", field.getName(),
				field.getName());
		if (setterChain) {
			spec.addStatement("return this");
		}
		return spec.build();
	}

	private MethodSpec m_entityClass() {
		return MethodSpec.methodBuilder("entityClass").addModifiers(Modifier.PUBLIC, Modifier.FINAL)
				.addAnnotation(Override.class).returns(Class.class).addStatement("return $L.class", entityClass(table))
				.build();
	}

	/**
	 * 设置关联字段和关联get方法
	 *
	 * @param relation RelationConfig
	 * @return MethodSpec
	 */
	private MethodSpec m_relation(RelationConfig relation) {
		String methodName = relation.methodName();
		String entityName = entityClass(table);
		boolean isAbstract = relation.getRelationByFields().isEmpty() && relation.getRelationByColumns().isEmpty();

		MethodSpec.Builder spec = MethodSpec.methodBuilder(methodName).addModifiers(Modifier.PUBLIC)
				.addAnnotation(relation.refMethodAnnotation()).returns(relation.returnType())
				.addStatement("return super.invoke($S, $L)", methodName, relation.isCached());
		if (isAbstract) {
			spec.addJavadoc("@see $L.IEntityRelation#$LOf$L($L)", table.gc().getBasePackage(), methodName, entityName,
					entityName);
		} else {
			spec.addJavadoc("@see $L.IEntityRelation#$LOf$L($T)", table.gc().getBasePackage(), methodName, entityName,
					List.class);
		}
		return spec.build();
	}

	/**
	 * 构造 @TableField(...)
	 *
	 * @param field TableField
	 * @return ignore
	 */
	private AnnotationSpec getTableFieldAnnotation(TableField field) {
		AnnotationSpec.Builder spec = AnnotationSpec.builder(ClassNames.FM_TableField).addMember("value", "$S",
				field.getColumnName());
		if (!GeneratorHelper.isBlank(field.getInsert())) {
			spec.addMember("insert", "$S", field.getInsert());
		}
		if (!GeneratorHelper.isBlank(field.getUpdate())) {
			spec.addMember("update", "$S", field.getUpdate());
		}
		if (field.getIsLarge() != null && !field.getIsLarge()) {
			spec.addMember("notLarge", "$L", Boolean.FALSE.toString());
		}
		this.addHandlerAndComment(spec, field);
		return spec.build();
	}

	/**
	 * 构造 @TableId(...)
	 *
	 * @param field TableField
	 * @return ignore
	 */
	private AnnotationSpec getTableIdAnnotation(TableField field) {
		AnnotationSpec.Builder spec = AnnotationSpec.builder(ClassNames.FM_TableId).addMember("value", "$S",
				field.getColumnName());
		String seqName = table.getSeqName();
		if (!this.isAuto(seqName, field.isPrimaryId())) {
			spec.addMember("auto", "$L", Boolean.FALSE.toString());
		}
		if (!GeneratorHelper.isBlank(seqName) && !"auto".equals(seqName)) {
			spec.addMember("seqName", "$S", seqName);
		}
		boolean before = table.selectKeyBefore();
		if (before) {
			spec.addMember("before", "$L", true);
		}
		this.addHandlerAndComment(spec, field);
		return spec.build();
	}

	private void addHandlerAndComment(AnnotationSpec.Builder spec, TableField field) {
		if (field.getTypeHandler() != null) {
			spec.addMember("typeHandler", "$T.class", field.getTypeHandler());
		}
		String comment = field.getComment();
		if (!GeneratorHelper.isBlank(comment)) {
			spec.addMember("desc", "$S", this.notNewLine(comment));
		}
	}

	boolean isAuto(String seqName, boolean isPrimaryId) {
		return "auto".equals(seqName) || ("".equals(seqName) && isPrimaryId);
	}

	private void addSuperInterface(TypeSpec.Builder builder, List<Class> interfaces) {
		if (interfaces == null || interfaces.size() == 0) {
			return;
		}
		for (Class _interface : interfaces) {
			if (FluentMybatisKit.hasEntityType(_interface)) {
				builder.addSuperinterface(parameterizedType(ClassName.get(_interface), this.className()));
			} else {
				builder.addSuperinterface(_interface);
			}
		}
	}

	/**
	 * 生成 @FluentMybatis注解
	 *
	 * @return ignore
	 */
	private AnnotationSpec fluentMybatisAnnotation() {
		AnnotationSpec.Builder builder = AnnotationSpec.builder(ClassNames.FM_FluentMybatis);

		builder.addMember("table", "$S", table.getTableName());
		if (!table.gc().getSchema().trim().isEmpty()) {
			builder.addMember("schema", "$S", table.gc().getSchema().trim());
		}
		if (table.gc().isUseCached()) {
			builder.addMember("useCached", "true");
		}
		if (!GeneratorHelper.isBlank(table.getMapperBeanPrefix())) {
			builder.addMember("mapperBeanPrefix", "$S", table.getMapperBeanPrefix());
		}
		if (table.getDefaults() != null) {
			builder.addMember("defaults", "$T.class", table.getDefaults());
		}
		if (table.getSuperMapper() != null) {
			builder.addMember("superMapper", "$T.class", table.getSuperMapper());
		}
		if (!Objects.equals(table.getEntitySuffix(), ConfigKey.Entity_Default_Suffix)) {
			builder.addMember("suffix", "$S", table.getEntitySuffix());
		}
		if (DbType.MYSQL != table.gc().getDbType()) {
			builder.addMember("dbType", "$T.$L", ClassNames.FM_FluentDbType, table.gc().getDbType().name());
		}
		if (!table.isUseDao()) {
			builder.addMember("useDao", "false");
		}
		if (!GeneratorHelper.isBlank(table.getComment())) {
			builder.addMember("desc", "$S", this.notNewLine(table.getComment()));
		}
		return builder.build();
	}

	private String notNewLine(String text) {
		if (text == null) {
			return "";
		} else {
			return text.replaceAll("[\\n\\r]+", ";").trim();
		}
	}

	@Override
	protected boolean isInterface() {
		return false;
	}
}
