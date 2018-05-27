package core;

import constant.PATH;
import core.mybatis.Inquire;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginWin extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH.LOGIN_FXML));
        Pane p = null;
        try {
            p = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene login = new Scene(p, 442, 176);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(PATH.ICON)));
        stage.setScene(login);
        stage.setResizable(false);
        stage.setTitle("登陆");
        stage.show();
        LoginController instance = loader.getController();
        instance.setStage(stage);
        initInquire();
    }

    public static void initInquire() {
        Inquire.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
