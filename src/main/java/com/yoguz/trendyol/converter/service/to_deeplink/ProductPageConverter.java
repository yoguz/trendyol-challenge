package com.yoguz.trendyol.converter.service.to_deeplink;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.IConverter;
import lombok.Getter;

@Getter
public class ProductPageConverter implements IConverter {

    private String convertedLink;
    private Page page;


    /**
     * Converts the Product Page part of the web url to Product deeplink
     *
     * @param link link to be converted
     * @return true if the given link can be converted
     */
    @Override
    public boolean convert(String link) {
        link = link.substring(link.lastIndexOf("/") + 1);
        String product = link.substring(link.indexOf("-p-")+3);
        String productId = product, boutiqueId = "", merchantId = "";

        if (product.contains("?")) {
            productId = product.substring(0, product.indexOf("?"));
            product = product.substring(product.indexOf("?")+1);
            boutiqueId = ConverterConfig.getParamValue(product, ConverterConfig.WEB_BOUTIQUE_PARAM);
            merchantId = ConverterConfig.getParamValue(product, ConverterConfig.WEB_MERCHANT_PARAM);
        }

        if (productId.matches(ConverterConfig.REGEX_ID) && boutiqueId != null && merchantId != null) {
            convertedLink = ConverterConfig.DL_PRODUCT + productId;

            if (boutiqueId.matches(ConverterConfig.REGEX_ID)) {
                convertedLink += "&" + ConverterConfig.DL_CAMPAIGN_PARAM + boutiqueId;
            }

            if (merchantId.matches(ConverterConfig.REGEX_ID)) {
                convertedLink += "&" + ConverterConfig.DL_MERCHANT_PARAM + merchantId;
            }

            page = Page.PRODUCT_DETAIL_PAGE;

            return true;
        }

        return false;
    }
}
