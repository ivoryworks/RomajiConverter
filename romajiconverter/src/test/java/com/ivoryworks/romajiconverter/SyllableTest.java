package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SyllableTest {
    @Test
    public void getSyllable() throws Exception {

    }

    @Test
    public void putString() throws Exception {
        Method putString = Syllable.class.getDeclaredMethod("putString", String[].class, String.class);
        putString.setAccessible(true);

        String[] inputArray = new String[0];
        putString.invoke(null, inputArray, "a");
        assertTrue(inputArray.length == 1);
        assertEquals(inputArray[0], "a");
    }

    @Test
    public void joinArray() throws Exception {
        Method joinArray = Syllable.class.getDeclaredMethod("joinArray", String[].class, String[].class);
        joinArray.setAccessible(true);

        String[] array = (String[]) joinArray.invoke(null, new String[]{"a"}, new String[]{"b"});
        assertTrue(array.length == 2);
        assertEquals(array[0], "a");
        assertEquals(array[1], "b");

        array = (String[]) joinArray.invoke(null, null, new String[]{"b"});
        assertTrue(array.length == 1);
        assertEquals(array[0], "b");

        array = (String[]) joinArray.invoke(null, new String[]{"a"}, null);
        assertTrue(array.length == 1);
        assertEquals(array[0], "a");

        array = (String[]) joinArray.invoke(null, null, null);
        assertTrue(array.length == 0);

        array = (String[]) joinArray.invoke(null, new String[]{"a","b","c"},
                new String[]{"d","e","f","g","h","i","j"});
        assertTrue(array.length == 10);
        assertEquals(array[0], "a");
        assertEquals(array[7], "h");

        array = (String[]) joinArray.invoke(null, new String[]{"a","b","c","d","e","f","g"},
                new String[]{"h","i","j"});
        assertTrue(array.length == 10);
        assertEquals(array[2], "c");
        assertEquals(array[9], "j");
    }

}