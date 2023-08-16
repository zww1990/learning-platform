package org.hibernate.tool.ant;

import org.hibernate.tool.hbm2x.Exporter;
import org.hibernate.tool.hbm2x.ServiceExporter;

/**
 * hibernate生成Service接口任务
 * @author honey
 */
public class Hbm2ServiceExporterTask extends Hbm2JavaExporterTask {

	public Hbm2ServiceExporterTask(HibernateToolTask parent) {
		super(parent);
	}

	@Override
	protected Exporter configureExporter(Exporter exp) {
		ServiceExporter exporter = (ServiceExporter) exp;
		super.configureExporter(exp);
		return exporter;
	}

	@Override
	protected Exporter createExporter() {
		return new ServiceExporter(super.parent.getConfiguration(), super.parent.getDestDir());
	}

	@Override
	public String getName() {
		return "hbm2service (Generates a set of Services)";
	}
}
