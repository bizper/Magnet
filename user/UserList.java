package user;

import core.Action;
import core.mybatis.Inquire;

import java.io.*;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;

public class UserList {

    public static UserMessage validate(User u) {
        UserMessage um = new UserMessage();
        if(Inquire.confirmUser(u, Action.getInstance()) == null) {
            um.setErrorType(ErrorType.USER_NOT_EXIST);
            return um;
        }
        User user;
        if((user = Inquire.selectUser(u, Action.getInstance())) == null) {
            um.setErrorType(ErrorType.USER_PASSWORD_ERROR);
            return um;
        } else {
            um.setErrorType(ErrorType.USER_EXIST_CURRENTLY);
            user.setLoginTime(modifierTime(user.getLoginTime()));
            um.setUser(user);
            return um;
        }
    }

    private static Date modifierTime(Date date) {
        var cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.HOUR_OF_DAY, -13);
        return cal.getTime();
    }

}
