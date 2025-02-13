package com.example.twitter_mock.service;

import com.example.twitter_mock.entity.Tweet;
import com.example.twitter_mock.entity.User;
import com.example.twitter_mock.repository.TweetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TweetService {

    private final TweetRepository tweetRepository;
    private final UserService userService;

    public TweetService(TweetRepository tweetRepository, UserService userService) {
        this.tweetRepository = tweetRepository;
        this.userService = userService;
    }

    public Tweet createTweet(Long userId, Tweet tweet) {
        User user = userService.getUserById(userId);
        tweet.setUser(user);
        return tweetRepository.save(tweet);
    }

    public List<Tweet> findTweetsByUserId(Long userId) {
        return tweetRepository.findByUserId(userId);
    }

    public Tweet findTweetById(Long id) {
        return tweetRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Tweet not found: " + id));
    }

    public Tweet updateTweet(Long tweetId, Long userId, Tweet updatedTweet) {
        Tweet existingTweet = findTweetById(tweetId);
        if (!existingTweet.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You are not the owner of this tweet!");
        }
        existingTweet.setContent(updatedTweet.getContent());
        return tweetRepository.save(existingTweet);
    }

    public void deleteTweet(Long tweetId, Long userId) {
        Tweet tweet = findTweetById(tweetId);
        if (!tweet.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You are not the owner of this tweet!");
        }
        tweetRepository.delete(tweet);
    }
}
