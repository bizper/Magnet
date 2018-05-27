package user.permission;

import user.User;

import static user.permission.UserGroup.*;
import static user.permission.PERMISSION.*;

public class PermissionManager {

    private static PERMISSION guest_permission[] = new PERMISSION[]{
            DATABASE_SELECT_VALUE,
            TABLE_INQUIRE_VALUE,
            ACCOUNT_EXIT_LOGIN
    };
    private static PERMISSION user_permission[] = new PERMISSION[]{
            DATABASE_SELECT_VALUE,
            DATABASE_DELETE_VALUE,
            DATABASE_INSERT_VALUE,
            DATABASE_UPDATE_VALUE,
            TABLE_INQUIRE_VALUE,
            TABLE_APPEND_VALUE,
            TABLE_DELETE_VALUE,
            TABLE_MODIFIER_VALUE,
            TABLE_REGISTER_VALUE,
            ACCOUNT_CHANGE_PASSWORD,
            ACCOUNT_EXIT_LOGIN
    };
    private static PERMISSION admin_permission[] = new PERMISSION[]{
            DATABASE_SELECT_VALUE,
            DATABASE_DELETE_VALUE,
            DATABASE_INSERT_VALUE,
            DATABASE_UPDATE_VALUE,
            TABLE_INQUIRE_VALUE,
            TABLE_APPEND_VALUE,
            TABLE_DELETE_VALUE,
            TABLE_MODIFIER_VALUE,
            TABLE_REGISTER_VALUE,
            SYSTEM_CHANGE_SETTING,
            SYSTEM_UPDATE,
            ACCOUNT_EXIT_LOGIN,
            ACCOUNT_CHANGE_PASSWORD
    };

    public static boolean check(User current, PERMISSION p) {
        return current != null && contains(getUserGroup(current), p);
    }

    private static boolean contains(UserGroup ug, PERMISSION p) {
        switch(ug) {
            case GUEST:
                return contain(guest_permission, p);
            case USER:
                return contain(user_permission, p);
            case ADMIN:
                return contain(admin_permission, p);
            default:
                return false;
        }
    }

    private static boolean contain(PERMISSION[] list, PERMISSION p) {
        for(PERMISSION pm : list) {
            if(pm.equals(p)) return true;
        }
        return false;
    }

}
