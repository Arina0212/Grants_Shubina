package org.example;

import static org.example.Constants.*;
public class Main {
    public static void main(String[] args) {

        var parser = new Parser(CSV_PATH);
        parser.loadGrantsFromFile();
        parser.generateFields();
        var years = parser.getYears().stream()
                .sorted()
                .toList();

        var streets = parser.getStreets();
        var types = parser.getTypes();
        var grants = parser.getGrants();
        for (var q : grants ) {
            System.out.println(q);
        }
        DBManager.createDB();

        for (var y : years)
            DBManager.insertCategories("years", y);

        for (var y : streets)
            DBManager.insertCategories("streets", y);

        for (var y : types)
            DBManager.insertCategories("types", y);

        for (Grant z : grants)
            DBManager.insertGrants(z);
  }
}