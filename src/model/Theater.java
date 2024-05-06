package model;

public class Theater {
    private String name;
    private String location;
    private boolean visibility;

    public Theater(String name, String location, boolean visibility) {
        this.name = name;
        this.location = location;
        this.visibility = visibility;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public boolean isVisibility() {
        return visibility;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }
}
