package utils;

import com.github.javafaker.Faker;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static utils.RegexUtils.removeColons;


@SuppressWarnings({"all"})
public final class RandomUtils {

    private static AtomicInteger HOTSPOT_NAME_COUNTER = new AtomicInteger(0);

    private static AtomicInteger HOTSPOT_SSID_COUNTER = new AtomicInteger(0);

    private static AtomicInteger NAME_COUNTER = new AtomicInteger(0);
    private static final Locale RU_LOCALE = new Locale("ru");
    private static final Locale EN_LOCALE = new Locale("en");

    private static final SecureRandom RANDOM = new SecureRandom();

    @SuppressWarnings("PMD")
    //CHECKSTYLE:OFF
    private static final String cyrillicCharacters = "АБВГДЕЁЖЗИЙКЛМНОПРСТУФХЦЧШЩЪЫЬЭЮЯ";
    //CHECKSTYLE:ON

    private RandomUtils() throws IllegalAccessException {
        throw new IllegalAccessException("Utility class!");
    }

    /**
     * Faker methods.
     */
    public static Faker getRuFaker() {
        return Faker.instance(RU_LOCALE);
    }
    public static Faker getEnFaker() {
        return Faker.instance(EN_LOCALE);
    }

    /**
     * Fast and dirty method to generate something that looks like middle russian name.
     * @return middle name.
     */
    //CHECKSTYLE:OFF
    public static String middleName() {
        return (RandomUtils.getRuFaker().name().firstName()
                .replaceFirst("[аяй]$", "")
                +
                RandomUtils.getRandomListElement(Arrays.asList("ович", "овна")))
                .replace("ио", "ие")
                .replace("ьо", "ие")
                .replace("ео", "ее");
    }

    public static String macAddress() {
        return RandomUtils.getRuFaker().internet().macAddress();
    }

    public static String appName() {
        return RandomUtils.getRuFaker().app().name();
    }
    //CHECKSTYLE:ON

    public static LocalDate toLocalDate(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

    public static String password(final Integer minLength, final Integer maxLength) {
        return getRuFaker().internet().password(minLength, maxLength, true, true, true);
    }

    public static LocalDateTime toLocalDateTime(final Date date) {
        return date.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static LocalDate localDateBirthday() {
        return toLocalDate(getRuFaker().date().birthday(18, 60));
    }

    public static LocalDateTime localDateTimeBirthday() {
        return toLocalDateTime(getRuFaker().date().birthday(18, 60));
    }

    public static String firstName() {
        return getRuFaker().name().firstName();
    }

    public static String lastName() {
        return getRuFaker().name().lastName();
    }

    public static String phoneNumber() {
        return getRuFaker().phoneNumber().phoneNumber().replaceAll("\\D", "");
    }

    public static String emailAddress() {
        return Faker.instance().internet().emailAddress();
    }

    @SuppressWarnings("PMD")
    public static String ssid() {
        int index = HOTSPOT_SSID_COUNTER.incrementAndGet();
        return Faker.instance().pokemon().name().toUpperCase(Locale.ROOT).charAt(0)
                + "-#'()*+"
                + Faker.instance().pokemon().name().toLowerCase(Locale.ROOT).charAt(0)
                + "$,-.:;<=>?_"
                + index
                + "_!%";
    }

    public static String hotspotName() {
        int index = HOTSPOT_NAME_COUNTER.incrementAndGet();
        return "Autotest_" + index + Faker.instance().address().streetAddress();
    }

    public static String randomName() {
        int index = NAME_COUNTER.incrementAndGet();
        return "Autotest_" + index;
    }

    public static <T extends Enum<?>> T randomEnumValue(final Class<T> enumClass) {
        return enumClass.getEnumConstants()[new Random().nextInt(enumClass.getEnumConstants().length)];
    }

    public static String passport() {
        return getRuFaker().numerify("#### ######");
    }

    public static Long randomPositiveNumber(final long limit) {
        return getRuFaker().random().nextLong(limit);
    }

    public static Long randomPositiveNumber() {
        return getRuFaker().random().nextLong();
    }

    public static Long randomPositiveNumberNonZero(final long limit) {
        final long newLimit = limit - 1;
        return getRuFaker().random().nextLong(newLimit) + 1;
    }

    public static String login() {
        return String.format(getEnFaker().pokemon().name() + randomPositiveNumber(777_777));
    }

    public static String clientName() {
        return String.format(generateRandomCharacter() + "\"Автотест " + getRuFaker().company().name() + "\"");
    }

    public static String url() {
        return String.format("https://www.%s.com", getRuFaker().pokemon().name());
    }

    public static List<String> appsUrl() {
        return Arrays.asList(
                "https://appstore.com/" + RegexUtils.removeWhiteSpaces(getRuFaker().beer().name()),
                "https://play.google.com/" + RegexUtils.removeWhiteSpaces(getRuFaker().beer().name()),
                "https://appgallery.huawei.com/" + RegexUtils.removeWhiteSpaces(getRuFaker().beer().name()),
                "https://store.nashstore.com/" + RegexUtils.removeWhiteSpaces(getRuFaker().beer().name()),
                "https://apps.rustore.com/" + RegexUtils.removeWhiteSpaces(getRuFaker().beer().name())
        );
    }

    public static String address() {
        return getRuFaker().address().streetAddress();
    }

    public static String city() {
        return getRuFaker().address().city();
    }

    public static <T> T getRandomListElement(final List<T> elementList) {
        return elementList.get((new Random()).nextInt(elementList.size()));
    }

    public static String randomPureMac() {
        return removeColons(getRuFaker().internet().macAddress());
    }

    public static <T extends Enum<?>> T getRandomEnumElement(final Class<T> randomEnum) {
        return randomEnum.getEnumConstants()[(new Random()).nextInt(randomEnum.getEnumConstants().length)];
    }

    public static String generateRandomCharacter() {
        final int randomCharIndex = RANDOM.nextInt(cyrillicCharacters.length());
        return String.valueOf(cyrillicCharacters.charAt(randomCharIndex));
    }

}
