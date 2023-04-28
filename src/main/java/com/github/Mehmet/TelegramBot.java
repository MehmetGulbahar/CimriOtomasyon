package com.github.Mehmet;
import okhttp3.*;

import java.io.IOException;

public class TelegramBot {
    private final String token;
    private final String chatId;
    private final OkHttpClient httpClient;

    public TelegramBot(String token, String chatId) {
        this.token = token;
        this.chatId = chatId;
        this.httpClient = new OkHttpClient();
    }

    public void sendMessage(String messageText) throws IOException {
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://api.telegram.org/bot" + token + "/sendMessage").newBuilder();
        urlBuilder.addQueryParameter("chat_id", chatId);
        urlBuilder.addQueryParameter("text", messageText);

        Request request = new Request.Builder()
                .url(urlBuilder.build())
                .build();

        try (Response response = httpClient.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Bir hata oluştu: " + response);
            }
        }
    }
}
