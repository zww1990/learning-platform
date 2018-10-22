package org.hibernate.tool.ant;

import java.util.Properties;

import org.hibernate.tool.hbm2x.Exporter;
import org.hibernate.tool.hbm2x.ServiceImplExporter;

public class Hbm2ServiceImplExporterTask extends Hbm2JavaExporterTask {

	private Properties daoProperties;
	private Properties serviceProperties;

	public Hbm2ServiceImplExporterTask(HibernateToolTask parent, Hbm2DAOExporterTask daoExporterTask,
			Hbm2ServiceExporterTask serviceExporterTask) {
		super(parent);
		this.daoProperties = daoExporterTask.properties;
		this.serviceProperties = serviceExporterTask.properties;
	}

	@Override
	protected Exporter configureExporter(Exporter exp) {
		ServiceImplExporter exporter = (ServiceImplExporter) exp;
		super.configureExporter(exp);
		return exporter;
	}

	@Override
	protected Exporter createExporter() {
		return new ServiceImplExporter(super.parent.getConfiguration(), super.parent.getDestDir(), this.daoProperties,
				this.serviceProperties);
	}

	@Override
	public String getName() {
		return "hbm2serviceimpl (Generates a set of Service Implements)";
	}
}
