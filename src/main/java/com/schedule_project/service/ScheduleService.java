package com.schedule_project.service;

import com.schedule_project.dto.*;
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

    //Read(One)
    @Transactional(readOnly = true)
    public GetOneScheduleResponse getOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정 아이디 입니다.")
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

    //Read(All/selected)
    @Transactional(readOnly = true)
    public List<GetOneScheduleResponse> getAll(String name) {
        List<Schedule> schedules = scheduleRepository.findAll();

        if (name != null && !name.isEmpty()) {
            schedules = new ArrayList<>(schedules.stream()
                    .filter(nameList -> nameList.getName().equals(name))
                    .toList()
            );
        }

        schedules.sort((lastOne, lastTwo) -> lastTwo.getLastModifiedDate().compareTo(lastOne.getLastModifiedDate()));

        List<GetOneScheduleResponse> responses = new ArrayList<>();
        for (Schedule nameList : schedules) {
            responses.add(new GetOneScheduleResponse(
                    nameList.getId(),
                    nameList.getTitle(),
                    nameList.getContent(),
                    nameList.getName(),
                    nameList.getCreateDate(),
                    nameList.getLastModifiedDate()
            ));

        }
        return responses;
    }

    //update
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정 아이디 입니다.")
        );
        if(!schedule.getPassword().equals(request.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }

        schedule.update(
                request.getTitle(),
                request.getName()
        );

        return new UpdateScheduleResponse(
                schedule.getTitle(),
                schedule.getName(),
                schedule.getLastModifiedDate()
        );
    }

    //delete
    @Transactional
    public void deleteSchedule(Long id, String password) {
        boolean existence = scheduleRepository.existsById(id);

        if (!existence) {
            throw new IllegalArgumentException("존재하지 않는 일정 아이디 입니다.");
        }
        if(!password.equals(scheduleRepository.findById(id).get().getPassword())) {
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        scheduleRepository.deleteById(id);
    }
}
