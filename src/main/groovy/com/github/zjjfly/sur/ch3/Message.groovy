package com.github.zjjfly.sur.ch3

import groovy.transform.Canonical

import java.time.Instant

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/3/2
 */
@Canonical
class Message implements Comparable<Message> {

    static final MAX_TEXT_LENGTH = 140;

    String text;

    Instant postedAt;

    User postedBy;

    Message(String text, Instant postedAt, User postedBy) {
        if (text.length() > MAX_TEXT_LENGTH) {
            throw new IllegalArgumentException("Message text cannot be longer than 140 characters")
        }
        this.text = text
        this.postedAt = postedAt
        this.postedBy = postedBy
    }

    int compareTo(Message o) {
        return o.postedAt <=> postedAt
    }

}
