package jdbc.mapper;

import entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    public Product mapRow(ResultSet resultSet) throws SQLException {
        return Product.builder()
                .id(resultSet.getInt("id"))
                .name(resultSet.getString("product_name"))
                .price(resultSet.getDouble("product_price"))
                .creationDate(resultSet.getTimestamp("creation_date"))
                .build();
    }

    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        return mapRow(resultSet);
    }
}
