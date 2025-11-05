package com.schedule_project.dto.schedule;

import lombok.Getter;

//업데이트간 요청사항[사용자 입력사항]
@Getter
public class UpdateScheduleRequest {
    //속성
    private String title;
    private String name;
    private String password;
}
