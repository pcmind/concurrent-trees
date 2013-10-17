package com.googlecode.concurrenttrees.radix.node.concrete.bytearray;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author Niall Gallagher
 */
public class ByteArrayCharSequenceTest {

    @Test
    public void testLength() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        assertEquals(6, bacs.length());
    }

    @Test
    public void testCharAt() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        {
            ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
            int i = 0;
            assertEquals('F', bacs.charAt(i++));
            assertEquals('O', bacs.charAt(i++));
            assertEquals('O', bacs.charAt(i++));
            assertEquals('B', bacs.charAt(i++));
            assertEquals('A', bacs.charAt(i++));
            assertEquals('R', bacs.charAt(i));
        }
        {
            ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 1, 5);
            int i = 0;
            assertEquals('O', bacs.charAt(i++));
            assertEquals('O', bacs.charAt(i++));
            assertEquals('B', bacs.charAt(i++));
            assertEquals('A', bacs.charAt(i));
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void testNegativeStart() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        new ByteArrayCharSequence(bytes, -1, bytes.length);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidLength() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        new ByteArrayCharSequence(bytes, 0, 7);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testStartGreaterThanEnd() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        new ByteArrayCharSequence(bytes, 2, 1);
    }

    @Test
    public void testSubSequenceLength() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        ByteArrayCharSequence subSequence = bacs.subSequence(1, 5); // OOBA
        assertEquals(4, subSequence.length());
    }
    
    @Test
    public void testSubSequenceCharAt() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        ByteArrayCharSequence subSequence = bacs.subSequence(1, 5); // OOBA
        int i = 0;
        assertEquals('O', subSequence.charAt(i++));
        assertEquals('O', subSequence.charAt(i++));
        assertEquals('B', subSequence.charAt(i++));
        assertEquals('A', subSequence.charAt(i));

        ByteArrayCharSequence subSequence2 = subSequence.subSequence(1, 3); // OB
        int j = 0;
        assertEquals('O', subSequence2.charAt(j++));
        assertEquals('B', subSequence2.charAt(j));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubSequenceNegativeStart() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        ByteArrayCharSequence subSequence = bacs.subSequence(1, 5); // OOBA
        subSequence.subSequence(-1, 3);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubSequenceInvalidLength() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        ByteArrayCharSequence subSequence = bacs.subSequence(1, 5); // OOBA
        subSequence.subSequence(0, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubSequenceStartGreaterThanEnd() throws Exception {
        byte[] bytes = "FOOBAR".getBytes("UTF-8");
        ByteArrayCharSequence bacs = new ByteArrayCharSequence(bytes, 0, bytes.length);
        ByteArrayCharSequence subSequence = bacs.subSequence(1, 5); // OOBA
        subSequence.subSequence(2, 1);
    }

}
