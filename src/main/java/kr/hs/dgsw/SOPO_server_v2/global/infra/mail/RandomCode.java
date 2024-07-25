package kr.hs.dgsw.SOPO_server_v2.global.infra.mail;

import java.util.Random;

public class RandomCode {
    private static final int LEFT_LIMIT_DIGITS = 48; // numeral '0'
    private static final int RIGHT_LIMIT_DIGITS = 57; // numeral '9'
    private static final int LEFT_LIMIT_UPPERCASE_LETTERS = 65; // letter 'A'
    private static final int RIGHT_LIMIT_UPPERCASE_LETTERS = 90; // letter 'Z'
    private static final int TARGET_STR_LENGTH = 6;
    private static final Random random = new Random();

    public static String generate() {
        StringBuilder stringBuilder = new StringBuilder(TARGET_STR_LENGTH);

        int firstDigit = random.nextInt(RIGHT_LIMIT_DIGITS - LEFT_LIMIT_DIGITS + 1) + LEFT_LIMIT_DIGITS;
        stringBuilder.append((char) firstDigit);

        for (int i = 1; i < TARGET_STR_LENGTH; i++) {
            int randomNumber = random.nextBoolean() ?
                    random.nextInt(RIGHT_LIMIT_DIGITS - LEFT_LIMIT_DIGITS + 1) + LEFT_LIMIT_DIGITS :
                    random.nextInt(RIGHT_LIMIT_UPPERCASE_LETTERS - LEFT_LIMIT_UPPERCASE_LETTERS + 1) + LEFT_LIMIT_UPPERCASE_LETTERS;
            stringBuilder.append((char) randomNumber);
        }

        return stringBuilder.toString();
    }
}
