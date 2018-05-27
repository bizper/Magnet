package core;

import constant.STRING;
import core.dialog.DialogUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import user.ErrorType;
import user.User;
import user.UserManager;

import java.sql.Timestamp;
import java.util.Date;

public class LoginController {

    private Stage s;

    @FXML
    private Label login_message;

    @FXML
    private TextField login_text_username;

    @FXML
    private PasswordField login_text_password;

    @FXML
    void passwordClick(ActionEvent e) {
        handleLoginAction(e);
    }

    @FXML
    void handleLoginAction(ActionEvent event) {
        if(login_text_username.getText().isEmpty()) {
            login_message.setText("！");
            return;
        }
        if(login_text_password.getText().isEmpty()) {
            login_message.setText("！");
            return;
        }
        User u = new User(login_text_username.getText(), login_text_password.getText());
        login(u);
    }

    @FXML
    void handleCreateAccount(ActionEvent event) {
        DialogUtil.getInstance(s, "此功能暂未开放");
    }

    @FXML
    void guestLogin(ActionEvent event) {
        User u = new User(STRING.GUEST_USERNAME, STRING.GUEST_PASSWORD);
        login(u);
    }

    private void login(User u) {
        ErrorType et;
        if((et = UserManager.validate(u)) == ErrorType.USER_EXIST_CURRENTLY) {
            s.hide();//隐藏当前窗口
            Main m = new Main();//创建主窗口
            m.start(new Stage());
        }
        else {
            if(et == ErrorType.USER_PASSWORD_ERROR) {
                DialogUtil.getInstance(s, "提示", "密码错误", "取消", "好的");
            }
            if(et == ErrorType.USER_NOT_EXIST) {
                DialogUtil.getInstance(s, "提示", "用户不存在", "取消", "好的");
            }
        }
    }

    void setStage(Stage s) {
        this.s = s;
    }

    @FXML
    void handleForgetPassword(ActionEvent ae) {
        DialogUtil.getInstance(s, "提示", "发送忘记密码邮件至needfast@hotmail.com", "取消", "好的", (cancel) -> {}, (confirm) -> {
            try {
                Runtime.getRuntime().exec("explorer.exe mailto:needfast@hotmail.com");
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

}
