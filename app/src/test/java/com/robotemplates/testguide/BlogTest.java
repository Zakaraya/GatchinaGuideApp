package com.robotemplates.testguide;

import org.junit.Test;

import static org.junit.Assert.*;

public class BlogTest {

    Blog blog=new Blog("Гатчинский дворец", "Большой Гатчинский дворец построен в 1766—1781 года", "GatchinaBuild.png");


    @Test
    public void getTitle() {
String ex="Гатчинский дворец";
String output=blog.getTitle();
assertEquals(ex, output);
    }

    @Test
    public void getDescription() {
        String ex="Большой Гатчинский дворец построен в 1766—1781 года";
        String output=blog.getTitle();
        assertEquals(ex, output);
    }
}