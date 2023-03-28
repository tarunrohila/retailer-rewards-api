# retailer-rewards-api
retailer-rewards-api

## Steps to run the api
Run below commands to build and run the api
```groovy
cd <projects main direcoty path>
./mvnw clean install
./mvnw spring-boot:run
```
This will start application with on the default port 8080.

## Available Endpoints in the application
- to retrieve all rewards point earned by the customer - /rewards/{customerId}
```groovy
curl --location --request GET 'http://localhost:8080/rewards/1' \
--header 'Content-Type: application/json' 
```
Sample Response :
```groovy
{
    "customerId": 1,
    "customerName": "Ahsan",
    "totalPurchase": 802.0,
    "totalPoints": 1008,
    "pointsByMonth": {
        "JANUARY": 252,
        "MARCH": 504,
        "FEBRUARY": 252
    }
}
```

- to add a new transaction /transactions
```groovy
curl --location 'http://localhost:8080/transactions' \
--header 'Content-Type: application/json' \
--data '{
"customerId": "1",
"purchaseAmount": 200.50
}'

```
Sample Response :
```groovy
{
    "id": 20,
    "customerId": 1,
    "customerName": "Ahsan",
    "purchaseAmount": 200.5,
    "pointEarned": 252,
    "createDate": "2023-03-28"
}
```

