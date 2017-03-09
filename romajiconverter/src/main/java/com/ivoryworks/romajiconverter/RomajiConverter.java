package com.ivoryworks.romajiconverter;

import java.util.List;

public class RomajiConverter {

    public enum RomajiSystem {
        HEPBURN,
        KUNREI,
        NIHON,
        HEPBURN_EXTEND,
        KUNREI_EXTEND,
        NIHON_EXTEND
    }

    public static List<String> ToRomaji(String kanaStr, RomajiSystem system) {
        return KanaToRomaji.convert(kanaStr, system);
    }

    public static List<String> ToKana(String romajiStr, RomajiSystem system) {
        return RomajiToKana.convert(romajiStr, system);
    }
}
