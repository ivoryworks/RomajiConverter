package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KanaToRomaji {
    public static final int SYSTEM_HEPBURN = 0;
    public static final int SYSTEM_KUNREI = 1;
    public static final int SYSTEM_NIHON = 2;

    Map mChokuMap;

    public KanaToRomaji() {
        loadChokuOnTable();
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

        // Choku-on
        String fig = String.valueOf(str.charAt(index));
        if (mChokuMap.containsKey(fig)) {
            List<String> recRoma = convertHepburnRecursion(str, index + 1);
            if (recRoma.size() == 0) {
                romaList.add((String) mChokuMap.get(fig));
            } else {
                for (String childStr : recRoma) {
                    romaList.add(mChokuMap.get(fig) + childStr);
                }
            }
        }

        return romaList;
    }

    private void loadChokuOnTable() {
        // 直音
        String[] chokuKana = {"あ", "い", "う", "え", "お", "か", "き", "く", "け", "こ",
                            "さ", "し", "す", "せ", "そ", "た", "ち", "つ", "て", "と",
                            "な", "に", "ぬ", "ね", "の", "は", "ひ", "ふ", "へ", "ほ",
                            "ま", "み", "む", "め", "も", "や", "ゐ", "ゆ", "ゑ", "よ",
                            "ら", "り", "る", "れ", "ろ", "わ", "を", "ん",
                            "が", "ぎ", "ぐ", "げ", "ご", "ざ", "じ", "ず", "ぜ", "ぞ",
                            "だ", "ぢ", "づ", "で", "ど", "ば", "び", "ぶ", "べ", "ぼ",
                            "ぱ", "ぴ", "ぷ", "ぺ", "ぽ"};
        String[] chokuRoma = {"a", "i", "u", "e", "o", "ka", "ki", "ku", "ke", "ko",
                            "sa", "shi", "su", "se", "so", "ta", "chi", "tsu", "te", "to",
                            "na", "ni", "nu", "ne", "no", "ha", "hi", "hu", "he", "ho",
                            "ma", "mi", "mu", "me", "mo", "ya", "i", "yu", "e", "yo",
                            "ra", "ri", "ru", "re", "ro", "wa", "o", "n",
                            "ga", "gi", "gu", "ge", "go", "za", "ji", "zu", "ze", "zo",
                            "da", "ji", "zu", "de", "do", "ba", "bi", "bu", "be", "bo",
                            "pa", "pi", "pu", "pe", "po"};
        mChokuMap = new HashMap<>();
        for (int i = 0; i < chokuKana.length; i++) {
            mChokuMap.put(chokuKana[i], chokuRoma[i]);
        }
    }
}
