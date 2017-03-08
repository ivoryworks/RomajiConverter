package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.List;

import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;

public class RomajiToKana {
    protected static List<String> convert(final String str) {
        return convert(str, RomajiSystem.HEPBURN);
    }

    protected static List<String> convert(final String romajiStr, final RomajiSystem system) {
        List<String> kanaStrList = new ArrayList<>();
        if (romajiStr == null || romajiStr.length() == 0) {
            return kanaStrList;
        }
        kanaStrList = converterRecursion(romajiStr, 0, system);

        return kanaStrList;
    }

    private static List<String> converterRecursion(final String romajiStr, int index, RomajiSystem system) {
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
