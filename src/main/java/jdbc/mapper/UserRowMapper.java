package jdbc.mapper;

import entity.Role;
import entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    public User mapRow(ResultSet resultSet) throws SQLException {
        return User.builder()
                .userName(resultSet.getString("login"))
                .password(resultSet.getString("password"))
                .salt(resultSet.getString("salt"))
                .role(Role.valueOf(resultSet.getString("role").toUpperCase()))
                .build();
    }

    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        return mapRow(resultSet);
    }
}
