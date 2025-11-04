package com.schedule_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing//날짜 자동 계산을 위한 어노테이션
public class ScheduleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScheduleProjectApplication.class, args);
    }

}
