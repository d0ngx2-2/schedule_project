package com.schedule_project.dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdateScheduleResponse {
    private final String title;
    private final String name;
    private final LocalDateTime lastModifiedDate;

    public UpdateScheduleResponse(String title, String name, LocalDateTime lastModifiedDate) {
        this.title = title;
        this.name = name;
        this.lastModifiedDate = lastModifiedDate;
    }
}
