package util;

import result.Bean;

import java.util.List;

public class StructUtil {

    public static String getListString(List<? extends Bean> list) {
        StringBuffer cache = new StringBuffer();
        cache.append("List").append(" [").append(list.get(0)).append("]");
        return cache.toString();
    }

}
