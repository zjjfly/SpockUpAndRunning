package com.github.zjjfly.sur

import groovy.transform.Canonical

/**
 * ${description}* @author Zi JunJie(junjie.zi@siemens.com)
 */
@Canonical
class User {
    String name

    List<User> following = []

    def follow(User user) {
        following.add(user)
    }
}
