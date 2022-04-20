package com.manager.StudentManager.Repository;

import com.manager.StudentManager.Entity.TrainingSite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainingSiteRepository extends JpaRepository<TrainingSite, String> {
}