package jdbc;

import entity.User;

public interface UserDAO {
    User getUserByLogin(String name);

    void createUser();
}
