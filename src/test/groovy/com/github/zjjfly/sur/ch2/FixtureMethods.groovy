package com.github.zjjfly.sur.ch2

import spock.lang.Specification

/**
 * @author zijunjie[https://github.com/zjjfly]
 * @date 2022/2/22
 */
class FixtureMethods extends Specification {

    //setupSpec类似@BeforeAll注释的方法
    def setupSpec() {
        println "> setupSpec"
    }

    //cleanupSpec类似@AfterAll注释的方法
    def cleanupSpec() {
        println "> cleanupSpec"
    }

    //setup类似@BeforeEach注释的方法
    def setup() {
        println "-> setup"
    }

    //setup类似@AfterEach注释的方法
    def cleanup() {
        println "-> cleanup"
    }

    def "feature method 1"() {
        println "--> feature method 1"
        expect :
        2 * 2 == 4
    }

    def "feature method 2"() {
        println "--> feature method 2"
        expect :
        3 * 2 == 6
    }
}
