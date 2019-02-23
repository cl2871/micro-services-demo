Ports:
- oauth-service: 9999
- edge-service: 8081
- eureka-service: 8761
- food-catalog-service: 8080


Get access token from the authorization server

```
curl reader:readerSecret@localhost:9999/oauth/token -d grant_type=password -d username=user -d password=secret
```

Get access token via gateway (check this)

```
curl reader:readerSecret@localhost:8081/oauth-service/oauth/token -d grant_type=password -d username=user -d password=secret
```

Passing token

```
curl -H "Authorization: Bearer $token" localhost:8081/noodles
curl -H "Authorization: Bearer $token" localhost:8081/food-catalog-service/foods
```