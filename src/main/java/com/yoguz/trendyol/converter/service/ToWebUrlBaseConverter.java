package com.yoguz.trendyol.converter.service;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.to_web_url.ProductPageConverter;
import com.yoguz.trendyol.converter.service.to_web_url.SearchPageConverter;
import lombok.Getter;

@Getter
public class ToWebUrlBaseConverter implements IConverter {

    private String convertedLink;
    private Page page;
    private IConverter restConverter;


    /**
     * Converts the corresponding web url to given deeplink
     * The deeplink can belongs to Product, Search or Other Pages.
     * If the deeplink belongs to either Product or Search Pages,
     * the remaining parts of the deeplink will be converted in these classes.
     *
     * @param link link to be converted
     * @return always returns true
     */
    @Override
    public boolean convert(String link) {
        this.convertedLink = ConverterConfig.WEB_BASE;
        this.page = Page.OTHER_PAGE;

        if (link.startsWith(ConverterConfig.DL_BASE)) {
            link = link.replace(ConverterConfig.DL_BASE, "");

            if (link.matches(ConverterConfig.REGEX_DL_SEARCH_PAGE)) {
                restConverter = new SearchPageConverter();
            } else if (link.matches(ConverterConfig.REGEX_DL_PRODUCT_PAGE)) {
                restConverter = new ProductPageConverter();
            }
        }

        if (restConverter != null && restConverter.convert(link)) {
            convertedLink += restConverter.getConvertedLink();
            page = restConverter.getPage();
        }

        return true;
    }
}
