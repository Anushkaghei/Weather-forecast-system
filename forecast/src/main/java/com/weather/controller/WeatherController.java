package com.weather.controller;

import com.weather.dao.WeatherDAO;
import com.weather.model.Weather;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/weather")
public class WeatherController extends HttpServlet {
    private WeatherDAO weatherDAO;

    public void init() {
        weatherDAO = new WeatherDAO();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        String location = request.getParameter("location");
        Weather weather = weatherDAO.getWeatherByLocation(location);

        if (weather != null) {
            request.setAttribute("weather", weather);
            request.getRequestDispatcher("weather.html").forward(request, response);
        } else {
            request.setAttribute("message", "Weather data not available for the requested location.");
            request.getRequestDispatcher("dashboard.html").forward(request, response);
        }
    }
}
