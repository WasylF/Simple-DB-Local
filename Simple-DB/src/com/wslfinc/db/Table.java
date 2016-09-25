package com.wslfinc.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static com.wslfinc.db.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class Table {

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
}
