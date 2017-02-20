package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanaToRomaji {
    public static final int SYSTEM_HEPBURN = 0;
    public static final int SYSTEM_KUNREI = 1;
    public static final int SYSTEM_NIHON = 2;

    Map mChokuBaseMap;
    Map mChokuHepburnMap;
    Map mChokuKunreiMap;
    Map mChokuNihonMap;
    Map mYouBaseMap;
    Map mYouHepburnMap;
    Map mYouKunreiMap;
    Map mYouNihonMap;

    public KanaToRomaji() {
        loadChokuOnBaseTable();
        loadChokuOnHepburnTable();
        loadChokuOnKunreiTable();
        loadChokuOnNihonTable();

        loadYouOnBaseTable();
        loadYouOnHepburnTable();
        loadYouOnKunreiTable();
        loadYouOnNihonTable();
    }

    public List<String> convert(final String str) {
        return convert(str, KanaToRomaji.SYSTEM_HEPBURN);
    }

    public List<String> convert(final String str, final int system) {
        List<String> strArray = new ArrayList<>();
        switch (system) {
            case SYSTEM_HEPBURN:
                strArray = converterHepburn(str);
                break;
            case SYSTEM_KUNREI:
                strArray = converterKunrei(str);
                break;
            case SYSTEM_NIHON:
                strArray = converterNihon(str);
                break;
            default:
                break;
        }
        return strArray;
    }

    private List<String> converterHepburn(final String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertHepburnRecursion(str, 0);

        return strList;
    }

    private List<String> convertHepburnRecursion(final String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        Map[] mapSet;
        if (isYouOn(str, index) || isMM(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertHepburnRecursion(str, index + 2);
            mapSet = new Map[]{mYouBaseMap, mYouHepburnMap};
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertHepburnRecursion(str, index + 1);
            mapSet = new Map[]{mChokuBaseMap, mChokuHepburnMap};
        }

        for (Map map : mapSet) {
            if (!map.containsKey(on)) {
                continue;
            }
            if (recRoma.size() == 0) {
                romaList.add((String) map.get(on));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(map.get(on) + childStr);
                }
            }
        }
        return romaList;
    }

    private List<String> converterKunrei(String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertKunreiRecursion(str, 0);

        return strList;
    }

    private List<String> convertKunreiRecursion(String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        Map[] mapSet;
        if (isYouOn(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertKunreiRecursion(str, index + 2);
            mapSet = new Map[]{mYouBaseMap, mYouKunreiMap};
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertKunreiRecursion(str, index + 1);
            mapSet = new Map[]{mChokuBaseMap, mChokuKunreiMap};
        }

        for (Map map : mapSet) {
            if (!map.containsKey(on)) {
                continue;
            }
            if (recRoma.size() == 0) {
                romaList.add((String) map.get(on));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(map.get(on) + childStr);
                }
            }
        }
        return romaList;
    }

    private List<String> converterNihon(final String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertNihonRecursion(str, 0);

        return strList;
    }

    private List<String> convertNihonRecursion(final String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        Map[] mapSet;
        if (isYouOn(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertNihonRecursion(str, index + 2);
            mapSet = new Map[]{mYouBaseMap, mYouNihonMap};
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertNihonRecursion(str, index + 1);
            mapSet = new Map[]{mChokuBaseMap, mChokuNihonMap};
        }

        for (Map map : mapSet) {
            if (!map.containsKey(on)) {
                continue;
            }
            if (recRoma.size() == 0) {
                romaList.add((String) map.get(on));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(map.get(on) + childStr);
                }
            }
        }
        return romaList;
    }

    private boolean isYouOn(final String str, int index) {
        // 拗音は2文字必要
        if ((str.length() - index) < 2) {
            return false;
        }
        String c = String.valueOf(str.charAt(index));
        String[] youUpper = {"き", "し", "ち", "に", "ひ", "み", "り", "ぎ", "じ", "ぢ", "び", "ぴ"};
        for (String yu : youUpper) {
            if (!yu.equals(c)) {
                continue;
            }
            String c2 = String.valueOf(str.charAt(index + 1));
            // You-on lower
            if (c2.equals("ゃ") || c2.equals("ゅ") || c2.equals("ょ")) {
                return true;
            }
        }

        String[] youEx = {"っ"};
        for (String yx : youEx) {
            if (yx.equals(c)) {
                return true;
            }
        }

        return false;
    }

    private boolean isMM(final String str, int index) {
        if ((str.length() - index) < 2) {
            return false;
        }
        String c = String.valueOf(str.charAt(index));
        if (!"ん".equals(c)) {
            return false;
        }
        String c2 = String.valueOf(str.charAt(index + 1));
        String[] mmLower = {"ま", "み", "む", "め", "も", "ば", "び", "ぶ", "べ", "ぼ", "ぱ", "ぴ", "ぷ", "ぺ", "ぽ"};
        for (String ml : mmLower) {
            if (ml.equals(c2)) {
                return true;
            }
        }
        return false;
    }

    /**
     * 共通直音マップローディング
     */
    private void loadChokuOnBaseTable() {
        String[] chokuKana = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
                "さ", "す", "せ", "そ", "た", "て", "と",
                "な", "に", "ぬ", "ね", "の", "は", "ひ", "へ", "ほ",
                "ま", "み", "む", "め", "も", "や", "ゆ", "よ",
                "ら", "り", "る", "れ", "ろ", "わ", "ん",
                "が", "ぎ", "ぐ", "げ", "ご", "ざ", "ず", "ぜ", "ぞ",
                "だ", "で", "ど", "ば", "び", "ぶ", "べ", "ぼ",
                "ぱ", "ぴ", "ぷ", "ぺ", "ぽ"};
        String[] chokuRoma = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko",
                "sa", "su", "se", "so", "ta", "te", "to",
                "na", "ni", "nu", "ne", "no", "ha", "hi", "he", "ho",
                "ma", "mi", "mu", "me", "mo", "ya", "yu", "yo",
                "ra", "ri", "ru", "re", "ro", "wa", "n",
                "ga", "gi", "gu", "ge", "go", "za", "zu", "ze", "zo",
                "da", "de", "do", "ba", "bi", "bu", "be", "bo",
                "pa", "pi", "pu", "pe", "po"};
        mChokuBaseMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuBaseMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    /**
     * ヘボン式直音マップローディング
     */
    private void loadChokuOnHepburnTable() {
        String[] chokuKana = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
        String[] chokuRoma = {"shi", "ji", "chi", "tsu", "ji", "zu", "fu", "i", "e", "o"};
        mChokuHepburnMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuHepburnMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    /**
     * 訓令式直音マップローディング
     */
    private void loadChokuOnKunreiTable() {
        String[] chokuKana = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
        String[] chokuRoma = {"si", "zi", "ti", "tu", "zi", "zu", "hu", "i", "e", "o"};
        mChokuKunreiMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuKunreiMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    /**
     * 日本式直音マップローディング
     */
    private void loadChokuOnNihonTable() {
        String[] chokuKana = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
        String[] chokuRoma = {"si", "zi", "ti", "tu", "di", "du", "hu", "wi", "we", "wo"};
        mChokuNihonMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuNihonMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    /**
     * 共通拗音マップローディング
     */
    private void loadYouOnBaseTable() {
        String[] youKana = {"きゃ", "きゅ", "きょ", "にゃ", "にゅ", "にょ", "ひゃ", "ひゅ", "ひょ",
                "みゃ", "みゅ", "みょ", "りゃ", "りゅ", "りょ", "ぎゃ", "ぎゅ", "ぎょ",
                "びゃ", "びゅ", "びょ", "ぴゃ", "ぴゅ", "ぴょ",
                "っか", "っき", "っく", "っけ", "っこ", "っさ", "っす", "っせ", "っそ",
                "った", "って", "っと", "っな", "っに", "っぬ", "っね", "っの",
                "っは", "っひ", "っへ", "っほ", "っま", "っみ", "っむ", "っめ", "っも",
                "っや", "っゐ", "っう", "っゑ", "っよ", "っら", "っり", "っる", "っれ", "っろ",
                "っわ", "っを", "っん",
                "っが", "っぎ", "っぐ", "っげ", "っご", "っざ", "っず", "っぜ", "っぞ",
                "っだ", "っで", "っど", "っば", "っび", "っぶ", "っべ", "っぼ",
                "っぱ", "っぴ", "っぷ", "っぺ", "っぽ"};
        String[] youRoma = {"kya", "kyu", "kyo", "nya", "nyu", "nyo", "hya", "hyu", "hyo",
                "mya", "myu", "myo", "rya", "ryu", "ryo", "gya", "gyu", "gyo",
                "bya", "byu", "byo", "pya", "pyu", "pyo",
                "kka", "kki", "kku", "kke", "kko", "ssa", "ssu", "sse", "sso",
                "tta", "tte", "tto", "nna", "nni", "nnu", "nne", "nno",
                "hha", "hhi", "hhe", "hho", "mma", "mmi", "mmu", "mme", "mmo",
                "yya", "yyi", "yyu", "yye", "yyo", "rra", "rri", "rru", "rre", "rro",
                "wwa", "oo", "nn",
                "gga", "ggi", "ggu", "gge", "ggo", "zza", "zzu", "zze", "zzo",
                "dda", "dde", "ddo", "bba", "bbi", "bbu", "bbe", "bbo",
                "ppa", "ppi", "ppu", "ppe", "ppo"};
        mYouBaseMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouBaseMap.put(youKana[i], youRoma[i]);
        }
    }

    /**
     * ヘボン式拗音マップローディング
     */
    private void loadYouOnHepburnTable() {
        String[] youKana = {"しゃ", "しゅ", "しょ", "ちゃ", "ちゅ", "ちょ", "じゃ", "じゅ", "じょ",
                "ぢゃ", "ぢゅ", "ぢょ",
                "っし", "っじ", "っち", "っつ", "っぢ", "っづ", "っふ",
                "んま", "んみ", "んむ", "んめ", "んも", "んば", "んび", "んぶ", "んべ", "んぼ",
                "んぱ", "んぴ", "んぷ", "んぺ", "んぽ"};
        String[] youRoma = {"sha", "shu", "sho", "cha", "chu", "cho", "ja", "ju", "jo",
                "ja", "ju", "jo",
                "sshi", "jji", "tchi", "ttsu", "jji", "zzu", "ffu",
                "mma", "mmi", "mmu", "mme", "mmo", "mba", "mbi", "mbu", "mbe", "mbo",
                "mmpa", "mmpi", "mmpu", "mmpe", "mmpo"};
        mYouHepburnMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouHepburnMap.put(youKana[i], youRoma[i]);
        }
    }

    /**
     * 訓令式拗音マップローディング
     */
    private void loadYouOnKunreiTable() {
        String[] youKana = {"しゃ", "しゅ", "しょ", "ちゃ", "ちゅ", "ちょ", "じゃ", "じゅ", "じょ",
                "ぢゃ", "ぢゅ", "ぢょ",
                "っし", "っじ", "っち", "っつ", "っぢ", "っづ", "っふ"};
        String[] youRoma = {"sya", "syu", "syo", "tya", "tyu", "tyo", "zya", "zyu", "zyo",
                "zya", "zyu", "zyo",
                "ssi", "zzi", "tti", "ttu", "zzi", "zzu", "hhu"};
        mYouKunreiMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouKunreiMap.put(youKana[i], youRoma[i]);
        }
    }

    /**
     * 日本式拗音マップローディング
     */
    private void loadYouOnNihonTable() {
        String[] youKana = {"しゃ", "しゅ", "しょ", "ちゃ", "ちゅ", "ちょ", "じゃ", "じゅ", "じょ",
                "ぢゃ", "ぢゅ", "ぢょ",
                "っし", "っじ", "っち", "っつ", "っぢ", "っづ", "っふ"};
        String[] youRoma = {"sya", "syu", "syo", "tya", "tyu", "tyo", "zya", "zyu", "zyo",
                "dya", "dyu", "dyo",
                "ssi", "zzi", "tti", "ttu", "ddi", "ddu", "hhu"};
        mYouNihonMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouNihonMap.put(youKana[i], youRoma[i]);
        }
    }
}