package ua.nure.lozychenko.facultative.servlet.filters;

public class Action {
    private String name;
    private String redirect;

    public Action(String name, String redirect) {
        this.name = name;
        this.redirect = redirect;
    }

    public String getName() {
        return name;
    }

    public String getRedirect() {
        return redirect;
    }
}
