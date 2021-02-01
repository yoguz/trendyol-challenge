package com.yoguz.trendyol.converter.service;

import com.yoguz.trendyol.converter.config.ConverterConfig;
import com.yoguz.trendyol.converter.dto.ConvertedLinkDto;
import com.yoguz.trendyol.converter.repository.ConverterRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ConverterServiceDeepLinkToWebUrlTest {

    @Spy
    @InjectMocks
    private ConverterService converterService = new ConverterService();

    @Mock
    private ConverterRepository converterRepository;

    private ConvertedLinkDto createConvertedLinkDto(String link) {
        return ConvertedLinkDto.builder()
                .link(link)
                .build();
    }

    @Test
    void product_detail_page_has_only_contentId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865", result);
    }

    @Test
    void product_detail_page_has_campaignId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CampaignId=439892"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892", result);
    }

    @Test
    void product_detail_page_has_merchantId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&MerchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?merchantId=105064", result);
    }

    @Test
    void product_detail_page_has_both_campaignId_and_merchantId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892&merchantId=105064", result);
    }

    @Test
    void search_page_has_only_english_characters() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Search&Query=elbise"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE + "/tum--urunler?q=elbise", result);
    }

    @Test
    void search_page_has_non_english_characters() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Search&Query=%C3%BCt%C3%BC"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE + "/tum--urunler?q=%C3%BCt%C3%BC" , result);
    }

    @Test
    void other_pages_1() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Favorites"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE, result);
    }

    @Test
    void other_pages_2() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Siparislerim"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE, result);
    }

    @Test
    void product_detail_page_has_no_contentId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId="))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE, result);
    }

    @Test
    void product_detail_page_has_nonnumerical_contentId() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=123A"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE, result);
    }

    @Test
    void product_detail_page_has_only_contentId_has_invalid_parameter() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CommentId=123"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865", result);
    }

    @Test
    void product_detail_page_has_campaignId_has_invalid_parameter_after() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CampaignId=439892&CommentId=123"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892", result);
    }

    @Test
    void product_detail_page_has_campaignId_has_invalid_parameter_before() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CommentId=123&CampaignId=439892"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892", result);
    }

    @Test
    void product_detail_page_has_merchantId_has_invalid_parameter_after() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&MerchantId=439892&CommentId=123"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?merchantId=439892", result);
    }

    @Test
    void product_detail_page_has_merchantId_has_invalid_parameter_before() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CommentId=123&MerchantId=439892"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?merchantId=439892", result);
    }

    @Test
    void product_detail_page_has_both_campaignId_and_merchantId_has_invalid_parameter_before() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CommentId=123&CampaignId=439892&MerchantId=439892"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892&merchantId=439892", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId_has_invalid_parameter_in_between() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CampaignId=439892&CommentId=123&MerchantId=439892"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892&merchantId=439892", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId_has_invalid_parameter_after() {
        String result = converterService.
                deepLinkToWebUrl(createConvertedLinkDto(ConverterConfig.DL_BASE
                        + "Product&ContentId=1925865&CampaignId=439892&MerchantId=439892&CommentId=123"))
                .getLink();

        assertEquals(ConverterConfig.WEB_BASE
                + "/brand/name-p-1925865?boutiqueId=439892&merchantId=439892", result);
    }
}