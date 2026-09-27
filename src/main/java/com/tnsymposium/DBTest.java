package com.tnsymposium;

import java.sql.Connection;

public class DBTest {
    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println("     TN TECHSYMPO JDBC TEST");
        System.out.println("====================================");
        try {
            Connection con=DBConnection.getConnection();
            System.out.println("1. JDBC CONNECTION : SUCCESS");
            System.out.println("2. DATABASE         : "+con.getCatalog());
            System.out.println("3. JDBC URL         : "+con.getMetaData().getURL());
            con.close();
            System.out.println("DATABASE TEST PASSED");
        } catch(Exception e) {
            System.out.println("DATABASE TEST FAILED");
            System.out.println("ERROR MESSAGE: "+e.getMessage());
            e.printStackTrace();
        }
    }
}
