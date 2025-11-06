package com.schedule_project.controller;

import com.schedule_project.dto.schedule.*;
import com.schedule_project.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<CreateScheduleResponse> create(@Valid @RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.saveSchedule(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    //하나의 일정 및 달린 댓글 조회[키값 해당되는]
    @GetMapping("/schedules/{id}")//GET/api/schedules/id
    public ResponseEntity<GetOneScheduleResponse> getOne(@Valid @PathVariable Long id) {
        GetOneScheduleResponse result = scheduleService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //모든 일정 조회[name입력 시 해당되는 일정만]
    @GetMapping("/schedules")//GET/api/schedules?=param[name]
    public ResponseEntity<List<GetAllScheduleResponse>> getAll(@Valid @RequestParam(required = false) String name) {
        List<GetAllScheduleResponse> result = scheduleService.getAll(name);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //일정 수정 기능[키값에 해당하는, 비밀번호 필요]
    @PutMapping("/schedules/{id}")//PUT/api/schedules/id, body에 작성
    public ResponseEntity<UpdateScheduleResponse> update(@Valid @PathVariable Long id, @Valid @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result = scheduleService.updateSchedule(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    //일정 삭제 기능[키값에 해당하는, 비밀번호 필요]
    @DeleteMapping("/schedules/{id}")//DELETE/api/schedules/id, body에 작성
    public ResponseEntity<Void> delete(@Valid @PathVariable Long id, @Valid @RequestBody DeleteScheduleRequest request) {
        scheduleService.deleteSchedule(id, request.getPassword());
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
