package com.example.twitter_mock.repository;

import com.example.twitter_mock.entity.Retweet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RetweetRepository extends JpaRepository<Retweet, Long> {
    Optional<Retweet> findByUserIdAndTweetId(Long userId, Long tweetId);
}