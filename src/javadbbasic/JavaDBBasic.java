package javadbbasic;

import java.sql.*;

public class JavaDBBasic {

    static String db_name = "StudentDB";
    static String user = "root";
    static String pass = "123456";
    static String hostName = "localhost";
    static String db_driver = "com.mysql.jdbc.Driver";

    public static void main(String[] args) {
        // connectDB();
        //insertDB();
        //updateDB();
        //showdata();
        //deleteDB();
        //showdata();
        PreparedSelect();

    }

    public static Connection connectDB() {
        try {
            Class.forName(db_driver);//ระบุ Driver
            String url = "jdbc:mysql://" + hostName + "/" + db_name; //=localhost/StudentDB
            Connection connect = DriverManager.getConnection(url, user, pass); //ใช้งาน interface ที่ชื่อ Connection
            System.out.println("เชื่อมต่อฐานข้อมูลเรียบร้อย");
            return connect;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    //------------------------------------
    public static void showdata() {
        String sql = "select * from tbstudent";
        try {
            Connection c = connectDB(); // เรียกใช้ Method connectDB
            ResultSet rs = c.createStatement().executeQuery(sql);
//ดึงข้อมูลมาเก็บใน ResultSet แล้วไป execute ด้วยใช้คำสั่ง select * from tbstudent 
            while (rs.next()) {
                System.out.println("รหัส: " + rs.getString(1)
                        + "ชื่อ: " + rs.getString(2)
                        + "นามสกุล: " + rs.getString(3)
                        + "อายุ: " + rs.getString(4)
                );
            }
            rs.close();
            c.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    //------------------------------------

    public static void insertDB() {
        String sql = "insert into tbstudent value ('555','llll','งุงิงง','25')";
        try {
            Connection c = connectDB();
            Statement stm = c.createStatement();
            stm.executeUpdate(sql);
            System.out.println("บันทึกข้อมูลเรียบร้อย");

            c.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    //------------------------------------

    public static void updateDB() {
        String sql = "update tbstudent "
                + "set fname='Mirana',"
                + "lname='Shiping',"
                + "age='19' "
                + "where id='444' ";
        try {
            Connection c = connectDB();             //เชื่อมต่อฐานข้อมูล
            Statement stm = c.createStatement();    //สร้าง Statement
            stm.executeUpdate(sql);                 //นำคำสั่ง sql มาดำเนินการ
            System.out.println("แก้ไขเรียบร้อย");
            c.close();
            stm.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //------------------------------------

    public static void deleteDB() {
        String sql = "delete from tbstudent where id='2222'";
        try {
            Connection c = connectDB();
            Statement stm = c.createStatement();
            stm.executeUpdate(sql);
            System.out.println("ลบข้อมูล ID [222] เรียบร้อยแล้ว");

            stm.close();
            c.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void PreparedSelect() {
        String sql = "Select id,fname from tbstudent where id = ? or id = ?";

        String sid;
        String name;

        try {
            Connection c = connectDB();
            PreparedStatement pre = c.prepareStatement(sql);
            
            pre.setString(1, "444");
            pre.setString(2, "333");

            ResultSet rs = pre.executeQuery();

            while (rs.next()) {
                sid = rs.getString(1);
                name = rs.getString(2);

                System.out.println("รหัส: " + sid + "  ชื่อ:  " + name);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
