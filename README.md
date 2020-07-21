## A microservice application to demonstrate communication and service discovery.

...It consists of 3 Spring Boot applications, and communicate using the RestTemplate object. A WebClient Builder is also looked into, since the RestTemplate is being planned to be depreceated. 
...The WebClient Builder is based on the reactive programming paradigm in Java.

### The different microservices are:
..1. Movie Catalogue Service
..2. Movie Info Service
..3. Ratings Data Service

# (**Important)
...There are 2 types of service discovery:
...1. Client Side Service Discovery
......eg: Eureka server
...2. Server Side Service Discovery
......eg: NGINX, AWS etc.

![Alt text](assests/Layout.png?raw=true "Microservice layout")
