package com.quaytex.volha.testproject.candlesticks.httprequest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Date;


public class HTTPRequest {
    private final String NASDAQ_PRICES_REQUEST = "https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?api_key=Gzvm_pfh2kYxk6TgejHS&&qopts.columns=ticker,date,open,high,low,close,volume&ticker=";
    ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Response response;
    private ResponseJSON responseJSON;

    public String buildRequestURL(String selectedTicket, Date startDate, Date endDate) {
        StringBuilder builtUrl = new StringBuilder(NASDAQ_PRICES_REQUEST);
        builtUrl.append(selectedTicket);
        builtUrl.append("&date.gt=");
        builtUrl.append(startDate);
        builtUrl.append("&date.lt=");
        builtUrl.append(endDate);
        return builtUrl.toString();
    }

    public String[][] send(String builtUrl) {
        Request request = new Request.Builder().
                url(builtUrl).build();
        try {
            response = okHttpClient.newCall(request).execute();
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        try {
            responseJSON = mapper.readValue(response.body().byteStream(), ResponseJSON.class);
        } catch (
                IOException e) {
            e.printStackTrace();
        }

        System.out.println("Printing response:" + System.lineSeparator() + responseJSON);
        return responseJSON.getDatatable().getData();
    }
}

