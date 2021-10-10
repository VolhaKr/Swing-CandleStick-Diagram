package com.quaytex.volha.testproject.candlesticks.gui;

import com.quaytex.volha.testproject.candlesticks.exceptions.NoDataException;
import com.quaytex.volha.testproject.candlesticks.httprequest.HTTPRequest;
import com.quaytex.volha.testproject.candlesticks.jfreecreation.DataSets;
import com.quaytex.volha.testproject.candlesticks.jfreecreation.JFreeDataPreparer;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Date;

public class CandleStickForm {
    private static JFrame frame;
    private static JPanel rootPanel = new JPanel();
    private static JPanel topPanel = new JPanel();
    private static HTTPRequest httpRequest = new HTTPRequest();

    public static void main(String[] args) {
        frame = new JFrame("Drawing CandleStick");
        frame.setPreferredSize(new Dimension(800, 600));
        CandleStickForm candleStickForm = new CandleStickForm();
        frame.setContentPane(candleStickForm.rootPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

        drawForm();
    }

    private static void drawForm() {

        topPanel.add(new Label("Ticket"));
        String[][] availableTickersData = httpRequest.send("https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/TICKERS?api_key=Gzvm_pfh2kYxk6TgejHS&qopts");
        String[] availableTickers = new String[availableTickersData.length];
        for ( int i = 0; i < availableTickersData.length; i++ ) {
            availableTickers[i] = availableTickersData[i][0];
        }
        final JComboBox<String> ticketList = new JComboBox<>(availableTickers);
        topPanel.add(ticketList);

        topPanel.add(new Label("Start Date"));
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        final JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        topPanel.add(datePicker1);

        topPanel.add(new Label("End Date"));
        UtilDateModel model2 = new UtilDateModel();
        final JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        final JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        topPanel.add(datePicker2);

        topPanel.add(new JButton(new AbstractAction("Draw") {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDraw(ticketList, datePicker1, datePicker2);
            }
        }), BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);
        topPanel.repaint();
        frame.repaint();
        rootPanel.repaint();
        SwingUtilities.updateComponentTreeUI(frame);

    }

    private static void onDraw(JComboBox<String> ticketList, JDatePickerImpl datePicker1, JDatePickerImpl datePicker2) {
        String selectedTicket = (String) ticketList.getSelectedItem();
        Date startDate = (Date) datePicker1.getModel().getValue();
        Date endDate = (Date) datePicker2.getModel().getValue();
        DataSets dataSets = new DataSets();

        if (startDate.compareTo(endDate) > 0) {
            JOptionPane.showMessageDialog(frame, "End Date cannot be earlier than Start Date");
        } else {
            String url = httpRequest.buildRequestURL(selectedTicket, startDate, endDate);
            JFreeDataPreparer jFreeDataPreparer = new JFreeDataPreparer();

            try {
                dataSets = jFreeDataPreparer.createDatasets(httpRequest.send(url));
            } catch (NoDataException e) {
                JOptionPane.showMessageDialog(frame, e.getMessage());
            }

            JPanel candleStickPanel = new JPanel();
            final Dimension CHART_DIMENTION = new Dimension(800, 500);
            candleStickPanel.setPreferredSize(CHART_DIMENTION);
            JFreeChart chart = null;
            chart = ChartFactory.createCandlestickChart("US End Of Day Candlestick", "Date", "Volume, Prices", dataSets.getCandlestickDataSet(), true);
            final ChartPanel chartPanel = new ChartPanel(chart);
            chartPanel.setPreferredSize(new Dimension(CHART_DIMENTION));
            candleStickPanel.add(chartPanel);
            frame.add(candleStickPanel, BorderLayout.CENTER);


            JPanel barPanel = new JPanel();
            JFreeChart barChart = null;
            barChart = ChartFactory.createBarChart("Date Volumes Bar Chart", "Date", "Volume", dataSets.getBarDataSet());
            final ChartPanel barChartPanel = new ChartPanel(barChart);
            barChartPanel.setPreferredSize(CHART_DIMENTION);
            barPanel.add(barChartPanel);
            frame.add(barPanel, BorderLayout.SOUTH);
            SwingUtilities.updateComponentTreeUI(frame);
        }
    }
}





