package academy.utility;

import java.security.SecureRandom;

public final class IdentityGenerator {

    private static final String ALPHANUMERIC =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final SecureRandom RANDOM = new SecureRandom();
    private static final int SUFFIX_LENGTH = 5; // 2-char prefix + 5 = 7 total

    private IdentityGenerator() {}

    public static String generatePlayerUsername() {
        return "PL" + randomSuffix();
    }

    public static String generateCoachUsername() {
        return "CH" + randomSuffix();
    }

    private static String randomSuffix() {
        StringBuilder sb = new StringBuilder(SUFFIX_LENGTH);
        for (int i = 0; i < SUFFIX_LENGTH; i++) {
            sb.append(ALPHANUMERIC.charAt(RANDOM.nextInt(ALPHANUMERIC.length())));
        }
        return sb.toString();
    }
}