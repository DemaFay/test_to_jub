package com.test.demafayz.testapplication.database.data;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by demafayz on 26.08.16.
 */
public class RealmBicCode extends RealmObject {

    private String name;
    private RealmList<RealmRecord> records;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RealmList<RealmRecord> getRecords() {
        return records;
    }

    public void setRecords(RealmList<RealmRecord> records) {
        this.records = records;
    }
}
