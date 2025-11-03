package com.schedule_project.controller;

import com.schedule_project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    //속성
    private final ScheduleService scheduleService;
    //생성자
    //기능
}
