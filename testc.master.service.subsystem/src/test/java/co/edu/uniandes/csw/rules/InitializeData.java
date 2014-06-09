/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.rules;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import co.edu.uniandes.csw.testc.logic.dto.TestCDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import uk.co.jemos.podam.api.PodamFactory;
import uk.co.jemos.podam.api.PodamFactoryImpl;

/**
 *
 * @author asistente
 */
public class InitializeData {

    private static String DB_URL = "jdbc:derby://localhost:1527/sun-appserv-samples;create=true;";

    public static void initDataShared(int number) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            Connection conn = DriverManager.getConnection(DB_URL);

            PodamFactory factory = new PodamFactoryImpl();

            for (int i = 0; i < number; i++) {
                TestBDTO entity = factory.manufacturePojo(TestBDTO.class);

                Statement stmt = conn.createStatement();

                String sql = "INSERT INTO TESTBENTITY (ID,NAME,ATR2) "
                        + "VALUES (" + i + ",'" + entity.getName() + "', '" + entity.getAtr2() + "')";

                stmt.executeUpdate(sql);
                //Se almacenan en el arreglo de datos

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<TestBDTO> fetchData(int number,TestCDTO master) {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            Connection conn = DriverManager.getConnection(DB_URL);

            PodamFactory factory = new PodamFactoryImpl();

            Statement stmt = conn.createStatement();

            String sql = "INSERT INTO TESTCENTITY (ID,NAME,ATR3) "
                    + "VALUES ("+master.getId()+",'"+master.getName()+"', '"+master.getAtr3()+"')";

            stmt.executeUpdate(sql);
            ArrayList<TestBDTO> detail = new ArrayList<TestBDTO>();
            for (int i = 0; i < number; i++) {
                TestBDTO entity = factory.manufacturePojo(TestBDTO.class);

                sql = "INSERT INTO TESTBENTITY (ID,NAME,ATR2) "
                        + "VALUES (" + i + ",'" + entity.getName() + "', '" + entity.getAtr2() + "')";

                stmt.executeUpdate(sql);

                sql = "INSERT INTO TESTCTESTBENTITY (TESTBID,TESTCID) "
                        + "VALUES (" + i + ","+master.getId()+")";

                stmt.executeUpdate(sql);
                detail.add(entity);

            }
            conn.close();
            return detail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    
     public static ArrayList<TestBDTO> generateTestB(int number) {
        try {
           

            PodamFactory factory = new PodamFactoryImpl();

            
            ArrayList<TestBDTO> detail = new ArrayList<TestBDTO>();
            for (int i = 0; i < number; i++) {
                TestBDTO entity = factory.manufacturePojo(TestBDTO.class);

              
                detail.add(entity);

            }
           
            return detail;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

     
    public static void flushDataShared() {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver").newInstance();
            //Get a connection
            Connection conn = DriverManager.getConnection(DB_URL);

            Statement stmt = conn.createStatement();

            String sql = "DELETE FROM TESTCTESTBENTITY";

            stmt.executeUpdate(sql);

            sql = "DELETE FROM TESTBENTITY";

            stmt.executeUpdate(sql);
            sql = "DELETE FROM TESTCENTITY";

            stmt.executeUpdate(sql);
            //Se almacenan en el arreglo de datos

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
