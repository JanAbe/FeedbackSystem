package cleaners;

public class Clean {

    /**
     * <p>Clean an uncleaned string by making it
     * all lowercase and stripping the leading and
     * trailing whitespaces</p>
     * @param uncleanedArg String
     * @return the cleaned version of the given argument
     */
    public static String string(String uncleanedArg) {
        var cleanedString = "";
        cleanedString = uncleanedArg.toLowerCase();
        cleanedString = cleanedString.strip();

        return cleanedString;
    }
}
