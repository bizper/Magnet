package core.dialog;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Consumer;

public class DialogWin extends Application {

    private Stage main;

    private String title = "";

    private String msg = "";

    private String cancel = "";

    private String confirm = "";

    private DialogController controller;

    public void setDialog(String title, String msg, String cancel, String confirm) {
        this.title = title;
        this.msg = msg;
        this.cancel = cancel;
        this.confirm = confirm;
    }

    public void setMainStage(Stage main) {
        this.main = main;
    }

    public void initAction(Consumer<? super Object> cancelAction, Consumer<? super Object> confirmAction) {
        controller.initAction(cancelAction, confirmAction);
    }

    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/core/dialog/dialog.fxml"));
        Pane p = null;
        try {
            p = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Scene login = new Scene(p, 368, 247);
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/resource/title.jpg")));
        stage.setScene(login);
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.initOwner(main);
        stage.setResizable(false);
        stage.setTitle(title);
        stage.show();
        this.controller = loader.getController();
        controller.setMsg(stage, new String[]{title, msg, cancel, confirm});
    }

}
