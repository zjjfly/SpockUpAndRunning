package com.github.zjjfly.sur.ch3

import com.github.zjjfly.sur.ch3.User
import spock.lang.Specification

/**
 * @author zjjfly[[https://github.com/zjjfly]]
 * @date 2022/2/27
 */
class AssertionErrorSpec extends Specification {

    def user = new User("kirk")

    def "assertion failure raised with useful info"() {

        given:
        def other = new User("spock")

        when:
        user.follow(other)

        then:
        //下面这行代码会无法通过测试，错误信息是
        //Condition not satisfied:
        //
        //user.following.size() == 2
        //|    |         |      |
        //|    [@spock]  1      false
        //@kirk
        //可以看出spock的断言失败的信息非常清晰，并可以很容易看出失败的原因
        user.following.size() == 2

        cleanup:
        user.following = []
        other.following = []
    }

    def "type mismatch can be detected by spock"() {
        given:
        def other = new User("spock")

        when:
        user.follow(other)

        then:
        //下面的断言的左右表达式的类型不同，所以会报错
        //Condition not satisfied:
        //
        //user.following[0] == other.toString()
        //|    |        |   |  |     |
        //|    [@spock] |   |  |     @spock (java.lang.String)
        //@kirk         |   |  @spock
        //              |   false
        //              @spock (com.github.zjjfly.sur.ch2.User)
        //可以看到错误信息中包含了类型信息
        user.following[0] == other.toString()

        cleanup:
        user.following = []
        other.following = []
    }


}
