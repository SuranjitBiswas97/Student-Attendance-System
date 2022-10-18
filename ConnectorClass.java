/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package studentattendancesystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author F.C
 */
public class ConnectorClass {

    public Connection con;
    public Statement stmt;
    public ResultSet rs;

//public static void main(String args[]){
    public ConnectorClass() {
        String dbtime;
        String dbUrl = "jdbc:mysql://localhost/smas";
        String query1 = "Select * FROM author";
        String query2 = "Select * FROM course";
        String query3 = "Select * FROM student";
        String query4 = "Select * FROM student_registration";
        String query5 = "Select * FROM teacher";
        String query6 = "Select * FROM teacher_registration";
        String query7 = "Select * FROM department";

        try {

//Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(dbUrl, "root", "");
            stmt = con.createStatement();
            rs = stmt.executeQuery(query1);
            rs = stmt.executeQuery(query2);
            rs = stmt.executeQuery(query3);
            rs = stmt.executeQuery(query4);
            rs = stmt.executeQuery(query5);
            rs = stmt.executeQuery(query6);
            rs = stmt.executeQuery(query7);

//con.close();
        } //end try
        catch (SQLException e) {
            e.printStackTrace();
        }

    }

}

