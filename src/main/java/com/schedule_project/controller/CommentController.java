package com.schedule_project.controller;

import com.schedule_project.dto.comment.CreateCommentRequest;
import com.schedule_project.dto.comment.CreateCommentResponse;
import com.schedule_project.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {
    //속성
    private final CommentService commentService;
    //생성자[자동완성]
    //기능
    @PostMapping("/schedules/{id}/comments")
    public CreateCommentResponse create(@PathVariable Long id, @RequestBody CreateCommentRequest request) {
        return commentService.createComment(id, request);
    }

}
