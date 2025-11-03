package com.schedule_project.controller;

import com.schedule_project.dto.CreateScheduleRequest;
import com.schedule_project.dto.CreateScheduleResponse;
import com.schedule_project.dto.GetOneScheduleResponse;
import com.schedule_project.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    //속성
    private final ScheduleService scheduleService;
    //생성자
    //기능
    //Create
    @PostMapping("/schedules")
    public CreateScheduleResponse save(@RequestBody CreateScheduleRequest request){
        return scheduleService.saveSchedule(request);
    }

    //Read(selected one)
    @GetMapping("/schedules/{id}")
    public GetOneScheduleResponse getOne(@PathVariable Long id){
        return scheduleService.getOne(id);
    }

    //Read(all)
    @GetMapping("/schedules")
    public List<GetOneScheduleResponse> getAll(){
        return scheduleService.getAll();
    }
}
