# README

## Overview

This project represents a secured microservices architecture that ensures all requests for backend resources are authorized. 

For this particular system, Spring Boot 2 and Netflix OSS are used for microservices. This system is composed of a Zuul api gateway, an OAuth2 authorization server, a Eureka server, a Feign intermediary service, and a REST resource service.

### Services

- edge-service: Zuul api gateway that handles all incoming requests 
- oauth-service: OAuth2 authorization server
- eureka-service: Eureka server for service discovery
- food-client-service: Feign service, performs client-side load balancing with Ribbon
- food-catalog-service: backend REST resource

### Project Structure and Request Flow

Within this project, the edge-service acts as the entry point for the entire system. Any request for backend resources will be routed by the edge-service.

The food-catalog-service is the backend REST resource that returns a list of Food entities. The food-client-service is an intermediary Feign service that utilizes the food-catalog-service. It can return a list of all Food entities as well as a list of noodles. It can load balance between multiple instances of the food-catalog-service.

The eureka-service is a Eureka server that is used for discovering the instances of each service. This is used by the edge-service for routing requests and the food-client-service for getting data from the food-catalog-service. Each application registers itself as a Eureka discovery client so that it can be discovered.

In order to get access to the backend resources, a request would need to be authorized to do so. The edge-service will prevent unauthorized requests to the food-client-service. It allows anonymous requests to the oauth-service, which will generate a JWT token that can be used to access backend resources. The edge-server acts as a resource server and can verify the JWT token before passing the request downstream.


## Usage Information

### Ports
- oauth-service: 9999
- edge-service: 8765
- eureka-service: 8761
- food-catalog-service: 8080
- food-client-service: 9000

Make sure to start the eureka service first.

### Multiple Instances

To run multiple instances of a microservice application, you can add the following VM option to the command line or run configuration.

```-Dserver.port=8081```

### Auth

To get access token from the authorization server, send a request with a client_id (reader) and client_secret (readerSecret) to a URI. The grant_type used here is the password grant.

```
curl reader:readerSecret@localhost:9999/oauth/token -d grant_type=password -d username=user -d password=secret
```

After getting an access token from the previous step, pass it in as a Bearer token.

```
curl -H "Authorization: Bearer $token" localhost:8081/noodles
curl -H "Authorization: Bearer $token" localhost:8081/food-catalog-service/foods
```

#### Auth with Gateway

Same flow as before but change the url to utilize the gateway.

```
curl reader:readerSecret@localhost:8765/api/oauth-service/oauth/token -d grant_type=password -d username=user -d password=secret
```

Pass the access token to get a backend resource.

```
curl -H "Authorization: Bearer $token" localhost:8765/api/food-client-service/noodles
```