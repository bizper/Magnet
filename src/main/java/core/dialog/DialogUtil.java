package core.dialog;

import constant.STRING;
import javafx.application.Platform;
import javafx.stage.Stage;

import java.util.function.Consumer;

public class DialogUtil {

    private static DialogWin dw;

    public static boolean getInstance(Stage main, String msg, String cancel, String confirm, Consumer<? super Object> cancelAction, Consumer<? super Object> confirmAction) {
        return DialogUtil.getInstance(main, STRING.DEFAULT_TITLE, msg, cancel, confirm, cancelAction, confirmAction);
    }

    public static boolean getInstance(Stage main, String msg, String cancel, String confirm) {
        return DialogUtil.getInstance(main, msg, cancel, confirm, null, null);
    }

    public static boolean getInstance(Stage main, String title, String msg, String cancel, String confirm) {
        return DialogUtil.getInstance(main, title, msg, cancel, confirm, null, null);
    }

    public static boolean getInstance(Stage main, String title, String msg) {
        return DialogUtil.getInstance(main, title, msg, STRING.DEFAULT_CANCEL, STRING.DEFAULT_CONFIRM, null, null);
    }

    public static boolean getInstance(Stage main, String msg) {
        return DialogUtil.getInstance(main, STRING.DEFAULT_TITLE, msg, STRING.DEFAULT_CANCEL, STRING.DEFAULT_CONFIRM, null, null);
    }

    public static boolean getInstance(Stage main, String title, String msg, String cancel, String confirm, Consumer<? super Object> cancelAction, Consumer<? super Object> confirmAction) {
        if(main == null) return false;
        Platform.runLater(() -> {
            try {
                if(dw == null) dw = new DialogWin();
                dw.setDialog(title, msg, cancel, confirm);
                dw.setMainStage(main);
                dw.start(new Stage());
                dw.initAction(cancelAction, confirmAction);
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
        return true;
    }

}
