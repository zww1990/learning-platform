package com.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.domain.Commit;

public interface CommitDao extends JpaRepository<Commit, Long> {
}
