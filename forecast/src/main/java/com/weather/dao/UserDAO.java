package com.weather.dao;

import com.weather.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class UserDAO {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public UserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public boolean registerUser(User user) {
        String INSERT_USER_SQL = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";
        try {
            int result = jdbcTemplate.update(INSERT_USER_SQL, user.getUsername(), user.getPassword(), user.getEmail());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


    public User authenticateUser(String username, String password) {
        String SELECT_USER_SQL = "SELECT * FROM users WHERE username = ?";
        try {
            return jdbcTemplate.queryForObject(SELECT_USER_SQL, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }, username);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public User validateUser(String usernameOrEmail, String password) {
        String VALIDATE_USER_SQL = "SELECT * FROM users WHERE (username = ? OR email = ?) AND password = ?";
        try {
            return jdbcTemplate.queryForObject(VALIDATE_USER_SQL, (resultSet, i) -> {
                User user = new User();
                user.setId(resultSet.getInt("id"));
                user.setUsername(resultSet.getString("username"));
                user.setEmail(resultSet.getString("email"));
                return user;
            }, usernameOrEmail, usernameOrEmail, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public boolean updateUser(User user) {
        String UPDATE_USER_SQL = "UPDATE users SET username = ?, email = ?, password = ? WHERE id = ?";
        try {
            int result = jdbcTemplate.update(UPDATE_USER_SQL, user.getUsername(), user.getEmail(), user.getPassword(), user.getId());
            return result > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public void deleteUserByEmail(String email) {
        String DELETE_USER_SQL = "DELETE FROM users WHERE email = ?";
        try {
            jdbcTemplate.update(DELETE_USER_SQL, email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    

}