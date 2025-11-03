package com.schedule_project.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDate;

@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "scheules")
@EnableJpaAuditing
public class Schedule {
    //속성
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    private String content;
    private String name;
    private String password;
    @CreatedDate
    private LocalDate createdDate;
    @LastModifiedDate
    private LocalDate lastModifiedDate;
    //생성자
    //기능
}
