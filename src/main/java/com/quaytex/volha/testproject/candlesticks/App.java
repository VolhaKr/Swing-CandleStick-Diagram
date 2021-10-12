package com.quaytex.volha.testproject.candlesticks;

import com.quaytex.volha.testproject.candlesticks.gui.CandleStickForm;

import javax.swing.*;

public class App {
    public static JFrame frame;

    public static void main(String[] args) {
        frame = new JFrame("Drawing CandleStick");
        new CandleStickForm(frame);
    }
}
