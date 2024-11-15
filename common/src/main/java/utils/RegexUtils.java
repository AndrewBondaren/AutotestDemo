package utils;


import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public final class RegexUtils {

    private static final String PATTERN_NUMBER = "\\d+";

    private RegexUtils() {
    }

    public static String removeWhiteSpaces(final String text) {
        return text.replaceAll("\\s+", "");
    }

    public static String removeSemicolons(final String text) {
        return text.replaceAll(";", "");
    }

    public static String removeColons(final String text) {
        return text.replaceAll(":", "");
    }

    public static String getStringWithMaxNumber(final List<String> textList) {
        if (textList != null && !textList.isEmpty()) {
            final HashMap<Integer, String> map = new HashMap<>();
            for (String s : textList) {
                map.put(getNumber(s, PATTERN_NUMBER), s);
            }
            final List<Integer> list = new ArrayList<>(map.keySet());
            return map.get(list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()).get(0));
        } else {
            return "";
        }
    }

    public static Integer getIndexOf(final String text, final String regex) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(text);
        if (m.find()) {
            return m.start();
        } else {
            return -1;
        }
    }

    public static String replaceNumberWithIncrement(final String text) {
        return text.replaceFirst(PATTERN_NUMBER, String.valueOf(getNumber(text, PATTERN_NUMBER) + 1));
    }

    public static int getNumberLength(final String text) {
        return String.valueOf(getNumber(text, PATTERN_NUMBER)).length();
    }

    public static Integer getNumber(final String text, final String regex) {
        final Pattern p = Pattern.compile(regex);
        final Matcher m = p.matcher(text);
        if (m.find()) {
          return Integer.valueOf(m.group());
        } else {
            return -1;
        }
    }

}
