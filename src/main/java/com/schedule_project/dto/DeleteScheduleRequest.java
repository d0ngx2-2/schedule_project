package com.schedule_project.dto;

import lombok.Getter;

//삭제할 시 요청사항[사용자 입력사항}
@Getter
public class DeleteScheduleRequest {
    private Long id;
    private String password;
}
