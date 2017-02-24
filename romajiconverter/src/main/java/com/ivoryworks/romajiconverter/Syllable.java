package com.ivoryworks.romajiconverter;

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
            "っわ", "っを", "っん",
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
            "wwa", "oo", "nn",
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

    static String[] getSyllable(String key, int system) {
        String[] syllables = getSyllable(CHOKU_KANA_BASE, CHOKU_ROMAJI_BASE, key);
        syllables = joinArray(syllables, getSyllable(YOU_KANA_BASE, YOU_ROMAJI_BASE, key));
        switch (system) {
            case KanaToRomaji.SYSTEM_HEPBURN:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_HEPBURN, key));
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_HEPBURN, key));
                syllables = joinArray(syllables, getSyllable(MM_KANA_HEPBURN, MM_ROMAJI_HEPBURN, key));
                break;
            case KanaToRomaji.SYSTEM_KUNREI:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_KUNREI, key));
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_KUNREI, key));
                break;
            case KanaToRomaji.SYSTEM_NIHON:
                syllables = joinArray(syllables, getSyllable(CHOKU_KANA_DIFF, CHOKU_ROMAJI_DIFF_NIHON, key));
                syllables = joinArray(syllables, getSyllable(YOU_KANA_DIFF, YOU_ROMAJI_DIFF_NIHON, key));
                break;
        }
        return syllables;
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
