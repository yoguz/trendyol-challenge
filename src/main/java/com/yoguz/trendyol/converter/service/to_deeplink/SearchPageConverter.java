package com.yoguz.trendyol.converter.service.to_deeplink;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.service.IConverter;
import lombok.Getter;

@Getter
public class SearchPageConverter implements IConverter {

    private String convertedLink;
    private Page page;


    /**
     * Converts the Search Page part of the web url to Search Query deeplink
     *
     * @param link link to be converted
     * @return returns true if the given link can be converted
     */
    @Override
    public boolean convert(String link) {
        String searchQuery = link.replace(ConverterConfig.WEB_SEARCH, "");

        if (searchQuery.matches(ConverterConfig.REGEX_SEARCH)) {
            convertedLink = ConverterConfig.DL_SEARCH + searchQuery;
            page = Page.SEARCH_PAGE;

            return true;
        }

        return false;
    }
}
