
package com.github.Mehmet;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;

public class MyTelegramBot extends TelegramLongPollingBot {

    private static final String BOT_TOKEN = "6023712082:AAEKa5E5rTtXmt6snOCkNBl_AG6HfueW370";

    public void onUpdateReceived(Update update) {
        // Process incoming messages here
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            SendMessage message = new SendMessage();
            message.setChatId(String.valueOf(chatId));
            try {
                String price = getPriceFromUrl(messageText);
                message.setText("The price is: " + price);
            } catch (IOException e) {
                message.setText("An error occurred while getting the price.");
            }
            try {
                execute(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private String getPriceFromUrl(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        Elements priceElement = doc.select("div.product-prices > span[itemprop=price]");
        return priceElement.text();
    }

    public String getBotUsername() {
        return "Fakir_avutan";
    }

    @Override
    public String getBotToken() {
        return BOT_TOKEN;
    }
}

