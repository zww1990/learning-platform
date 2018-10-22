package org.hibernate.tool.ant;

public class MyHibernateToolTask extends HibernateToolTask {
	public ExporterTask createHbm2Service() {
		ExporterTask generator = new Hbm2ServiceExporterTask(this);
		super.addGenerator(generator);
		return generator;
	}

	public ExporterTask createHbm2ServiceImpl(Hbm2DAOExporterTask daoExporterTask,
			Hbm2ServiceExporterTask serviceExporterTask) {
		ExporterTask generator = new Hbm2ServiceImplExporterTask(this, daoExporterTask, serviceExporterTask);
		super.addGenerator(generator);
		return generator;
	}

	public ExporterTask createHbm2Controller(Hbm2ServiceExporterTask serviceExporterTask) {
		ExporterTask generator = new Hbm2ControllerExporterTask(this, serviceExporterTask);
		super.addGenerator(generator);
		return generator;
	}
}
