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
    Map mYouBaseMap;
    Map mYouHepburnMap;
    Map mYouKunreiMap;

    public KanaToRomaji() {
        loadChokuOnBaseTable();
        loadChokuOnHepburnTable();
        loadChokuOnKunreiTable();
        loadYouOnBaseTable();
        loadYouOnHepburnTable();
        loadYouOnKunreiTable();
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
        if (isYouOn(str, index)) {
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
     * 共通拗音マップローディング
     */
    private void loadYouOnBaseTable() {
        String[] youKana = {"きゃ", "きゅ", "きょ", "にゃ", "にゅ", "にょ", "ひゃ", "ひゅ", "ひょ",
                "みゃ", "みゅ", "みょ", "りゃ", "りゅ", "りょ", "ぎゃ", "ぎゅ", "ぎょ",
                "びゃ", "びゅ", "びょ", "ぴゃ", "ぴゅ", "ぴょ"};
        String[] youRoma = {"kya", "kyu", "kyo", "nya", "nyu", "nyo", "hya", "hyu", "hyo",
                "mya", "myu", "myo", "rya", "ryu", "ryo", "gya", "gyu", "gyo",
                "bya", "byu", "byo", "pya", "pyu", "pyo"};
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
                "ぢゃ", "ぢゅ", "ぢょ"};
        String[] youRoma = {"sha", "shu", "sho", "cha", "chu", "cho", "ja", "ju", "jo",
                "ja", "ju", "jo"};
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
                "ぢゃ", "ぢゅ", "ぢょ"};
        String[] youRoma = {"sya", "syu", "syo", "tya", "tyu", "tyo", "zya", "zyu", "zyo",
                "zya", "zyu", "zyo"};
        mYouKunreiMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouKunreiMap.put(youKana[i], youRoma[i]);
        }
    }
}