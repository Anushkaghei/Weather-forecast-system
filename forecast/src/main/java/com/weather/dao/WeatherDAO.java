// WeatherDAO.java
package com.weather.dao;

import com.weather.model.Weather;
import com.weather.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class WeatherDAO {

    public Weather getWeatherByLocation(String location) {
        Weather weather = null;
        String SELECT_WEATHER_BY_LOCATION = "SELECT * FROM weather_data WHERE city = ?";

        try (Connection connection = DBConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_WEATHER_BY_LOCATION)) {
            preparedStatement.setString(1, location);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) {
                weather = new Weather();
                weather.setCity(rs.getString("city"));
                weather.setTemperature(rs.getDouble("temperature"));
                weather.setHumidity(rs.getDouble("humidity"));
                weather.setWindSpeed(rs.getDouble("wind_speed"));
                weather.setPressure(rs.getDouble("pressure"));

            }
        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly
        }
        return weather;
    }
}
