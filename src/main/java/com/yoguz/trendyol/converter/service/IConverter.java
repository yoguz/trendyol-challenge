package com.yoguz.trendyol.converter.service;

import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;

public interface IConverter {


    /**
     * Converts the link according to implemented class behaviour
     *
     * @param link link to be converted
     * @return true if conversion is successful
     */
    boolean convert(String link);


    /**
     * Returns the class's converted link
     *
     * @return converted link
     */
    String getConvertedLink();


    /**
     * Returns the page where the converted link belongs
     *
     * @return page
     */
    Page getPage();

}
