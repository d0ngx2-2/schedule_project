package com.schedule_project.dto.comment;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentRequest {
    private String comment;
    private String name;
    private String password;
}
