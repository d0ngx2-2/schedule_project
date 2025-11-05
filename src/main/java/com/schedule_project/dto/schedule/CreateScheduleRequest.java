package com.schedule_project.dto.schedule;

import lombok.Getter;

//생성간 요청사항[사용자 입력사항]
@Getter
public class CreateScheduleRequest {
    private String title;
    private String content;
    private String name;
    private String password;
}
