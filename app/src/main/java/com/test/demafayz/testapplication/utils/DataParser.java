package com.test.demafayz.testapplication.utils;

import android.text.TextUtils;
import android.util.Log;

import com.test.demafayz.testapplication.data.BankInfo;
import com.test.demafayz.testapplication.data.BicCode;
import com.test.demafayz.testapplication.data.Record;

import org.json.JSONException;
import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;

/**
 * Created by demafayz on 25.08.16.
 */
public class DataParser {

    public static BicCode parseBanks(String response) {

        String tmp = "";

        BicCode bicCode = null;
        Record record = null;
        boolean shortName = false;
        boolean bic = false;

        try {
            XmlPullParser xpp = prepareXpp(response);
            while (xpp.getEventType() != XmlPullParser.END_DOCUMENT) {
                switch (xpp.getEventType()) {
                    case XmlPullParser.START_DOCUMENT:
                        AppUtil.dLog(DataParser.class, "START_DOC");
                        break;
                    case XmlPullParser.START_TAG:
                        AppUtil.dLog(DataParser.class, "START_TAG: name = " + xpp.getName()
                                + ", depth = " + xpp.getDepth() + ", attrCount = "
                                + xpp.getAttributeCount());
                        if (xpp.getName().equals("BicCode")) {
                            bicCode = new BicCode();
                        } else if (xpp.getName().equals("Record")) {
                            record = new Record();
                        } else if (xpp.getName().equals("ShortName")) {
                            shortName = true;
                        } else if (xpp.getName().equals("Bic")) {
                            bic = true;
                        }
                        for (int i = 0; i < xpp.getAttributeCount(); i++) {
                            tmp = tmp + xpp.getAttributeName(i) + " = "
                                    + xpp.getAttributeValue(i) + ", ";
                            if (xpp.getAttributeName(i).equals("name")) {
                                bicCode.setName(xpp.getAttributeValue(i));
                            } else if (xpp.getAttributeName(i).equals("ID")) {
                                record.setId(Long.valueOf(xpp.getAttributeValue(i)));
                            } else if (xpp.getAttributeName(i).equals("DU")) {
                                record.setDu(DateUtil.stringDateToLong(xpp.getAttributeValue(i), null));
                            }
                        }
                        if (!TextUtils.isEmpty(tmp))
                            AppUtil.dLog(DataParser.class, "Attributes: " + tmp);
                        tmp = "";
                        break;
                    case XmlPullParser.END_TAG:
                        AppUtil.dLog(DataParser.class, "END_TAG: name = " + xpp.getName());
                        if (xpp.getName().equals("ShortName")) {
                            shortName = false;
                        } else if (xpp.getName().equals("Bic")) {
                            bic = false;
                        } else if (xpp.getName().equals("Record")) {
                            bicCode.addRecord(record);
                            record = null;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        AppUtil.dLog(DataParser.class, "text = " + xpp.getText());
                        if (shortName) {
                            record.setShortName(xpp.getText());
                        } else if (bic) {
                            record.setBic(xpp.getText());
                        }
                        break;
                    default:
                        break;
                }
                xpp.next();
            }
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bicCode;
    }

    private static XmlPullParser prepareXpp(String response) throws XmlPullParserException {
        XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
        factory.setNamespaceAware(true);
        XmlPullParser xpp = factory.newPullParser();
        xpp.setInput(new StringReader(response));
        return xpp;
    }

    public static BankInfo parseBankInfo(String request) {
        BankInfo bankInfo = null;
        try {
            bankInfo = new BankInfo();
            JSONObject root = new JSONObject(request);
            String address = root.getString("adress");
            String ks = root.getString("ks");
            String telephone = root.getString("tel");
            String name = root.getString("name");
            String upd = root.getString("upd");
            String bic = root.getString("bic");
            String city = root.getString("city");
            bankInfo.setAddress(address);
            bankInfo.setKs(ks);
            bankInfo.setTelephone(telephone);
            bankInfo.setName(name);
            bankInfo.setUpd(DateUtil.stringDateToLong(upd, null));
            bankInfo.setBic(bic);
            bankInfo.setCity(city);

        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return bankInfo;
    }
}
