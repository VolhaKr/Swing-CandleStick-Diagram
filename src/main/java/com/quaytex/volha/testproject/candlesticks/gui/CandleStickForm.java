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

public class CandleStickForm {

        private JPanel rootPanel = new JPanel();
      //  private JPanel topPanel = new JPanel();
        private JPanel bottomPanel;

        public static void main(String[] args) {
            JFrame frame = new JFrame("CandleStickForm");
            //com.quaytex.volha.testproject.candlesticks.gui.CandleStickForm candleSticksForm = new com.quaytex.volha.testproject.candlesticks.gui.CandleSticksFormOld();
            CandleStickForm candleStickForm = new CandleStickForm();
            frame.setContentPane(candleStickForm.rootPanel);
            candleStickForm.rootPanel.setLayout(new BorderLayout());
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.pack();
            frame.setVisible(true);


            UtilDateModel model = new UtilDateModel();
            JDatePanelImpl datePanel = new JDatePanelImpl(model);
            JDatePickerImpl datePicker = new JDatePickerImpl(datePanel);
            JPanel topPanel = new JPanel();
            frame.add(topPanel);
            topPanel.add(new Label ("Start Date"));
            frame.add(datePicker);


            JPanel candleStickPanel = new JPanel();
            frame.add(candleStickPanel);

            UtilDateModel model1 = new UtilDateModel();
            JDatePanelImpl datePanel1 = new JDatePanelImpl(model1);
            JDatePickerImpl datePicker1 = new JDatePickerImpl(datePanel1);
            frame.add(new Label ("End Date"));
            frame.add(datePicker1);


            HTTPRequest httpRequest = new HTTPRequest();
            JFreeCreator jFreeCreator =new JFreeCreator();

//            System.out.println("****OOOOOO****");
//            System.out.println(jFreeCreator.createOHLCDataset(httpRequest.send()));
//            System.out.println("=========================================================jFreeCreator.createOHLCDataset(httpRequest.send()).getVolume(5, 6)" +jFreeCreator.createOHLCDataset(httpRequest.send()).getVolume(5, 6));

            final JFreeChart chart = ChartFactory.createCandlestickChart("US End Of Day", "Date",  "Volume",jFreeCreator.createOHLCDataset(httpRequest.send()), true );
            final ChartPanel chartPanel = new ChartPanel(chart);
            candleStickPanel.add(chartPanel);
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
    }




