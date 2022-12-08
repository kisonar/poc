# Spring Security

## Endpoints

curl -X GET http://localhost:8080/secured

curl -X POST -H 'Content-Type: application/json' -i http://localhost:8080/authenticate --data '{
"username":"javainuse",
"password":"password"
}'