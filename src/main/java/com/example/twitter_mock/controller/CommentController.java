package com.example.twitter_mock.controller;

import com.example.twitter_mock.entity.Comment;
import com.example.twitter_mock.service.CommentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<Comment> addComment(@RequestParam Long tweetId, @RequestParam Long userId, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.addComment(tweetId, userId, comment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Comment> updateComment(@PathVariable Long id, @RequestParam Long userId, @RequestBody Comment comment) {
        return ResponseEntity.ok(commentService.updateComment(id, userId, comment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long id, @RequestParam Long userId, @RequestParam Long tweetOwnerId) {
        commentService.deleteComment(id, userId, tweetOwnerId);
        return ResponseEntity.noContent().build();
    }
}
