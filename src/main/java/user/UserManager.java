package user;

import core.Action;
import core.mybatis.Inquire;
import user.permission.PERMISSION;
import user.permission.PermissionManager;
import user.permission.UserGroup;

import java.text.SimpleDateFormat;
import java.util.Date;

public class UserManager {

    private static User u;
    private static boolean isLogin = false;
    private static SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日 hh:mm:ss a");


    public static String getUsername() {
        return u == null || !isLogin ? "" : u.getUsername().toUpperCase();
    }

    public static String getUserLevelDesc() {
        return UserGroup.getUserGroup(u == null || !isLogin ? -1 : u.getLevel()).getDesc();
    }

    public static int getUserLevel() {
        return u == null || !isLogin ? -1 : u.getLevel();
    }

    public static int getUserId() {
        return u == null || !isLogin ? -1 : u.getId();
    }

    public static String getUserDate() {
        return u == null || !isLogin  ?  "" : sf.format(u.getLoginTime());
    }

    public static ErrorType validate(User user) {
        UserMessage um = UserList.validate(user);
        switch(um.getErrorType()) {
            case USER_EXIST_CURRENTLY:
                loginSuccessfully(um.getUser());
                break;
            case USER_NOT_EXIST:
                break;
            case USER_PASSWORD_ERROR:
                break;
        }
        return um.getErrorType();

    }

    public static boolean validatePermission(User u, PERMISSION p) {
        return PermissionManager.check(u, p);
    }

    public static boolean validatePermission(PERMISSION p) {
        return validatePermission(u, p);
    }

    public static void exitLogin() {
        isLogin = false;
        u = null;
    }

    private static void loginSuccessfully(User u) {
        isLogin = true;
        UserManager.u = u;
        Inquire.updateUser(u, Action.getInstance());
    }

    public static boolean getLoginStatus() {
        return isLogin;
    }

}
