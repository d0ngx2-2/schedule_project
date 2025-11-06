package com.schedule_project.dto.schedule;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

//삭제할 시 요청사항[사용자 입력사항}
@Getter
public class DeleteScheduleRequest {
    private Long id;
    @NotBlank(message = "비밀번호를 입력해 주십시오.")
    private String password;
}
