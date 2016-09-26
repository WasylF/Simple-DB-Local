package com.wslfinc.db;

import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class TableTest {

    public TableTest() {
    }

    /**
     * Test of cartesianProduct method, of class Table.
     */
    @Test
    public void testCartesianProduct() {
        System.out.println("cartesianProduct");
        Table a = new Table("A");
        a.addColumn("1", "integer");
        a.finalizeTable();
        List<String> listA = new ArrayList<>();
        listA.add("1");
        a.addRow(listA);
        
        Table b = new Table("B");
        b.addColumn("2", "integer");
        b.finalizeTable();
        List<String> listB = new ArrayList<>();
        listB.add("2");
        b.addRow(listB);
        
        Table expResult = new Table("A*B");
        expResult.addColumn("#1", "integer");
        expResult.addColumn("$2", "integer");
        expResult.finalizeTable();
        List<String> listC = new ArrayList<>();
        listC.add("1");
        listC.add("2");
        expResult.addRow(listC);

        Table result = Table.cartesianProduct(a, b);
        assertEquals(expResult, result);
    }

}
