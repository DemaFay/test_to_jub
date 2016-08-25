package com.test.demafayz.testapplication.data;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by demafayz on 26.08.16.
 */
public class BicCode {
    private String name;
    private List<Record> records;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }

    public void addRecord(Record record) {
        if (records == null) records = new ArrayList<>();
        records.add(record);
    }

    public Record getRecord(int position) {
        if (records.size() < position || position < 0) return null;
        return records.get(position);
    }
}
