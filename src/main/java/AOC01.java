import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

public class AOC01 {

    private static final Map<String, String> wordToDigit = new HashMap<>();

    static {
        wordToDigit.put("one", "1");
        wordToDigit.put("two", "2");
        wordToDigit.put("three", "3");
        wordToDigit.put("four", "4");
        wordToDigit.put("five", "5");
        wordToDigit.put("six", "6");
        wordToDigit.put("seven", "7");
        wordToDigit.put("eight", "8");
        wordToDigit.put("nine", "9");
    }

    public static void main(String[] args) throws IOException {
        int sum = Files.lines(Paths.get("./data/input01.txt")).mapToInt(AOC01::getSumOfDigitsFromLine).sum();
        System.out.println(sum);
    }

    /**
     * Parses the first and last digits found in the given string and returns their numeric value as an integer.
     * If no digits are found, returns 0.
     *
     * @param s The input string to parse.
     * @return The numeric value obtained by concatenating the first and last digits found in the string.
     */
    public static int parseFirstAndLastDigits(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            if (!Character.isDigit(s.charAt(i)))
                i++;
            if (!Character.isDigit(s.charAt(j)))
                j--;
            if (Character.isDigit(s.charAt(i)) && Character.isDigit(s.charAt(j)))
                break;
        }
        return Integer.parseInt(s.charAt(i) + "" + s.charAt(j));
    }

    /**
     * Gets the sum of the first and last digits or their word representations in the given string.
     *
     * @param s The input string to process.
     * @return The sum of the first and last digits or -1 if it isn't found.
     */
    public static int getSumOfDigitsFromLine(String s) {
        String firstDigit = getFirstDigit(s);
        String lastDigit = getLastDigit(s);
        if (firstDigit == null || lastDigit == null)
            return -1;

        return Integer.parseInt(getFirstDigit(s) + getLastDigit(s));
    }

    /**
     * Finds the first digit or its word representation in the given string.
     *
     * @param s The input string to search.
     * @return The first digit found in the string or null if not found.
     */
    public static String getFirstDigit(String s) {
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                return String.valueOf(ch);
            }
            for (int len = 3; len <= 5 && i + len <= s.length(); len++) {
                String substring = s.substring(i, i + len);
                if (wordToDigit.containsKey(substring)) {
                    return wordToDigit.get(substring);
                }
            }
        }
        return null;
    }

    /**
     * Finds the last digit or its word representation in the given string.
     *
     * @param s The input string to search.
     * @return The last digit found in the string or null if not found.
     */
    public static String getLastDigit(String s) {
        for (int i = s.length() - 1; i >= 0; i--) {
            char ch = s.charAt(i);
            if (Character.isDigit(ch)) {
                return String.valueOf(ch);
            }
            for (int len = 3; len <= 5 && i - len + 1 >= 0; len++) {
                String substring = s.substring(i - len + 1, i + 1);
                if (wordToDigit.containsKey(substring)) {
                    return wordToDigit.get(substring);
                }
            }
        }
        return null;
    }

}