package core;

import constant.STRING;
import core.dialog.DialogUtil;
import core.password.PasswordWin;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import pack.SearchPack;
import result.Result;
import user.UserManager;
import user.permission.PERMISSION;
import util.SearchPackUtil;
import static core.PostInterceptor.handle;

import java.io.File;
import java.util.List;

public class Controller {

    private Action act = Action.getInstance();

    private Stage stage;

    @FXML
    private CheckBox setting_allow_inquire_cache;

    @FXML
    private Hyperlink contact_author;

    @FXML
    private Tab sheet_inquire;

    @FXML
    private Label lastTime;

    @FXML
    private CheckBox inquire_allow_id;

    @FXML
    private TextField inquire_text_id;

    @FXML
    private CheckBox inquire_allow_group;

    @FXML
    private TextField inquire_text_group;

    @FXML
    private CheckBox inquire_allow_master;

    @FXML
    private TextField inquire_text_master;

    @FXML
    private CheckBox allow_inquire_like;

    @FXML
    private TextField inquire_text_member;

    @FXML
    private Label user_title;

    @FXML
    private TableView<Result> sheet_inquire_show;

    @FXML
    private TableColumn<String, Result> sheet_id;

    @FXML
    private TableColumn<String, Result> sheet_group;

    @FXML
    private TableColumn<String, Result> sheet_master;

    @FXML
    private TableColumn<String, Result> sheet_member;

    @FXML
    private TableColumn<String, Result> sheet_count_1;

    @FXML
    private TableColumn<String, Result> sheet_non_member;

    @FXML
    private TableColumn<String, Result> sheet_count_2;

    @FXML
    private ProgressBar status_progress;

    @FXML
    private TextField input_file_text;

    @FXML
    private TableView<Result> sheet_insert_show;

    @FXML
    private TableColumn<String, Result> sheet_id1;

    @FXML
    private TableColumn<String, Result> sheet_group1;

    @FXML
    private TableColumn<String, Result> sheet_master1;

    @FXML
    private TableColumn<String, Result> sheet_member1;

    @FXML
    private TableColumn<String, Result> sheet_count_11;

    @FXML
    private TableColumn<String, Result> sheet_non_member1;

    @FXML
    private TableColumn<String, Result> sheet_count_21;

    @FXML
    private TableColumn<String, Result> sheet_region1;

    @FXML
    private TableColumn<String, Result> sheet_region;

    @FXML
    private Label status_text;

    @FXML
    private Label user_level;

    @FXML
    void Inquire(ActionEvent event) {
        if(!UserManager.validatePermission(PERMISSION.TABLE_INQUIRE_VALUE)) {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
            return;
        }
        Boolean select[] = new Boolean[]{inquire_allow_id.isSelected(), inquire_allow_group.isSelected(), inquire_allow_master.isSelected(), allow_inquire_like.isSelected()};
        String text[] = new String[]{inquire_text_id.getText(), inquire_text_group.getText(), inquire_text_master.getText(), inquire_text_member.getText()};
        if(!select[0] && !select[1] && !select[2] && !select[3]) {
            setStatus(STRING.EMPTY_INQUIRE);
            return;
        }
        if(text[0].isEmpty() && text[1].isEmpty() && text[2].isEmpty() && text[3].isEmpty()) {
            setStatus(STRING.EMPTY_INQUIRE);
            return;
        }
        SearchPack sp = SearchPackUtil.build(select, text);
        sheet_inquire_show.setItems(act.inquire(sp));
        setStatus("查询完成！");
    }

    @FXML
    void change(ActionEvent e) {
        if(UserManager.validatePermission(PERMISSION.TABLE_MODIFIER_VALUE)) {
            TableViewSelectionModel<Result> r = sheet_inquire_show.getSelectionModel();
            Platform.runLater(() -> {
                try {
                    ModifierWin mw = new ModifierWin();
                    mw.start(new Stage());
                    mw.setMainStage(stage);
                    mw.getController().setResult(r.getSelectedItem());
                    ModifierListener ml = new ModifierListener(r.getSelectedItem(), this);
                    mw.getController().addListener(ml);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        }
    }

    @FXML
    void remove(ActionEvent e) {
        if(UserManager.validatePermission(PERMISSION.TABLE_DELETE_VALUE)) {
            DialogUtil.getInstance(stage, STRING.WARNING, "是否确认删除此条目？", "取消", "删除", null, (confirm) -> {
                Result r = sheet_inquire_show.getFocusModel().getFocusedItem();
                sheet_inquire_show.getItems().remove(r);
                if(UserManager.validatePermission(PERMISSION.DATABASE_DELETE_VALUE)) {
                    act.deleteBean(r);
                    setStatus("更新至数据库！");
                } else DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
            });
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        }
    }

    /**
     * equals isAllowInquireId
     * @param event
     */
    @FXML
    void isAllowInquireName(ActionEvent event) {
        inquire_text_id.setDisable(!inquire_allow_id.selectedProperty().getValue());
    }

    /**
     * equals isAllowInquireGroup
     * @param event
     */
    @FXML
    void isAllowInquireSex(ActionEvent event) {
        inquire_text_group.setDisable(!inquire_allow_group.selectedProperty().getValue());
    }

    /**
     * equals isAllowInquireMaster
     * @param event
     */
    @FXML
    void isAllowInqureProperty(ActionEvent event) {
        inquire_text_master.setDisable(!inquire_allow_master.selectedProperty().getValue());
    }

    @FXML
    void isAllowInquireLike(ActionEvent event) {
        inquire_text_member.setDisable(!allow_inquire_like.selectedProperty().getValue());
    }


    @FXML
    void inputFileChoose(ActionEvent event) {
        if(!UserManager.validatePermission(PERMISSION.TABLE_APPEND_VALUE)) {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
            return;
        }
        FileChooser fc = new FileChooser();
        fc.setInitialDirectory(new File("./"));
        File f = fc.showOpenDialog(stage);
        if(f == null) {
            setStatus("文件不存在！");
            return;
        }
        setStatus("获取文件，开始读取！");
        input_file_text.setText(f.getAbsolutePath());
        sheet_insert_show.setItems(act.check(act.readFile(f)));
        setStatus("读取完成！");
    }

    @FXML
    void insertIntoDatabase(ActionEvent event) {
        if(!UserManager.validatePermission(PERMISSION.DATABASE_INSERT_VALUE)) {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        } else {
            List<Result> list = sheet_insert_show.getItems();
            act.insertList(list);
            setStatus("导入成功！");
        }
    }

    @FXML
    void createNewResult(ActionEvent ae) {
        if(UserManager.validatePermission(PERMISSION.TABLE_REGISTER_VALUE)) {
            Platform.runLater(() -> {
                try {
                    ModifierWin mw = new ModifierWin();
                    mw.start(new Stage());
                    mw.setMainStage(stage);
                    ModifierListener ml = new ModifierListener(this);
                    mw.getController().addListener(ml);
                    mw.setIsAppend(true);
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
            });
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        }
    }

    @FXML
    void changePassword(ActionEvent ae) {
        if(UserManager.validatePermission(PERMISSION.ACCOUNT_CHANGE_PASSWORD)) {
            Platform.runLater(() -> {
                try {
                    PasswordWin pw = new PasswordWin();
                    pw.setMainStage(stage);
                    pw.start(new Stage());
                } catch(Exception e) {
                    e.printStackTrace();
                }
            });
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_CHANGE_PASSWORD);
        }
    }

    @FXML
    void exitLogin(ActionEvent ae) {
        if(UserManager.validatePermission(PERMISSION.ACCOUNT_EXIT_LOGIN)) {
            LoginWin lw = new LoginWin();
            try {
                lw.start(new Stage());
                this.stage.hide();
                UserManager.exitLogin();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        }
    }

    /*******************************************************************************
     *                METHODS IN CONTROLLER WITHOUT CONTROLS.                      *
     *                                                                             *
     *******************************************************************************/

    void init() {
        sheet_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        sheet_group.setCellValueFactory(new PropertyValueFactory<>("group"));
        sheet_master.setCellValueFactory(new PropertyValueFactory<>("master"));
        sheet_member.setCellValueFactory(new PropertyValueFactory<>("object_member"));
        sheet_count_1.setCellValueFactory(new PropertyValueFactory<>("object_count_1"));
        sheet_non_member.setCellValueFactory(new PropertyValueFactory<>("object_non_member"));
        sheet_count_2.setCellValueFactory(new PropertyValueFactory<>("object_count_2"));
        sheet_region.setCellValueFactory(new PropertyValueFactory<>("region"));
        sheet_id1.setCellValueFactory(new PropertyValueFactory<>("id"));
        sheet_group1.setCellValueFactory(new PropertyValueFactory<>("group"));
        sheet_master1.setCellValueFactory(new PropertyValueFactory<>("master"));
        sheet_member1.setCellValueFactory(new PropertyValueFactory<>("object_member"));
        sheet_count_11.setCellValueFactory(new PropertyValueFactory<>("object_count_1"));
        sheet_non_member1.setCellValueFactory(new PropertyValueFactory<>("object_non_member"));
        sheet_count_21.setCellValueFactory(new PropertyValueFactory<>("object_count_2"));
        sheet_region1.setCellValueFactory(new PropertyValueFactory<>("region"));
    }

    void setStatus(String status) {
        status_text.setText("Status:" + status);
    }

    void setStage(Stage s) {
        stage = s;
    }

    void resetValueInTable(Result old, Result r) {
        if(UserManager.validatePermission(PERMISSION.TABLE_MODIFIER_VALUE) && UserManager.validatePermission(PERMISSION.TABLE_DELETE_VALUE)) {
            sheet_inquire_show.getItems().add(r.getTable_id(), r);
            sheet_inquire_show.getItems().remove(old);
            if(UserManager.validatePermission(PERMISSION.DATABASE_UPDATE_VALUE)) {
                act.updateBean(r);
                setStatus("更新至数据库！");
            } else DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
        } else DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
    }

    void addValueInTable(Result r) {
        if(UserManager.validatePermission(PERMISSION.TABLE_REGISTER_VALUE)) {
            sheet_insert_show.getItems().add(sheet_insert_show.getItems().size(), r);
        } else DialogUtil.getInstance(stage, STRING.WARNING, STRING.NO_PERMISSION_WARNING);
    }

    void initUser() {
        user_title.setText(UserManager.getUsername());
        user_level.setText(UserManager.getUserLevelDesc());
        lastTime.setText(UserManager.getUserDate());
    }

}
