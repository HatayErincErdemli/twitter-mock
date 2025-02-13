package com.example.twitter_mock.controller;

import com.example.twitter_mock.service.RetweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/retweet")
public class RetweetController {

    private final RetweetService retweetService;

    public RetweetController(RetweetService retweetService) {
        this.retweetService = retweetService;
    }

    @PostMapping
    public ResponseEntity<Void> retweet(@RequestParam Long tweetId, @RequestParam Long userId) {
        retweetService.retweet(tweetId, userId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRetweet(@PathVariable Long id, @RequestParam Long userId) {
        retweetService.deleteRetweet(id, userId);
        return ResponseEntity.noContent().build();
    }
}
