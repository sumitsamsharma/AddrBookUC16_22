import java.sql.*;
import java.util.Enumeration;

public class ConnectionCreate {
    Connection con = null;
    public Connection makeConnection() {
        String jdbcURL = "jdbc:mysql://localhost:3306/addrbook?allowPublicKeyRetrieval=true&useSSL=false";
        String userName = "root";
        String password = "Olaybhai";
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Driver Loaded.");
            System.out.println("Connecting to the Database... " + jdbcURL);
            con = DriverManager.getConnection(jdbcURL, userName, password);
            System.out.println("Connection was successful.");
        }catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find the driver", e);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
        return con;
    }


    private static void listDrivers() {
        Enumeration<Driver> driverList = DriverManager.getDrivers();
        while (driverList.hasMoreElements()) {
            Driver driverClass = driverList.nextElement();
            System.out.println(driverClass.getClass().getName());
        }
    }


}