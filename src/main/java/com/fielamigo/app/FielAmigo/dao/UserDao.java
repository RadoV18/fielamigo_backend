package com.fielamigo.app.FielAmigo.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fielamigo.app.FielAmigo.dto.UserDto;

@Service
public class UserDao {
    private DataSource dataSource;
    
    @Autowired
    public UserDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public UserDto createUser(UserDto userDto) {
        String query = "INSERT INTO FA_USER " +
            "(EMAIL, PASSWORD, FIRST_NAME, LAST_NAME, PHONE_NUMBER, BIRTH_DATE) " +
            "VALUES (?, ?, ?, ?, ?, ?)";
        Date date = new Date(userDto.getBirthDate().getTime());
        try {
            Connection conn = dataSource.getConnection();
            String generatedColumns[] = { "id" };
            PreparedStatement ps = conn.prepareStatement(query, generatedColumns);
            ps.setString(1, userDto.getEmail());
            ps.setString(2, userDto.getPassword());
            ps.setString(3, userDto.getFirstName());
            ps.setString(4, userDto.getLastName());
            ps.setString(5, userDto.getPhoneNumber());
            ps.setDate(6, date);
            int affectedRows = ps.executeUpdate();
            if (affectedRows == 0) {
                throw new SQLException("Creating user failed, no rows affected.");
            }

            ResultSet generatedKeys = ps.getGeneratedKeys();
            if (generatedKeys.next()) {
                userDto.setUserId(generatedKeys.getLong(1));
            } else {
                throw new SQLException("Creating user failed, no ID obtained.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userDto;
    }
}
