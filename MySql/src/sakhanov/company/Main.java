package sakhanov.company;




import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import com.mysql.fabric.proto.xmlrpc.XmlRpcClient;
import com.mysql.jdbc.*;
public class Main {

    // JDBC URL, username and password of MySQL server
   // private static final String url = "jdbc:mysql://localhost:3306/shops";
    private static final String url = "jdbc:mysql://localhost:3306/world";
    private static final String user = "root";
    private static final String password = "1qaz2wsX";
    private static final String className="sun.jdbc.odbc.jdncOdbcDriver";

    // JDBC variables for opening and managing connection
    private static Connection con;
    private static Statement stmt;
    private static ResultSet rs;

    //    создадим тестовую БД в памяти
    static Connection createDB()  {
        Connection conn = null;

          return conn;
    }
    private static  void Coonect(){
        Connection conn = createDB();
//        final String DBDRIVER = "org.gjt.mm.mysql.Driver";
//        try {
//            Class.forName("com.mysql.jdbs.Driver");
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//        try{
//            //Class.forName(DBDRIVER);
//
//            //con = DriverManager.getConnection(url, user, password);
//            //Connection con1 = ConnectionPool.getInstance().getConnection();
//            // getting Statement object to execute query
//            //stmt = con.createStatement();
//
//            System.out.print("Ура подключились");
//        }catch (SQLException sqlEx){
//            sqlEx.printStackTrace();
//        }
    }
    public static void main(String[] args) {
	// write your code here
        System.out.print("Подключение к MySQL\n");
        String query = "SELECT * FROM world.city;";
        try {
            //Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/world", "root", "1qaz2wsX");
            Connection con = DriverManager.getConnection(url, user, password);
            // getting Statement object to execute query
            stmt = con.createStatement();

            // executing SELECT query
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                String count = rs.getString(2);
                System.out.println("ciry : " + count);
            }

        }catch(Exception ex){
            System.out.println("Connection failed...");

            System.out.println(ex);
        }
        try { con.close(); } catch(SQLException se) { /*can't do anything */ }
        try { stmt.close(); } catch(SQLException se) { /*can't do anything */ }
        try { rs.close(); } catch(SQLException se) { /*can't do anything */ }

        System.out.println("OK!!!!!!!!!!!!!!!!!!!!!!!!");

    }
}
