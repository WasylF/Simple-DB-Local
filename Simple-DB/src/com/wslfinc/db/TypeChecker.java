package com.wslfinc.db;

import static com.wslfinc.db.Constants.*;

/**
 *
 * @author Wsl_F
 */
public class TypeChecker {

    /**
     * Check does db support that type
     *
     * @param type type for checking
     * @return does type belongs to supportable types
     */
    public static boolean checkTypeCaption(String type) {
        return DATA_TYPES.contains(type) || isStringNCaption(type);
    }

    /**
     * check does value matches to type
     *
     * @param value value for check
     * @param type supposed type
     * @return does value matches to type
     */
    public static boolean check(String value, String type) {
        if (value == null) {
            return false;
        }
        
        if (type.equals("integer")) {
            return isInteger(value);
        }

        if (type.equals("real")) {
            return isReal(value);
        }

        if (type.equals("char")) {
            return isChar(value);
        }

        if (type.equals("complexInteger")) {
            return isComplex(value, true);
        }

        if (type.equals("complexReal")) {
            return isComplex(value, false);
        }

        if (isStringNCaption(type)) {
            return isStringN(value, type);
        }

        if (type.equals("stringInvl")) {
            return isStringInvl(value);
        }

        return false;
    }

    private static int stringNLength(String type) {
        try {
            int n = Integer.valueOf(type.substring(7, type.length() - 1));
            return n;
        } catch (Exception ex) {
            System.err.println("Wrong type \"" + type + "\": " + ex.getMessage()
                    + '\n' + ex.toString());
            return -1;
        }
    }

    private static boolean isStringNCaption(String type) {
        if (type.startsWith("string[") && type.endsWith("]")) {
            int n = stringNLength(type);
            return n > 0 && n <= MAX_STRING_LENGTH;
        }
        return false;
    }

    private static boolean isStringInvl(String value) {
        return true;
    }

    /**
     *
     * @param value complex integer in format a;b = a + bi
     * @return does value match complex format pattern
     */
    private static boolean isComplex(String value, boolean isInt) {
        int i1 = value.lastIndexOf(';');
        int i2 = value.indexOf(';');
        if (i1 == -1 || i1 != i2) {
            return false;
        }

        String a = value.substring(0, i1 - 1);
        String b = value.substring(i1 + 1);

        if (isInt) {
            return isInteger(a) && isInteger(b);
        } else {
            return isReal(a) && isReal(b);
        }
    }

    private static boolean isStringN(String value, String type) {
        int l = stringNLength(type);
        return value.length() <= l && value.length() > 0;
    }

    private static boolean isChar(String value) {
        return value.length() == 1;
    }

    private static boolean isReal(String value) {
        try {
            Double.parseDouble(value);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    private static boolean isInteger(String value) {
        return isInteger(value, 10);
    }

    private static boolean isInteger(String value, int radix) {
        if (value.isEmpty()) {
            return false;
        }
        for (int i = 0; i < value.length(); i++) {
            if (i == 0 && value.charAt(i) == '-' && value.charAt(i) == '+') {
                if (value.length() == 1) {
                    return false;
                } else {
                    continue;
                }
            }
            if (Character.digit(value.charAt(i), radix) < 0) {
                return false;
            }
        }
        return true;
    }

}
