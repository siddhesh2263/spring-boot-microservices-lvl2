## Spring-based microservice with Fault Tolerance and Resiliance using Circuit Breaker Pattern (Hystrix)


There are multiple points of failure in a microservice ecosystem. There is a limitation on the resources a microservice application depends on, such as:
1. The number of requests being sent
2. Thread pool capacity


Different ways to manage fault tolerance in a microservice system are:
1. Create more instances of the microservice
2. Use the Circuit Breaker Pattern
3. Using the Bulkhead Pattern

While using Hystrix for implementing the Circuit Breaker Pattern, the issues related to Hystrix Proxies must be taken into account.

### All microservices up and running:

![Alt text](assests/HystrixP1.JPG?raw=true "All microservices running")

### In the event one or more microservices are down:

![Alt text](assests/HystrixP2.JPG?raw=true "One microservice down")
