package com.schedule_project.service;

import com.schedule_project.dto.comment.CreateCommentRequest;
import com.schedule_project.dto.comment.CreateCommentResponse;
import com.schedule_project.entity.Comment;
import com.schedule_project.entity.Schedule;
import com.schedule_project.repository.CommentRepository;
import com.schedule_project.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {
    //속성
    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    //생성자[자동생성]

    //기능
    //Create
    @Transactional
    public CreateCommentResponse createComment(Long scheduleId, CreateCommentRequest request){
        //scheduleid 조회 없으면 예외처리
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("해당 일정이 없습니다."));
        //위에 조회된 스캐줄의 댓글들만을 필터링한 후 개수 파악
        long count = commentRepository.findAll().stream()
                .filter(commentList -> commentList.getSchedule().equals(schedule))
                .count();
        //그 갯수가 10개 이상일 경우 예외처리
        if(count >= 10){
            throw new IllegalStateException("일정에는 10개의 댓글을 작성할 수 있습니다.");
        }
        //댓글 생성
        Comment comment = new Comment(
                request.getComment(),
                request.getName(),
                request.getPassword()
        );
        //comment, schedule 연결
        comment.setSchedule(schedule);
        //댓글 저장
        Comment savedcomment = commentRepository.save(comment);
        //반환
        return new CreateCommentResponse(
                savedcomment.getId(),
                savedcomment.getContent(),
                savedcomment.getName(),
                savedcomment.getCreatedDate(),
                savedcomment.getModifiedDate()
        );
    }

}
