package core.mybatis;

import org.apache.ibatis.annotations.Param;
import user.User;

import java.util.Date;

public interface UserMapper {

    User selectUser(@Param("username")String username, @Param("password")String password);

    User confirmUser(String username);

    void insertUser(User u);

    Date selectLoginTime(String username);

    void updateUser(@Param("id")int id);

    void updatePassword(User user);

}
