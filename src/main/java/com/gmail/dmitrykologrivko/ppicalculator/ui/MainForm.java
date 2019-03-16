package com.gmail.dmitrykologrivko.ppicalculator.ui;

import com.gmail.dmitrykologrivko.ppicalculator.utils.PpiHelper;

import javax.swing.*;
import java.util.Locale;
import java.util.ResourceBundle;

import static com.gmail.dmitrykologrivko.ppicalculator.consts.Errors.*;
import static com.gmail.dmitrykologrivko.ppicalculator.consts.Messages.MAIN_HELP;
import static com.gmail.dmitrykologrivko.ppicalculator.consts.Messages.MAIN_RESULT;
import static com.gmail.dmitrykologrivko.ppicalculator.consts.ResourceBundles.*;

public class MainForm extends JFrame {

    private static final int WINDOW_WIDTH = 200;
    private static final int WINDOW_HEIGHT = 300;
    private static final boolean WINDOW_VISIBLE = true;
    private static final boolean WINDOW_RESIZABLE = false;

    // Main panel
    private JPanel mainPanel;

    // Text fields
    private JTextField textInch;
    private JTextField textWidth;
    private JTextField textHeight;

    // Buttons
    private JButton buttonCalculate;
    private JButton buttonClear;
    private JButton buttonHelp;

    // Default locale
    private Locale defaultLocale;

    // Resource bundles
    private final ResourceBundle mesBundle;
    private final ResourceBundle errBundle;

    public MainForm() {
        defaultLocale = new Locale("en", "US");

        ResourceBundle.Control bundleControl =
                ResourceBundle.Control.getNoFallbackControl(ResourceBundle.Control.FORMAT_PROPERTIES);

        mesBundle = ResourceBundle.getBundle(MESSAGES, defaultLocale, bundleControl);
        errBundle = ResourceBundle.getBundle(ERRORS, defaultLocale, bundleControl);

        setUpWindow();
        setUpViews();
    }

    private void setUpWindow() {
        setContentPane(mainPanel);
        setVisible(WINDOW_VISIBLE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setResizable(WINDOW_RESIZABLE);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void setUpViews() {
        // Button calculate listener
        buttonCalculate.addActionListener(e -> calculate());
        // Button clear listener
        buttonClear.addActionListener(e -> clearTextFields());
        // Button help listener
        buttonHelp.addActionListener(e -> showHelp());
    }

    private void calculate() {
        if (isEmptyTextFields()) {
            JOptionPane.showMessageDialog(mainPanel,
                    errBundle.getString(MAIN_FILL_OUT_ALL_FIELDS));
            return;
        }
        if (!isValidInch()) {
            JOptionPane.showMessageDialog(mainPanel,
                    errBundle.getString(MAIN_VALIDATION_INCH));
            return;
        }
        if (!isValidWidth()) {
            JOptionPane.showMessageDialog(mainPanel,
                    errBundle.getString(MAIN_VALIDATION_WIDTH));
            return;
        }
        if (!isValidHeight()) {
            JOptionPane.showMessageDialog(mainPanel,
                    errBundle.getString(MAIN_VALIDATION_HEIGHT));
            return;
        }

        double width = Double.valueOf(textWidth.getText());
        double height = Double.valueOf(textHeight.getText());
        double inch = Double.valueOf(textInch.getText());

        double ppi = PpiHelper.ppi(width, height, inch);
        String classification = PpiHelper.classification(ppi);

        JOptionPane.showMessageDialog(mainPanel,
                String.format(mesBundle.getString(MAIN_RESULT), ppi, classification));
    }

    private void clearTextFields() {
        textInch.setText(null);
        textWidth.setText(null);
        textHeight.setText(null);
    }

    private void showHelp() {
        JOptionPane.showMessageDialog(mainPanel, mesBundle.getString(MAIN_HELP));
    }

    private boolean isEmptyTextFields() {
        return textInch.getText().length() == 0 || textWidth.getText().length() == 0 || textHeight.getText().length() == 0;
    }

    private boolean isValidInch() {
        return textInch.getText().matches("(\\d+)(.\\d+)?");
    }

    private boolean isValidWidth() {
        return textWidth.getText().matches("(\\d+)");
    }

    private boolean isValidHeight() {
        return textHeight.getText().matches("(\\d+)");
    }
}