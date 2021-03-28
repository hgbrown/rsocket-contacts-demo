package dev.hbrown.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class RsocketContactsDemoApplication

fun main(args: Array<String>) {
    runApplication<RsocketContactsDemoApplication>(*args)
}
