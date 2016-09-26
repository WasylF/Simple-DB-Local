package com.wslfinc.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.wslfinc.db.Constants.*;
import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author Wsl_F
 */
public class Table implements Serializable {

    private final String name;
    private final ArrayList<String> columnNames;
    private final ArrayList<String> columnTypes;
    private final Map<String, Integer> columnNumbers;
    private final ArrayList<Row> rows;
    private boolean finalizedTable;

    /**
     * creates table with specified name
     *
     * @param name caption of a new table
     */
    public Table(String name) {
        this.name = name;
        this.columnNames = new ArrayList<>();
        this.columnTypes = new ArrayList<>();
        this.columnNumbers = new HashMap<>();
        this.rows = new ArrayList<>();
        this.finalizedTable = false;
    }

    /**
     *
     * @return name of the table
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param caption
     * @param type
     * @return result of operation: good - operation successefully finished
     */
    public String addColumn(String caption, String type) {
        if (finalizedTable) {
            return "This table (" + name + ") alredy finalized!";
        }

        if (columnNumbers.containsKey(caption)) {
            return "Tale alredy containts column with caption \"" + caption + "\"";
        }

        if (!TypeChecker.checkTypeCaption(type)) {
            return "DB doesn't support \"" + type + "\" data type";
        }

        int num = columnNames.size();
        columnNames.add(caption);
        columnTypes.add(type);
        columnNumbers.put(caption, num);

        return SUCCESSFUL_STATUS;
    }

    /**
     * Finalized table. After calling this method you couldn't add any column to
     * the table, but before calling this method you couldn't add any row to the
     * table.
     */
    public void finalizeTable() {
        finalizedTable = true;
    }

    /**
     * add row to the table if possible
     *
     * @param row row to be added to the table
     * @return status or error message
     */
    public String addRow(List<String> row) {
        if (!finalizedTable) {
            return "You couln't add any row before finalize table!";
        }

        int columnNum = columnNames.size();
        if (row.size() != columnNum) {
            return "wrong number of columns";
        }

        for (int i = 0; i < columnNum; i++) {
            if (!TypeChecker.check(row.get(i), columnTypes.get(i))) {
                return row.get(i) + " doesn't match " + columnTypes.get(i) + "data type";
            }
        }

        Row newRow = new Row(row);
        rows.add(newRow);

        return SUCCESSFUL_STATUS;
    }

    public ArrayList<Row> getRows() {
        return rows;
    }

    public ArrayList<String> getColumnNames() {
        return columnNames;
    }

    public boolean isFinalized() {
        return finalizedTable;
    }

    public static Table difference(Table a, Table b) {
        boolean flag = false;
        if (a.columnTypes.size() != b.columnTypes.size()) {
            flag = true;
        }
        if (!flag) {
            for (int i = a.columnTypes.size() - 1; !flag && i >= 0; i--) {
                if (!a.columnTypes.get(i).equals(b.columnTypes.get(i))) {
                    flag = true;
                }
            }
        }

        Table result = new Table(a.getName() + "-" + b.getName());
        for (int i = 0; i < a.columnTypes.size(); i++) {
            result.addColumn(a.columnNames.get(i), a.columnTypes.get(i));
        }
        result.finalizeTable();

        if (flag) {
            for (int i = 0; i < a.rows.size(); i++) {
                result.addRow(Arrays.asList(a.rows.get(i).toArray()));
            }
        } else {
            for (int i = 0; i < a.rows.size(); i++) {
                boolean contains = false;
                for (int j = 0; !contains && j < b.rows.size(); j++) {
                    boolean equals = true;
                    for (int k = a.columnTypes.size() - 1; equals && k >= 0; k--) {
                        if (!a.rows.get(i).get(k).equals(b.rows.get(j).get(k))) {
                            equals = false;
                        }
                        contains |= equals;
                    }
                }
                if (!contains) {
                    result.addRow(Arrays.asList(a.rows.get(i).toArray()));
                }
            }

        }
        return result;
    }

    public static Table cartesianProduct(Table a, Table b) {
        Table result = new Table(a.getName() + "*" + b.getName());

        for (int i = 0; i < a.columnTypes.size(); i++) {
            result.addColumn("#" + a.columnNames.get(i), a.columnTypes.get(i));
        }

        for (int i = 0; i < b.columnTypes.size(); i++) {
            result.addColumn("$" + b.columnNames.get(i), b.columnTypes.get(i));
        }

        result.finalizeTable();

        for (int i = 0; i < a.rows.size(); i++) {
            String[] rowA = a.rows.get(i).toArray();

            for (int j = 0; j < b.rows.size(); j++) {
                String[] rowB = b.rows.get(j).toArray();
                ArrayList<String> rowResult = new ArrayList<>(Arrays.asList(rowA));
                rowResult.addAll(new ArrayList<>(Arrays.asList(rowB)));

                result.addRow(rowResult);
            }
        }

        return result;
    }
}
