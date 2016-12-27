
package javadbbasic;
import java.sql.*;

public class JavaDBBasic {

   static String db_name = "StudentDB";
   static String user = "root";
   static String pass = "123456";
   static String hostName = "localhost";
   static String db_driver= "com.mysql.jdbc.Driver";
   
   public static void main(String[] args) {
       // connectDB();
        showdata(); 
    }
    public static Connection connectDB(){
        try{
            Class.forName(db_driver);//ระบุ Driver
            String url = "jdbc:mysql://"+hostName+"/"+db_name; //=localhost/StudentDB
            Connection connect = DriverManager.getConnection(url,user,pass); //ใช้งาน interface ที่ชื่อ Connection
            System.out.println("เชื่อมต่อฐานข้อมูลเรียบร้อย");
            return connect; 
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    
    public static void showdata(){
        String sql = "select * from tbstudent";
        try{
            Connection c = connectDB(); // เรียกใช้ Method connectDB
            ResultSet rs = c.createStatement().executeQuery(sql);
//ดึงข้อมูลมาเก็บใน ResultSet แล้วไป execute ด้วยใช้คำสั่ง select * from tbstudent 
            while (rs.next()){
                System.out.println("รหัส: " + rs.getString(1)+ 
                        "ชื่อ: " + rs.getString(2)+
                        "นามสกุล: " + rs.getString(3)+
                        "อายุ: " + rs.getString(4)
                        );
            }       
        }catch (Exception e){
           System.out.println(e.getMessage());
        }
    }
}
