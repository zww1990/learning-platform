package com.cfilmcloud.collate.orm.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.cfilmcloud.collate.orm.domain.TSysTaskSchedule;

public interface TSysTaskScheduleDao
		extends JpaRepository<TSysTaskSchedule, Integer>, JpaSpecificationExecutor<TSysTaskSchedule> {

}
