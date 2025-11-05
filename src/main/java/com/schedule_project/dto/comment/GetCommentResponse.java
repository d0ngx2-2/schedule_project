package com.schedule_project.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class GetCommentResponse {
    private final Long id;
    private final String title;
    private final String content;
    private final LocalDateTime createDate;
    private final LocalDateTime lastModifiedDate;

    public GetCommentResponse(Long id, String title, String content, LocalDateTime createDate, LocalDateTime lastModifiedDate) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createDate = createDate;
        this.lastModifiedDate = lastModifiedDate;
    }
}
