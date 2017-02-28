package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.List;

public class RomajiToKana {
    static final int SYSTEM_HEPBURN = 0;
    static final int SYSTEM_KUNREI = 1;
    static final int SYSTEM_NIHON = 2;

    public static List<String> convert(final String str) {
        return convert(str, SYSTEM_HEPBURN);
    }

    static List<String> convert(final String romajiStr, final int system) {
        List<String> strArray = new ArrayList<>();
        switch (system) {
            case SYSTEM_HEPBURN:
                strArray = converterHepburn(romajiStr);
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

    private static List<String> converterHepburn(final String romajiStr) {
        List<String> kanaStrList = new ArrayList<>();
        if (romajiStr == null || romajiStr.length() == 0) {
            return kanaStrList;
        }
        kanaStrList = convertHepburnRecursion(romajiStr, 0);

        return kanaStrList;
    }

    private static List<String> convertHepburnRecursion(final String romajiStr, int index) {
        List<String> kanaStrList = new ArrayList<>();
        if (romajiStr.length() == index) {
            return kanaStrList;    // Terminate
        }

        for (int i = 0; i < 4; i++) {
            List<String> recKana = new ArrayList<>();
            int SyllableLength = i + 1;
            if ((romajiStr.length() - index) < SyllableLength) {
                break;
            }
            String romajiSyllable = romajiStr.substring(index, index + SyllableLength);
            if (Syllable.isRomajiChokuSyllable(romajiSyllable, SYSTEM_HEPBURN)) {
                recKana.addAll(convertHepburnRecursion(romajiStr, index + SyllableLength));
            }
            if (Syllable.isRomajiYouSyllable(romajiSyllable, SYSTEM_HEPBURN)) {
                recKana.addAll(convertHepburnRecursion(romajiStr, index + SyllableLength));
            }

            String[] kanaSyllables = Syllable.getKanaSyllable(romajiSyllable, SYSTEM_HEPBURN);
            for (String kanaSyllable : kanaSyllables) {
                if (recKana.size() == 0) {
                    kanaStrList.add(kanaSyllable);
                } else {
                    for (String childStr : recKana) {
                        kanaStrList.add(kanaSyllable + childStr);
                    }
                }
            }
        }

        return kanaStrList;
    }
}
