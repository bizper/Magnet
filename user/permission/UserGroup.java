package user.permission;

import user.User;

public enum UserGroup {

    UNREGISTERED(-1, "未注册"),
    GUEST(0, "游客"),
    USER(1, "普通用户"),
    ADMIN(2, "管理员");

    private int level;
    private String desc;

    UserGroup(int level, String desc) {
        this.level = level;
        this.desc = desc;
    }

    public int getLevel() {
        return level;
    }

    public String getDesc() {
        return desc;
    }

    public static UserGroup getUserGroup(int i) {
        for(UserGroup ug : UserGroup.values()) {
            if(ug.level == i) return ug;
        }
        return UNREGISTERED;
    }

    public static UserGroup getUserGroup(User u) {
        return getUserGroup(u.getLevel());
    }

}
