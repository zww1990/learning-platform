package org.hibernate.tool.ant;

public class MyHibernateToolTask extends HibernateToolTask {
	/**
	 * @return 创建Service接口类
	 */
	public ExporterTask createHbm2Service() {
		ExporterTask generator = new Hbm2ServiceExporterTask(this);
		super.addGenerator(generator);
		return generator;
	}

	/**
	 * @param daoExporterTask
	 * @param serviceExporterTask
	 * @return 创建Service实现类
	 */
	public ExporterTask createHbm2ServiceImpl(Hbm2DAOExporterTask daoExporterTask,
			Hbm2ServiceExporterTask serviceExporterTask) {
		ExporterTask generator = new Hbm2ServiceImplExporterTask(this, daoExporterTask, serviceExporterTask);
		super.addGenerator(generator);
		return generator;
	}

	/**
	 * @param serviceExporterTask
	 * @return 创建Controller类
	 */
	public ExporterTask createHbm2Controller(Hbm2ServiceExporterTask serviceExporterTask) {
		ExporterTask generator = new Hbm2ControllerExporterTask(this, serviceExporterTask);
		super.addGenerator(generator);
		return generator;
	}
}
