package com.gmail.dmitrykologrivko.ppicalculator;

import com.gmail.dmitrykologrivko.ppicalculator.ui.MainForm;

import java.util.Locale;

public class Main {

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        new MainForm();
    }

}
