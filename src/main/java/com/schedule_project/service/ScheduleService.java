package com.schedule_project.service;

import com.schedule_project.dto.comment.GetCommentResponse;
import com.schedule_project.dto.schedule.*;
import com.schedule_project.entity.Comment;
import com.schedule_project.entity.Schedule;
import com.schedule_project.exception.CustomException;
import com.schedule_project.repository.CommentRepository;
import com.schedule_project.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    //속성
    //하위 층을 속성으로 갖는다.
    private final ScheduleRepository scheduleRepository;
    private final CommentRepository commentRepository;

    //생성자[자동생성]

    //기능
    //Create(생성)
    @Transactional
    public CreateScheduleResponse saveSchedule(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContent(),
                request.getName(),
                request.getPassword()
        );
        //저장
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
    //단일 건수 조회[특정 id키 값 입력 시 그 키값을 갖는 일정을 조회]
    @Transactional(readOnly = true)//조회기능
    public GetOneScheduleResponse getOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST, "존재하지 않는 일정 아이디 입니다.")
        );//id가 존재하지 않는다면 다음과 같은 오류문구 출력

        //해당 일정에 달린 댓글 DB에서 찾기
        List<Comment> comments = commentRepository.findAll()
                .stream().filter(seletedCmmt -> seletedCmmt.getSchedule()
                        .getId()
                        .equals(id))
                .toList();
        //DTO변환
        List<GetCommentResponse> response = new ArrayList<>();
        for (Comment comment : comments) {
            response.add(new GetCommentResponse(
                    comment.getId(),
                    comment.getContent(),
                    comment.getName(),
                    comment.getCreatedDate(),
                    comment.getModifiedDate()
            ));
        }
        //반환
        return new GetOneScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContent(),
                schedule.getName(),
                schedule.getCreateDate(),
                schedule.getLastModifiedDate(),
                response
        );
    }

    //Read(All/selected)
    //다 건수 조회[name을 입력 시 해당 name이 해당된 일정을 전부 조회 / 입력하지 않을 시 전부 조회] 날짜 내림차 순으로 정렬
    @Transactional(readOnly = true)
    public List<GetAllScheduleResponse> getAll(String name) {
        List<Schedule> schedules = scheduleRepository.findAll(); //DB에서 모든 일정을 찾는다.

        if (name != null && !name.isEmpty()) { //이름의 값이 비어있지 않거나, null이 입력되지 않는 경우(사용자 입력이기때문)
            //name 필터링
            //일치한 name 값들을 필터하여 새로운 배열에 넣은 후 다시 schedules에 넣는다.
            schedules = new ArrayList<>(schedules.stream()
                    .filter(nameList -> nameList.getName().equals(name))
                    .toList()
            );
        }
        // 수정한 날짜 내림차순 정렬
        schedules.sort((lastOne, lastTwo) -> lastTwo.getLastModifiedDate().compareTo(lastOne.getLastModifiedDate()));
        //반환을 위한 형변환
        List<GetAllScheduleResponse> responses = new ArrayList<>();
        for (Schedule nameList : schedules) {
            responses.add(new GetAllScheduleResponse(
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
    //일정 제목 및 이름 수정 기능[특정 키 입력 후 그 키에 대한 일정을 수정하되 비밀번호를 요청받음]
    @Transactional
    public UpdateScheduleResponse updateSchedule(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                () -> new CustomException(HttpStatus.BAD_REQUEST,"존재하지 않는 일정 아이디 입니다.")
        );//id가 일치하지않을 경우 예외처리
        if(!schedule.getPassword().equals(request.getPassword())){
            throw new CustomException(HttpStatus.BAD_REQUEST,"비밀번호가 일치하지 않습니다.");
        }//비밀번호가 일치하지 않을 경우 예외처리
        //수정
        schedule.update(
                request.getTitle(),
                request.getName()
        );
        //반환을 위한 형변환
        return new UpdateScheduleResponse(
                schedule.getTitle(),
                schedule.getName(),
                schedule.getLastModifiedDate()
        );
    }

    //delete
    //[특정 키 값 입력 후 맞는 일정을 삭제하되 비밀번호를 요청]
    @Transactional
    public void deleteSchedule(Long id, String password) {
        boolean existence = scheduleRepository.existsById(id);
        if (!existence) {
            throw new CustomException(HttpStatus.BAD_REQUEST,"존재하지 않는 일정 아이디 입니다.");
        }//키값이 아닐경우 예외처리
        if(!password.equals(scheduleRepository.findById(id).get().getPassword())) {
            throw new CustomException(HttpStatus.BAD_REQUEST, "비밀번호가 일치하지 않습니다.");
        }//비밀번호가 아닐경우 예외처리
        commentRepository.deleteByScheduleId(id);
        scheduleRepository.deleteById(id); //참이면 키에 해당하는 일정 삭제
    }
}
