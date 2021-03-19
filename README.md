# Tree Radius Project

Spring Boot MVC REST application with MongoDB database.

This application implement a specific feature as a REST service that uses some 3rd party API.
A service should make an aggregated search of trees (plants, not binary trees) in the circle.

Input:
  - X and Y of the cartesian center point
  - A search radius in meters

Output:
  - Aggregation by "common name" (please see in the documentation on the same page above which field to refer to)

Example of the expected output:
```json
{
    "red maple": 30,
    "American linden": 1,
    "London planetree": 3
}
```

The service should use the data from the 3rd party API (https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh): `https://data.cityofnewyork.us/resource/nwxe-4ae8.json`

# *******************************************

Start the "App" class and visit http://localhost:8080/swagger-ui.html 

![1](https://user-images.githubusercontent.com/14158040/111809491-4b2e9180-88ea-11eb-9e3d-942bb9d1e0c3.jpg)


Click on "tree-controller" then "GET /trees/loadDataFromApi" and then " Try it out! " to retrieve data from https://data.cityofnewyork.us and insert to Mongodb 

![2](https://user-images.githubusercontent.com/14158040/111813771-e164b680-88ee-11eb-916f-d6f52d0c5338.jpg)


To make an aggregated search of plants in the circle, Click on "GET /trees" then set latitude = 40.81646683 , longitude = -73.88917503 and radious = 50  and then " Try it out! " to see the Aggregation by "common name" 

![3](https://user-images.githubusercontent.com/14158040/111814024-399bb880-88ef-11eb-82da-6f4e1accf9bd.jpg)


Response is :


![4](https://user-images.githubusercontent.com/14158040/111814889-41a82800-88f0-11eb-9983-f34969530139.jpg)









References :

https://data.cityofnewyork.us/Environment/2015-Street-Tree-Census-Tree-Data/uvpi-gqnh/
https://data.cityofnewyork.us/resource/nwxe-4ae8.json
https://www.baeldung.com/swagger-2-documentation-for-spring-rest-api
https://www.baeldung.com/rest-api-pagination-in-spring
https://docs.spring.io/spring-data/data-mongodb/docs/current-SNAPSHOT/reference/html/#mongo.geospatial

