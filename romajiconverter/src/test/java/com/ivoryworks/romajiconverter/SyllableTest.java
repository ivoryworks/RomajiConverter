package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.lang.reflect.Method;

import static org.junit.Assert.*;

public class SyllableTest {
    @Test
    public void getSyllable() throws Exception {
        // BASEで1つ検出されるケース
        String[] outputArray = Syllable.getSyllable("あ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(outputArray.length == 1);
        assertEquals(outputArray[0], "a");

        // BASEで2つ検出されるケース
        outputArray = Syllable.getSyllable("え", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(outputArray.length == 2);
        assertEquals(outputArray[0], "e");
        assertEquals(outputArray[1], "ye");

        // BASEとDIFFで1つずつ検出されるケース
        outputArray = Syllable.getSyllable("づ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(outputArray.length == 2);
        assertEquals(outputArray[0], "dzu");
        assertEquals(outputArray[1], "zu");
    }

    @Test
    public void getSyllablePrivate() throws Exception {
        Method getSyllable = Syllable.class.getDeclaredMethod("getSyllable", String[].class, String[].class, String.class);
        getSyllable.setAccessible(true);

        String[] outputArray = (String[]) getSyllable.invoke(null, Syllable.CHOKU_KANA_BASE, Syllable.CHOKU_ROMAJI_BASE, "あ");
        assertTrue(outputArray.length == 1);
        assertEquals(outputArray[0], "a");

        outputArray = (String[]) getSyllable.invoke(null, Syllable.CHOKU_KANA_BASE, Syllable.CHOKU_ROMAJI_BASE, "え");
        assertTrue(outputArray.length == 2);
        assertEquals(outputArray[0], "e");
        assertEquals(outputArray[1], "ye");
    }

    @Test
    public void putString() throws Exception {
        Method putString = Syllable.class.getDeclaredMethod("putString", String[].class, String.class);
        putString.setAccessible(true);

        String[] inputArray = new String[0];
        String[] outputArray = (String[]) putString.invoke(null, inputArray, "a");
        assertTrue(outputArray.length == 1);
        assertEquals(outputArray[0], "a");

        inputArray = new String[]{"a"};
        outputArray = (String[]) putString.invoke(null, inputArray, "b");
        assertTrue(outputArray.length == 2);
        assertEquals(outputArray[0], "a");
        assertEquals(outputArray[1], "b");
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