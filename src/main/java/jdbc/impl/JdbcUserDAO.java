package jdbc.impl;

import entity.Role;
import entity.User;
import jdbc.UserDAO;
import jdbc.mapper.UserRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;

public class JdbcUserDAO implements UserDAO {
    private JdbcTemplate jdbcTemplate;
    private static  final String GET_USER_BY_NAME = "SELECT role, password, salt FROM user WHERE login = ?";
    private UserRowMapper userRowMapper;

    @Override
    public User getUserByLogin(String name) {
        return jdbcTemplate.query(GET_USER_BY_NAME, userRowMapper, name).get(0);
    }

    @Override
    public void createUser() {

    }
}
