package com.yoguz.trendyol.converter.controllers;

import com.yoguz.trendyol.converter.dto.ConvertedLinkDto;
import com.yoguz.trendyol.converter.service.ConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/converter")
public class ConverterController {

    @Autowired
    ConverterService converterService;

    @PostMapping("/to_deeplink")
    public ResponseEntity<ConvertedLinkDto> toDeepLink(@RequestBody ConvertedLinkDto convertedLinkDto) {
        ConvertedLinkDto convertedLink = converterService.webUrlToDeepLink(convertedLinkDto);

        return new ResponseEntity<>(convertedLink, HttpStatus.OK);
    }

    @PostMapping("/to_web_url")
    public ResponseEntity<ConvertedLinkDto> toWebUrl(@RequestBody ConvertedLinkDto convertedLinkDto) {
        ConvertedLinkDto convertedLink = converterService.deepLinkToWebUrl(convertedLinkDto);

        return new ResponseEntity<>(convertedLink, HttpStatus.OK);
    }
}
