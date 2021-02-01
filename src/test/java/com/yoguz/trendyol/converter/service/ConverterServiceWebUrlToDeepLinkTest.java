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
class ConverterServiceWebUrlToDeepLinkTest {
    
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
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865", result);
    }

    @Test
    void product_detail_page_has_boutiqueId() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?boutiqueId=439892"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892", result);
    }

    @Test
    void product_detail_page_has_merchantId() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?merchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&MerchantId=105064", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", result);
    }

    @Test
    void search_page_has_only_english_characters() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/tum--urunler?q=elbise"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Search&Query=elbise", result);
    }

    @Test
    void search_page_has_non_english_characters() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/tum--urunler?q=%C3%BCt%C3%BC"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Search&Query=%C3%BCt%C3%BC", result);
    }

    @Test
    void other_pages_1() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/Hesabim/Favoriler"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Home", result);
    }

    @Test
    void other_pages_2() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/Hesabim/#/Siparislerim"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Home", result);
    }

    @Test
    void product_detail_page_has_no_contentId() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Home", result);
    }

    @Test
    void product_detail_page_has_nonnumerical_contentId() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-123A"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Home", result);
    }

    @Test
    void product_detail_page_has_no_p_text() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-1925865"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Home", result);
    }

    @Test
    void product_detail_page_has_no_product_name() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/-p-1925865"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865", result);
    }

    @Test
    void product_detail_page_has_only_contentId_has_invalid_parameter() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?commentId=123"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865", result);
    }

    @Test
    void product_detail_page_has_boutiqueId_has_invalid_parameter_after() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?boutiqueId=439892&commentId=123"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892", result);
    }

    @Test
    void product_detail_page_has_boutiqueId_has_invalid_parameter_before() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?commentId=123&boutiqueId=439892"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892", result);
    }

    @Test
    void product_detail_page_has_merchantId_has_invalid_parameter_after() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?merchantId=105064&commentId=123"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&MerchantId=105064", result);
    }

    @Test
    void product_detail_page_has_merchantId_has_invalid_parameter_before() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/erkek-kol-saat-p-1925865?commentId=123&merchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&MerchantId=105064", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId_has_invalid_parameter_before() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/saat-p-1925865?commentId=123&boutiqueId=439892&merchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId_has_invalid_parameter_in_between() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/saat-p-1925865?boutiqueId=439892&commentId=123&merchantId=105064"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", result);
    }

    @Test
    void product_detail_page_has_both_boutiqueId_and_merchantId_has_invalid_parameter_after() {
        String result = converterService.
                webUrlToDeepLink(createConvertedLinkDto(ConverterConfig.WEB_BASE
                        + "/casio/saat-p-1925865?boutiqueId=439892&merchantId=105064&commentId=123"))
                .getLink();

        assertEquals(ConverterConfig.DL_BASE
                + "Product&ContentId=1925865&CampaignId=439892&MerchantId=105064", result);
    }

}