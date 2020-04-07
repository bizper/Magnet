package core;

import constant.PATH;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class ModifierWin extends Application {

    private Stage main;
    private ModifierController this_c;

    public void setMainStage(Stage main) {
        this.main = main;
    }

    public void setIsAppend(Boolean b) { this_c.setIsAppend(b); }

    public ModifierController getController() {
        return this_c;
    }

    @Override
    public void start(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PATH.MODIFIER_FXML));
        Pane p;
        try {
            p = loader.load();
            Scene mod = new Scene(p, 400, 300);
            stage.setScene(mod);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.setResizable(false);
            stage.initOwner(main);
            stage.getIcons().add(new Image(getClass().getResourceAsStream(PATH.ICON)));
            stage.setTitle("修改值");
            stage.setOnCloseRequest(e -> main.requestFocus());
            stage.show();
            this_c = loader.getController();
            this_c.setStage(stage);
            this_c.init();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
