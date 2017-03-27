package com.gmail.dmitrykologrivko.ppicalculator.utils;

public class PpiHelper {

    public static final String NONE_CLASSIFICATION = "none";
    public static final String LDPI_CLASSIFICATION = "LDPI";
    public static final String MDPI_CLASSIFICATION = "MDPI";
    public static final String HDPI_CLASSIFICATION = "HDPI";
    public static final String XHDPI_CLASSIFICATION = "XHDPI";
    public static final String XXHDPI_CLASSIFICATION = "XXHDPI";
    public static final String XXXHDPI_CLASSIFICATION = "XXXHDPI";

    /**
     * Calculate ppi by next formula:
     * ppi = Sqrt((width^2 + height^2) / inch^2)
     *
     * @param width  screen width
     * @param height screen height
     * @param inch   screen inch
     * @return calculated ppi
     */
    public static double ppi(double width, double height, double inch) {
        return Math.sqrt((Math.pow(width, 2) + Math.pow(height, 2)) / Math.pow(inch, 2));
    }

    /**
     * Return screen classification by ppi
     *
     * @param ppi calculated ppi
     * @return screen classification
     */
    public static String classification(double ppi) {
        // None
        if (ppi < 120) {
            return NONE_CLASSIFICATION;
        }
        // LDPI
        if (ppi == 120 || ppi > 120 && ppi < 160) {
            return LDPI_CLASSIFICATION;
        }
        // MDPI
        if (ppi == 160 || ppi > 160 && ppi < 240) {
            return MDPI_CLASSIFICATION;
        }
        // HDPI
        if (ppi == 240 || ppi > 240 && ppi < 320) {
            return HDPI_CLASSIFICATION;
        }
        // XHDPI
        if (ppi == 320 || ppi > 320 && ppi < 480) {
            return XHDPI_CLASSIFICATION;
        }
        // XXHDPI
        if (ppi == 480 || ppi > 480 && ppi < 640) {
            return XXHDPI_CLASSIFICATION;
        }
        // XXXHDPI
        if (ppi == 640 || ppi > 640) {
            return XXXHDPI_CLASSIFICATION;
        }

        return "";
    }

}
