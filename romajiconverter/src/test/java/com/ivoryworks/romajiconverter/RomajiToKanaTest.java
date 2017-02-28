package com.ivoryworks.romajiconverter;

import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.assertTrue;

public class RomajiToKanaTest {
    @Test
    public void convert() throws Exception {
        assertTrue(convertChecker("ni", new String[]{"んい", "んゐ", "に"}));
        assertTrue(convertChecker("irohanihoheto", new String[]{
                "いろはんいほへと", "いろはんゐほへと", "いろはにほへと",
                "ゐろはんいほへと", "ゐろはんゐほへと", "ゐろはにほへと"}));
    }

    @Test
    public void converterRomaji2KanaHepburnChokuOn() throws Exception {
        // shi
        assertTrue(convertChecker("sushi", new String[]{"すし"}, RomajiToKana.SYSTEM_HEPBURN));

        // tsu,chi
        assertTrue(convertChecker("chitsu", new String[]{"ちつ"}, RomajiToKana.SYSTEM_HEPBURN));

        // ji
        assertTrue(convertChecker("ji", new String[]{"じ", "ぢ"}, RomajiToKana.SYSTEM_HEPBURN));

        // ji,zu
        assertTrue(convertChecker("jizu", new String[]{"じず", "じづ", "ぢず", "ぢづ"}, RomajiToKana.SYSTEM_HEPBURN));
    }

    @Test
    public void converterRomaji2KanaHepburnYouOn() throws Exception {
        // sha,shu,sho
        assertTrue(convertChecker("shashusho", new String[]{"しゃしゅしょ"}, KanaToRomaji.SYSTEM_HEPBURN));

        // cha,chu,cho
        assertTrue(convertChecker("chachucho", new String[]{"ちゃちゅちょ"}, KanaToRomaji.SYSTEM_HEPBURN));

        // ja,ju,jo
        assertTrue(convertChecker("jajujo", new String[]{"じゃじゅじょ", "じゃじゅぢょ", "じゃぢゅじょ", "じゃぢゅぢょ",
                "ぢゃじゅじょ", "ぢゃじゅぢょ", "ぢゃぢゅじょ", "ぢゃぢゅぢょ"}, KanaToRomaji.SYSTEM_HEPBURN));

        // sshi,jji,tchi,ttsu,jji,zzu,ffu
        assertTrue(convertChecker("sasshi", new String[]{"さっし"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("satchi", new String[]{"さっち"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("sattsu", new String[]{"さっつ"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("sajji", new String[]{"さっじ", "さっぢ"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("sazzu", new String[]{"さっず", "さっづ"}, KanaToRomaji.SYSTEM_HEPBURN));
        assertTrue(convertChecker("saffu", new String[]{"さっふ"}, KanaToRomaji.SYSTEM_HEPBURN));

        // gumma, namba
        assertTrue(convertChecker("gumma", new String[]{"ぐんま", "ぐっま"}, KanaToRomaji.SYSTEM_HEPBURN));   // ToDo
        assertTrue(convertChecker("namba", new String[]{"んあんば", "なんば"}, KanaToRomaji.SYSTEM_HEPBURN));  // ToDO
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

    private boolean convertChecker(String romaji, String[] kanas, int system) {
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