package com.github.Mehmet;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		String telegramToken = "Telegram Token";
		String chatId = "Chat ID";

		TelegramBot telegramBot = new TelegramBot(telegramToken, chatId);

		try {
			Document doc = Jsoup.connect(
					"https://www.cimri.com/oyuncu-kulakliklari/en-ucuz-hyperx-cloud-ii-wireless-oyuncu-kulakligi-fiyatlari,1048058496")
					.get();
			Elements headers = doc.select(
					"#main_container > div > div.s98wa6g-0.feTYBN > div:nth-child(2) > div.s1a29zcm-7.dPVoLl > div.s1wytv2f-0.bpjMIm > div.s1wytv2f-1.FzQtN > a.notOnlyApp.s1wl91l5-0.cZnMAi > span.s1wl91l5-4.cBVHJG\r\n");

			StringBuilder messageText = new StringBuilder();
			for (int i = 0; i < headers.size(); i++) {
				messageText.append("Yeni Fiyat ").append(i + 1).append(": ").append(headers.get(i).text()).append("\n");
			}

			//Verileri Telegrama gönderin.
			telegramBot.sendMessage(messageText.toString());
			System.out.println("Veri başarıyla Telegram'a gönderildi.");
			//System.out.println(headers.text());
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
//#main_container > div > div.s98wa6g-0.feTYBN > div:nth-child(2) > div.s1a29zcm-7.dPVoLl > div.s1wytv2f-0.bpjMIm > div.s1wytv2f-1.FzQtN > a.notOnlyApp.s1wl91l5-0.cZnMAi > span.s1wl91l5-4.cBVHJG
