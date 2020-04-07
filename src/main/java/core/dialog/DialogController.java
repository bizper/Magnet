package core.dialog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class DialogController {

    @FXML
    private Label title;

    @FXML
    private Label msg;

    @FXML
    private Button cancel;

    @FXML
    private Button confirm;

    private Consumer<? super Object> cancelAction;

    private Consumer<? super Object> confirmAction;

    private Stage stage;

    @FXML
    public void confirm(ActionEvent e) {
        if(confirmAction != null) confirmAction.accept(e);
        if(stage != null) stage.hide();
    }

    @FXML
    public void cancel(ActionEvent e) {
        if(cancelAction != null) cancelAction.accept(e);
        if(stage != null) stage.hide();
    }

    public void setMsg(Stage stage, String arr[]) {
        this.stage = stage;
        if(arr.length != 4) return;//0:title 1:msg 2:cancel 3:confirm
        title.setText(arr[0]);
        msg.setText(arr[1]);
        cancel.setText(arr[2]);
        confirm.setText(arr[3]);
    }

    public void initAction(Consumer<? super Object> cancelAction, Consumer<? super Object> confirmAction) {
        this.cancelAction = cancelAction;
        this.confirmAction = confirmAction;
    }


}
