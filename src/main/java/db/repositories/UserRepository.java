package db.repositories;

import model.User;

import java.sql.SQLException;

public interface UserRepository {

    boolean isUserPresent(String login, String passToMD5, String userDB, String passDB) throws Exception;

    String saveUser(String login1, String md5_val) throws Exception;

}
