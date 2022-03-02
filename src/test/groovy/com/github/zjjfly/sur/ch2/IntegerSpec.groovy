package com.github.zjjfly.sur.ch2


import spock.lang.Specification

/**
 * @author zjjfly[[https://github.com/zjjfly]]
 * @date 2021/8/30
 */
class IntegerSpec extends Specification {

    def "an integer can be incremented"() {
        given:
        int i = 1

        when:
        i++

        then:
        i == 2
    }

}
