package ua.nure.lozychenko.facultative.constants;

public class Filters {
    public static final String ALL = "all";
    public static final String JOINED = "joined";
    public static final String JOINED_STARTED = "joined_started";
    public static final String JOINED_NOT_STARTED = "joined_not_started";

    public static String[][] getFilters() {
        return new String[][]{
                {"all", "All"},
                {"joined", "Joined"},
                {"joined_started", "Started"},
                {"joined_not_started", "Not Started"}
        };
    }
}
