package com.test.demafayz.testapplication.utils.sync;

/**
 * Created by demafayz on 25.08.16.
 */
public class ApiHelper {

    private static final String GET_BANKS_URL = "http://www.cbr.ru/scripts/XML_bic.asp";

    public static String getBanks() {
        String result = ApiSync.getBaseResponse(GET_BANKS_URL);
        return result;
    }
}
