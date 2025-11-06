package com.schedule_project.dto.comment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreateCommentRequest {
    @NotBlank(message = "내용을 입력해 주십시오.")
    @Size(min = 1, max = 100)
    private String content;
    @NotBlank(message = "이름일 입력해 주십시오.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해 주십시오.")
    private String password;
}
