package com.ppm.clarity.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;





public class DbUtil {



    private static Connection connection = null;



    public static Connection getConnection() {

        if (connection != null)

            return connection;

        else {

            try {

                Properties prop = new Properties();

               // InputStream inputStream = DbUtil.class.getClassLoader().getResourceAsStream("C:/Users/RF048808/workspace/SchedulerServices/src/db.properties");

                //prop.load(inputStream);

                String driver = "oracle.jdbc.OracleDriver";

                String url = "dbc:oracle:thin:@localhost:1521:xe";

                String user = "hr";

                String password = "password";

                Class.forName(driver);

                connection = DriverManager.getConnection(url, user, password);

            } catch (ClassNotFoundException e) {

                e.printStackTrace();

            } catch (SQLException e) {

                e.printStackTrace();

            } 

            return connection;

        }



    }

}
