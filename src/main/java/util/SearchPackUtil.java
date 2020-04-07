package util;

import pack.SearchPack;

public class SearchPackUtil {

    public static SearchPack build(Boolean select[], String text[]) {
        SearchPack sp = new SearchPack();//创建查询包
        sp.setRegion((!select[0]) ? null : text[0]);//获得区域
        sp.setGroup((!select[1]) ? null : text[1]);
        sp.setMaster((!select[2]) ? null : text[2]);
        sp.setAllowLikeInquire(select[3]);
        sp.setMember((!select[3]) ? null : text[3]);
        return sp;
    }

    public static void print(SearchPack sp) {
        System.out.println(sp);
    }

}
