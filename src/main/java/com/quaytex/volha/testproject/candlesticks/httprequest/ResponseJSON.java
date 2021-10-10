package com.quaytex.volha.testproject.candlesticks.httprequest;

public class ResponseJSON {
    ResponseJSONPrices datatable;

    public ResponseJSONPrices getDatatable() {
        return datatable;
    }
    public void setDatatable(ResponseJSONPrices datatable) {
        this.datatable = datatable;
    }

    @Override
    public String toString() {
        return "ResponseJSON{" +
                "datatable=" + datatable +
                '}';
    }
}
