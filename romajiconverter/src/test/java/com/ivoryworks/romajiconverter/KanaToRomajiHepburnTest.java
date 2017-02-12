package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class KanaToRomajiHepburnTest {

    /**
     * かなtoローマ字 日本式 直音変換
     */
    @Test
    public void convertKana2RomajiNihonChokuOn() {
        // si, zi
        assertTrue(convertChecker("しじ", new String[]{"sizi"}, KanaToRomaji.SYSTEM_NIHON));

        // ti, tu
        assertTrue(convertChecker("ちつ", new String[]{"titu"}, KanaToRomaji.SYSTEM_NIHON));

        // di, du
        assertTrue(convertChecker("ぢづ", new String[]{"didu"}, KanaToRomaji.SYSTEM_NIHON));

        // hu
        assertTrue(convertChecker("ふ", new String[]{"hu"}, KanaToRomaji.SYSTEM_NIHON));

        // wi, we, wo
        assertTrue(convertChecker("ゐゑを", new String[]{"wiwewo"}, KanaToRomaji.SYSTEM_NIHON));
    }

    /**
     * かなtoローマ字 訓令式 拗音変換
     */
    @Test
    public void convertKana2RomajiKunreiYouOn() {
        // sya,syu,syo
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"syasyusyo"}, KanaToRomaji.SYSTEM_KUNREI));

        // tya,tyu,tyo
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"tyatyutyo"}, KanaToRomaji.SYSTEM_KUNREI));

        // zya,zyu,zyo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"zyazyuzyo"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"zyazyuzyo"}, KanaToRomaji.SYSTEM_KUNREI));
    }

    /**
     * かなtoローマ字 訓令式 直音変換
     */
    @Test
    public void convertKana2RomajiKunreiChokuOn() {
        // si, zi
        assertTrue(convertChecker("しじ", new String[]{"sizi"}, KanaToRomaji.SYSTEM_KUNREI));

        // ti, tu
        assertTrue(convertChecker("ちつ", new String[]{"titu"}, KanaToRomaji.SYSTEM_KUNREI));

        // di, du
        assertTrue(convertChecker("ぢづ", new String[]{"zizu"}, KanaToRomaji.SYSTEM_KUNREI));

        // hu
        assertTrue(convertChecker("ふ", new String[]{"hu"}, KanaToRomaji.SYSTEM_KUNREI));

        // wi, we, wo
        assertTrue(convertChecker("ゐゑを", new String[]{"ieo"}, KanaToRomaji.SYSTEM_KUNREI));
    }

    /**
     * かなtoローマ字 ヘボン式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnYouOn() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"shashusho"}, KanaToRomaji.SYSTEM_HEPBURN));

        // cha,chu,cho
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"chachucho"}, KanaToRomaji.SYSTEM_HEPBURN));

        // ja,ju,jo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"jajujo"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"jajujo"}, KanaToRomaji.SYSTEM_HEPBURN));
    }

    /**
     * かなtoローマ字 ヘボン式 直音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnChokuOn() throws Exception {
        // shi
        assertTrue(convertChecker("すし", new String[]{"sushi"}, KanaToRomaji.SYSTEM_HEPBURN));

        // tsu,chi
        assertTrue(convertChecker("ちつ", new String[]{"chitsu"}, KanaToRomaji.SYSTEM_HEPBURN));

        // ji
        assertTrue(convertChecker("じ", new String[]{"ji"}, KanaToRomaji.SYSTEM_HEPBURN));

        // ji,zu
        assertTrue(convertChecker("ぢづ", new String[]{"jizu"}, KanaToRomaji.SYSTEM_HEPBURN));
    }

    private boolean convertChecker(String kana, String[] romajis, int system) {
        List<String> result;
        KanaToRomaji k2r = new KanaToRomaji();

        result = k2r.convert(kana, system);
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