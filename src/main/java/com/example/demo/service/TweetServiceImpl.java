package com.example.demo.service;

import com.example.demo.entity.Tweet;
import com.example.demo.entity.User;
import com.example.demo.repository.TweetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class TweetServiceImpl implements TweetService {

    private final TweetRepository tweetRepository;

    @Override
    public List<Tweet> fetchTweetsList() {
        return tweetRepository.findAllByOrderByCreatedAtDesc();
    }

    @Override
    public List<Tweet> findAllByUser(User user) {
        return tweetRepository.findAllByUserOrderByCreatedAtDesc(user);
    }

    @Override
    public Tweet fetchTweetById(Long id) {
        return tweetRepository.findById(id).get();
    }

    @Override
    public Tweet saveTweet(Tweet tweet) {
        return tweetRepository.save(tweet);
    }

    @Override
    public Tweet updateTweet(Long id, Tweet tweet) {
        Tweet tweetDB = tweetRepository.findById(id).
                orElseThrow(() -> new IllegalStateException("no tweet with ID: " + id));

        if (Objects.nonNull(tweet.getMessage()) &&
                !"".equalsIgnoreCase(tweet.getMessage())) {
            tweetDB.setMessage(tweet.getMessage());
        }

        return tweetRepository.save(tweet);
    }
}
