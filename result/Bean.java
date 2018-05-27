package result;

public class Bean {

    private String ID;

    private String Groups;

    private String Host;

    private String Member;

    private int MemberCount;

    private String NonMember;

    private int NonMemberCount;

    private String Region;

    public String getRegion() {
        return Region;
    }

    public void setRegion(String Region) {
        this.Region = Region;
    }

    public String getID() {
        return ID;
    }

    public String getGroup() {
        return Groups;
    }

    public String getHost() {
        return Host;
    }

    public String getMember() {
        return Member;
    }

    public String getMemberCount() {
        return String.valueOf(MemberCount);
    }

    public String getNonMember() {
        return NonMember;
    }

    public String getNonMemberCount() {
        return String.valueOf(NonMemberCount);
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public void setGroup(String group) {
        Groups = group;
    }

    public void setHost(String master) {
        Host = master;
    }

    public void setMember(String member) {
        Member = member;
    }

    public void setMemberCount(String memberCount) {
        try {
            MemberCount = (int) Double.parseDouble(memberCount.isEmpty() ? "1" : memberCount);
        } catch(NumberFormatException nfe) {
            MemberCount = 1;
        }
    }

    public void setNonMember(String nonMember) {
        NonMember = nonMember;
    }

    public void setNonMemberCount(String nonMemberCount) {
        try {
            NonMemberCount = (int) Double.parseDouble(nonMemberCount.isEmpty() ? "0" : nonMemberCount);
        } catch(NumberFormatException nfe) {
            NonMemberCount = 1;
        }
    }

    @Override
    public String toString() {
        return "Bean [" +
                "ID='" + ID + '\'' +
                ", Groups='" + Groups + '\'' +
                ", Host='" + Host + '\'' +
                ", Member='" + Member + '\'' +
                ", MemberCount=" + MemberCount +
                ", NonMember='" + NonMember + '\'' +
                ", NonMemberCount=" + NonMemberCount +
                ", Region='" + Region + '\'' +
                ']';
    }
}
