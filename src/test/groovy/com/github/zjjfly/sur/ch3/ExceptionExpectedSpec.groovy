package com.github.zjjfly.sur.ch3

import spock.lang.Specification

import static java.time.Instant.now

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/3/2
 */
class ExceptionExpectedSpec extends Specification {

    def user = new User("kirk")

    def "the list of posts is not modifiable"() {
        when:
        user.posts << new Message("Fascinating!", now(), user)

        then:
        //thrown方法可以对when:中抛出的异常进行断言
        thrown(UnsupportedOperationException)
    }

    def "a posted message may not be longer than 140 characters"() {
        given:
        def messageText = """Lieutenant, I am half Vulcanian. Vulcanians do not speculate. I speak from pure logic. If I let go of a hammer on a planet that has a positive gravity, I need not see it fall to know that it has in fact fallen."""
        expect:
        messageText.length() > Message.MAX_TEXT_LENGTH
        when:
        user.post(messageText, now())
        then:
        //thrown返回的值是when:中抛出的异常，这可以让我们对异常做进一步的断言
        def e = thrown(IllegalArgumentException)
        e.message == "Message text cannot be longer than 140 characters"
    }
}
