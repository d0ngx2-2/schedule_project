package com.schedule_project.service;

import com.schedule_project.dto.CreateScheduleRequest;
import com.schedule_project.dto.CreateScheduleResponse;
import com.schedule_project.dto.GetOneScheduleResponse;
import com.schedule_project.entity.Schedule;
import com.schedule_project.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    //속성
    private final ScheduleRepository scheduleRepository;
    //생성자
    //기능
    //Create
    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContent(),
                savedSchedule.getName(),
                savedSchedule.getCreateDate(),
                savedSchedule.getLastModifiedDate()
        );
    }

    //한 건 조회
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 유저 아이디 입니다.")
        );
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreateDate(),
                schedule.getLastModifiedDate()
        );
    }
    //다 건 조회
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll() {
        List<Schedule> schedules = scheduleRepository.findAll();

        List<GetOneScheduleResponse> responses = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetOneScheduleResponse response = new GetOneScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContent(),
                    schedule.getName(),
                    schedule.getCreateDate(),
                    schedule.getLastModifiedDate()
            );
            responses.add(response);
        }
        return responses;
    }
}
