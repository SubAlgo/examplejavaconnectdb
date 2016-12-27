
package javadbbasic;
import java.sql.*;

public class JavaDBBasic {

   static String db_name = "StudentDB";
   static String user = "root";
   static String pass = "123456";
   static String hostName = "localhost";
   static String db_driver= "com.mysql.jdbc.Driver";
    public static void main(String[] args) {
        connectDB();
    }
    public static void connectDB(){
        try{
            Class.forName(db_driver);//ระบุ Driver
            String url = "jdbc:mysql://"+hostName+"/"+db_name;
            Connection connect = DriverManager.getConnection(url,user,pass);
            System.out.println("เชื่อมต่อฐานข้อมูลเรียบร้อย");
            
        } catch (Exception e){
            e.printStackTrace();
        }
    }
    
}
