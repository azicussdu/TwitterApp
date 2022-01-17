package com.example.demo.controller;

import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.error.ResourceNotFoundException;
import com.example.demo.service.TweetService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
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
    public List<Tweet> fetchTweetsByUser(@PathVariable("username") String username) throws ResourceNotFoundException {
        User user = userService.findByUsername(username);
        if (user != null) {
            return tweetService.findAllByUser(user);
        }
        return null;
    }

    @PostMapping("/tweets")
    public Tweet saveTweet(@Valid @RequestBody Tweet tweet) throws ResourceNotFoundException {
        log.info("@@@ TweetController: in saveTweet()");
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String activeUsername = authentication.getName();
        User user = userService.findByUsername(activeUsername);
        tweet.setUser(user);
        return tweetService.saveTweet(tweet);
    }

    @PutMapping("/tweets/{id}")
    public Tweet updateTweet(@PathVariable("id") Long id,
                             @RequestBody Tweet tweet) {
        return tweetService.updateTweet(id, tweet);
    }
}
