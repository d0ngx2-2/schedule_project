package com.schedule_project.controller;

import com.schedule_project.dto.schedule.*;
import com.schedule_project.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {
    //속성
    //하위 클래스를 속성으로 의존성 주입
    private final ScheduleService scheduleService;

    //기능
    //일정 생성
    @PostMapping("/schedules")//POST/api/schedules, body에 작성
    public CreateScheduleResponse create(@Valid @RequestBody CreateScheduleRequest request) {
        return scheduleService.saveSchedule(request);
    }

    //하나의 일정 및 달린 댓글 조회[키값 해당되는]
    @GetMapping("/schedules/{id}")//GET/api/schedules/id
    public GetOneScheduleResponse getOne(@Valid @PathVariable Long id) {
        return scheduleService.getOne(id);
    }

    //모든 일정 조회[name입력 시 해당되는 일정만]
    @GetMapping("/schedules")//GET/api/schedules?=param[name]
    public List<GetAllScheduleResponse> getAll(@Valid @RequestParam(required = false) String name) {
        return scheduleService.getAll(name);
    }

    //일정 수정 기능[키값에 해당하는, 비밀번호 필요]
    @PutMapping("/schedules/{id}")//PUT/api/schedules/id, body에 작성
    public UpdateScheduleResponse update(@Valid @PathVariable Long id, @Valid @RequestBody UpdateScheduleRequest request) {
        return scheduleService.updateSchedule(id, request);
    }

    //일정 삭제 기능[키값에 해당하는, 비밀번호 필요]
    @DeleteMapping("/schedules/{id}")//DELETE/api/schedules/id, body에 작성
    public void delete(@Valid @PathVariable Long id, @Valid @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(id, request.getPassword());
    }
}
