package com.quaytex.volha.testproject.candlesticks.httprequest;

import com.sun.org.apache.xml.internal.serialize.LineSeparator;

import java.util.Arrays;

public class ResponseJSONPrices {
    String[][] data;

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder buildString= new StringBuilder("");
        for ( String [] d: data ){
            for (String dE:d){
                buildString.append(dE).append(",");
            }
            buildString.append(System.lineSeparator() );
        }
        return "ResponseJSONPrices{" +
                "data=" +
                buildString.toString()
                +'}';
    }
}
