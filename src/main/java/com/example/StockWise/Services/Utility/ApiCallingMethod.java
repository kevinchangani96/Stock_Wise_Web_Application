package com.example.StockWise.Services.Utility;

import com.example.StockWise.Model.dto.StockData;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

public class ApiCallingMethod {
    public static StockData apiCallingMethodByStock(String shareName) throws IOException {
        StockData stockData = new StockData();
        // Define the base URL
        HttpUrl.Builder urlBuilder = HttpUrl.parse("https://nse-market.p.rapidapi.com/stocks").newBuilder();
        urlBuilder.addQueryParameter("symbol", shareName);
        String urlString = urlBuilder.build().toString();

        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(urlString)
                    .get()
                    .addHeader("X-RapidAPI-Host", "nse-market.p.rapidapi.com")
                    .addHeader("X-RapidAPI-Key", "c005d0823cmshb3dbcbcd155520ap1c188bjsn9e28a416e7f2")
                    .build();

            Response response = client.newCall(request).execute();

            if (response.isSuccessful()) {
                InputStream inputStream = response.body().byteStream();
                String jsonResponse = convertStreamToString(inputStream);

                JSONObject jsonAPIResponse = new JSONObject(jsonResponse);

                stockData.setSymbol(jsonAPIResponse.optString("Symbol"));
                stockData.setCurrentPrice(jsonAPIResponse.optString("LastPrice"));
                stockData.setDayHigh(jsonAPIResponse.optString("DayHigh"));
                stockData.setDayLow(jsonAPIResponse.optString("DayLow"));
                stockData.setPercentChange(jsonAPIResponse.optString("PercentChange"));
                stockData.setLastUpdatedTime(jsonAPIResponse.optString("LastUpdateTime"));

                System.out.println(jsonAPIResponse);
            } else {
                System.out.println("API call could not be made! Response code: " + response.code());
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }
        return stockData;
    }
    private static String convertStreamToString(InputStream inputStream) {
        Scanner scanner = new Scanner(inputStream).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
