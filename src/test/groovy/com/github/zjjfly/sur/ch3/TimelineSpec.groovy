package com.github.zjjfly.sur.ch3

import spock.lang.Specification
import spock.lang.Subject

import java.time.Clock
import java.time.Instant
import java.time.ZoneOffset

import static java.time.temporal.ChronoUnit.MINUTES

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/3/2
 */
class TimelineSpec extends Specification {

    @Subject
            user = new User("khan")
    def followedUser = new User("kirk")
    def otherUser = new User("spock")


    def setup() {
        user.follow(followedUser)
        def now = Instant.now()
        postMessage(otherUser, now.minus(6, MINUTES),
                "His pattern indicates two-dimensional thinking.")
        postMessage(user, now.minus(5, MINUTES),
                "@kirk You're still alive, my old friend?")
        postMessage(followedUser, now.minus(4, MINUTES),
                "@khan KHAAANNNN!")
        postMessage(followedUser, now.minus(3, MINUTES),
                "@scotty I need warp speed in three minutes or we're all dead!")
        postMessage(otherUser, now.minus(2, MINUTES),
                "@bones I'm sorry, Doctor, I have no time to explain this logically.")
        postMessage(user, now.minus(1, MINUTES),
                "It is very cold in space!")
    }

    private void postMessage(User poster, Instant at, String text) {
        def clock = Clock.fixed(at, ZoneOffset.UTC)
        poster.post(text, clock.instant())
    }

    def "a user's timeline contains posts from themselves and followed users"() {
        expect:
        with(user.timeline()) {
            size() == 4
            it*.postedBy.every { it in [user, followedUser] }
            !it*.postedBy.any {
                it == otherUser
            }
        }
    }

    def "a user's timeline is ordered most recent first"() {
        expect:
        with(user.timeline()) {
            it*.postedAt == it*.postedAt.sort().reverse()
        }
    }

    def "a timeline cannot be modified directly"() {
        when:
        user.timeline() << new Message("@kirk You're still alive, my old friend?", Instant.now(), user)

        then:
        thrown(UnsupportedOperationException)
    }

}
