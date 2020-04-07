package user;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class User {

    private int id;

    private final String username;

    private final String password;

    private final int level;

    private Date loginTime;

    public User(String name) {
        this(name, null);
    }

    public User(String username, String password) {
        this(-1, username, password);
    }

    public User(int id, String name) {
        this(id, name, null, 0, null);
    }

    public User(int id, String name, int Level) {
        this(id, name, null, Level, null);
    }

    public User(int id, String name, int Level, Timestamp date) {
        this(id, name, null, Level, date);
    }

    public User(int id, String name, String pwd, int Level) {
        this(id, name, pwd, Level, null);
    }

    public User(int id, String name, String pwd) {
        this(id, name, pwd, 0, null);
    }

    public User(int id, String name, String pwd, Timestamp time) {
        this(id, name, pwd, 0, time);
    }

    public User(int id, String name, String pwd, int Level, Timestamp stamp) {
        username = name;
        password = pwd;
        level = Level;
        if(stamp != null) {
            loginTime = Date.from(stamp.toInstant());
        } else {
            loginTime = null;
        }
    }

    public void setLoginTime(Date date) {
        this.loginTime = date;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getLevel() {
        return level;
    }

    public Date getLoginTime() {
        return loginTime;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object u) {
        if(!(u instanceof User)) return false;
        else {
            User n = User.toUser(u);
            if(n.getPassword().equals(password) && n.getUsername().equals(username)) return true;
        }
        return false;
    }

    static User toUser(Object obj) {
        if(obj instanceof User) return (User)obj;
        else return null;
    }

    public User clone() {
        return new User(id, username, null, level, Timestamp.from(loginTime.toInstant()));
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", level=" + level +
                ", loginTime=" + loginTime +
                '}';
    }
}
