package com.task.cards;

import com.task.cards.controller.mainController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import java.io.File;
import java.io.FileInputStream;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MainTest {

    @Value("${upload.path}")
    private String uploadPath;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private mainController controller;

    @Test
    public void test() throws Exception {
        this.mockMvc.perform(get("/"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Загрузить файл")))
                .andExpect(content().string(containsString("Выберите xml файл")))
                .andExpect(content().string(containsString("Обработать")));

    }
    @Test
    public void validFile() throws Exception {
        MockMultipartFile xmlfile = new MockMultipartFile("file", new FileInputStream(new File(uploadPath + "/test/users.xml" )));
        MockHttpServletRequestBuilder multipart = multipart("/result")
                .file(xmlfile);

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
               // .andExpect(xpath("//tbody/tr").nodeCount(3)) Хмм.. выходной html5 не валиден xhtml
                .andExpect(content().string(containsString("peter@brown.ru")))
                .andExpect(content().string(containsString("распознано карточек: 3")));
    }
    @Test
    public void invalidFile() throws Exception {
        MockMultipartFile xmlfile = new MockMultipartFile("file", new FileInputStream(new File(uploadPath + "/test/users-inv.xml" )));
        MockHttpServletRequestBuilder multipart = multipart("/result")
                .file(xmlfile);

        this.mockMvc.perform(multipart)
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Файл не соответствует формату. Проверьте ваш файл на соответствие <a href='/xml/format.xsd'>XSD</a>")));
    }

}

