package com.ivoryworks.romajiconverter;

import java.util.ArrayList;
import java.util.List;

import com.ivoryworks.romajiconverter.RomajiConverter.RomajiSystem;

public class KanaToRomaji {
    public static List<String> convert(final String str) {
        return convert(str, RomajiConverter.RomajiSystem.HEPBURN);
    }

    public static List<String> convert(final String str, final RomajiSystem system) {
        List<String> strList = new ArrayList<>();
        if (str == null || str.length() == 0) {
            return strList;
        }
        strList = convertRecursion(str, 0, system);

        return strList;
    }

    private static List<String> convertRecursion(final String kanaStr, int index, RomajiSystem system) {
        List<String> romaList = new ArrayList<>();
        if (kanaStr.length() == index) {
            return romaList;    // Terminate
        }

        List<String> recRomaji = new ArrayList<>();
        String kanaSyllable;
        if (isYouOn(kanaStr, index, system)) {
            // 拗音
            // 変換音韻の抽出
            kanaSyllable = kanaStr.substring(index, index + 2);
            recRomaji.addAll(convertRecursion(kanaStr, index + 2, system));
            romaList.addAll(createRomajiSyllableList(recRomaji, kanaSyllable, system));
        } else {
            // 直音
            // 変換音韻の抽出
            if (system == RomajiSystem.HEPBURN && isMM(kanaStr, index)) {
                // ヘボン式に限ったmb,mm,mpの音韻
                kanaSyllable = kanaStr.substring(index, index + 2);
                recRomaji.addAll(convertRecursion(kanaStr, index + 2, system));
                romaList.addAll(createRomajiSyllableList(recRomaji, kanaSyllable, system));
            }
            // 共通
            kanaSyllable = kanaStr.substring(index, index + 1);
            recRomaji.addAll(convertRecursion(kanaStr, index + 1, system));
            romaList.addAll(createRomajiSyllableList(recRomaji, kanaSyllable, system));
        }
        return romaList;
    }

    /**
     * 仮名の音韻を元にローマ字の音韻リストを作成する
     * @param recRomaji 連結するローマ字リスト
     * @param kanaSyllable 仮名文字の音韻
     * @param system 変換システム
     * @return
     */
    private static List<String> createRomajiSyllableList(List<String> recRomaji, String kanaSyllable, RomajiSystem system) {
        List<String> romaList = new ArrayList<>();
        String[] romajiSyllables = Syllable.getRomajiSyllable(kanaSyllable, system);
        for (String romajiSyllable : romajiSyllables) {
            if (recRomaji.size() == 0) {
                romaList.add(romajiSyllable);
            } else {
                for (String childStr : recRomaji) {
                    romaList.add(romajiSyllable + childStr);
                }
            }
        }
        return romaList;
    }

    /**
     * 文字列の指定した位置にある音韻が拗音かどうかを判定する
     * @param kanaStr 対象文字列
     * @param index 音韻の位置
     * @param system 変換タイプ
     * @return
     */
    private static boolean isYouOn(final String kanaStr, int index, RomajiSystem system) {
        // 拗音は2文字必要
        if ((kanaStr.length() - index) < 2) {
            return false;
        }
        String kanaSyllable = kanaStr.substring(index, index + 2);
        return Syllable.isKanaYouSyllable(kanaSyllable, system);
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