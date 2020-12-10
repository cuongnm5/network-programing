/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.sun.jdi.connect.spi.Connection;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author supreme
 */
class User {
    private int id_user;
    private int id_problem;
    private boolean solved;

    public User(int id_user, int id_problem, boolean solved) {
        this.id_user = id_user;
        this.id_problem = id_problem;
        this.solved = solved;
    }

    public int getId_user() {
        return id_user;
    }

    public int getId_problem() {
        return id_problem;
    }

    public boolean isSolved() {
        return solved;
    }

    public void setId_user(int id_user) {
        this.id_user = id_user;
    }

    public void setId_problem(int id_problem) {
        this.id_problem = id_problem;
    }

    public void setSolved(boolean solved) {
        this.solved = solved;
    }

    @Override
    public String toString() {
        return "User{" + "id_user=" + id_user + ", id_problem=" + id_problem + ", solved=" + solved + '}';
    }
    
}

public class MyServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */ 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
            String problem = "<th>Name</th>"; 
            String user = "";
            ArrayList<Integer> problemId = new ArrayList<Integer>();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                java.sql.Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/network_programing", "admin", "admin");
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("select * from problem");
                while(rs.next()) {
                    problem += "<th> <a href=\""+rs.getString(7)+"\">"+rs.getString(2)+"</th>"; 
                    problemId.add(rs.getInt(1));
                }
                
                Statement stmt2 = con.createStatement();
                Statement stmt3 = con.createStatement();
                ResultSet rs2 = stmt2.executeQuery("select * from user");

                
                
                while(rs2.next()) {
                    ResultSet rs3 = stmt3.executeQuery("select * from user_problem where id_user="+rs2.getInt(1));
                    ArrayList<User> users = new ArrayList<User>();
                    while(rs3.next()){
                        User u = new User(rs3.getInt(2),rs3.getInt(3),rs3.getBoolean(5));
                        System.out.println(u);
                        users.add(u);
                    }
                    
                    user += "<tr> <td>"+rs2.getString(4)+"</td>";
                    for(int i=0;i<problemId.size();i++) {
                        int flag=0;
                        for(User u: users){
                            if(i+1==u.getId_problem() && u.isSolved()){
                                user += "<td id=\""+problemId.get(i)+"-"+rs2.getInt(1)+"\">X</td>";
                                flag = 1;
                                break;
                            }
                        }
                        if(flag==0){
                            user += "<td id=\""+problemId.get(i)+"-"+rs2.getInt(1)+"\"></td>";
                        }
                    }
                    user += "</tr>";
                }
              
                con.close();
            } catch (Exception e) {
                System.out.println(e);
            }
            
            request.setAttribute("problems", problem);
            request.setAttribute("users", user);
            dispatcher.forward( request, response );
        } 
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
