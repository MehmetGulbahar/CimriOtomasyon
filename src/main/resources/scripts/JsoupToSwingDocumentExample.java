package com.github.Mehmet;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Element;

public class JsoupToSwingDocumentExample {
    public static void main(String[] args) throws IOException, BadLocationException {
        String html = "<html><body><h1>Hello, world!</h1></body></html>";
        Document jsoupDoc = (Document) Jsoup.parse(html);
        javax.swing.text.Document swingDoc = new RTFEditorKit().createDefaultDocument();
        Element body = ((org.jsoup.nodes.Document) jsoupDoc).body();
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

//document.querySelector("#main_container > div > div.s98wa6g-0.feTYBN > div:nth-child(2) > div.s1a29zcm-7.dPVoLl > div.s1wytv2f-0.bpjMIm > div.s1wytv2f-1.FzQtN > a.notOnlyApp.s1wl91l5-0.cZnMAi > span.s1wl91l5-4.cBVHJG")