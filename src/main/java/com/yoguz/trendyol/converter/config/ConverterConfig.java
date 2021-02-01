package com.yoguz.trendyol.converter.config;

public class ConverterConfig {

    public static final String WEB_BASE = "https://www.trendyol.com";
    public static final String WEB_PRODUCT = "/brand/name-p-";
    public static final String WEB_SEARCH = "/tum--urunler?q=";
    public static final String WEB_BOUTIQUE_PARAM = "boutiqueId=";
    public static final String WEB_MERCHANT_PARAM = "merchantId=";

    public static final String DL_BASE = "ty://?Page=";
    public static final String DL_HOME = "Home";
    public static final String DL_PRODUCT = "Product&ContentId=";
    public static final String DL_SEARCH = "Search&Query=";
    public static final String DL_CAMPAIGN_PARAM = "CampaignId=";
    public static final String DL_MERCHANT_PARAM = "MerchantId=";

    public static final String REGEX_WEB_PRODUCT_PAGE = "\\/[^\\/]+\\/[^\\/]*-p-[0-9]+[^\\/]*";
    public static final String REGEX_WEB_SEARCH_PAGE = "\\/tum--urunler\\?q=[^\\/]+";
    public static final String REGEX_DL_PRODUCT_PAGE = "Product\\&ContentId=[0-9]+[^\\/]*";
    public static final String REGEX_DL_SEARCH_PAGE = "Search\\&Query=[^\\/]+";
    public static final String REGEX_SEARCH = "[a-zA-Z%0-9]+";
    public static final String REGEX_ID = "[0-9]+";


    /**
     * Returns the value of the requested parameter in a String of parameters
     * The returned value can be null if the value does not match REGEX_ID pattern
     * Value will be empty if the parameter does not exist in given parameter String
     *
     * @param params a String of parameters separated with ampersand character
     * @param key requested parameter key
     * @return null if the parameter does not match REGEX_ID pattern
     *         else returns the value of the parameter if the parameter exist
     *         if parameter does not exist, returns empty String
     */
    public static String getParamValue(String params, String key) {
        if (params.contains(key)) {
            String rest = params.substring(params.indexOf(key));
            int lastIndex = rest.length();
            if (rest.contains("&")) {
                lastIndex = rest.indexOf("&");
            }
            String value = rest.substring(key.length(), lastIndex);

            return value.matches(REGEX_ID) ? value : null;
        }

        return "";
    }
}
