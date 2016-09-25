package com.wslfinc.db;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wsl_F
 */
public class Row implements Serializable {

    ArrayList<String> items;

    Row(List<String> row) {
        this.items = new ArrayList<>(row);
    }

    public String get(int index) {
        return items.get(index);
    }

    public void set(int index, String value) {
        items.set(index, value);
    }

    public String[] toArray() {
        String[] array = new String[items.size()];
        array = items.toArray(array);
        return array;
    }
}
