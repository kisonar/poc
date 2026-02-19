# Spring

## RAS API usage

http://localhost:8080/tunnel?connect

returns UUID

ws://localhost:8080/websocket

Required at client side TightVNC as VNC server/receiver/sender

## Endpoints

curl -X GET http://localhost:8080/hello

## Swagger

http://localhost:8080/swagger-ui/index.html

## Actuator

curl -X GET http://localhost:8080/actuator/info
curl -X GET http://localhost:8080/actuator/health

#### Adminer

```  
localhost:8080
system: mysql
server: mariadb - must match docker conatiner instance
database name: default_database
user/password: root/root 
```

#### TODOs

JPA not working after upgrade to Spring 4.x