package com.schedule_project.dto.schedule;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetAllScheduleResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final String name;
    private final LocalDateTime createDate;
    private final LocalDateTime lastModifiedDate;

    public GetAllScheduleResponse(Long id, String title, String content, String name, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.name = name;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
