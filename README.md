spring-examples
===============

Spring Examples: the idea behind these examples is to have a quick-start guide in how to setup different alternatives for use the Spring Framework and its different modules.

**NOTES**

 * All the projects are based on Groovy and Gradle.
 * Projects with beginning with **'j'** are based on Java 
 * There are projects that don't contain UNIT TEST but groovy scripts.

###01 - Spring Container
|Project:|
|:-| 
|01-spring-container|

This project covers the essential spring container, with some variations like:

 * Standard Bean Definition
 * Using Annotations
 * Using Profile
 
###01 - Spring AMQP 
|Project:|
|:-| 
|01-spring-amqp|

This project covers the spring-amqp module.

 * Simple - (SimpleTest) is the minimal configuration to send and receive a text message.
 * Listener - (ListenerTest) example for using ***asynchronous*** consumers.
 * ListenerDelegate - (ListenerDelegateTest) example for using ***asynchronous*** consumers using a ***delegate***. 
 
###01 - Spring Integration
|Project:|
|:-| 
|01-spring-integration|
 
This project covers the spring-integration module.
 
 * Simple - (SimpleTest) is the minimal configuration to send a message to a channel and print it out to the stream (console).
 
###01 - Spring Integration: Service Activator
|Project:|
|:-| 
|01-spring-integration-service-activator|

This project contains different examples on how to use the service-activator endpoint.

|Project:|
|:-| 
|j01-spring-integration-service-activator|

This project is a Java version of the service-activator endpoint, and its based on a maven artifact.

###01 - Spring Integration: Channels
|Project:|
|:-| 
|01-spring-integration-channels|

This project shows how to implement all available channels from the Spring Integration module:

 * P2P
 * Publish and Subscribe
 * Priority
 * Rendezvous
 * Direct
 * Executor

###01 - Spring Integration: EndPoints
|Project:|
|:-| 
|01-spring-integration-endpoints|

This project shows how to implement different endpoints from the Spring Integration module:

 * Service Activator
 * Bridge
 * Enricher (Synchronous and Asynchronous)
 * Gateway
 * Delayer

###01 - Spring / Camel
|Project:|
|:-| 
|01-spring-camel|

This project shows the Spring and Camel working together. Its another alternative to Spring Integration. Part of the code was using the **BeanBuilder** from Grails.

 
 