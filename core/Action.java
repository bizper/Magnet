package core;

import core.mybatis.Inquire;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import pack.SearchPack;
import result.Bean;
import result.ConventFactory;
import result.Result;
import user.UserList;
import util.StringUtil;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Action {

    private static Action instance;

    private Action() {}

    public static Action getInstance() {
        if(instance == null) {
            synchronized (Action.class) {
                if(instance == null) {
                    instance = new Action();
                }
            }
        }
        return instance;
    }

    public ObservableList<Result> check(ArrayList<ArrayList<String>> type) {
        if(type == null) return null;
        ArrayList<Result> result = new ArrayList<>();
        String region = type.get(0).get(0);
        region = StringUtil.trim(region);
        for(int j=3; j<type.size(); j++) {
            ArrayList<String> sa = type.get(j);
            Result r = new Result().setProperties(sa);
            if(r != null) {
                r.setRegion(region);
                result.add(r);
            }
        }
        return FXCollections.observableArrayList((result));
    }

    public ArrayList<ArrayList<String>> readFile(File f) {
        return Documents.readFile(f);
    }

    public ObservableList<Result> inquire(SearchPack str) {
        List<Bean> list = Inquire.selectList(str, this);
        List<Result> cache = list.stream().map(ConventFactory::build).collect(Collectors.toList());
        return FXCollections.observableArrayList(cache);
    }

    public void insert(Bean r) {
        Inquire.insert(r, this);
    }

    public void insertList(List<Result> r) {
        Inquire.insertBeanList(r.stream().map(ConventFactory::build).collect(Collectors.toList()), this);
    }

    public void deleteBean(Result r) {
        Inquire.deleteBean(ConventFactory.build(r), this);
    }

    public void updateBean(Result r) {
        Inquire.updateBean(ConventFactory.build(r), this);
    }

    public void close() {
        Inquire.close();
    }

}
