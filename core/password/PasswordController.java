package core.password;

import constant.STRING;
import core.Action;
import core.LoginWin;
import core.Main;
import core.dialog.DialogUtil;
import core.mybatis.Inquire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import user.ErrorType;
import user.User;
import user.UserManager;
import user.permission.PERMISSION;

public class PasswordController {

    private Stage stage;

    private Stage main;

    @FXML
    private Label origin;

    @FXML
    private PasswordField originPassword;

    @FXML
    private PasswordField newPassword;

    @FXML
    private PasswordField confirmPassword;

    @FXML
    private Hyperlink forgetPassword;

    @FXML
    void cancel(ActionEvent event) {
        stage.hide();
    }

    @FXML
    void submit(ActionEvent event) {
        if(blankCheck()) {
            DialogUtil.getInstance(stage, "输入不能为空");
            return;
        }
        if(!sameCheck()) {
            DialogUtil.getInstance(stage, "两次输入不一致");
            return;
        }
        int id = UserManager.getUserId();
        String name = UserManager.getUsername();
        int level = UserManager.getUserLevel();
        User u = new User(id, name, originPassword.getText(), level);
        ErrorType et;
        if((et = UserManager.validate(u)) == ErrorType.USER_EXIST_CURRENTLY) {
            u = new User(id, name, confirmPassword.getText(), level);
            Inquire.updatePassword(u, Action.getInstance());
            DialogUtil.getInstance(stage, "修改完成！请重新登陆！", "关闭窗口", "重新登陆", (e) -> exit(), (e) -> exit());
        }
        else {
            if (et == ErrorType.USER_PASSWORD_ERROR) {
                DialogUtil.getInstance(stage, "提示", "密码错误", "取消", "好的");
            }
        }
    }

    private void exit() {
        if(UserManager.validatePermission(PERMISSION.ACCOUNT_EXIT_LOGIN)) {
            LoginWin lw = new LoginWin();
            try {
                lw.start(new Stage());
                main.hide();
                stage.hide();
                UserManager.exitLogin();
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        }
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public void setMainStage(Stage stage) {
        main = stage;
    }

    private boolean sameCheck() {
        return newPassword.getText().equals(confirmPassword.getText());
    }

    private boolean blankCheck() {
        return originPassword.getText().isEmpty() || newPassword.getText().isEmpty() || confirmPassword.getText().isEmpty();
    }

}
