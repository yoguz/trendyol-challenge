package com.yoguz.trendyol.converter.service;

import com.yoguz.trendyol.converter.dto.ConvertedLinkDto;
import com.yoguz.trendyol.converter.entity.ConvertedLink;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Direction;
import com.yoguz.trendyol.converter.enums.ConverterEnums.Page;
import com.yoguz.trendyol.converter.repository.ConverterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConverterService {

    @Autowired
    private ConverterRepository converterRepository;


    /**
     * Returns a deeplink converted from requested link
     *
     * @param convertedLinkDto requested web url
     * @return converted deeplink
     */
    public ConvertedLinkDto webUrlToDeepLink(ConvertedLinkDto convertedLinkDto) {
        IConverter converter = new ToDeepLinkBaseConverter();
        converter.convert(convertedLinkDto.getLink());

        save(convertedLinkDto, converter.getPage(), Direction.TO_DEEPLINK, converter.getConvertedLink());
        return ConvertedLinkDto.builder()
                .link(converter.getConvertedLink())
                .build();
    }


    /**
     * Returns a web url converted from requested deeplink
     *
     * @param convertedLinkDto requested deeplink
     * @return converted web url
     */
    public ConvertedLinkDto deepLinkToWebUrl(ConvertedLinkDto convertedLinkDto) {
        IConverter converter = new ToWebUrlBaseConverter();
        converter.convert(convertedLinkDto.getLink());

        save(convertedLinkDto, converter.getPage(), Direction.TO_WEB_URL, converter.getConvertedLink());
        return ConvertedLinkDto.builder()
                .link(converter.getConvertedLink())
                .build();
    }


    /**
     * Stores the conversion into designated repository
     *
     * @param link original requested link
     * @param page the page where converted link belongs
     * @param direction link conversion direction
     * @param converted converted link
     */
    private void save(ConvertedLinkDto link, Page page, Direction direction, String converted) {
        ConvertedLink convertedLink = new ConvertedLink();
        convertedLink.setPage(page);
        convertedLink.setDirection(direction);

        if (direction == Direction.TO_DEEPLINK) {
            convertedLink.setDeep_link(converted);
            convertedLink.setWeb_url(link.getLink());
        } else {
            convertedLink.setDeep_link(link.getLink());
            convertedLink.setWeb_url(converted);
        }

        converterRepository.save(convertedLink);
    }
}