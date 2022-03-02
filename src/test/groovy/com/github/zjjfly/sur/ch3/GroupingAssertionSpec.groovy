package com.github.zjjfly.sur.ch3

import spock.lang.Specification

import java.time.Instant

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/3/2
 */
class GroupingAssertionSpec extends Specification {

//    def "initial state of a user is correct"() {
//        given:
//        def user = new User("kirk")
//
//        expect:
//        user.name == "kirk"
//        user.following.isEmpty()
//        user.posts.isEmpty()
//        user.registered instanceof Instant
//    }

    //使用with可以对某个对象进行断言，在with中，可以直接访问对象的属性和方法（类似JS的with）
    //注意，这个with是Spock的方法，而不是Groovy的Object.with(Closure)方法
    def "initial state of a user is correct"() {
        given:
        def user = new User("kirk")
        expect:
        with(user) {
            name == "kirk"
            following.isEmpty()
            posts.isEmpty()
            registered instanceof Instant
        }
    }
}
