package com.example.twitter_mock.service;

import com.example.twitter_mock.entity.Comment;
import com.example.twitter_mock.entity.Tweet;
import com.example.twitter_mock.entity.User;
import com.example.twitter_mock.repository.CommentRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;
    private final TweetService tweetService;
    private final UserService userService;

    public CommentService(CommentRepository commentRepository, TweetService tweetService, UserService userService) {
        this.commentRepository = commentRepository;
        this.tweetService = tweetService;
        this.userService = userService;
    }

    public Comment addComment(Long tweetId, Long userId, Comment comment) {
        Tweet tweet = tweetService.findTweetById(tweetId);
        User user = userService.getUserById(userId);
        comment.setTweet(tweet);
        comment.setUser(user);
        return commentRepository.save(comment);
    }

    public Comment updateComment(Long commentId, Long userId, Comment updatedComment) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("Comment not found: " + commentId));

        if (!comment.getUser().getId().equals(userId)) {
            throw new IllegalStateException("You can only edit your own comment!");
        }
        comment.setContent(updatedComment.getContent());
        return commentRepository.save(comment);
    }

    public void deleteComment(Long commentId, Long userId, Long tweetOwnerId) {
        Comment comment = commentRepository.findById(commentId)
                .orElseThrow(() -> new IllegalStateException("Comment not found: " + commentId));

        if (!comment.getUser().getId().equals(userId) && !comment.getTweet().getUser().getId().equals(tweetOwnerId)) {
            throw new IllegalStateException("Only the comment owner or the tweet owner can delete this comment!");
        }

        commentRepository.delete(comment);
    }
}