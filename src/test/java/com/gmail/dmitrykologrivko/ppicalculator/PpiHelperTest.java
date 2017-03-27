package com.gmail.dmitrykologrivko.ppicalculator;

import org.junit.Test;

import static com.gmail.dmitrykologrivko.ppicalculator.utils.PpiHelper.*;
import static junit.framework.TestCase.assertEquals;

public class PpiHelperTest {

    @Test
    public void test_ppi() {
        // ppi = Sqrt((width^2 + height^2) / inch^2)
        final double WIDTH = 1080;
        final double HEIGHT = 1920;
        final double INCH = 4.9;
        final double PPI = 449.5728918535302;

        assertEquals(PPI, ppi(WIDTH, HEIGHT, INCH));
    }

    @Test
    public void test_classification_none() {
        // NONE is lesser than 120 ppi
        final double NONE_PPI = 119;
        assertEquals(NONE_CLASSIFICATION, classification(NONE_PPI));
    }

    @Test
    public void test_classification_LDPI() {
        // LDPI is 120 - 159 ppi
        final double LDPI_PPI_1 = 120;
        assertEquals(LDPI_CLASSIFICATION, classification(LDPI_PPI_1));

        final double LDPI_PPI_2 = 159;
        assertEquals(LDPI_CLASSIFICATION, classification(LDPI_PPI_2));
    }

    @Test
    public void test_classification_MDPI() {
        // MDPI is 160 - 239 ppi
        final double MDPI_PPI_1 = 160;
        assertEquals(MDPI_CLASSIFICATION, classification(MDPI_PPI_1));

        final double MDPI_PPI_2 = 239;
        assertEquals(MDPI_CLASSIFICATION, classification(MDPI_PPI_2));
    }

    @Test
    public void test_classification_HDPI() {
        // HDPI is 240 - 319 ppi
        final double HDPI_PPI_1 = 240;
        assertEquals(HDPI_CLASSIFICATION, classification(HDPI_PPI_1));

        final double HDPI_PPI_2 = 319;
        assertEquals(HDPI_CLASSIFICATION, classification(HDPI_PPI_2));
    }

    @Test
    public void test_classification_XHDPI() {
        // XHDPI is 320 - 479 ppi
        final double XHDPI_PPI_1 = 320;
        assertEquals(XHDPI_CLASSIFICATION, classification(XHDPI_PPI_1));

        final double XHDPI_PPI_2 = 479;
        assertEquals(XHDPI_CLASSIFICATION, classification(XHDPI_PPI_2));
    }

    @Test
    public void test_classification_XXHDPI() {
        // XXHDPI is 480 - 639 ppi
        final double XHDPI_PPI_1 = 480;
        assertEquals(XXHDPI_CLASSIFICATION, classification(XHDPI_PPI_1));

        final double XHDPI_PPI_2 = 639;
        assertEquals(XXHDPI_CLASSIFICATION, classification(XHDPI_PPI_2));
    }

    @Test
    public void test_classification_XXXHDPI() {
        // XXXHDPI is 640 ppi or greater
        final double XXXHDPI_PPI_1 = 640;
        assertEquals(XXXHDPI_CLASSIFICATION, classification(XXXHDPI_PPI_1));

        final double XXXHDPI_PPI_2 = 1000;
        assertEquals(XXXHDPI_CLASSIFICATION, classification(XXXHDPI_PPI_2));
    }
}
