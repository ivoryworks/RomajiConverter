package com.ivoryworks.romajiconverter;

import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;

class Syllable {
    // 直音音節(かな)
    private static final String[] CHOKU_KANA_BASE = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
            "さ", "す", "せ", "そ", "た", "て", "と",
            "な", "に", "ぬ", "ね", "の", "は", "ひ", "へ", "ほ",
            "ま", "み", "む", "め", "も", "や", "ゆ", "よ",
            "ら", "り", "る", "れ", "ろ", "わ", "ん",
            "が", "ぎ", "ぐ", "げ", "ご", "ざ", "ず", "ぜ", "ぞ",
            "だ", "で", "ど", "ば", "び", "ぶ", "べ", "ぼ",
            "ぱ", "ぴ", "ぷ", "ぺ", "ぽ", "え", "づ"};
    // 直音音節(ローマ字)
    private static final String[] CHOKU_ROMAJI_BASE = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko",
            "sa", "su", "se", "so", "ta", "te", "to",
            "na", "ni", "nu", "ne", "no", "ha", "hi", "he", "ho",
            "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo",
            "ra", "ri", "ru", "re", "ro", "wa", "n",
            "ga", "gi", "gu", "ge", "go", "za", "zu", "ze", "zo",
            "da", "de", "do", "ba", "bi", "bu", "be", "bo",
            "pa", "pi", "pu", "pe", "po", "ye", "dzu"};
    // 直音音節(差分)
    private static final String[] CHOKU_KANA_DIFF = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
    private static final String[] CHOKU_ROMAJI_DIFF_HEPBURN = {"shi", "ji", "chi", "tsu", "ji", "zu", "fu", "i", "e", "o"};
    private static final String[] CHOKU_ROMAJI_DIFF_KUNREI = {"si", "zi", "ti", "tu", "zi", "zu", "hu", "i", "e", "o"};
    private static final String[] CHOKU_ROMAJI_DIFF_NIHON = {"si", "zi", "ti", "tu", "di", "du", "hu", "wi", "we", "wo"};

    // 拗音音節(かな)
    private static final String[] YOU_KANA_BASE = {"きゃ", "きゅ", "きょ", "にゃ", "にゅ", "にょ",
            "ひゃ", "ひゅ", "ひょ", "みゃ", "みゅ", "みょ", "りゃ", "りゅ", "りょ", "ぎゃ", "ぎゅ", "ぎょ",
            "びゃ", "びゅ", "びょ", "ぴゃ", "ぴゅ", "ぴょ",
            "っか", "っき", "っく", "っけ", "っこ", "っさ", "っす", "っせ", "っそ",
            "った", "って", "っと", "っな", "っに", "っぬ", "っね", "っの",
            "っは", "っひ", "っへ", "っほ", "っま", "っみ", "っむ", "っめ", "っも",
            "っや", "っゐ", "っう", "っゑ", "っよ", "っら", "っり", "っる", "っれ", "っろ",
            "っわ",
            "っが", "っぎ", "っぐ", "っげ", "っご", "っざ", "っず", "っぜ", "っぞ",
            "っだ", "っで", "っど", "っば", "っび", "っぶ", "っべ", "っぼ",
            "っぱ", "っぴ", "っぷ", "っぺ", "っぽ"};
    // 拗音音節(ローマ字)
    private static final String[] YOU_ROMAJI_BASE = {"kya", "kyu", "kyo", "nya", "nyu", "nyo",
            "hya", "hyu", "hyo", "mya", "myu", "myo", "rya", "ryu", "ryo", "gya", "gyu", "gyo",
            "bya", "byu", "byo", "pya", "pyu", "pyo",
            "kka", "kki", "kku", "kke", "kko", "ssa", "ssu", "sse", "sso",
            "tta", "tte", "tto", "nna", "nni", "nnu", "nne", "nno",
            "hha", "hhi", "hhe", "hho", "mma", "mmi", "mmu", "mme", "mmo",
            "yya", "yyi", "yyu", "yye", "yyo", "rra", "rri", "rru", "rre", "rro",
            "wwa",
            "gga", "ggi", "ggu", "gge", "ggo", "zza", "zzu", "zze", "zzo",
            "dda", "dde", "ddo", "bba", "bbi", "bbu", "bbe", "bbo",
            "ppa", "ppi", "ppu", "ppe", "ppo"};
    // 拗音音節(差分)
    private static final String[] YOU_KANA_DIFF = {"しゃ", "しゅ", "しょ", "ちゃ", "ちゅ", "ちょ", "じゃ", "じゅ", "じょ",
            "ぢゃ", "ぢゅ", "ぢょ", "くゎ", "ぐゎ",
            "っし", "っじ", "っち", "っつ", "っぢ", "っづ", "っふ"};
    private static final String[] YOU_ROMAJI_DIFF_HEPBURN = {"sha", "shu", "sho", "cha", "chu", "cho", "ja", "ju", "jo",
            "ja", "ju", "jo", "kuwa", "guwa",
            "sshi", "jji", "tchi", "ttsu", "jji", "zzu", "ffu"};
    private static final String[] YOU_ROMAJI_DIFF_KUNREI = {"sya", "syu", "syo", "tya", "tyu", "tyo", "zya", "zyu", "zyo",
            "zya", "zyu", "zyo", "kwa", "gwa",
            "ssi", "zzi", "tti", "ttu", "zzi", "zzu", "hhu"};
    private static final String[] YOU_ROMAJI_DIFF_NIHON = {"sya", "syu", "syo", "tya", "tyu", "tyo", "zya", "zyu", "zyo",
            "dya", "dyu", "dyo", "kwa", "gwa",
            "ssi", "zzi", "tti", "ttu", "ddi", "ddu", "hhu"};

    // m,b,pの前に限りnの代わりにmを使用するためのもの
    private static final String[] MM_KANA_HEPBURN = {"んま", "んみ", "んむ", "んめ", "んも", "んば", "んび", "んぶ", "んべ",
            "んぼ", "んぱ", "んぴ", "んぷ", "んぺ", "んぽ"};
    private static final String[] MM_ROMAJI_HEPBURN = {"mma", "mmi", "mmu", "mme", "mmo", "mba", "mbi", "mbu", "mbe",
            "mbo", "mpa", "mpi", "mpu", "mpe", "mpo"};

    /**
     * 与えた仮名文字に対するローマ字(直音+拗音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getRomajiSyllable(String key, RomajiSystem system) {
        String[] syllables = getRomajiChokuSyllable(key, system);
        syllables = joinArray(syllables, getRomajiYouSyllable(key, system));
        return syllables;
    }

    /**
     * 与えた仮名文字に対するローマ字(直音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getRomajiChokuSyllable(String key, RomajiSystem system) {
        String[] syllables = getSyllable(CHOKU_KANA_BASE, CHOKU_ROMAJI_BASE, key);
        switch (system) {
            case HEPBURN:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_HEPBURN, key));
                syllables = joinArray(syllables, getSyllable(MM_KANA_HEPBURN, MM_ROMAJI_HEPBURN, key));
                break;
            case KUNREI:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_KUNREI, key));
                break;
            case NIHON:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_NIHON, key));
                break;
        }
        return syllables;
    }

    /**
     * 与えた仮名文字に対するローマ字(拗音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getRomajiYouSyllable(String key, RomajiSystem system) {
        String[] syllables = getSyllable(YOU_KANA_BASE, YOU_ROMAJI_BASE, key);
        switch (system) {
            case HEPBURN:
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_HEPBURN, key));
                break;
            case KUNREI:
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_KUNREI, key));
                break;
            case NIHON:
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_NIHON, key));
                break;
        }
        return syllables;
    }

    /**
     * 与えたローマ字に対する仮名文字(直音+拗音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getKanaSyllable(String key, RomajiSystem system) {
        String[] syllables = getKanaChokuSyllable(key, system);
        syllables = joinArray(syllables, getKanaYouSyllable(key, system));
        return syllables;
    }

    /**
     * 与えたローマ字に対する仮名文字(直音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getKanaChokuSyllable(String key, RomajiSystem system) {
        String[] syllables = getSyllable(CHOKU_ROMAJI_BASE, CHOKU_KANA_BASE, key);
        switch (system) {
            case HEPBURN:
                syllables = joinArray(syllables, getSyllable(CHOKU_ROMAJI_DIFF_HEPBURN, CHOKU_KANA_DIFF, key));
                syllables = joinArray(syllables, getSyllable(MM_ROMAJI_HEPBURN, MM_KANA_HEPBURN, key));
                break;
            case KUNREI:
                syllables = joinArray(syllables, getSyllable(CHOKU_ROMAJI_DIFF_KUNREI, CHOKU_KANA_DIFF, key));
                break;
            case NIHON:
                syllables = joinArray(syllables, getSyllable(CHOKU_ROMAJI_DIFF_NIHON, CHOKU_KANA_DIFF, key));
                break;
        }
        return syllables;
    }

    /**
     * 与えたローマ字に対する仮名文字(拗音)を取得する
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static String[] getKanaYouSyllable(String key, RomajiSystem system) {
        String[] syllables = getSyllable(YOU_ROMAJI_BASE, YOU_KANA_BASE, key);
        switch (system) {
            case HEPBURN:
                syllables = joinArray(syllables, getSyllable(YOU_ROMAJI_DIFF_HEPBURN, YOU_KANA_DIFF, key));
                break;
            case KUNREI:
                syllables = joinArray(syllables, getSyllable(YOU_ROMAJI_DIFF_KUNREI, YOU_KANA_DIFF, key));
                break;
            case NIHON:
                syllables = joinArray(syllables, getSyllable(YOU_ROMAJI_DIFF_NIHON, YOU_KANA_DIFF, key));
                break;
        }
        return syllables;
    }

    /**
     * 与えた文字列が仮名の直音であるか
     * @param key Kana syllable
     * @param system Modern system
     * @return
     */
    static boolean isKanaChokuSyllable(String key, RomajiSystem system) {
        String[] out = getRomajiSyllable(key, system);
        return (out.length > 0);
    }

    /**
     * 与えた文字列が仮名の拗音であるか
     * @param key Kana syllable
     * @param system Modern system
     * @return
     */
    static boolean isKanaYouSyllable(String key, RomajiSystem system) {
        String[] out = getRomajiYouSyllable(key, system);
        return (out.length > 0);
    }

    /**
     * 与えた文字列がローマ字の直音であるか
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static boolean isRomajiChokuSyllable(String key, RomajiSystem system) {
        String[] out = getKanaChokuSyllable(key, system);
        return (out.length > 0);
    }

    /**
     * 与えた文字列がローマ字の拗音であるか
     * @param key Romaji syllable
     * @param system Modern system
     * @return
     */
    static boolean isRomajiYouSyllable(String key, RomajiSystem system) {
        String[] out = getKanaYouSyllable(key, system);
        return (out.length > 0);
    }

    private static String[] getSyllable(String[] keyArray, String[] valueArray, String key) {
        String[] syllables = new String[0];
        for (int i = 0; i < keyArray.length; i++) {
            if (keyArray[i].equals(key)) {
                syllables = putString(syllables, valueArray[i]);
            }
        }
        return syllables;
    }

    private static String[] putString(String[] array, String syllable) {
        return joinArray(array, new String[]{syllable});
    }

    private static String[] joinArray(String[] array1, String[] array2) {
        array1 = (array1 == null) ? new String[0] : array1;
        array2 = (array2 == null) ? new String[0] : array2;
        String[] result = new String[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}
