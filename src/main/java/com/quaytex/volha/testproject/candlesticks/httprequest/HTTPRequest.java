package com.quaytex.volha.testproject.candlesticks.httprequest;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;


public class HTTPRequest {
    ObjectMapper mapper = new ObjectMapper()
  .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//request for all the tickers
    //https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/TICKERS?api_key=Gzvm_pfh2kYxk6TgejHS&qopts.
    OkHttpClient okHttpClient = new OkHttpClient();
    //candidate request
    //https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?api_key=Gzvm_pfh2kYxk6TgejHS&ticker=XOM&qopts.columns=ticker,date,open,high,low,close,volume&date.gt=2017-10-27
    String url = "https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?api_key=Gzvm_pfh2kYxk6TgejHS&ticker=XOM&qopts.columns=ticker,date,open,high,low,close,volume";
           // "&date.gt=2017-10-27";
            //"https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?api_key=Gzvm_pfh2kYxk6TgejHS&qopts&ticker=SPY&columns=ticker,date,open,high,low,close,volume";
            //"https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/TICKERS?api_key=Gzvm_pfh2kYxk6TgejHS&qopts.export=true";
            //"https://data.nasdaq.com/api/v3/datatables/ETFG/FUND.json?ticker=SPY&api_key=YOURAPIKEY";
    //https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?date=2017-10-31%2C2017-10-30%2C2017-10-27&ticker=XOM%2CWMT%2CVZ&api_key=Gzvm_pfh2kYxk6TgejHS
    Response response;
    ResponseJSON responseJSON;

    public String[][] send() {
        Request request = new Request.Builder().
                url(url).build();

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

        System.out.println("Printing Smth" + responseJSON);

        return responseJSON.getDatatable().getData();
    }

}

//
//    ArrayList bitfinexMessageArray                 //get Channel_ID from array message
//            = (ArrayList) gbt.fromJson(message, List.class);
//    Double receivedArrayChanId = (Double) bitfinexMessageArray.get(0);
//    //get price from array message
//    Double receivedArrayPrice = (Double) ((ArrayList) bitfinexMessageArray.get(1)).get(6);
//    String receivedArrayProduct = bitfinexChannelIDProductID.get(receivedArrayChanId);


//    ObjectMapper mapper = new ObjectMapper();
//
//    OkHttpClient client = new OkHttpClient();
//
//    Request request = new Request.Builder()
//            .url("https://api.nasa.gov/planetary/apod?api_key=DEMO_KEY")
//            .build(); // defaults to GET
//
//    Response response = client.newCall(request).execute();
//
//    APOD apod = mapper.readValue(response.body().byteStream(), APOD.class);
//
//System.out.println(apod.title);


