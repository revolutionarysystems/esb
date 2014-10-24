
package uk.co.revsys.esb.utils;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class URLEncoderTest {

    public URLEncoderTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of encode method, of class URLEncoder.
     */
    @Test
    public void testEncode() throws Exception {
        String string = "This is a test";
        String encoding = "UTF-8";
        URLEncoder instance = new URLEncoder();
        String expResult = "This+is+a+test";
        String result = instance.encode(string, encoding);
        assertEquals(expResult, result);
    }

    /**
     * Test of decode method, of class URLEncoder.
     */
    @Test
    public void testDecode() throws Exception {
        String string = "This+is+a+test";
        String encoding = "UTF-8";
        URLEncoder instance = new URLEncoder();
        String expResult = "This is a test";
        String result = instance.decode(string, encoding);
        assertEquals(expResult, result);
    }

    /**
     * Test of encodeQueryString method, of class URLEncoder.
     */
    @Test
    public void testEncodeQueryString() throws Exception {
        String queryString = "p1=This is a test&p2=And another";
        String encoding = "UTF-8";
        URLEncoder instance = new URLEncoder();
        String expResult = "p1=This+is+a+test&p2=And+another";
        String result = instance.encodeQueryString(queryString, encoding);
        assertEquals(expResult, result);
    }

}