package org.hibernate.tool.ant;

import java.util.Properties;

import org.hibernate.tool.hbm2x.ControllerExporter;
import org.hibernate.tool.hbm2x.Exporter;

/**
 * hibernate生成Controller任务
 * @author honey
 */
public class Hbm2ControllerExporterTask extends Hbm2JavaExporterTask {

	private Properties serviceProperties;

	public Hbm2ControllerExporterTask(HibernateToolTask parent, Hbm2ServiceExporterTask serviceExporterTask) {
		super(parent);
		this.serviceProperties = serviceExporterTask.properties;
	}

	@Override
	protected Exporter configureExporter(Exporter exp) {
		ControllerExporter exporter = (ControllerExporter) exp;
		super.configureExporter(exp);
		return exporter;
	}

	@Override
	protected Exporter createExporter() {
		return new ControllerExporter(super.parent.getConfiguration(), super.parent.getDestDir(),
				this.serviceProperties);
	}

	@Override
	public String getName() {
		return "hbm2controller (Generates a set of Controllers)";
	}
}
