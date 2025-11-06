package com.schedule_project.controller;

import com.schedule_project.dto.comment.CreateCommentRequest;
import com.schedule_project.dto.comment.CreateCommentResponse;
import com.schedule_project.service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    //속성
    private final CommentService commentService;

    //기능
    //댓글 생성 기능[키값에 일치하는 일정에]
    @PostMapping("/schedules/{id}/comments")
    public ResponseEntity<CreateCommentResponse> create(@Valid @PathVariable Long id, @Valid @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.createComment(id, request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
}
