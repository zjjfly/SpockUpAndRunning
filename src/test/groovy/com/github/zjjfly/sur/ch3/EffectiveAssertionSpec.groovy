package com.github.zjjfly.sur.ch3

import spock.lang.Specification

import java.time.Clock
import java.time.Duration
import java.time.ZoneOffset
import java.time.temporal.ChronoUnit

import static java.time.Instant.now

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/3/2
 */
class EffectiveAssertionSpec extends Specification {

    def user = new User("kirk")

    def "a user can post a message"() {
        given:
        def time = now()

        when:
        user.post("@kirk that is illogical, Captain!", time)

        then:
        //使用Groovy的List的spread operator和List字面量对下面的代码进行简化
        //def message = user.posts[0]
        //message.text == "@kirk that is illogical, Captain!"
        user.posts*.text == ["@kirk that is illogical, Captain!"]
    }

    def "the posting user is recorded in the message"() {

        //spock的then:或expect:标签对for循环或if不起作用，如果这么写了，这部分代码无论如何都会通过
        //底层的原因使，spock会对标签下的代码进行AST转换，如果其中有类型是Boolean的表达式，则会认为其实一个断言
        //而for和if这两种是没有类型的，所以它们里面的代码就不会认为是断言
        expect:
        for (int i = 1; i <= 10; i++) {
            //下面的这个表达式明显为false，但却不会使测试失败
            i == -i
        }

        when:
        user.post("Fascinating!", now())
        user.post("@bones I was merely stating a fact, Doctor.", now())

        then:
        //对于上面问题的解决方案是使用Groovy引入的every方法
        user.posts.postedBy.every {
            it == user
        }
    }

    def "messages are ordered most recent first"() {
        given:
        def clock = Clock.fixed(now(), ZoneOffset.UTC)
        def olderMessage = new Message("Fascinating.", clock.instant(), user)

        and:
        clock = Clock.offset(clock, Duration.of(1, ChronoUnit.MINUTES))
        def newerMessage = new Message("Live long and prosper.", clock.instant(), user)

        //利用Groovy的操作符重载，让Message实现Comparable接口，这样就可以使用>,<,>=,<=来进行比较
        expect:
        newerMessage < olderMessage
        and:
        olderMessage > newerMessage
    }

    def "a user can follow another user"() {
        given:
        def other = new User("spock")

        when:
        user.follow(other)

        then:
        user.following.size() == 1
        //使用Groovy的in关键字来判断一个元素是否在一个集合值
        other in user.following
    }

}
