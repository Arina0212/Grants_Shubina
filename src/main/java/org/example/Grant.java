package org.example;

public class Grant {
    public String name;
    public String street;
    public double grant_size;
    public String year;
    public String type;
    public int workplaces;

    public Grant(String name, String street, double grant_size, String year, String type,
                  int workplaces) {
        this.name = name;
        this.street = street;
        this.grant_size = grant_size;
        this.year = year;
        this.type = type;
        this.workplaces = workplaces;
    }

    @Override
    public String toString() {
        return  this.name + ", " +
                this.street + ", " +
                this.grant_size + ", " +
                this.year + ", " +
                this.type + ", " +
                this.workplaces;
    }
}
