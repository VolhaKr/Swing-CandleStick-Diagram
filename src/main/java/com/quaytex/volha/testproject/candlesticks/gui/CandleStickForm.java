package com.quaytex.volha.testproject.candlesticks.gui;

import com.quaytex.volha.testproject.candlesticks.httprequest.HTTPRequest;
import com.quaytex.volha.testproject.candlesticks.jfreecreation.JFreeCreator;
import net.sourceforge.jdatepicker.impl.JDatePanelImpl;
import net.sourceforge.jdatepicker.impl.JDatePickerImpl;
import net.sourceforge.jdatepicker.impl.UtilDateModel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.renderer.xy.CandlestickRenderer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class CandleStickForm {
    private static JFrame frame;
     private static JPanel rootPanel = new JPanel();
    private static JPanel topPanel = new JPanel();
    private static HTTPRequest httpRequest = new HTTPRequest();
//      //  private JPanel topPanel = new JPanel();
//        private JPanel bottomPanel;

        public static void main(String[] args) {


            frame = new JFrame("Drawing CandleStick");
            frame.setPreferredSize(new Dimension(800, 600));
            //com.quaytex.volha.testproject.candlesticks.gui.CandleStickForm candleSticksForm = new com.quaytex.volha.testproject.candlesticks.gui.CandleSticksFormOld();
            CandleStickForm candleStickForm = new CandleStickForm();
            frame.setContentPane(candleStickForm.rootPanel);
            // candleStickForm.rootPanel.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);

           // JPanel topPanel = new JPanel();


            drawForm();


           // frame.add(new JButton(new AbstractAction("Save") {


//                XYDataset ds = createDataset();
//
//                JFreeChart chart = ChartFactory.createXYLineChart("Test Chart",
//                        "x", "y", ds, PlotOrientation.VERTICAL, true, true,
//                        false);
//
//                ChartPanel cp = new ChartPanel(chart);
//
//                frame.getContentPane().add(cp);
//
//
//                @Override
//                public void actionPerformed(ActionEvent e) {
//                    saveChart(chart);
//                }
//            }), BorderLayout.SOUTH);
        }

    private static void drawForm() {
        topPanel.add(new Label ("Ticket"));
        String[][] availableTickersData = httpRequest.send("https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/TICKERS?api_key=Gzvm_pfh2kYxk6TgejHS&qopts");
        String [] availableTickers = new String[availableTickersData.length];
        for (int i =0; i< availableTickersData.length; i++){
            availableTickers[i] = availableTickersData [i] [0];
        }
       final JComboBox<String> ticketList = new JComboBox<>(availableTickers);
        topPanel.add(ticketList);

// get the selected item:


        topPanel.add(new Label ("Start Date"));
        UtilDateModel model1 = new UtilDateModel();
        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
        final JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
        topPanel.add(datePicker1);

        topPanel.add(new Label ("End Date"));
        UtilDateModel model2 = new UtilDateModel();
        final JDatePanelImpl datePanel2 = new JDatePanelImpl(model2);
        final JDatePickerImpl datePicker2 = new JDatePickerImpl(datePanel2);
        topPanel.add(datePicker2);

//        final JTextField textField = new JTextField("HelloWorld ____________________________");
//        topPanel.add(textField);
//        JButton buttonOK = new JButton("OK");
//        topPanel.add(buttonOK);
//        buttonOK.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                onOK(textField, datePicker2);
//            }
//        });

        topPanel.add(new JButton (new AbstractAction("Draw") {

            @Override
            public void actionPerformed(ActionEvent e) {
               onDraw(ticketList, datePicker1, datePicker2 );
            }
        }), BorderLayout.EAST);

        frame.add(topPanel, BorderLayout.NORTH);



        //JPanel candleStickPanel = new JPanel();
        // frame.add(candleStickPanel, BorderLayout.SOUTH);

//        UtilDateModel model1 = new UtilDateModel();
//        JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
//        JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);

        //  JPanel bottomPanel = new JPanel();

//        topPanel.add(new Label ("Start Date"));
//        topPanel.add(datePicker);
        //frame.add(bottomPanel);

//            frame.add(new Label ("End Date"));
//            frame.add(datePicker1);


//        HTTPRequest httpRequest = new HTTPRequest();
//        JFreeCreator jFreeCreator =new JFreeCreator();
//        final JFreeChart chart = ChartFactory.createCandlestickChart("US End Of Day", "Date",  "Volume",jFreeCreator.createOHLCDataset(httpRequest.send(createUrl())), true );
//        final ChartPanel chartPanel = new ChartPanel(chart);
//        candleStickPanel.add(chartPanel);
//        frame.add(candleStickPanel, BorderLayout.SOUTH);
    }

    private static void onDraw(JComboBox<String> ticketList, JDatePickerImpl datePicker1, JDatePickerImpl datePicker2) {
        String selectedTicket = (String) ticketList.getSelectedItem();
        Date startDate = (Date) datePicker1.getModel().getValue();
        Date endDate = (Date) datePicker2.getModel().getValue();

        if (startDate.compareTo(endDate) > 0) {
            JOptionPane.showMessageDialog(frame, "End Date cannot be earlier than Start Date");
        } else {
            String url = httpRequest.buildRequestURL( selectedTicket, startDate, endDate);
            JPanel candleStickPanel = new JPanel();
            JFreeCreator jFreeCreator = new JFreeCreator();
            final JFreeChart chart = ChartFactory.createCandlestickChart("US End Of Day", "Date", "Volume, Prices", jFreeCreator.createOHLCDataset(httpRequest.send(url)), true);
            final ChartPanel chartPanel = new ChartPanel(chart);
            candleStickPanel.add(chartPanel);
            frame.add(candleStickPanel, BorderLayout.SOUTH);
            //    textField.setText(selectedDate.toString());
        }
    }

//    private static String createUrl(String selectedTicket, Date startDate, Date endDate) {
//            return "https://data.nasdaq.com/api/v3/datatables/QUOTEMEDIA/PRICES?api_key=Gzvm_pfh2kYxk6TgejHS&ticker=AAPL&qopts.columns=ticker,date,open,high,low,close,volume";
//    }
}




