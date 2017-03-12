package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;
import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;

import static junit.framework.Assert.assertTrue;

public class RomajiToKanaTest {
    @Test
    public void convert() throws Exception {
        assertTrue(convertChecker("ni", new String[]{"んい", "に"}));
        assertTrue(convertChecker("irohanihoheto", new String[]{"いろはんいほへと", "いろはにほへと",}));
    }

    /**
     * ローマ字toかな ヘボン式 直音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaHepburnChokuOn() throws Exception {
        // shi
        assertTrue(convertChecker("sushi", new String[]{"すし"}, RomajiSystem.HEPBURN));

        // tsu,chi
        assertTrue(convertChecker("chitsu", new String[]{"ちつ"}, RomajiSystem.HEPBURN));

        // ji
        assertTrue(convertChecker("ji", new String[]{"じ", "ぢ"}, RomajiSystem.HEPBURN));

        // ji,zu
        assertTrue(convertChecker("jizu", new String[]{"じず", "じづ", "ぢず", "ぢづ"}, RomajiSystem.HEPBURN));
    }

    @Test
    public void converterRomaji2KanaHepburnChokuOnEx() throws Exception {
        // shi
        assertTrue(convertChecker("sushi", new String[]{"すし"}, RomajiSystem.HEPBURN_EXTEND));

        // tsu,chi
        assertTrue(convertChecker("chitsu", new String[]{"ちつ"}, RomajiSystem.HEPBURN_EXTEND));

        // ji
        assertTrue(convertChecker("ji", new String[]{"じ", "ぢ"}, RomajiSystem.HEPBURN_EXTEND));

        // ji,zu
        assertTrue(convertChecker("jizu", new String[]{"じず", "じづ", "ぢず", "ぢづ"}, RomajiSystem.HEPBURN_EXTEND));
    }

    /**
     * ローマ字toかな ヘボン式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaHepburnYouOn() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("shashusho", new String[]{"しゃしゅしょ"}, RomajiSystem.HEPBURN));

        // cha,chu,cho
        assertTrue(convertChecker("chachucho", new String[]{"ちゃちゅちょ"}, RomajiSystem.HEPBURN));

        // ja,ju,jo
        assertTrue(convertChecker("jajujo", new String[]{"じゃじゅじょ", "じゃじゅぢょ", "じゃぢゅじょ", "じゃぢゅぢょ",
                "ぢゃじゅじょ", "ぢゃじゅぢょ", "ぢゃぢゅじょ", "ぢゃぢゅぢょ"}, RomajiSystem.HEPBURN));

        // sshi,jji,tchi,ttsu,jji,zzu,ffu
        assertTrue(convertChecker("sasshi", new String[]{"さっし"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("satchi", new String[]{"さっち"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("sattsu", new String[]{"さっつ"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("sazzu", new String[]{"さっず", "さっづ"}, RomajiSystem.HEPBURN));
        assertTrue(convertChecker("saffu", new String[]{"さっふ"}, RomajiSystem.HEPBURN));

        // gumma, namba
        assertTrue(convertChecker("gumma", new String[]{"ぐんま", "ぐっま"}, RomajiSystem.HEPBURN));   // ToDo
        assertTrue(convertChecker("namba", new String[]{"んあんば", "なんば"}, RomajiSystem.HEPBURN));  // ToDO
    }

    @Test
    public void converterRomaji2KanaHepburnYouOnEx() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("shashusho", new String[]{"しゃしゅしょ"}, RomajiSystem.HEPBURN_EXTEND));

        // cha,chu,cho
        assertTrue(convertChecker("chachucho", new String[]{"ちゃちゅちょ"}, RomajiSystem.HEPBURN_EXTEND));

        // ja,ju,jo
        assertTrue(convertChecker("jajujo", new String[]{"じゃじゅじょ", "じゃじゅぢょ", "じゃぢゅじょ", "じゃぢゅぢょ",
                "ぢゃじゅじょ", "ぢゃじゅぢょ", "ぢゃぢゅじょ", "ぢゃぢゅぢょ"}, RomajiSystem.HEPBURN_EXTEND));

        // sshi,jji,tchi,ttsu,jji,zzu,ffu
        assertTrue(convertChecker("sasshi", new String[]{"さっし"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("satchi", new String[]{"さっち"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("sattsu", new String[]{"さっつ"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("sazzu", new String[]{"さっず", "さっづ"}, RomajiSystem.HEPBURN_EXTEND));
        assertTrue(convertChecker("saffu", new String[]{"さっふ"}, RomajiSystem.HEPBURN_EXTEND));

        // gumma, namba
        assertTrue(convertChecker("gumma", new String[]{"ぐんま", "ぐっま"}, RomajiSystem.HEPBURN_EXTEND));   // ToDo
        assertTrue(convertChecker("namba", new String[]{"んあんば", "なんば"}, RomajiSystem.HEPBURN_EXTEND));  // ToDO
    }

    /**
     * ローマ字toかな 訓令式 直音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaKunreiChokuOn() throws Exception {
        // si, zi
        assertTrue(convertChecker("sizi", new String[]{"しじ", "しぢ"}, RomajiSystem.KUNREI));

        // ti, tu
        assertTrue(convertChecker("titu", new String[]{"ちつ"}, RomajiSystem.KUNREI));

        // di, du
        assertTrue(convertChecker("zidzu", new String[]{}, RomajiSystem.KUNREI));

        // hu
        assertTrue(convertChecker("hu", new String[]{"ふ"}, RomajiSystem.KUNREI));
    }

    @Test
    public void converterRomaji2KanaKunreiChokuOnEx() throws Exception {
        // si, zi
        assertTrue(convertChecker("sizi", new String[]{"しじ", "しぢ"}, RomajiSystem.KUNREI_EXTEND));

        // ti, tu
        assertTrue(convertChecker("titu", new String[]{"ちつ"}, RomajiSystem.KUNREI_EXTEND));

        // di, du
        assertTrue(convertChecker("zidzu", new String[]{"じづ", "ぢづ"}, RomajiSystem.KUNREI_EXTEND));

        // hu
        assertTrue(convertChecker("hu", new String[]{"ふ"}, RomajiSystem.KUNREI_EXTEND));
    }

    /**
     * ローマ字toかな 訓令式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaKunreiYouOn() throws Exception {
        // sya,syu,syo
        assertTrue(convertChecker("syasyusyo", new String[]{"しゃしゅしょ"}, RomajiSystem.KUNREI));

        // tya,tyu,tyo
        assertTrue(convertChecker("tyatyutyo", new String[]{"ちゃちゅちょ"}, RomajiSystem.KUNREI));

        // zya,zyu,zyo
        assertTrue(convertChecker("zyazyuzyo", new String[]{"じゃじゅじょ", "じゃじゅぢょ", "じゃぢゅじょ", "じゃぢゅぢょ",
                "ぢゃじゅじょ", "ぢゃじゅぢょ", "ぢゃぢゅじょ", "ぢゃぢゅぢょ"}, RomajiSystem.KUNREI));

        // ssi,zzi,tti,ttu,zzi,zzu,hhu
        assertTrue(convertChecker("sassi", new String[]{"さっし"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("sazzi", new String[]{"さっじ", "さっぢ"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("satti", new String[]{"さっち"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("sattu", new String[]{"さっつ"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("sazzu", new String[]{"さっず", "さっづ"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("sahhu", new String[]{"さっふ"}, RomajiSystem.KUNREI));

        // kwa,gwa
        assertTrue(convertChecker("kwagwa", new String[]{"くゎぐゎ"}, RomajiSystem.KUNREI));

        // gunma, nanba
        assertTrue(convertChecker("gunma", new String[]{"ぐんま"}, RomajiSystem.KUNREI));
        assertTrue(convertChecker("nanba", new String[]{"んあんば", "なんば"}, RomajiSystem.KUNREI));
    }

    @Test
    public void converterRomaji2KanaKunreiYouOnEx() throws Exception {
        // sya,syu,syo
        assertTrue(convertChecker("syasyusyo", new String[]{"しゃしゅしょ"}, RomajiSystem.KUNREI_EXTEND));

        // tya,tyu,tyo
        assertTrue(convertChecker("tyatyutyo", new String[]{"ちゃちゅちょ"}, RomajiSystem.KUNREI_EXTEND));

        // zya,zyu,zyo
        assertTrue(convertChecker("zyazyuzyo", new String[]{"じゃじゅじょ", "じゃじゅぢょ", "じゃぢゅじょ", "じゃぢゅぢょ",
                "ぢゃじゅじょ", "ぢゃじゅぢょ", "ぢゃぢゅじょ", "ぢゃぢゅぢょ"}, RomajiSystem.KUNREI_EXTEND));

        // ssi,zzi,tti,ttu,zzi,zzu,hhu
        assertTrue(convertChecker("sassi", new String[]{"さっし"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("sazzi", new String[]{"さっじ", "さっぢ"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("satti", new String[]{"さっち"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("sattu", new String[]{"さっつ"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("sazzu", new String[]{"さっず", "さっづ"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("sahhu", new String[]{"さっふ"}, RomajiSystem.KUNREI_EXTEND));

        // kwa,gwa
        assertTrue(convertChecker("kwagwa", new String[]{"くゎぐゎ"}, RomajiSystem.KUNREI_EXTEND));

        // gunma, nanba
        assertTrue(convertChecker("gunma", new String[]{"ぐんま"}, RomajiSystem.KUNREI_EXTEND));
        assertTrue(convertChecker("nanba", new String[]{"んあんば", "なんば"}, RomajiSystem.KUNREI_EXTEND));
    }

    /**
     * ローマ字toかな 日本式 直音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaNihonChokuOn() throws Exception {
        // si, zi
        assertTrue(convertChecker("sizi", new String[]{"しじ"}, RomajiSystem.NIHON));

        // ti, tu
        assertTrue(convertChecker("titu", new String[]{"ちつ"}, RomajiSystem.NIHON));

        // di, du
        assertTrue(convertChecker("didzu", new String[]{}, RomajiSystem.NIHON));
        assertTrue(convertChecker("didu", new String[]{"ぢづ"}, RomajiSystem.NIHON));

        // hu
        assertTrue(convertChecker("hu", new String[]{"ふ"}, RomajiSystem.NIHON));

        // wi, we, wo
        assertTrue(convertChecker("wo", new String[]{"を"}, RomajiSystem.NIHON));

        // e. ye
        assertTrue(convertChecker("e", new String[]{"え"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("ye", new String[]{}, RomajiSystem.NIHON));
    }

    @Test
    public void converterRomaji2KanaNihonChokuOnEx() throws Exception {
        // si, zi
        assertTrue(convertChecker("sizi", new String[]{"しじ"}, RomajiSystem.NIHON_EXTEND));

        // ti, tu
        assertTrue(convertChecker("titu", new String[]{"ちつ"}, RomajiSystem.NIHON_EXTEND));

        // di, du
        assertTrue(convertChecker("didzu", new String[]{"ぢづ"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("didu", new String[]{"ぢづ"}, RomajiSystem.NIHON_EXTEND));

        // hu
        assertTrue(convertChecker("hu", new String[]{"ふ"}, RomajiSystem.NIHON_EXTEND));

        // wi, we, wo
        assertTrue(convertChecker("wiwewo", new String[]{"ゐゑを"}, RomajiSystem.NIHON_EXTEND));

        // e. ye
        assertTrue(convertChecker("e", new String[]{"え"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("ye", new String[]{"え"}, RomajiSystem.NIHON_EXTEND));
    }

    /**
     * ローマ字toかな 日本式 拗音変換
     * @throws Exception
     */
    @Test
    public void converterRomaji2KanaNihonYouOn() throws Exception {
        // sya,syu,syo
        assertTrue(convertChecker("syasyusyo", new String[]{"しゃしゅしょ"}, RomajiSystem.NIHON));

        // tya,tyu,tyo
        assertTrue(convertChecker("tyatyutyo", new String[]{"ちゃちゅちょ"}, RomajiSystem.NIHON));

        // zya,zyu,zyo
        assertTrue(convertChecker("zyazyuzyo", new String[]{"じゃじゅじょ"}, RomajiSystem.NIHON));

        // dya,dyu,dyo
        assertTrue(convertChecker("dyadyudyo", new String[]{"ぢゃぢゅぢょ"}, RomajiSystem.NIHON));

        // ssi,zzi.tti.ttu.ddi.ddu,hhu
        assertTrue(convertChecker("sassi", new String[]{"さっし"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("sazzi", new String[]{"さっじ"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("satti", new String[]{"さっち"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("sattu", new String[]{"さっつ"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("saddi", new String[]{"さっぢ"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("saddu", new String[]{"さっづ"}, RomajiSystem.NIHON));
        assertTrue(convertChecker("sahhu", new String[]{"さっふ"}, RomajiSystem.NIHON));

        // kwa,gwa
        assertTrue(convertChecker("kwagwa", new String[]{"くゎぐゎ"}, RomajiSystem.NIHON_EXTEND));

        // gunma, nanba
        assertTrue(convertChecker("gunma", new String[]{"ぐんま"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("nanba", new String[]{"んあんば", "なんば"}, RomajiSystem.NIHON_EXTEND));
    }

    @Test
    public void converterRomaji2KanaNihonYouOnEx() throws Exception {
        // sya,syu,syo
        assertTrue(convertChecker("syasyusyo", new String[]{"しゃしゅしょ"}, RomajiSystem.NIHON_EXTEND));

        // tya,tyu,tyo
        assertTrue(convertChecker("tyatyutyo", new String[]{"ちゃちゅちょ"}, RomajiSystem.NIHON_EXTEND));

        // zya,zyu,zyo
        assertTrue(convertChecker("zyazyuzyo", new String[]{"じゃじゅじょ"}, RomajiSystem.NIHON_EXTEND));

        // dya,dyu,dyo
        assertTrue(convertChecker("dyadyudyo", new String[]{"ぢゃぢゅぢょ"}, RomajiSystem.NIHON_EXTEND));

        // ssi,zzi.tti.ttu.ddi.ddu,hhu
        assertTrue(convertChecker("sassi", new String[]{"さっし"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("sazzi", new String[]{"さっじ"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("satti", new String[]{"さっち"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("sattu", new String[]{"さっつ"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("saddi", new String[]{"さっぢ"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("saddu", new String[]{"さっづ"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("sahhu", new String[]{"さっふ"}, RomajiSystem.NIHON_EXTEND));

        // kwa,gwa
        assertTrue(convertChecker("kwagwa", new String[]{"くゎぐゎ"}, RomajiSystem.NIHON_EXTEND));

        // gunma, nanba
        assertTrue(convertChecker("gunma", new String[]{"ぐんま"}, RomajiSystem.NIHON_EXTEND));
        assertTrue(convertChecker("nanba", new String[]{"んあんば", "なんば"}, RomajiSystem.NIHON_EXTEND));
    }

    private boolean convertChecker(String romaji, String[] kanas) {
        List<String> result;

        result = RomajiToKana.convert(romaji);
        if (result.size() != kanas.length) {
            return false;
        }
        for (int i = 0; i < kanas.length; i++) {
            if (!result.get(i).equals(kanas[i])) {
                return false;
            }
        }
        return true;
    }

    private boolean convertChecker(String romaji, String[] kanas, RomajiSystem system) {
        List<String> result;

        result = RomajiToKana.convert(romaji, system);
        if (result.size() != kanas.length) {
            return false;
        }
        for (int i = 0; i < kanas.length; i++) {
            if (!result.get(i).equals(kanas[i])) {
                return false;
            }
        }
        return true;
    }
}