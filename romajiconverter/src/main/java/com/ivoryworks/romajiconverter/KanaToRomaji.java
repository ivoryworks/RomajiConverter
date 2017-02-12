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

    public KanaToRomaji() {
        loadChokuOnBaseTable();
        loadChokuOnHepburnTable();
        loadChokuOnKunreiTable();
        loadYouOnBaseTable();
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

        if (isYouOn(str, index)) {
            // You-on
            String youOn = str.substring(index, index + 2);
            List<String> recRoma = convertHepburnRecursion(str, index + 2);
            if (recRoma.size() == 0) {
                romaList.add((String) mYouBaseMap.get(youOn));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(mYouBaseMap.get(youOn) + childStr);
                }
            }
        } else {
            // Choku-on
            String fig = String.valueOf(str.charAt(index));
            if (mChokuBaseMap.containsKey(fig)) {
                List<String> recRoma = convertHepburnRecursion(str, index + 1);
                if (recRoma.size() == 0) {
                    romaList.add((String) mChokuBaseMap.get(fig));
                } else {
                    for (String childStr : recRoma) {
                        romaList.add(mChokuBaseMap.get(fig) + childStr);
                    }
                }
            } else if (mChokuHepburnMap.containsKey(fig)) {
                List<String> recRoma = convertHepburnRecursion(str, index + 1);
                if (recRoma.size() == 0) {
                    romaList.add((String) mChokuHepburnMap.get(fig));
                } else {
                    for (String childStr : recRoma) {
                        romaList.add(mChokuHepburnMap.get(fig) + childStr);
                    }
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

        // Choku-on
        String fig = String.valueOf(str.charAt(index));
        if (mChokuBaseMap.containsKey(fig)) {
            List<String> recRoma = convertKunreiRecursion(str, index + 1);
            if (recRoma.size() == 0) {
                romaList.add((String) mChokuBaseMap.get(fig));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(mChokuBaseMap.get(fig) + childStr);
                }
            }
        } else if (mChokuKunreiMap.containsKey(fig)) {
            List<String> recRoma = convertKunreiRecursion(str, index + 1);
            if (recRoma.size() == 0) {
                romaList.add((String) mChokuKunreiMap.get(fig));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(mChokuKunreiMap.get(fig) + childStr);
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
     * 共通変換マップローディング
     */
    private void loadChokuOnBaseTable() {
        // Choku-on
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
     * ヘボン式変換マップローディング
     */
    private void loadChokuOnHepburnTable() {
        // Choku-on
        String[] chokuKana = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
        String[] chokuRoma = {"shi", "ji", "chi", "tsu", "ji", "zu", "fu", "i", "e", "o"};
        mChokuHepburnMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuHepburnMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    /**
     * 訓令式変換マップローディング
     */
    private void loadChokuOnKunreiTable() {
        // Choku-on
        String[] chokuKana = {"し", "じ", "ち", "つ", "ぢ", "づ", "ふ", "ゐ", "ゑ", "を"};
        String[] chokuRoma = {"si", "zi", "ti", "tu", "zi", "zu", "hu", "i", "e", "o"};
        mChokuKunreiMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuKunreiMap.put(chokuKana[i], chokuRoma[i]);
        }
    }

    private void loadYouOnBaseTable() {
        // 拗音 
        String[] youKana = {"きゃ", "きゅ", "きょ", "しゃ", "しゅ", "しょ", "ちゃ", "ちゅ", "ちょ",
                "にゃ", "にゅ", "にょ", "ひゃ", "ひゅ", "ひょ", "みゃ", "みゅ", "みょ",
                "りゃ", "りゅ", "りょ", "ぎゃ", "ぎゅ", "ぎょ", "じゃ", "じゅ", "じょ",
                "ぢゃ", "ぢゅ", "ぢょ", "びゃ", "びゅ", "びょ", "ぴゃ", "ぴゅ", "ぴょ"};
        String[] youRoma = {"kya", "kyu", "kyo", "sha", "shu", "sho", "cha", "chu", "cho",
                "nya", "nyu", "nyo", "hya", "hyu", "hyo", "mya", "myu", "myo",
                "rya", "ryu", "ryo", "gya", "gyu", "gyo", "ja", "ju", "jo",
                "ja", "ju", "jo", "bya", "byu", "byo", "pya", "pyu", "pyo"};
        mYouBaseMap = new HashMap<>();
        for (int i = 0; i < youKana.length; i++) {
            mYouBaseMap.put(youKana[i], youRoma[i]);
        }
    }
}