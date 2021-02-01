package com.yoguz.trendyol.converter.controllers;


import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


@ExtendWith(SpringExtension.class)
@WebMvcTest(ConverterController.class)
class ConverterControllerIntTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ConverterController converterController;

    // TODO Write Tests for Controller
}