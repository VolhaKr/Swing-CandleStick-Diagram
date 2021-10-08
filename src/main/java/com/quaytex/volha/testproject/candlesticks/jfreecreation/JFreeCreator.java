package com.quaytex.volha.testproject.candlesticks.jfreecreation;

import org.jfree.data.xy.DefaultHighLowDataset;
import org.jfree.data.xy.OHLCDataset;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JFreeCreator {
    String[][] pricesTable;


   public OHLCDataset createOHLCDataset (String[][] pricesTable){
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
        final int numberOfRecords = pricesTable.length;
        Date[] dates = new Date[numberOfRecords];
        double[] opens = new double[numberOfRecords];
        double[] highs = new double[numberOfRecords];
        double[] lows = new double[numberOfRecords];
        double[] closes = new double[numberOfRecords];
        double[] volumes = new double[numberOfRecords];

        for (int i = 0; i < numberOfRecords; i++) {
            try {
                dates[i] = new SimpleDateFormat("yyyy-MM-dd").parse(pricesTable[i][1]);
           //     System.out.println("date " + dates[i]);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            //  Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
       //     System.out.println( dates[i]+"\t");
            opens[i] = Double.parseDouble(pricesTable[i][2]);
        //    System.out.println("opens " + opens[i]);
            highs[i] = Double.parseDouble(pricesTable[i][3]);
            lows[i] = Double.parseDouble(pricesTable[i][4]);
            closes[i] = Double.parseDouble(pricesTable[i][5]);
            volumes[i] = Double.parseDouble(pricesTable[i][6]);
        }

        OHLCDataset dataset = new DefaultHighLowDataset(DATASET_NAME, dates, highs, lows, opens, closes, volumes);

//dataset.
        return dataset;
    }


}
