package com.yoguz.trendyol.converter.service.to_web_url;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.IConverter;
import lombok.Getter;

@Getter
public class ProductPageConverter implements IConverter {

    private String convertedLink;
    private Page page;


    /**
     * Converts the Product part of the deeplink to Product Page web url
     *
     * @param link link to be converted
     * @return true if the given link can be converted
     */
    @Override
    public boolean convert(String link) {
        link = link.replace(ConverterConfig.DL_PRODUCT, "");
        String productId = link, campaignId = "", merchantId = "";

        if (link.contains("&")) {
            productId = link.substring(0, link.indexOf("&"));
            link = link.substring(link.indexOf("&")+1);
            campaignId = ConverterConfig.getParamValue(link, ConverterConfig.DL_CAMPAIGN_PARAM);
            merchantId = ConverterConfig.getParamValue(link, ConverterConfig.DL_MERCHANT_PARAM);
        }

        if (productId.matches(ConverterConfig.REGEX_ID) && campaignId != null && merchantId != null) {
            convertedLink = ConverterConfig.WEB_PRODUCT + productId;

            if (campaignId.matches(ConverterConfig.REGEX_ID) || merchantId.matches(ConverterConfig.REGEX_ID)) {
                convertedLink += "?";
            }

            if (campaignId.matches(ConverterConfig.REGEX_ID)) {
                convertedLink += ConverterConfig.WEB_BOUTIQUE_PARAM + campaignId;
            }

            if (merchantId.matches(ConverterConfig.REGEX_ID)) {
                if (campaignId.matches(ConverterConfig.REGEX_ID)) {
                    convertedLink += "&";
                }

                convertedLink += ConverterConfig.WEB_MERCHANT_PARAM + merchantId;
            }

            page = Page.PRODUCT_DETAIL_PAGE;

            return true;
        }

        return false;
    }
}
