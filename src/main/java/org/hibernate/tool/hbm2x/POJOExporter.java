package org.hibernate.tool.hbm2x;

public class POJOExporter extends GenericExporter {
	private static final String POJO_JAVACLASS_FTL = "org/hibernate/pojo/Pojo.ftl";

	protected void init() {
		setTemplateName(POJO_JAVACLASS_FTL);
		setFilePattern("{package-name}/{class-name}.java");
	}

	public POJOExporter() {
		init();
	}

	public String getName() {
		return "hbm2java";
	}

	protected void setupContext() {
		// TODO: this safe guard should be in the root templates instead for each
		// variable they depend on.
		if (!getProperties().containsKey("ejb3")) {
			getProperties().put("ejb3", "false");
		}
		if (!getProperties().containsKey("jdk5")) {
			getProperties().put("jdk5", "false");
		}
		super.setupContext();
	}
}
