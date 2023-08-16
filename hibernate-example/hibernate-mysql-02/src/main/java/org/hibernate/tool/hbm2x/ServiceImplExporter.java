package org.hibernate.tool.hbm2x;

import java.io.File;
import java.util.Map;
import java.util.Properties;

import org.hibernate.cfg.Configuration;
import org.hibernate.internal.util.StringHelper;
import org.hibernate.tool.hbm2x.pojo.POJOClass;

public class ServiceImplExporter extends POJOExporter {

	private String daoPackageName;
	private String servicePackageName;

	public ServiceImplExporter(Configuration cfg, File outputdir, Properties daoProperties,
			Properties serviceProperties) {
		super(cfg, outputdir);
		this.daoPackageName = daoProperties.getProperty("package-name");
		this.servicePackageName = serviceProperties.getProperty("package-name");
	}

	@Override
	protected void init() {
		super.init();
		super.setTemplateName("org/hibernate/serviceimpl/serviceimplhome.ftl");
		super.setFilePattern("{package-name}/{class-name}ServiceImpl.java");
	}

	@Override
	protected void exportComponent(Map<String, Object> additionalContext, POJOClass element) {
		// noop - we dont want components
	}

	@Override
	public String getName() {
		return "hbm2serviceimpl";
	}

	protected void exportPOJO(Map<String, Object> additionalContext, POJOClass element) {
		TemplateProducer producer = new TemplateProducer(super.getTemplateHelper(), super.getArtifactCollector());
		additionalContext.put("pojo", element);
		additionalContext.put("clazz", element.getDecoratedObject());
		additionalContext.put("daoPackageName", this.daoPackageName);
		additionalContext.put("servicePackageName", this.servicePackageName);
		additionalContext.put("serviceImplPackageName", super.getPackageDeclaration(
				super.getProperties().getProperty("package-name", element.getPackageName())));
		String filename = this.resolveFilename(element);
		if (filename.endsWith(".java") && filename.indexOf('$') >= 0) {
			super.log.warn("Filename for " + super.getClassNameForFile(element)
					+ " contains a $. Innerclass generation is not supported.");
		}
		producer.produce(additionalContext, super.getTemplateName(), new File(super.getOutputDirectory(), filename),
				super.getTemplateName(), element.toString());
	}

	protected String resolveFilename(POJOClass element) {
		String filename = StringHelper.replace(super.getFilePattern(), "{class-name}",
				super.getClassNameForFile(element));
		String packageName = element.getPackageName();
		packageName = super.getProperties().getProperty("package-name", packageName);
		String packageLocation = StringHelper.replace(packageName, ".", "/");
		if (StringHelper.isEmpty(packageLocation)) {
			packageLocation = "."; // done to ensure default package classes doesn't end up in the root of the
									// filesystem when outputdir=""
		}
		filename = StringHelper.replace(filename, "{package-name}", packageLocation);
		return filename;
	}

	public String toCamelCase(String declarationName) {
		return StringUtils.uncapitalise(declarationName);
	}
}
