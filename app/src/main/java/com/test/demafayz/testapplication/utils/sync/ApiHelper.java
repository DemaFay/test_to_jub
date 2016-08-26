package com.test.demafayz.testapplication.utils.sync;

/**
 * Created by demafayz on 25.08.16.
 */
public class ApiHelper {

    private static final String GET_BANKS_URL = "http://www.cbr.ru/scripts/XML_bic.asp";
    private static final String GET_BANK_INFO_PREFIX = "http://htmlweb.ru/service/api.php?bic=";
    private static final String GET_BANK_INF_SUFFIX = "&json";

    public static String getBanks() {
        String result = ApiSync.getBaseResponse(GET_BANKS_URL);
        return result;
    }

    public static String getBankInfo(String bic) {
        String result = ApiSync.getBaseResponse(GET_BANK_INFO_PREFIX + bic + GET_BANK_INF_SUFFIX);
        return result;
    }
}
