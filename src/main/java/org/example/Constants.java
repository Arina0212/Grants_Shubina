package org.example;

public class Constants {
    //  PATHs
    public static final String JDBC_URL = "jdbc:sqlite:C:/Users/Арина/IJavaProjects/Grant/src/main/resources/Grants_db";
    public static final String CSV_PATH = "src/main/resources/Grants.csv";
    //  SQLITE QUERIES
    public static final String CREATE_YEARS_TABLE = "create table IF NOT EXISTS 'years' (year_id integer primary key autoincrement, year text);";
    public static final String CREATE_TYPES_TABLE = "create table IF NOT EXISTS 'types'    (type_id integer primary key autoincrement, type text);";
    public static final String CREATE_STREETS_TABLE = "create table IF NOT EXISTS 'streets' (street_id integer primary key autoincrement, street text);";
    public static final String CREATE_GRANTS_TABLE = """
            create table IF NOT EXISTS 'Grants'
            (
                name           text primary key,
                street         integer,
                grant_size     real,
                year           integer,
                type           integer,
                workplaces     integer,

                foreign key (street) references streets (street_id),
                foreign key (year) references years (year_id),
                foreign key (type) references types (type_id),
            );
            """;
    public static final String INSERT_GRANTS = "insert into grants values (?,?,?,?,?,?);";
    public static final String INSERT_STREETS = "select street_id from streets where street=?;";
    public static final String INSERT_YEARS = "select year_id from years where year=?;";
    public static final String INSERT_TYPES = "select type_id from types where type=?;";
    private Constants() {
    }
}
