package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.List;

public class KanaToRomaji {
    static final int SYSTEM_HEPBURN = 0;
    static final int SYSTEM_KUNREI = 1;
    static final int SYSTEM_NIHON = 2;

    public static List<String> convert(final String str) {
        return convert(str, KanaToRomaji.SYSTEM_HEPBURN);
    }

    static List<String> convert(final String str, final int system) {
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

    private static List<String> converterHepburn(final String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertHepburnRecursion(str, 0);

        return strList;
    }

    private static List<String> convertHepburnRecursion(final String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        if (isYouOn(str, index) || isMM(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertHepburnRecursion(str, index + 2);
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertHepburnRecursion(str, index + 1);
        }

        String[] syllables = Syllable.getRomajiSyllable(on, SYSTEM_HEPBURN);
        for (String syllable : syllables) {
            if (recRoma.size() == 0) {
                romaList.add(syllable);
            } else {
                for (String childStr : recRoma) {
                    romaList.add(syllable + childStr);
                }
            }
        }

        return romaList;
    }

    private static List<String> converterKunrei(String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertKunreiRecursion(str, 0);

        return strList;
    }

    private static List<String> convertKunreiRecursion(String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        if (isYouOn(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertKunreiRecursion(str, index + 2);
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertKunreiRecursion(str, index + 1);
        }

        String[] syllables = Syllable.getRomajiSyllable(on, SYSTEM_KUNREI);
        for (String syllable : syllables) {
            if (recRoma.size() == 0) {
                romaList.add(syllable);
            } else {
                for (String childStr : recRoma) {
                    romaList.add(syllable + childStr);
                }
            }
        }

        return romaList;
    }

    private static List<String> converterNihon(final String str) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertNihonRecursion(str, 0);

        return strList;
    }

    private static List<String> convertNihonRecursion(final String str, int index) {
        List<String> romaList = new ArrayList<>();
        if (str.length() == index) {
            return romaList;    // Terminate
        }

        String on;
        List<String> recRoma;
        if (isYouOn(str, index)) {
            // You-on
            on = str.substring(index, index + 2);
            recRoma = convertNihonRecursion(str, index + 2);
        } else {
            // Choku-on
            on = str.substring(index, index + 1);
            recRoma = convertNihonRecursion(str, index + 1);
        }

        String[] syllables = Syllable.getRomajiSyllable(on, SYSTEM_NIHON);
        for (String syllable : syllables) {
            if (recRoma.size() == 0) {
                romaList.add(syllable);
            } else {
                for (String childStr : recRoma) {
                    romaList.add(syllable + childStr);
                }
            }
        }

        return romaList;
    }

    private static boolean isYouOn(final String str, int index) {
        // 拗音は2文字必要
        if ((str.length() - index) < 2) {
            return false;
        }
        String c = String.valueOf(str.charAt(index));
        String c2 = String.valueOf(str.charAt(index + 1));
        String[] youUpper = {"き", "し", "ち", "に", "ひ", "み", "り", "ぎ", "じ", "ぢ", "び", "ぴ"};
        for (String yu : youUpper) {
            if (!yu.equals(c)) {
                continue;
            }
            // You-on lower
            if (c2.equals("ゃ") || c2.equals("ゅ") || c2.equals("ょ")) {
                return true;
            }
        }

        if ("くゎ".equals(c+c2) || "ぐゎ".equals(c+c2)) {
            return true;
        }

        // 「(き)っぷ」ki-ppuなどの判定
        String[] youEx = {"っ"};
        for (String yx : youEx) {
            if (yx.equals(c)) {
                return true;
            }
        }

        return false;
    }

    private static boolean isMM(final String str, int index) {
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
}