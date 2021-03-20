package Charlie.Web_Crawler_Project;

public class Utils {

    public static boolean isEmpty(String... strings) {
        for (String string : strings) {
            if (string == null || string.isEmpty()) {
                return true;
            }
        }
        return false;
    }
}
