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
    //하위 클래스를 속성으로 의존성 주입
    private final ScheduleService scheduleService;
    //생성자[자동생성]

    //기능
    //Create
    @PostMapping("/schedules")//POST/api/schedules, body에 작성
    public CreateScheduleResponse save(@RequestBody CreateScheduleRequest request){
        return scheduleService.saveSchedule(request);
    }

    //Read(selected one)
    @GetMapping("/schedules/{id}")//GET/api/schedules/id
    public GetOneScheduleResponse getOne(@PathVariable Long id){
        return scheduleService.getOne(id);
    }

    //Read(all)
    @GetMapping("/schedules")//GET/api/schedules?=param[name]
    public List<GetOneScheduleResponse> getAll(@RequestParam(required = false) String name){
        return scheduleService.getAll(name);
    }

    //Update
    @PutMapping("/schedules/{id}")//PUT/api/schedules/id, body에 작성
    public UpdateScheduleResponse update(@PathVariable Long id, @RequestBody UpdateScheduleRequest request){
        return scheduleService.updateSchedule(id, request);
    }

    //Delete
    @DeleteMapping("/schedules/{id}")//DELETE/api/schedules/id, body에 작성
    public void delete(@PathVariable Long id, @RequestBody DeleteScheduleRequest request){
        scheduleService.deleteSchedule(id, request.getPassword());

    }

}
