package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class KanaToRomajiTest {

    @Test
    public void convertKana2Romaji() {
        // irohanihoheto
        assertTrue(convertChecker("いろはにほへと", new String[]{"irohanihoheto"}));

        // kippu
        assertTrue(convertChecker("きっぷ", new String[]{"kippu"}));

        // sammpo
        assertTrue(convertChecker("さんぽ", new String[]{"sammpo"}));
    }

    /**
     * かなtoローマ字 日本式 拗音変換
     */
    @Test
    public void convertKana2RomajiNihonYouOn() {
        // sya,syu,syo
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"syasyusyo"}, KanaToRomaji.SYSTEM_NIHON));

        // tya,tyu,tyo
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"tyatyutyo"}, KanaToRomaji.SYSTEM_NIHON));

        // zya,zyu,zyo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"zyazyuzyo"}, KanaToRomaji.SYSTEM_NIHON));

        // dya,dyu,dyo
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"dyadyudyo"}, KanaToRomaji.SYSTEM_NIHON));

        // ssi,zzi.tti.ttu.ddi.ddu,hhu
        assertTrue(convertChecker("さっし", new String[]{"sassi"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっじ", new String[]{"sazzi"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっち", new String[]{"satti"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっつ", new String[]{"sattu"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっぢ", new String[]{"saddi"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっづ", new String[]{"saddu"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("さっふ", new String[]{"sahhu"}, KanaToRomaji.SYSTEM_NIHON));

        // kwa,gwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kwagwa"}, KanaToRomaji.SYSTEM_NIHON));

        // gunma, nanba
        assertTrue(convertChecker("ぐんま", new String[]{"gunma"}, KanaToRomaji.SYSTEM_NIHON));
        assertTrue(convertChecker("なんば", new String[]{"nanba"}, KanaToRomaji.SYSTEM_NIHON));
    }

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
        assertTrue(convertChecker("ぢづ", new String[]{"didzu", "didu"}, KanaToRomaji.SYSTEM_NIHON));

        // hu
        assertTrue(convertChecker("ふ", new String[]{"hu"}, KanaToRomaji.SYSTEM_NIHON));

        // wi, we, wo
        assertTrue(convertChecker("ゐゑを", new String[]{"wiwewo"}, KanaToRomaji.SYSTEM_NIHON));

        // e. ye
//        assertTrue(convertChecker("え", new String[]{"e", "ye"}, KanaToRomaji.SYSTEM_NIHON));
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

        // ssi,zzi,tti,ttu,zzi,zzu,hhu
        assertTrue(convertChecker("さっし", new String[]{"sassi"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっじ", new String[]{"sazzi"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっち", new String[]{"satti"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっつ", new String[]{"sattu"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっぢ", new String[]{"sazzi"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっづ", new String[]{"sazzu"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("さっふ", new String[]{"sahhu"}, KanaToRomaji.SYSTEM_KUNREI));

        // kwa,gwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kwagwa"}, KanaToRomaji.SYSTEM_KUNREI));

        // gunma, nanba
        assertTrue(convertChecker("ぐんま", new String[]{"gunma"}, KanaToRomaji.SYSTEM_KUNREI));
        assertTrue(convertChecker("なんば", new String[]{"nanba"}, KanaToRomaji.SYSTEM_KUNREI));
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
        assertTrue(convertChecker("ぢづ", new String[]{"zidzu", "zizu"}, KanaToRomaji.SYSTEM_KUNREI));

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

        // sshi,jji,tchi,ttsu,jji,zzu,ffu
        assertTrue(convertChecker("さっし", new String[]{"sasshi"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっじ", new String[]{"sajji"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっち", new String[]{"satchi"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっつ", new String[]{"sattsu"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっぢ", new String[]{"sajji"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっづ", new String[]{"sazzu"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("さっふ", new String[]{"saffu"}, KanaToRomaji.SYSTEM_HEPBURN));

        // kuwa,guwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kuwaguwa"}, KanaToRomaji.SYSTEM_HEPBURN));

        // gumma, namba
        assertTrue(convertChecker("ぐんま", new String[]{"gumma"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("なんば", new String[]{"namba"}, KanaToRomaji.SYSTEM_HEPBURN));
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
        assertTrue(convertChecker("ぢづ", new String[]{"jidzu", "jizu"}, KanaToRomaji.SYSTEM_HEPBURN));
    }

    private boolean convertChecker(String kana, String[] romajis) {
        List<String> result;
        KanaToRomaji k2r = new KanaToRomaji();

        result = k2r.convert(kana);
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
