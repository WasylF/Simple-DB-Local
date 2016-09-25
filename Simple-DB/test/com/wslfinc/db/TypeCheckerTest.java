package com.wslfinc.db;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Wsl_F
 */
public class TypeCheckerTest {

    public TypeCheckerTest() {
    }

    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption() {
        System.out.println("checkTypeCaption");
        String type = "integer";
        boolean expResult = true;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption2() {
        System.out.println("checkTypeCaption");
        String type = "int";
        boolean expResult = false;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption3() {
        System.out.println("checkTypeCaption");
        String type = "string[10]";
        boolean expResult = true;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption4() {
        System.out.println("checkTypeCaption");
        String type = "string[n]";
        boolean expResult = false;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }


    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption5() {
        System.out.println("checkTypeCaption");
        String type = "string[255]";
        boolean expResult = true;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkTypeCaption method, of class TypeChecker.
     */
    @Test
    public void testCheckTypeCaption6() {
        System.out.println("checkTypeCaption");
        String type = "string[0]";
        boolean expResult = false;
        boolean result = TypeChecker.checkTypeCaption(type);
        assertEquals(expResult, result);
    }

    /**
     * Test of check method, of class TypeChecker.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        String value = "123;234";
        String type = "complexInteger";
        boolean expResult = true;
        boolean result = TypeChecker.check(value, type);
        assertEquals(expResult, result);
    }

}
