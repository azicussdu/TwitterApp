package com.example.demo.service;

import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;

import java.util.List;

public interface TweetService {
    List<Tweet> fetchTweetsList();
    List<Tweet> findAllByUser(User user);
    Tweet fetchTweetById(Long id);
    Tweet saveTweet(Tweet tweet);
    Tweet updateTweet(Long id, Tweet tweet);
}