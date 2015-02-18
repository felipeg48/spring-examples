package com.springboot.rabbitmq

import org.springframework.amqp.rabbit.annotation.EnableRabbit
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.scheduling.annotation.EnableScheduling

import com.springboot.rabbitmq.Application;


@EnableRabbit
@EnableScheduling
@SpringBootApplication
class Application {

    static void main(String[] args) {
        SpringApplication.run Application, args
    }
}
