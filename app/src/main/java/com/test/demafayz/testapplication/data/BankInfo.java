package com.test.demafayz.testapplication.data;

import com.test.demafayz.testapplication.database.data.RealmRecord;

/**
 * Created by demafayz on 26.08.16.
 */
public class BankInfo {

    private String address;
    private String ks;
    private String telephone;
    private String name;
    private long upd;
    private String bic;
    private String city;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getKs() {
        return ks;
    }

    public void setKs(String ks) {
        this.ks = ks;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getUpd() {
        return upd;
    }

    public void setUpd(long upd) {
        this.upd = upd;
    }

    public String getBic() {
        return bic;
    }

    public void setBic(String bic) {
        this.bic = bic;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
