package cleaners;

public class Clean {

    public static String string(String s) {
        var cleanedString = "";
        cleanedString = s.toLowerCase();
        cleanedString = cleanedString.strip();

        return cleanedString;
    }
}
