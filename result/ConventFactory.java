package result;

public class ConventFactory {

    public static Bean build(Result r) {
        Bean b = new Bean();
        b.setID(r.getId());
        b.setGroup(r.getGroup());
        b.setHost(r.getMaster());
        b.setMember(r.getObject_member());
        b.setMemberCount(r.getObject_count_1());
        b.setNonMember(r.getObject_non_member());
        b.setNonMemberCount(r.getObject_count_2());
        b.setRegion(r.getRegion());
        return b;
    }

    public static Result build(Bean b) {
        Result r = new Result();
        r.setId(b.getID());
        r.setGroup(b.getGroup());
        r.setMaster(b.getHost());
        r.setObject_member(b.getMember());
        r.setObject_count_1(b.getMemberCount());
        r.setObject_non_member(b.getNonMember());
        r.setObject_count_2(b.getNonMemberCount());
        r.setRegion(b.getRegion());
        return r;
    }

}
