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
        List<String> kanaStrList = new ArrayList<>();
        if (romajiStr == null || romajiStr.length() == 0) {
            return kanaStrList;
        }
        kanaStrList = converterRecursion(romajiStr, 0, system);

        return kanaStrList;
    }

    private static List<String> converterRecursion(final String romajiStr, int index, int system) {
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
            if (Syllable.isRomajiChokuSyllable(romajiSyllable, system)) {
                recKana.addAll(converterRecursion(romajiStr, index + SyllableLength, system));
            }
            if (Syllable.isRomajiYouSyllable(romajiSyllable, system)) {
                recKana.addAll(converterRecursion(romajiStr, index + SyllableLength, system));
            }

            String[] kanaSyllables = Syllable.getKanaSyllable(romajiSyllable, system);
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
