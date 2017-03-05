package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;
import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;

import static junit.framework.Assert.assertTrue;

public class KanaToRomajiTest {

    @Test
    public void convertKana2Romaji() {
        // irohanihoheto
        assertTrue(convertChecker("いろはにほへと", new String[]{"irohanihoheto"}));

        // kippu
        assertTrue(convertChecker("きっぷ", new String[]{"kippu"}));

        // sammpo
        assertTrue(convertChecker("さんぽ", new String[]{"sampo"}));
    }

    /**
     * かなtoローマ字 日本式 拗音変換
     */
    @Test
    public void convertKana2RomajiNihonYouOn() {
        // sya,syu,syo
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"syasyusyo"}, RomajiSystem.NIHON));

        // tya,tyu,tyo
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"tyatyutyo"}, RomajiSystem.NIHON));

        // zya,zyu,zyo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"zyazyuzyo"}, RomajiSystem.NIHON));

        // dya,dyu,dyo
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"dyadyudyo"}, RomajiSystem.NIHON));

        // ssi,zzi.tti.ttu.ddi.ddu,hhu
        assertTrue(convertChecker("さっし", new String[]{"sassi"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっじ", new String[]{"sazzi"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっち", new String[]{"satti"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっつ", new String[]{"sattu"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっぢ", new String[]{"saddi"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっづ", new String[]{"saddu"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("さっふ", new String[]{"sahhu"}, RomajiSystem.NIHON));

        // kwa,gwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kwagwa"}, RomajiSystem.NIHON));

        // gunma, nanba
        assertTrue(convertChecker("ぐんま", new String[]{"gunma"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("なんば", new String[]{"nanba"}, RomajiSystem.NIHON));
    }

    /**
     * かなtoローマ字 日本式 直音変換
     */
    @Test
    public void convertKana2RomajiNihonChokuOn() {
        // si, zi
        assertTrue(convertChecker("しじ", new String[]{"sizi"}, RomajiSystem.NIHON));

        // ti, tu
        assertTrue(convertChecker("ちつ", new String[]{"titu"}, RomajiSystem.NIHON));

        // di, du
        assertTrue(convertChecker("ぢづ", new String[]{"didzu", "didu"}, RomajiSystem.NIHON));

        // hu
        assertTrue(convertChecker("ふ", new String[]{"hu"}, RomajiSystem.NIHON));

        // wi, we, wo
        assertTrue(convertChecker("ゐゑを", new String[]{"wiwewo"}, RomajiSystem.NIHON));

        // e. ye
        assertTrue(convertChecker("え", new String[]{"e", "ye"}, RomajiSystem.NIHON));
    }

    /**
     * かなtoローマ字 訓令式 拗音変換
     */
    @Test
    public void convertKana2RomajiKunreiYouOn() {
        // sya,syu,syo
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"syasyusyo"}, RomajiSystem.KUNREI));

        // tya,tyu,tyo
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"tyatyutyo"}, RomajiSystem.KUNREI));

        // zya,zyu,zyo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"zyazyuzyo"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"zyazyuzyo"}, RomajiSystem.KUNREI));

        // ssi,zzi,tti,ttu,zzi,zzu,hhu
        assertTrue(convertChecker("さっし", new String[]{"sassi"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっじ", new String[]{"sazzi"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっち", new String[]{"satti"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっつ", new String[]{"sattu"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっぢ", new String[]{"sazzi"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっづ", new String[]{"sazzu"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("さっふ", new String[]{"sahhu"}, RomajiSystem.KUNREI));

        // kwa,gwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kwagwa"}, RomajiSystem.KUNREI));

        // gunma, nanba
        assertTrue(convertChecker("ぐんま", new String[]{"gunma"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("なんば", new String[]{"nanba"}, RomajiSystem.KUNREI));
    }

    /**
     * かなtoローマ字 訓令式 直音変換
     */
    @Test
    public void convertKana2RomajiKunreiChokuOn() {
        // si, zi
        assertTrue(convertChecker("しじ", new String[]{"sizi"}, RomajiSystem.KUNREI));

        // ti, tu
        assertTrue(convertChecker("ちつ", new String[]{"titu"}, RomajiSystem.KUNREI));

        // di, du
        assertTrue(convertChecker("ぢづ", new String[]{"zidzu", "zizu"}, RomajiSystem.KUNREI));

        // hu
        assertTrue(convertChecker("ふ", new String[]{"hu"}, RomajiSystem.KUNREI));

        // wi, we, wo
        assertTrue(convertChecker("ゐゑを", new String[]{"ieo"}, RomajiSystem.KUNREI));
    }

    /**
     * かなtoローマ字 ヘボン式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnYouOn() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("しゃしゅしょ", new String[]{"shashusho"}, RomajiSystem.HEPBURN));

        // cha,chu,cho
        assertTrue(convertChecker("ちゃちゅちょ", new String[]{"chachucho"}, RomajiSystem.HEPBURN));

        // ja,ju,jo
        assertTrue(convertChecker("じゃじゅじょ", new String[]{"jajujo"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("ぢゃぢゅぢょ", new String[]{"jajujo"}, RomajiSystem.HEPBURN));

        // sshi,jji,tchi,ttsu,jji,zzu,ffu
        assertTrue(convertChecker("さっし", new String[]{"sasshi"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっじ", new String[]{"sajji"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっち", new String[]{"satchi"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっつ", new String[]{"sattsu"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっぢ", new String[]{"sajji"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっづ", new String[]{"sazzu"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("さっふ", new String[]{"saffu"}, RomajiSystem.HEPBURN));

        // kuwa,guwa
        assertTrue(convertChecker("くゎぐゎ", new String[]{"kuwaguwa"}, RomajiSystem.HEPBURN));

        // gumma, namba
        assertTrue(convertChecker("ぐんま", new String[]{"gumma"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("なんば", new String[]{"namba"}, RomajiSystem.HEPBURN));
    }

    /**
     * かなtoローマ字 ヘボン式 直音変換
     * @throws Exception
     */
    @Test
    public void converterKana2RomajiHepburnChokuOn() throws Exception {
        // shi
        assertTrue(convertChecker("すし", new String[]{"sushi"}, RomajiSystem.HEPBURN));

        // tsu,chi
        assertTrue(convertChecker("ちつ", new String[]{"chitsu"}, RomajiSystem.HEPBURN));

        // ji
        assertTrue(convertChecker("じ", new String[]{"ji"}, RomajiSystem.HEPBURN));

        // ji,zu
        assertTrue(convertChecker("ぢづ", new String[]{"jidzu", "jizu"}, RomajiSystem.HEPBURN));
    }

    private boolean convertChecker(String kana, String[] romajis) {
        List<String> result;

        result = KanaToRomaji.convert(kana);
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

    private boolean convertChecker(String kana, String[] romajis, RomajiSystem system) {
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
