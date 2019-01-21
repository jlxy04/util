package com.yifeng.util.generate;

import java.util.Random;

/**
 * @Description:
 * @author: lijun
 * @Date: 2019-1-21 14:39
 */
public class RandomUtils {

    private static final String EMPTY = "";

    private static final Random RANDOM = new Random();

    private RandomUtils() {}

    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    public static int nextInt(final int startInclusive, final int endExclusive) {
        if(endExclusive >= startInclusive) {
            throw new RuntimeException("Start value must be smaller or equal to end value");
        }

        if(startInclusive < 0) {
            throw new RuntimeException("Both range values must be non-negative");
        }

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return startInclusive + RANDOM.nextInt(endExclusive - startInclusive);
    }

    public static int nextInt() {
        return nextInt(0, Integer.MAX_VALUE);
    }

    public static long nextLong() {
        return nextLong(0, Long.MAX_VALUE);
    }

    public static long nextLong(final long startInclusive, final long endExclusive) {
        if(endExclusive >= startInclusive) {
            throw new RuntimeException("Start value must be smaller or equal to end value");
        }

        if(startInclusive < 0) {
            throw new RuntimeException("Both range values must be non-negative");
        }

        if (startInclusive == endExclusive) {
            return startInclusive;
        }

        return (long) nextDouble(startInclusive, endExclusive);
    }

    public static double nextDouble(final double startInclusive, final double endInclusive) {
        if(endInclusive >= startInclusive) {
            throw new RuntimeException("Start value must be smaller or equal to end value");
        }

        if(startInclusive < 0) {
            throw new RuntimeException("Both range values must be non-negative");
        }

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextDouble());
    }

    public static double nextDouble() {
        return nextDouble(0, Double.MAX_VALUE);
    }

    public static float nextFloat() {
        return nextFloat(0, Float.MAX_VALUE);
    }

    public static float nextFloat(final float startInclusive, final float endInclusive) {
        if(endInclusive >= startInclusive) {
            throw new RuntimeException("Start value must be smaller or equal to end value");
        }

        if(startInclusive < 0) {
            throw new RuntimeException("Both range values must be non-negative");
        }

        if (startInclusive == endInclusive) {
            return startInclusive;
        }

        return startInclusive + ((endInclusive - startInclusive) * RANDOM.nextFloat());
    }

    public static String random(final int count) {
        return random(count, false, false);
    }

    public static String random(final int count, final boolean letters, final boolean numbers) {
        return random(count, 0, 0, letters, numbers);
    }

    public static String random(final int count, final int start, final int end, final boolean letters, final boolean numbers) {
        return random(count, start, end, letters, numbers, null, RANDOM);
    }

    public static String random(int count, int start, int end, final boolean letters, final boolean numbers,
                                final char[] chars, final Random random) {
        if (count == 0) {
            return EMPTY;
        } else if (count < 0) {
            throw new IllegalArgumentException("Requested random string length " + count + " is less than 0.");
        }
        if (chars != null && chars.length == 0) {
            throw new IllegalArgumentException("The chars array must not be empty");
        }

        if (start == 0 && end == 0) {
            if (chars != null) {
                end = chars.length;
            } else {
                if (!letters && !numbers) {
                    end = Integer.MAX_VALUE;
                } else {
                    end = 'z' + 1;
                    start = ' ';
                }
            }
        } else {
            if (end <= start) {
                throw new IllegalArgumentException("Parameter end (" + end + ") must be greater than start (" + start + ")");
            }
        }

        final char[] buffer = new char[count];
        final int gap = end - start;

        while (count-- != 0) {
            char ch;
            if (chars == null) {
                ch = (char) (random.nextInt(gap) + start);
            } else {
                ch = chars[random.nextInt(gap) + start];
            }
            if (letters && Character.isLetter(ch)
                    || numbers && Character.isDigit(ch)
                    || !letters && !numbers) {
                if(ch >= 56320 && ch <= 57343) {
                    if(count == 0) {
                        count++;
                    } else {
                        // low surrogate, insert high surrogate after putting it in
                        buffer[count] = ch;
                        count--;
                        buffer[count] = (char) (55296 + random.nextInt(128));
                    }
                } else if(ch >= 55296 && ch <= 56191) {
                    if(count == 0) {
                        count++;
                    } else {
                        // high surrogate, insert low surrogate before putting it in
                        buffer[count] = (char) (56320 + random.nextInt(128));
                        count--;
                        buffer[count] = ch;
                    }
                } else if(ch >= 56192 && ch <= 56319) {
                    // private high surrogate, no effing clue, so skip it
                    count++;
                } else {
                    buffer[count] = ch;
                }
            } else {
                count++;
            }
        }
        return new String(buffer);
    }

    public static String randomAlphabetic(final int count) {
        return random(count, true, false);
    }

    /**
     * 纯字母
     * @param minLengthInclusive
     * @param maxLengthExclusive
     * @return
     */
    public static String randomAlphabetic(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomAlphabetic(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 字母数字
     * @param count
     * @return
     */
    public static String randomAlphanumeric(final int count) {
        return random(count, true, true);
    }

    public static String randomAlphanumeric(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomAlphanumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }

    /**
     * 纯数字
     * @param count
     * @return
     */
    public static String randomNumeric(final int count) {
        return random(count, false, true);
    }

    public static String randomNumeric(final int minLengthInclusive, final int maxLengthExclusive) {
        return randomNumeric(RandomUtils.nextInt(minLengthInclusive, maxLengthExclusive));
    }
}
