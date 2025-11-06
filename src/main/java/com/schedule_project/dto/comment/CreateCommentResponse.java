package com.schedule_project.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentResponse {
    private final Long id;
    private final String content;
    private final String name;
    private final LocalDateTime createdData;
    private final LocalDateTime modifiedData;

    public CreateCommentResponse(Long id, String content, String name, LocalDateTime createdData, LocalDateTime modifiedData) {
        this.id = id;
        this.content = content;
        this.name = name;
        this.createdData = createdData;
        this.modifiedData = modifiedData;
    }
}
