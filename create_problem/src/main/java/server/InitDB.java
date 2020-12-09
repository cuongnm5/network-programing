/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Problem;

/**
 *
 * @author dodo
 */
public class InitDB {
    private Connection conn;
    private String jdbcURL = "jdbc:mysql://192.168.1.5:3306/judge_db?autoReconnect=true&useSSL=false";
    private String jdbcUsername = "phpmyadmin";
    private String jdbcPassword = "Duchanhctn9_";
    private String insertProblem = "INSERT INTO problem (name, title, statement, solution, testPath, timeLimit, memLimit) values(?,?,?,?,?,?,?)";

    public InitDB() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(jdbcURL, jdbcUsername,jdbcPassword);
            System.out.println("Get connect success!!!");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public void insertData(Problem p){
        try {
            PreparedStatement ps = conn.prepareStatement(insertProblem);
            ps.setString(1, p.getName());
            ps.setString(2, p.getTitle());
            ps.setString(3, p.getContent());
            ps.setString(4, p.getCode());
            ps.setString(5, p.getFilePath());
            ps.setInt(6, Integer.parseInt(p.getTimeLimit()));
            ps.setInt(7, Integer.parseInt(p.getMemLimit()));
            ps.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(InitDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}