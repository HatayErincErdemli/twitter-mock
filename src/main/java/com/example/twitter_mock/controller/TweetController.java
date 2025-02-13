package com.example.twitter_mock.controller;

import com.example.twitter_mock.entity.Tweet;
import com.example.twitter_mock.service.TweetService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tweet")
public class TweetController {

    private final TweetService tweetService;

    public TweetController(TweetService tweetService) {
        this.tweetService = tweetService;
    }

    @PostMapping
    public ResponseEntity<Tweet> createTweet(@RequestParam Long userId, @RequestBody Tweet tweet) {
        return ResponseEntity.ok(tweetService.createTweet(userId, tweet));
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<List<Tweet>> findTweetsByUserId(@RequestParam Long userId) {
        return ResponseEntity.ok(tweetService.findTweetsByUserId(userId));
    }

    @GetMapping("/findById")
    public ResponseEntity<Tweet> findTweetById(@RequestParam Long tweetId) {
        return ResponseEntity.ok(tweetService.findTweetById(tweetId));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Tweet> updateTweet(@PathVariable Long id, @RequestParam Long userId, @RequestBody Tweet tweet) {
        return ResponseEntity.ok(tweetService.updateTweet(id, userId, tweet));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTweet(@PathVariable Long id, @RequestParam Long userId) {
        tweetService.deleteTweet(id, userId);
        return ResponseEntity.noContent().build();
    }
}
