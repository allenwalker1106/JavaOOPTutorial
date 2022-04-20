package com.manager.StudentManager.Repository;

import com.manager.StudentManager.Entity.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Long> {
}