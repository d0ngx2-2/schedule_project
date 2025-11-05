package com.schedule_project.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String comment;
    private final String name;
    private final LocalDateTime createdData;
    private final LocalDateTime modifiedData;

    public CreateCommentResponse(Long id, String comment, String name, LocalDateTime createdData, LocalDateTime modifiedData) {
        this.id = id;
        this.comment = comment;
        this.name = name;
        this.createdData = createdData;
        this.modifiedData = modifiedData;
    }
}
