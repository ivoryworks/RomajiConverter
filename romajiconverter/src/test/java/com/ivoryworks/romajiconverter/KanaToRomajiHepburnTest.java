package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

public class KanaToRomajiHepburnTest {
    /**
     * かなtoローマ字 ヘボン式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnYouOn() throws Exception {
        List<String> result;
        KanaToRomaji k2r = new KanaToRomaji();

        // sha,shu,sho
        result = k2r.convert("しゃしゅしょ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "shashusho");

        // cha,chu,cho
        result = k2r.convert("ちゃちゅちょ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "chachucho");

        // ja,ju,jo
        result = k2r.convert("じゃじゅじょ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "jajujo");
        result = k2r.convert("ぢゃぢゅぢょ", KanaToRomaji.SYSTEM_HEPBURN);
        assertTrue(result.size() == 1);
        assertEquals(result.get(0), "jajujo");
    }

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