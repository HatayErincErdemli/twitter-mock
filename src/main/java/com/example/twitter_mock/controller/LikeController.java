package com.example.twitter_mock.controller;

import com.example.twitter_mock.service.LikeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeController {

    private final LikeService likeService;

    public LikeController(LikeService likeService) {
        this.likeService = likeService;
    }

    @PostMapping
    public ResponseEntity<Void> likeTweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        likeService.likeTweet(tweetId, userId);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dislike")
    public ResponseEntity<Void> dislikeTweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        likeService.dislikeTweet(tweetId, userId);
        return ResponseEntity.ok().build();
    }
}