## Ports:
- oauth-service: 9999
- edge-service: 8765
- eureka-service: 8761
- food-catalog-service: 8080
- food-client-service: 9000

Make sure to start the eureka service first.

## Multiple Instances

Create a new run configuration.

Add the VM option: -Dserver.port=8081

## Auth

Get access token from the authorization server

```
curl reader:readerSecret@localhost:9999/oauth/token -d grant_type=password -d username=user -d password=secret
```

Passing token

```
curl -H "Authorization: Bearer $token" localhost:8081/noodles
curl -H "Authorization: Bearer $token" localhost:8081/food-catalog-service/foods
```

### Auth with Gateway

Same flow as before but change the url to the gateway

```
curl reader:readerSecret@localhost:8765/api/oauth-service/oauth/token -d grant_type=password -d username=user -d password=secret
```

Passing token

```
curl -H "Authorization: Bearer $token" localhost:8765/api/food-client-service/noodles
```