/*
 * Database Management System class
 */
package com.wslfinc.db;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import static com.wslfinc.db.Constants.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Wsl_F
 */
public class DBMS implements Serializable {

    private static DBMS instance;

    private DBMS() {
        bases = new HashMap<>();
    }

    public static DBMS getInstance() {
        if (instance == null) {
            if (!deserialize()) {
                instance = new DBMS();
            }
        }
        return instance;
    }

    private Map<String, Base> bases;

    public boolean serialize() {
        try {
            FileOutputStream fos = new FileOutputStream(PATH_DBMS);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            System.err.println("Unsuccessful serialization!" + ex.toString());
        }
        return false;
    }

    private static boolean deserialize() {
        try {
            FileInputStream fis = new FileInputStream("temp.out");
            ObjectInputStream oin = new ObjectInputStream(fis);
            instance = (DBMS) oin.readObject();
            return true;
        } catch (IOException | ClassNotFoundException ex) {
            System.err.println("Unsuccessful deserialization!" + ex.toString());
        }
        return false;
    }
}
