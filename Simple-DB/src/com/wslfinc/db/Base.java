package com.wslfinc.db;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author Wsl_F
 */
public class Base implements Serializable {

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

    void serialize(String PATH_DBMS) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
