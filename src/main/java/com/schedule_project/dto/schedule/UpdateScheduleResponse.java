package com.schedule_project.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

//입력 시 응답받을 데이터
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
