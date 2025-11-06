package com.schedule_project.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

//생성간 요청사항[사용자 입력사항]
@Getter
public class CreateScheduleRequest {
    @NotBlank(message = "제목을 입력해 주십시오.")
    @Size(min = 1, max = 30)
    private String title;
    @NotBlank(message = "내용을 입력해 주십시오.")
    @Size(min = 1, max = 200)
    private String content;
    @NotBlank(message = "이름을 입력해 주십시오.")
    private String name;
    @NotBlank(message = "비밀번호를 입력해 주십시오.")
    private String password;
}
