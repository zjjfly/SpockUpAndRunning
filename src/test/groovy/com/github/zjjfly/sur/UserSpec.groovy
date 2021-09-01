package com.github.zjjfly.sur

import spock.lang.Specification

/**
 * ${description}* @author Zi JunJie(junjie.zi@siemens.com)
 */
class UserSpec extends Specification {

    def "a user can follow another user"() {
        given:
        def user = new User("kirk")
        def other = new User("spock")

        when:
        user.follow(other)

        then:
        user.following.size() == 1
        user.following.contains(other)
    }
}
