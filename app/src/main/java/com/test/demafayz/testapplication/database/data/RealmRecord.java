package com.test.demafayz.testapplication.database.data;

import io.realm.RealmObject;

/**
 * Created by demafayz on 26.08.16.
 */
public class RealmRecord extends RealmObject {

    private long id;
    private long du;
    private String shortName;
    private String bic;
    private RealmBicCode bicCode;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getDu() {
        return du;
    }

    public void setDu(long du) {
        this.du = du;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public RealmBicCode getBicCode() {
        return bicCode;
    }

    public void setBicCode(RealmBicCode bicCode) {
        this.bicCode = bicCode;
    }
}
