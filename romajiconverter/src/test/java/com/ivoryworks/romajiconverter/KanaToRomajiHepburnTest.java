package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class KanaToRomajiHepburnTest {
    /**
     * かなtoローマ字 ヘボン式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnYouOn() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"shashusho"}));

        // cha,chu,cho
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"chachucho"}));

        // ja,ju,jo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"jajujo"}));
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"jajujo"}));
    }

    /**
     * かなtoローマ字 ヘボン式 直音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnChokuOn() throws Exception {
        // shi
        assertTrue(convertChecker("すし", new String[]{"sushi"}));

        // tsu,chi
        assertTrue(convertChecker("ちつ", new String[]{"chitsu"}));

        // ji
        assertTrue(convertChecker("じ", new String[]{"ji"}));

        // ji,zu
        assertTrue(convertChecker("ぢづ", new String[]{"jizu"}));
    }

    private boolean convertChecker(String kana, String[] romajis) {
        List<String> result;
        KanaToRomaji k2r = new KanaToRomaji();

        result = k2r.convert(kana, KanaToRomaji.SYSTEM_HEPBURN);
        if (result.size() != romajis.length) {
            return false;
        }
        for (int i = 0; i < romajis.length; i++) {
            if (!result.get(i).equals(romajis[i])) {
                return false;
            }
        }
        return true;
    }
}