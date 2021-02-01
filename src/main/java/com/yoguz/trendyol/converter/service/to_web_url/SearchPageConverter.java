package com.yoguz.trendyol.converter.service.to_web_url;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.IConverter;
import lombok.Getter;

@Getter
public class SearchPageConverter implements IConverter {

    private String convertedLink;
    private Page page;


    /**
     * Converts the Search Query part of the deeplink to Search Page web url
     *
     * @param link link to be converted
     * @return returns true if the given link can be converted
     */
    @Override
    public boolean convert(String link) {
        String searchQuery = link.replace(ConverterConfig.DL_SEARCH, "");

        if (searchQuery.matches(ConverterConfig.REGEX_SEARCH)) {
            convertedLink = ConverterConfig.WEB_SEARCH + searchQuery;
            page = Page.SEARCH_PAGE;

            return true;
        }

        return false;
    }
}
