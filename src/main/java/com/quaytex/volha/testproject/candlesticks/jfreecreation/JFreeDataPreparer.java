package com.quaytex.volha.testproject.candlesticks.jfreecreation;

import com.quaytex.volha.testproject.candlesticks.exceptions.NoDataException;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JFreeDataPreparer {
    
    public DataSets createDatasets(String[][] pricesTable) throws NoDataException {
        DataSets dataSets = new DataSets();
        final String DATASET_NAME = "USEndOfDayPrices";
/*
          {
                "name": "ticker",
                "type": "text"
            },
            {
                "name": "date",
                "type": "Date"
            },
            {
                "name": "open",
                "type": "double"
            },
            {
                "name": "high",
                "type": "double"
            },
            {
                "name": "low",
                "type": "double"
            },
            {
                "name": "close",
                "type": "double"
            },
            {
                "name": "volume",
                "type": "double"
            }
 */
        DefaultCategoryDataset barDataset = new DefaultCategoryDataset();
        final int numberOfRecords = pricesTable.length;
        if (numberOfRecords <= 0) {
            throw new NoDataException("There is no data for these settings");
        }
        Date[] dates = new Date[numberOfRecords];
        double[] opens = new double[numberOfRecords];
        double[] highs = new double[numberOfRecords];
        double[] lows = new double[numberOfRecords];
        double[] closes = new double[numberOfRecords];
        double[] volumes = new double[numberOfRecords];
        String[] datesForBar = new String[numberOfRecords];
        for ( int i = 0; i < numberOfRecords; i++ ) {
            try {
                dates[i] = new SimpleDateFormat("yyyy-MM-dd").parse(pricesTable[i][1]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            datesForBar[i] = pricesTable[i][1];
            opens[i] = Double.parseDouble(pricesTable[i][2]);
            highs[i] = Double.parseDouble(pricesTable[i][3]);
            lows[i] = Double.parseDouble(pricesTable[i][4]);
            closes[i] = Double.parseDouble(pricesTable[i][5]);
            volumes[i] = Double.parseDouble(pricesTable[i][6]);
            barDataset.setValue(volumes[i], "Volume", datesForBar[i]);
        }
        OHLCDataset candlestickDataSet = new DefaultHighLowDataset(DATASET_NAME, dates, highs, lows, opens, closes, volumes);
        dataSets.setCandlestickDataSet(candlestickDataSet);
        dataSets.setBarDataSet(barDataset);
        return dataSets;
    }
}



