package com.yoguz.trendyol.converter.service;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.to_deeplink.ProductPageConverter;
import com.yoguz.trendyol.converter.service.to_deeplink.SearchPageConverter;
import lombok.Getter;

@Getter
public class ToDeepLinkBaseConverter implements IConverter {

    private String convertedLink;
    private Page page;
    private IConverter restConverter;


    /**
     * Converts the corresponding deeplink to given web url
     * The web url can belongs to Product, Search or Other Pages.
     * If the web url belongs to either Product or Search Pages,
     * the remaining parts of the web url will be converted in these classes.
     *
     * @param link link to be converted
     * @return always returns true
     */
    @Override
    public boolean convert(String link) {
        this.convertedLink = ConverterConfig.DL_BASE;
        this.page = Page.OTHER_PAGE;

        if (link.startsWith(ConverterConfig.WEB_BASE)) {
            link = link.replace(ConverterConfig.WEB_BASE, "");

            if (link.matches(ConverterConfig.REGEX_WEB_SEARCH_PAGE)) {
                restConverter = new SearchPageConverter();
            } else if (link.matches(ConverterConfig.REGEX_WEB_PRODUCT_PAGE)) {
                restConverter = new ProductPageConverter();
            }
        }

        if (restConverter != null && restConverter.convert(link)) {
            convertedLink += restConverter.getConvertedLink();
            page = restConverter.getPage();
        } else {
            convertedLink += ConverterConfig.DL_HOME;
        }

        return true;
    }
}
