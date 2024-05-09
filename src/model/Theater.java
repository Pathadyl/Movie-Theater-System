package model;

public class Theater {
    private int id;
    private String name;
    private String location;
    private boolean visibility;

    public Theater(int id, String name, String location, boolean visibility) {
        this.id = id;
        this.name = name;
        this.location = location;
        this.visibility = visibility;
    }

    public int getId() {
        return id;
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
