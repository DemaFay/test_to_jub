package com.test.demafayz.testapplication.database;

import com.test.demafayz.testapplication.data.BankInfo;
import com.test.demafayz.testapplication.data.BicCode;
import com.test.demafayz.testapplication.data.Record;
import com.test.demafayz.testapplication.database.data.RealmBankInfo;
import com.test.demafayz.testapplication.database.data.RealmBicCode;
import com.test.demafayz.testapplication.database.data.RealmRecord;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmList;

/**
 * Created by demafayz on 26.08.16.
 */
public class DBHelper {

    public static void saveBicCode(BicCode bicCode) {

        Realm realm = Realm.getDefaultInstance();
        realm.where(RealmBicCode.class).findAll().deleteAllFromRealm();
        realm.where(RealmRecord.class).findAll().deleteAllFromRealm();
        realm.beginTransaction();
        populateRealmBicCode(bicCode, realm);
        realm.commitTransaction();
        realm.close();
    }

    public static boolean bankListIsDownloaded() {
        int count;
        Realm realm = Realm.getDefaultInstance();
        count = realm.where(RealmBicCode.class).findAll().size();
        realm.close();
        if (count == 0) {
            return true;
        } else {
            return false;
        }
    }

    private static RealmBicCode populateRealmBicCode(BicCode bicCode, Realm realm) {

        List<Record> records = bicCode.getRecords();

        RealmBicCode realmBicCode = realm.createObject(RealmBicCode.class);
        realmBicCode.setName(bicCode.getName());
        RealmList<RealmRecord> realmRecords = new RealmList<>();
        for (int i = 0; i < records.size(); i++) {
            realmRecords.add(populateRealmRecord(realmBicCode, records.get(i), realm));
        }
        realmBicCode.setRecords(realmRecords);
        return realmBicCode;
    }

    private static RealmRecord populateRealmRecord(RealmBicCode realmBicCode, Record record, Realm realm) {
        RealmRecord realmRecord = realm.createObject(RealmRecord.class);
        realmRecord.setBic(record.getBic());
        realmRecord.setId(record.getId());
        realmRecord.setDu(record.getDu());
        realmRecord.setShortName(record.getShortName());
        realmRecord.setBicCode(realmBicCode);
        return realmRecord;
    }

    public static BicCode getBanks() {
        Realm realm = Realm.getDefaultInstance();
        RealmBicCode realmBicCode = realm.where(RealmBicCode.class).findFirst();
        BicCode bicCode = populateBicCode(realmBicCode);
        realm.close();
        return bicCode;
    }

    private static BicCode populateBicCode(RealmBicCode realmBicCode) {
        BicCode bicCode = new BicCode();
        bicCode.setName(realmBicCode.getName());

        RealmList<RealmRecord> realmRecords = realmBicCode.getRecords();
        for (int i = 0; i < realmRecords.size(); i++) {
            bicCode.addRecord(populateRecord(realmBicCode.getRecords().get(i)));
        }
        return bicCode;
    }

    private static Record populateRecord(RealmRecord realmRecord) {
        Record record = new Record();
        record.setId(realmRecord.getId());
        record.setDu(realmRecord.getDu());
        record.setBic(realmRecord.getBic());
        record.setShortName(realmRecord.getShortName());
        return record;
    }

    public static void saveBankInfo(BankInfo bankInfo) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        RealmBankInfo realmBankInfo = populateRealmBankInfo(realm, bankInfo);
        realm.commitTransaction();
        realm.close();
    }

    private static RealmBankInfo populateRealmBankInfo(Realm realm, BankInfo bankInfo) {

        realm.where(RealmBankInfo.class).equalTo("bic", bankInfo.getBic()).findAll().deleteAllFromRealm();

        RealmBankInfo realmBankInfo = realm.createObject(RealmBankInfo.class);
        realmBankInfo.setAddress(bankInfo.getAddress());
        realmBankInfo.setKs(bankInfo.getKs());
        realmBankInfo.setTelephone(bankInfo.getTelephone());
        realmBankInfo.setName(bankInfo.getName());
        realmBankInfo.setUpd(bankInfo.getUpd());
        realmBankInfo.setBic(bankInfo.getBic());
        realmBankInfo.setCity(bankInfo.getCity());
        return realmBankInfo;
    }

    public static boolean bankInfoIsDownloaded(String bic) {
        int count;
        Realm realm = Realm.getDefaultInstance();
        count = realm.where(RealmBankInfo.class).equalTo("bic", bic).findAll().size();
        realm.close();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public static BankInfo getBankInfo(String bic) {
        BankInfo bankInfo;
        Realm realm = Realm.getDefaultInstance();
        bankInfo = populateBankInfo(realm, bic);
        realm.close();
        return bankInfo;
    }

    private static BankInfo populateBankInfo(Realm realm, String bic) {
        RealmBankInfo realmBankInfo = realm.where(RealmBankInfo.class).equalTo("bic", bic).findFirst();
        BankInfo bankInfo = new BankInfo();
        bankInfo.setAddress(realmBankInfo.getAddress());
        bankInfo.setKs(realmBankInfo.getKs());
        bankInfo.setTelephone(realmBankInfo.getTelephone());
        bankInfo.setName(realmBankInfo.getName());
        bankInfo.setUpd(realmBankInfo.getUpd());
        bankInfo.setBic(realmBankInfo.getBic());
        bankInfo.setCity(realmBankInfo.getCity());
        return bankInfo;
    }
}
