package jdbc.mapper;

import entity.Product;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;


class ProductRowMapperTest {

    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    @DisplayName("Mapping resultSet to Product")
    @Test
    void mapRow() throws SQLException {
        ProductRowMapper rowMapper = new ProductRowMapper();
        ResultSet resultSet = mock(ResultSet.class);

        when(resultSet.getInt("id")).thenReturn(1);
        when((resultSet.getString("product_name"))).thenReturn("lamp");
        when(resultSet.getDouble("product_price")).thenReturn((double) 200);
        when((resultSet.getTimestamp("creation_date"))).thenReturn(timestamp);

        Product product = rowMapper.mapRow(resultSet);

        assertNotNull(product);

        assertEquals(1, product.getId());
        assertEquals("lamp", product.getName());
        assertEquals(200, product.getPrice());
        assertEquals(timestamp, product.getCreationDate());

    }
}