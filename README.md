### Overview
The Weather Forecast Management System is a web application designed to provide live weather updates seamlessly. It follows the Model-View-Controller (MVC) architecture, ensuring a smooth user experience. Users can register, login, update profile, and access real-time forecasts worldwide.

### How to Run

1. Create your [Weather API key](https://www.weatherapi.com/) and add it to weather.html in the placeholder provided

2. Create a database in MySQL as 'weather_app' and a table in it as 'users'.  
   
3. Update your MySQL credentials such as username and password in the following files :
   - forecast/src/main/java/com/weather/util/DBConnection.java
   - forecast/src/main/resources/application.properties
   - forecast/target/classes/application.properties
     
4. Build the package and run spring boot
```
mvn install package
mvn spring-boot:run
```
5. access the application on localhost:8080


