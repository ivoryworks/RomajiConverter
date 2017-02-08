package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class KanaToRomajiHepburnTest {
    /**
     * かなtoローマ字 ヘボン式 直音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnChokuOn() throws Exception {
        List<String> result;
        KanaToRomaji k2r = new KanaToRomaji();
        // shi
        result = k2r.convert("すし", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "sushi");

        // tsu,chi
        result = k2r.convert("つち", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "tsuchi");

        // ji
        result = k2r.convert("じ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "ji");

        // ji,zu
        result = k2r.convert("ぢづ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "jizu");
    }
}