package utils;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@SuppressWarnings("MultipleStringLiterals")
public final class StringUtils {

    private StringUtils() {
    }

    public static String transliterate(final String message) {
        final List<String> abcCyr = Arrays.asList("а", "б", "в", "г", "д", "е", "ё", "ж", "з", "и", "й", "к",
                "л", "м", "н", "о", "п", "р", "с", "т", "у", "ф", "х", "ц", "ч", "ш", "щ", "ъ", "ы", "ь", "э",
                "ю", "я", "А", "Б", "В", "Г", "Д", "Е", "Ё", "Ж", "З", "И", "Й", "К", "Л", "М", "Н", "О", "П",
                "Р", "С", "Т", "У", "Ф", "Х", "Ц", "Ч", "Ш", "Щ", "Ъ", "Ы", "Ь", "Э", "Ю", "Я");
        final List<String> abcLat = Arrays.asList("a", "b", "v", "g", "d", "e", "e", "zh", "z", "i", "i", "k",
                "l", "m", "n", "o", "p", "r", "s", "t", "u", "f", "h", "ts", "ch", "sh", "sch", "", "i", "",
                "e", "yu", "ya", "A", "B", "V", "G", "D", "E", "E", "ZH", "Z", "I", "I", "K", "L", "M", "N",
                "O", "P", "R", "S", "T", "U", "F", "H", "TS", "CH", "SH", "SCH", "", "I", "", "E", "YU", "YA");

        return message.chars()
                .mapToObj(c -> String.valueOf((char) c))
                .map(c -> {
                    final int i = abcCyr.indexOf(c);
                    if (i > 0) {
                        return abcLat.get(i);
                    } else {
                        return c;
                    }
                }).collect(Collectors.joining(""));
    }

}
