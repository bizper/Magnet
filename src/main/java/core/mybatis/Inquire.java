package core.mybatis;

import constant.PATH;
import core.Action;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import pack.SearchPack;
import result.Bean;
import user.User;
import user.UserManager;
import user.permission.PERMISSION;
import user.permission.PERMISSION.*;
import util.StructUtil;

import java.io.*;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import static core.PostInterceptor.handle;

public class Inquire {

    private static InputStreamReader inputStream;
    private static SqlSessionFactory sqlSessionFactory;
    private static SqlSession session;
    private static ResultMapper m;
    private static UserMapper um;
    private static OperationMapper om;

    public static void cleanList(Action act) {
        if(!validate(act)) return;
        m.cleanList();
    }

    public static void deleteBean(Bean b, Action act) {
        if(!validate(act)) return;
        m.deleteBean(b);
        handle(UserManager.getUserId(), PERMISSION.DATABASE_DELETE_VALUE, b);
    }

    public static void updateBean(Bean b, Action act) {
        if(!validate(act)) return;
        m.updateBean(b);
        handle(UserManager.getUserId(), PERMISSION.DATABASE_UPDATE_VALUE, b);
    }

    public static List<Bean> selectList(SearchPack sp, Action act) {
        if(!validate(act)) return null;
        String Region = sp.getRegion();
        String Groups = sp.getGroup();
        String Host = sp.getMaster();
        Boolean isAllowLike = sp.getAllowLikeInquire();
        String Member = sp.getMember();
        handle(UserManager.getUserId(), PERMISSION.DATABASE_SELECT_VALUE, sp);
        if(!isAllowLike) return m.selectBeanList(Region, Groups, Host);
        else return m.selectBeanListByLike(Region, Groups, Host, Member);
    }

    public static void insert(Bean r, Action act) {
        if(!validate(act)) return;//检查action实例是否合法
        m.insertBean(r);
        handle(UserManager.getUserId(), PERMISSION.DATABASE_INSERT_VALUE, r);
    }

    public static void insertBeanList(List<Bean> b, Action act) {
        if(!validate(act)) return;//检查action实例是否合法
        m.insertBeanList(b);
        handle(UserManager.getUserId(), PERMISSION.DATABASE_INSERT_VALUE, StructUtil.getListString(b));
    }

    public static User selectUser(User u, Action act) {
        if(!validate(act)) return null;//检查action实例是否合法
        handle(UserManager.getUserId(), PERMISSION.DATABASE_SELECT_VALUE, "LoginUser:" + u.getUsername());
        return um.selectUser(u.getUsername(), u.getPassword());
    }

    public static Date selectDate(String username, Action act) {
        if(!validate(act)) return null;
        handle(UserManager.getUserId(), PERMISSION.DATABASE_SELECT_VALUE, username);
        return um.selectLoginTime(username);
    }

    public static User confirmUser(User u, Action act) {
        if(!validate(act)) return null;//检查action实例是否合法
        return um.confirmUser(u.getUsername());
    }

    public static void updateUser(User u, Action act) {
        if(!validate(act)) return;
        um.updateUser(u.getId());
        handle(UserManager.getUserId(), PERMISSION.DATABASE_UPDATE_VALUE, u);
    }

    public static void updatePassword(User u, Action act) {
        if(!validate(act)) return;
        um.updatePassword(u);
        handle(UserManager.getUserId(), PERMISSION.DATABASE_UPDATE_VALUE, "UpdatePassword By: " + u.getUsername());
    }

    public static void insertOperation(HashMap<String, Object> list) {
        om.insert(list);
    }

    private static boolean validate(Action act) {
        return act == Action.getInstance();
    }

    public static void close() {
        session.close();
    }

    public static void start() {
        try {
            inputStream = new InputStreamReader(Resources.getResourceAsStream(PATH.RESOURCE));
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            session = sqlSessionFactory.openSession(true);
            m = session.getMapper(ResultMapper.class);
            um = session.getMapper(UserMapper.class);
            om = session.getMapper(OperationMapper.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}