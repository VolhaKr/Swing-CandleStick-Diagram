package com.quaytex.volha.testproject.candlesticks.jfreecreation;

import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.OHLCDataset;

public class DataSets {
    OHLCDataset candlestickDataSet;
    DefaultCategoryDataset barDataSet;

    public OHLCDataset getCandlestickDataSet() {
        return candlestickDataSet;
    }

    public void setCandlestickDataSet(OHLCDataset candlestickDataSet) {
        this.candlestickDataSet = candlestickDataSet;
    }

    public DefaultCategoryDataset getBarDataSet() {
        return barDataSet;
    }

    public void setBarDataSet(DefaultCategoryDataset barDataSet) {
        this.barDataSet = barDataSet;
    }
}
