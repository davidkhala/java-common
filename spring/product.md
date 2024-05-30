# Spring Cloud
Spring Cloud based on spring boot. Spring cloud orchestrate services, each provided by spring boot

major features in Spring Cloud
- Distributed/versioned configuration
- Service registration and discovery
- Routing
- Service-to-service calls
- Load balancing
- Circuit Breakers
- Distributed messaging

# [Spring Cloud: Data Flow](https://github.com/spring-cloud/spring-cloud-dataflow)

![](https://dataflow.spring.io/static/19e89c2894aa4586aec3336ac4e6954b/5105f/arch-overview.webp)



Common in Data Flow Server and Skipper Server
- use OAuth 2.0 authentication or Basic authentication
  - except: the REST endpoints (administration, management, health) and the Dashboard UI do not require authenticated access.
- expose RESTFul endpoints
- need to have an RDBMS installed
  - Supported DBs: H2 (default), HSQLDB, MariaDB, Oracle, Postgresql, DB2, and MS SqlServer
  - The schemas are automatically created when each server starts.

Integrate with Spring Cloud Stream or Spring Cloud Task

## Data Flow Server
Data Flow server delegates to the Skipper server to deploy long-lived applications.


## Skipper Server
The Skipper Server is responsible for managing streams
- Deploy, upgrade(blue/green), rollback stream to platform (Local, Cloud Foundry, k8s)
- Storing the history of each stream's manifest file

Feats
- use OAuth 2.0 authentication




# [Spring Cloud Stream](https://github.com/spring-cloud/spring-cloud-stream) 
Use cases: Long-lived applications

a programming model to simplify the writing of message-driven microservice applications that are connected to a common messaging system
- Write core business logic which is agnostic to the specific MQ.
- choosing a binder library among 
  - RabbitMQ
  - Kafka and [Kafka Streams](https://kafka.apache.org/documentation/streams/)
  - [Amazon Kinesis](https://aws.amazon.com/kinesis/)
  - [Google Pub/Sub](https://cloud.google.com/pubsub/docs/)
  - Solace PubSub+
  - [Azure Event Hubs](https://azure.microsoft.com/en-us/services/event-hubs/)



# [Spring Cloud Task](https://github.com/spring-cloud/spring-cloud-task)
Use cases: Short-lived applications



# Spring Boot
- Spring boot focus on build single service
- In Spring Boot there are default configurations that allow faster bootstrapping.
## Feats
- Embed Tomcat, Jetty or Undertow directly (no need to deploy WAR files)
- Automatically configure Spring and 3rd party libraries whenever possible
- Absolutely no code generation and no requirement for XML configuration
## Getting started
[spring initializr](https://start.spring.io/): scaffold of configuration

[davidkhala's spring-template](https://github.com/davidkhala/spring-template)

## Spring Boot Actuator
To manage and monitor the Spring Boot application

# Spring Framework
In the Spring framework, you have to build configurations manually. 

The core features of Spring Framework include Inversion-Of-Control (IoC), Dependency-Injection (DI), Aspect-Oriented-Programming (AOP),

# Spring Batch framework

# [Spring Tool Suite (STS)](https://spring.io/guides/gs/sts)
A plugin for various IDEs: VSCode, Eclipse IDE, Eclipse Theia
