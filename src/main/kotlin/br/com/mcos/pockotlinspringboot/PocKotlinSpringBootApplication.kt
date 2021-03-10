package br.com.mcos.pockotlinspringboot

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class PocKotlinSpringBootApplication

fun main(args: Array<String>) {
	runApplication<PocKotlinSpringBootApplication>(*args)
}
