package jdbc.mapper;

import entity.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("product_name"))
                .price(resultSet.getDouble("product_price"))
                .creationDate(resultSet.getTimestamp("creation_date"))
                .build();
    }
}
