package pack;

public class SearchPack {

    private String Region;
    private String Group;
    private String Master;
    private Boolean isAllowLikeInquire = false;
    private String Member;

    public SearchPack() {}

    public SearchPack(String region, String group, String master) {
        Region = region;
        Group = group;
        Master = master;
    }

    public String getRegion() {
        return Region;
    }

    public void setRegion(String region) {
        Region = region;
    }

    public SearchPack setGroup(String group) {
        Group = group;
        return this;
    }

    public SearchPack setMaster(String master) {
        Master = master;
        return this;
    }

    public String getGroup() {
        return Group;
    }

    public String getMaster() {
        return Master;
    }

    public Boolean getAllowLikeInquire() {
        return isAllowLikeInquire;
    }

    public void setAllowLikeInquire(Boolean allowLikeInquire) {
        isAllowLikeInquire = allowLikeInquire;
    }

    public String getMember() {
        return Member;
    }

    public void setMember(String member) {
        Member = member;
    }

    public String getType() {
        return (Region == null ? "0" : "1") + (Group == null ? "0" : "1") + (Master == null ? "0" : "1") + (isAllowLikeInquire ? "0" : "1");
    }

    @Override
    public String toString() {
        return "SearchPack [" +
                "Region='" + Region + '\'' +
                ", Group='" + Group + '\'' +
                ", Master='" + Master + '\'' +
                ", isAllowLikeInquire=" + isAllowLikeInquire +
                ", Member='" + Member + '\'' +
                ']';
    }
}
