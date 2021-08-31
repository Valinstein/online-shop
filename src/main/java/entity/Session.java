package entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Session {

    private LocalDateTime expireDate;
    private List<Product> bucket;
    private User user;
}
