package com.xiao.gs.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author luoxiaoxiao
 */
@Controller
@RequestMapping(value = "/pdf", produces = MediaType.APPLICATION_PDF_VALUE)
public class PdfController {

    @GetMapping(value = "/word")
    public String exprotWordPdf(@RequestParam("words") List<String> words, ModelMap model) {
        model.put("words", words);
        return "wordPdfView";
    }

}
