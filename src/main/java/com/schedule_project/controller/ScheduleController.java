package com.schedule_project.controller;

import com.schedule_project.dto.*;
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
    public List<GetOneScheduleResponse> getAll(@RequestParam(required = false) String name){
        return scheduleService.getAll(name);
    }

    //Update
    @PutMapping("/schedules/{id}")
    public UpdateScheduleResponse update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        return scheduleService.updateSchedule(id, request);
    }

}
