package com.github.zjjfly.sur.ch2

import groovy.transform.Canonical

/**
 * @author zijunjie[https://github.com/zjjfly]
 */
@Canonical
class User {
    String name

    List<User> following = []

    def follow(User user) {
        following.add(user)
    }

    def follows(User user) {
        following.contains(user)
    }
}
