/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zadanie.helpers;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author vikto
 */
public class Language {
     public enum LanguageEnum {

        English,

        German,

        Slovak,

        Unknown

    }


    public static double ICEnglish = 0.0665;

    public static double ICGerman = 0.076;

    public static double ICSlovak = 0.0603;


    private static final Map<LanguageEnum, Double> icRef = new HashMap<>();

    static {

        icRef.put(LanguageEnum.English, ICEnglish);

        icRef.put(LanguageEnum.German, ICGerman);

        icRef.put(LanguageEnum.Slovak, ICSlovak);

    }


    public static LanguageEnum guessLanguage(String txt) {

        Collection<Double> values = TextStatistics.readNgram(txt, 1, false).values();

        Double stat[] = new Double[values.size()];

        values.toArray(stat);

        double ic = TextStatistics.IndexOfCoincidence(stat, txt.length());


        LanguageEnum bestLang = LanguageEnum.Unknown;

        double minDistance = 1;

        for (Map.Entry<LanguageEnum, Double> entrySet : icRef.entrySet()) {

            Language.LanguageEnum key = entrySet.getKey();

            Double value = entrySet.getValue();

            double dist = java.lang.Math.abs(ic - value);

            if (dist < minDistance) {

                minDistance = dist;

                bestLang = key;

            }

        }

        return bestLang;

    }
}
