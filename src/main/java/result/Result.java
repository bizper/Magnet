package result;

import javafx.beans.property.SimpleStringProperty;
import util.NumberUtil;

import java.text.NumberFormat;
import java.util.List;

public class Result {

    private SimpleStringProperty id = new SimpleStringProperty();

    private SimpleStringProperty group = new SimpleStringProperty();

    private SimpleStringProperty master = new SimpleStringProperty();

    private SimpleStringProperty object_member = new SimpleStringProperty();

    private SimpleStringProperty object_count_1 = new SimpleStringProperty("0");

    private SimpleStringProperty object_non_member = new SimpleStringProperty();

    private SimpleStringProperty object_count_2 = new SimpleStringProperty("0");

    private SimpleStringProperty region = new SimpleStringProperty();

    private int table_id;

    public Result() {}

    public String getId() {
        return id.get();
    }

    public String getGroup() {
        return group.get();
    }

    public String getMaster() {
        return master.get();
    }

    public String getObject_member() {
        return object_member.get();
    }

    public String getObject_count_1() {
        return object_count_1.get();
    }

    public String getObject_non_member() {
        return object_non_member.get();
    }

    public String getObject_count_2() {
        return object_count_2.get();
    }

    public void setId(String id) {
        this.id.set(id);
    }

    public void setGroup(String group) {
        this.group.set(group);
    }

    public void setMaster(String master) {
        this.master.set(master);
    }

    public void setObject_member(String object_member) {
        this.object_member.set(object_member);
    }

    public void setObject_count_1(String object_count_1) {
        this.object_count_1.set(object_count_1);
    }

    public void setObject_non_member(String object_non_member) {
        this.object_non_member.set(object_non_member);
    }

    public void setObject_count_2(String object_count_2) {
        this.object_count_2.set(object_count_2);
    }

    public String getRegion() {
        return region.get();
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

    public int getTable_id() {
        return table_id;
    }

    public void setTable_id(int table_id) {
        this.table_id = table_id;
    }

    public Result setProperties(List<String> list) {
        try {
            if(!(list.size() > 0)) return null;
            if(list.size() <= 5) {
                setId(list.get(0).replace(" ", ""));
                setGroup(list.get(1).replace(" ", ""));
                setMaster(list.get(2).replace(" ", ""));
                setObject_member(list.get(3).replace(" ", ""));
                setObject_non_member(list.get(4).replace(" ", ""));
            } else {
                setId(list.get(0).replace(" ", ""));
                setGroup(list.get(1).replace(" ", ""));
                setMaster(list.get(2).replace(" ", ""));
                setObject_member(list.get(3).replace(" ", ""));
                setObject_count_1(NumberUtil.isNumber(list.get(4).replace(" ", "")) ? list.get(4).replace(" ", "") : "0");
                setObject_non_member(list.get(5).replace(" ", ""));
                setObject_count_2(NumberUtil.isNumber(list.get(6).replace(" ", "")) ? list.get(6).replace(" ", "") : "0");
            }
        } catch(IndexOutOfBoundsException ioe) {
            list.forEach(System.out::println);
        }
        return this;
    }
    /*
    这是一个程序注释
    这是多行的
     */
    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer("Result [");
        sb.append("ID=").append(getId()).append("\n")
                .append("Group=").append(getGroup()).append("\n")
                .append("Master=").append(getMaster()).append("\n")
                .append("Member=").append(getObject_member()).append("\n")
                .append("MemberCount=").append(getObject_count_1()).append("\n")
                .append("NonMember=").append(getObject_non_member()).append("\n")
                .append("NonMemberCount=").append(getObject_count_2()).append("\n")
                .append("Region=").append(getRegion()).append("\n");
        sb.append("]");
        return sb.toString();
    }

}
