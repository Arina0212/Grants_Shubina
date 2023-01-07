package org.example;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Parser {
    private final String path;
    private final List<Grant> grants = new ArrayList<>();
    private final HashSet<String>  types = new HashSet<>();
    private final HashSet<String> streets = new HashSet<>();
    private final HashSet<String> years = new HashSet<>();

    public Parser(String path) {
        this.path = path;
    }

    public void loadGrantsFromFile() {
        var file = Paths.get(path);
        if (!Files.exists(file)) throw new IllegalArgumentException();
        try {
            var lines = Files.readAllLines(file);
            lines.remove(0);
            for (var line : lines) {
                var row = line.split(",");
                row[2]=row[2].replaceAll("$","");
                if (row.length != 6) {
                    continue;
                }
                grants.add(
                        new Grant(
                                row[0], row[1], Double.parseDouble(row[2]),
                                row[3], row[4], Integer.parseInt(row[5])
                        )
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void generateFields() {
        for (var r : grants) {
            streets.add(r.street);
            types.add(r.type);
            years.add(r.year);

        }
    }

    public List<Grant> getGrants() {
        return grants;
    }

    public Set<String> getTypes() {
        return types;
    }

    public Set<String> getYears() {
        return years;
    }

    public Set<String> getStreets() {
        return streets;
    }

}
