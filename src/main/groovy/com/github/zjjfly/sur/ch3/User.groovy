package com.github.zjjfly.sur.ch3

import groovy.transform.Canonical

import java.time.Instant

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/2/27
 */
@Canonical
class User {

    String name

    List<User> following = []

    Set<Message> posts = new TreeSet()

    Instant registered = Instant.now()

    def follow(User user) {
        following.add(user)
    }

    def follows(User user) {
        following.contains(user)
    }

    def post(String text, Instant postedAt) {
        posts.add(new Message(text, postedAt, this))
    }

    Set<Message> getPosts() {
        posts.asImmutable()
    }

    @Override
    String toString() {
        "@" + name;
    }

    def timeline() {
        Set<Message> timeline = posts.clone()
        following.each {
            timeline.addAll(it.posts)
        }
        return timeline.asImmutable()
    }
}

