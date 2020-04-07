package core;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import result.Result;

import java.util.Arrays;

public class ModifierController {

    private Stage stage;
    private ModifierListener ml;
    private boolean isAppend = false;

    private char[] separators = new char[]{'，', '、', '：', '/', '；'};

    void init() {
        separator.getItems().addAll('，', '、', '：', '/', '；');
        separator.setValue('、');
        separator1.getItems().addAll('，', '、', '：', '/', '；');
        separator1.setValue('、');
    }

    private String ID;

    private int table_id;

    @FXML
    private TextField Group;

    @FXML
    private TextField Master;

    @FXML
    private TextField Member;

    @FXML
    private TextField NonMember;

    @FXML
    private TextField Region;

    @FXML
    private ChoiceBox<Character> separator;

    @FXML
    private ChoiceBox<Character> separator1;

    @FXML
    private CheckBox allow_separator;

    @FXML
    private CheckBox allow_separator1;

    @FXML
    void complete() {
        if(stage == null) return;
        if(Group.getText().isEmpty() || Master.getText().isEmpty() || Region.getText().isEmpty()) {
            stage.hide();
            return;
        }
        if(isAppend) ml.addResult(createResult()); else ml.complete(createResult());
        stage.hide();
    }

    private Result createResult() {
        Result r = new Result();
        r.setId(ID);
        r.setGroup(Group.getText());
        r.setMaster(Master.getText());
        String member = Member.getText();
        r.setObject_member(member.isEmpty() ? Master.getText() : member);//如果为空的话则将户主加入
        char c = allow_separator.isSelected() ? separator.getSelectionModel().selectedItemProperty().get() : separators[1];//判断是否选择使用自定义的分隔符，未选择则默认使用顿号
        int count = member.split(String.valueOf(c)).length;//至少为1
        r.setObject_count_1(String.valueOf(count));
        member = NonMember.getText();
        r.setObject_non_member(member);
        c = allow_separator1.isSelected() ? separator1.getSelectionModel().selectedItemProperty().get() : separators[1];
        count = member.isEmpty() ? 0 : member.split(String.valueOf(c)).length;//判断是否为空，为空的话则为0
        r.setObject_count_2(String.valueOf(count));
        r.setRegion(Region.getText());
        r.setTable_id(table_id);
        return r;
    }

    @FXML
    void cancel() {
        if(stage == null) return;
        stage.hide();
    }

    @FXML
    void allowSeparator() {
        separator.setDisable(!allow_separator.isSelected());
    }

    @FXML
    void allowSeparator1() {
        separator1.setDisable(!allow_separator1.isSelected());
    }

    void setResult(Result r) {
        if(r == null) return;
        this.table_id = r.getTable_id();
        this.ID = r.getId();
        Group.setText(r.getGroup());
        Master.setText(r.getMaster());
        Member.setText(r.getObject_member());
        NonMember.setText(r.getObject_non_member());
        Region.setText(r.getRegion());
    }

    void setIsAppend(Boolean b) {
        this.isAppend = b;
    }

    void setStage(Stage s) {
        stage = s;
    }

    void addListener(ModifierListener ml) {
        this.ml = ml;
    }

}
