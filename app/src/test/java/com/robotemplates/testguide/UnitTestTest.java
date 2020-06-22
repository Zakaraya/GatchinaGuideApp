package com.robotemplates.testguide;

import org.junit.Test;

import static org.junit.Assert.*;

public class UnitTestTest {

    @Test
    public void convert() {
        float in=1;
        float out;
        float ex=-5;
        double detta=.1;
        UnitTest unitTest=new UnitTest();
        out=unitTest.convert(in);

        assertEquals(ex, out,detta);

    }
}