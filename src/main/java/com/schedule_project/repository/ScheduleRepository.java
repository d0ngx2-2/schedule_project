package com.schedule_project.repository;

import com.schedule_project.entity.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

//JPA 및 Entity class import
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
