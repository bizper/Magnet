package core;

import result.Result;

public class ModifierListener {

    Result r;
    Result old;
    Controller c;

    public ModifierListener(Controller c) {
        this.c = c;
    }

    public ModifierListener(Result old, Controller c) {
        this.old = old;
        this.c = c;
    }

    void complete(Result r) {
        this.r = r;
        c.resetValueInTable(old, r);
    }

    void addResult(Result r) {
        this.r = r;
        c.addValueInTable(r);
    }

    Result getResult() {
        return r;
    }

}
