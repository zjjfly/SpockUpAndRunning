package com.github.zjjfly.sur.ch2


import spock.lang.Specification

/**
 * @author zjjfly[[https://github.com/zjjfly]]
 * @date 2021/9/1
 */
class UserSpec extends Specification {
    //多个feature method共享对象,这个对象在每个feature method执行之前都会重新初始化
    def user = new User("kirk")

    //定义feature method
    def "a user can follow another user"() {
        //given用于准备测试环境，如初始化数据，登录系统等.和它相同作用的block是setup
        given:
        def other = new User("spock")

        //expect一般在when之前，用于验证一些测试需要的预置条件
        expect:
        user.following.isEmpty()

        //when中执行待测试的代码,后面必须跟着then
        when:
        user.follow(other)

        //then对测试结果进行验证
        then:
        user.following.size() == 1
        //and可以跟在then，given，expect之后，一般用于分割不相关的断言，使得代码可读性更好
        and:
        user.following.contains(other)

        //cleanup清理测试环境，无论前面的断言结果如何，都会执行里面的代码，类似finally
        cleanup:
        user.following = []
        and: //and用于扩充其他的block，当某个block比较长的时候，and可以把它们拆分为多个block来提高可读性
        other.following = []
    }

    def "a user reports if they are following someone"() {
        given:
        def other = new User("spock")
        expect:
        !user.follows(other)
        when:
        user.follow(other)
        then:
        user.follows(other)
    }
}
