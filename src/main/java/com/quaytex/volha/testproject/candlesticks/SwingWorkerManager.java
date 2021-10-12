package com.quaytex.volha.testproject.candlesticks;

import com.quaytex.volha.testproject.candlesticks.exceptions.NoDataException;
import com.quaytex.volha.testproject.candlesticks.gui.CandleStickForm;
import com.quaytex.volha.testproject.candlesticks.httprequest.HTTPRequest;
import com.quaytex.volha.testproject.candlesticks.jfreecreation.DataSets;
import com.quaytex.volha.testproject.candlesticks.jfreecreation.JFreeDataPreparer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class SwingWorkerManager {

    private static DataSets dataSets = new DataSets();
    private static HTTPRequest httpRequest = new HTTPRequest();

    public static void startThread(final String selectedTicket, final Date startDate, final Date endDate, final JFrame frame) {

        SwingWorker<DataSets, Void> swingWorkerManagerInstance = new SwingWorker<DataSets, Void>() {

            @Override
            protected DataSets doInBackground() throws Exception {
                DataSets tempDataSets = null;
                String url = httpRequest.buildRequestURL(selectedTicket, startDate, endDate);
                JFreeDataPreparer jFreeDataPreparer = new JFreeDataPreparer();
                tempDataSets = jFreeDataPreparer.createDatasets(httpRequest.send(url));
                return tempDataSets;
            }

            @Override
            protected void done() {
                try {
                    dataSets = get();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(frame, "Some problem occured");
                }
                JPanel candleStickPanel = new JPanel();
                final Dimension CHART_DIMENTION = new Dimension(800, 500);
                candleStickPanel.setPreferredSize(CHART_DIMENTION);
                JFreeChart chart;
                chart = ChartFactory.createCandlestickChart("US End Of Day Candlestick", "Date", "Volume, Prices", dataSets.getCandlestickDataSet(), true);
                final ChartPanel chartPanel = new ChartPanel(chart);
                chartPanel.setPreferredSize(new Dimension(CHART_DIMENTION));
                candleStickPanel.add(chartPanel);
                frame.add(candleStickPanel, BorderLayout.CENTER);

                JPanel barPanel = new JPanel();
                JFreeChart barChart;
                barChart = ChartFactory.createBarChart("Date Volumes Bar Chart", "Date", "Volume", dataSets.getBarDataSet());
                final ChartPanel barChartPanel = new ChartPanel(barChart);
                barChartPanel.setPreferredSize(CHART_DIMENTION);
                barPanel.add(barChartPanel);
                frame.add(barPanel, BorderLayout.SOUTH);
                SwingUtilities.updateComponentTreeUI(frame);
            }
        };

        // executes the swingworker on worker thread
        swingWorkerManagerInstance.execute();
    }
}








