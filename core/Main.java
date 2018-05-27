package core;

import constant.PATH;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage stage) {
        Action act = Action.getInstance();
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH.MAIN_FXML));
        Pane p = null;
        try {
            p = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene main = new Scene(p, 1270, 760);
        stage.getIcons().add(new Image(getClass().getResourceAsStream(PATH.ICON)));
        stage.setScene(main);
        stage.setResizable(false);
        stage.setOnCloseRequest(event -> {
            act.close();//store the change to database
        });
        stage.setTitle("文档管理");
        stage.show();
        Controller instance = loader.getController();
        instance.init();
        instance.setStage(stage);
        instance.initUser();
    }

}
