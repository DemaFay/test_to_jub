package com.test.demafayz.testapplication.data;

/**
 * Created by demafayz on 26.08.16.
 */
public class Record {
    private long id;
    private long du;
    private String shortName;
    private String bic;


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
}
