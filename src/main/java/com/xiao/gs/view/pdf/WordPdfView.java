package com.xiao.gs.view.pdf;

import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

/**
 * @author luoxiaoxiao
 */
@Component
public class WordPdfView extends AbstractPdfView {

    @SuppressWarnings("unchecked")
    @Override
    protected void buildPdfDocument(@NonNull Map<String, Object> model, @NonNull Document document, @NonNull PdfWriter writer, @NonNull HttpServletRequest request, @NonNull HttpServletResponse response) {

        List<String> words = (List<String>) model.get("words");
        for (String word : words) {
            document.add(new Paragraph(word));
        }
    }

}
