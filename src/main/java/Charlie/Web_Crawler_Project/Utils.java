package Charlie.Web_Crawler_Project;


/**
 * This is the Utility class for various functions to be use in the system
 *
 * @author Haikal
 * @version 1.0
 */
public class Utils {

    /**
     * Is empty boolean.
     *
     * @param strings the strings
     * @return the boolean
     */
// Function to check if each input give is null or not
    public static boolean isEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
