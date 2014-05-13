/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package co.edu.uniandes.csw.common.web.test.utils;

import co.edu.uniandes.csw.testb.logic.dto.TestBDTO;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
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
