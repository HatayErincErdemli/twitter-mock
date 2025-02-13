package com.example.twitter_mock.service;

import com.example.twitter_mock.entity.Like;
import com.example.twitter_mock.entity.Tweet;
import com.example.twitter_mock.entity.User;
import com.example.twitter_mock.repository.LikeRepository;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public LikeService(LikeRepository likeRepository, TweetService tweetService, UserService userService) {
        this.likeRepository = likeRepository;
        this.tweetService = tweetService;
        this.userService = userService;
    }

    public void likeTweet(Long tweetId, Long userId) {
        Tweet tweet = tweetService.findTweetById(tweetId);
        User user = userService.getUserById(userId);
        if (likeRepository.findByUserIdAndTweetId(userId, tweetId).isEmpty()) {
            Like like = Like.builder().tweet(tweet).user(user).build();
            likeRepository.save(like);
        }
    }

    public void dislikeTweet(Long tweetId, Long userId) {
        likeRepository.findByUserIdAndTweetId(userId, tweetId)
                .ifPresent(likeRepository::delete);
    }
}