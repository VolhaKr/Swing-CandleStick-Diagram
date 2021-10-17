package com.quaytex.volha.testproject.candlesticks.httprequest;

public class ResponseJSONPrices {
    private String[][] data;

    public String[][] getData() {
        return data;
    }

    public void setData(String[][] data) {
        this.data = data;
    }

    @Override
    public String toString() {
        StringBuilder buildString = new StringBuilder("");
        for ( String[] d : data ) {
            for ( String dataElement : d ) {
                buildString.append(dataElement).append(",");
            }
            buildString.append(System.lineSeparator());
        }
        return "ResponseJSONPrices{" +
                "data=" +
                buildString.toString()
                + '}';
    }
}
