package com.example.demo.controller;

import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.service.TweetService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController

@RequiredArgsConstructor
public class TweetController {

    private final TweetService tweetService;
    private final UserService userService;

    @GetMapping(value = {"/tweets", "/"})
    public List<Tweet> fetchTweetsList() {
        return tweetService.fetchTweetsList();
    }

    @GetMapping("/tweets/{id}")
    public Tweet fetchTweetById(@PathVariable("id") Long id) {
        return tweetService.fetchTweetById(id);
    }

    @GetMapping(value = "/tweets/user/{username}")
    public List<Tweet> fetchTweetsByUser(@PathVariable("username") String username) {
        User user = userService.findByUsername(username);
        if (user != null) {
            return tweetService.findAllByUser(user);
        }

        return null; //throw exception
    }

    @PostMapping("/tweets")
    public Tweet saveTweet(@Valid @RequestBody Tweet tweet) {
        return tweetService.saveTweet(tweet);
    }

    @PutMapping("/tweets/{id}")
    public Tweet updateTweet(@PathVariable("id") Long id,
                             @RequestBody Tweet tweet) {
        return tweetService.updateTweet(id, tweet);
    }
}
