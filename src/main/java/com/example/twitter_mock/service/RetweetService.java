package com.example.twitter_mock.service;

import com.example.twitter_mock.entity.Retweet;
import com.example.twitter_mock.entity.Tweet;
import com.example.twitter_mock.entity.User;
import com.example.twitter_mock.repository.RetweetRepository;
import org.springframework.stereotype.Service;

@Service
public class RetweetService {

    private final RetweetRepository retweetRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public RetweetService(RetweetRepository retweetRepository, TweetService tweetService, UserService userService) {
        this.retweetRepository = retweetRepository;
        this.tweetService = tweetService;
        this.userService = userService;
    }

    public void retweet(Long tweetId, Long userId) {
        Tweet tweet = tweetService.findTweetById(tweetId);
        User user = userService.getUserById(userId);
        if (retweetRepository.findByUserIdAndTweetId(userId, tweetId).isEmpty()) {
            Retweet retweet = Retweet.builder().tweet(tweet).user(user).build();
            retweetRepository.save(retweet);
        }
    }

    public void deleteRetweet(Long retweetId, Long userId) {
        Retweet retweet = retweetRepository.findById(retweetId)
                .orElseThrow(() -> new IllegalStateException("Retweet not found: " + retweetId));

        if (!retweet.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You can only delete your own retweets!");
        }

        retweetRepository.delete(retweet);
    }
}