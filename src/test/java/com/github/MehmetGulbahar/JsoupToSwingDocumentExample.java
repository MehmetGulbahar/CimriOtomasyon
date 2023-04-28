package com.github.MehmetGulbahar;
import com.github.MehmetGulbahar.JsoupToSwingDocumentExample;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.text.BadLocationException;
import javax.swing.text.rtf.RTFEditorKit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class JsoupToSwingDocumentExample {
    public static void main(String[] args) throws IOException, BadLocationException {
        String html = "<html><body><h1>Hello, world!</h1></body></html>";
        org.jsoup.nodes.Document jsoupDoc = Jsoup.parse(html);
        javax.swing.text.Document swingDoc = new RTFEditorKit().createDefaultDocument();
        Element body = jsoupDoc.body();
        ByteArrayInputStream bais = null;
        try {
            bais = new ByteArrayInputStream(body.html().getBytes(StandardCharsets.UTF_8));
            new RTFEditorKit().read(bais, swingDoc, 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (BadLocationException e) {
            e.printStackTrace();
        } finally {
            if (bais != null) {
                try {
                    bais.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        System.out.println(swingDoc.getText(0, swingDoc.getLength()));

    }
}

