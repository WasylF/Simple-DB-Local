package com.wslfinc.db;

import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Wsl_F
 */
public class Base {

    private final Map<String, Table> tables;
    private final String name;

    public Base(String name) {
        this.name = name;
        this.tables = new TreeMap<>();
    }

    public void createTable(String tableName) {
        Table newTable = new Table(tableName);
        tables.put(tableName, newTable);
    }

    public Table getTable(String tableName) {
        if (!tables.containsKey(tableName)) {
            return null;
        }
        return tables.get(tableName);
    }

    public void removeTable(String tableName) {
        tables.remove(tableName);
    }

    public String getName() {
        return name;
    }

}
