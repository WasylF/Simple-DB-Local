package com.wslfinc.db;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author Wsl_F
 */
public class Constants {

    public static final Set<String> DATA_TYPES;
    public static final int MAX_STRING_LENGTH = 255;
    public static final String SUCCESSFUL_STATUS = "good";

    static {
        DATA_TYPES = new HashSet<>(Arrays.asList("integer", "real", "char",
                "stringInvl", "complexInteger", "complexReal"));
    }

}
