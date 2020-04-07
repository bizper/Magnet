package user.permission;

public enum PERMISSION {

    /********************************
     *         表格操作权限           *
     *******************************/
    TABLE_MODIFIER_VALUE,//修改
    TABLE_DELETE_VALUE,//删除
    TABLE_INQUIRE_VALUE,//查询
    TABLE_APPEND_VALUE,//增加表
    TABLE_REGISTER_VALUE,//增加表名录

    /********************************
     *         数据库操作权限         *
     *******************************/
    DATABASE_INSERT_VALUE,//插入数据库
    DATABASE_UPDATE_VALUE,//更新数据库
    DATABASE_SELECT_VALUE,//查询数据库
    DATABASE_DELETE_VALUE,//删除数据库

    /********************************
     *         系统操作权限           *
     *******************************/
    SYSTEM_CHANGE_SETTING,//修改系统设置
    SYSTEM_UPDATE,//升级系统

    /********************************
     *         账户操作权限           *
     *******************************/
    ACCOUNT_EXIT_LOGIN,//退出登陆
    ACCOUNT_CHANGE_PASSWORD//修改当前账户密码

}
