/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.Food;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import library.LibraryDBConnect;

/**
 *
 * @author HungPham
 */
public class ModelFood {

    LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public ModelFood() {
        libraryDBConnect = new LibraryDBConnect();
    }
    public Food getFood(int idfood){
        Food food=null;
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT food.mafood as mafood, foodname, giaban FROM food where mafood= '"+idfood+"'";
        try {
        	
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                food = new Food(rs.getInt("mafood"), rs.getString("foodname"),rs.getInt("giaban"),"xxxxx");
           
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return food;
    }
    public ArrayList<Food> getList(Food fitem) {

        ArrayList<Food> alItem = new ArrayList<Food>();

        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }

        String sql = "SELECT food.mafood as mafood, foodname, giaban FROM food ";

        if (fitem != null) {
            if ((fitem.getMafood()) > 0) {
             sql += " && food.mafood = " + fitem.getMafood();
            }
            if (!("").equals(fitem.getFoodname())){
               sql += " && foodname LIKE '%" + fitem.getFoodname()+ "%'";
            }
        }
        try {
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                Food item = new Food(rs.getInt("mafood"), rs.getString("foodname"), rs.getInt("giaban"), "xmcnxmnc");
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;

    }

    public int add(Food item) {
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = 0;
        String max1 = "SELECT MAX(mafood) as mafood FROM food";
        int idfood = -1;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(max1);
            while (rs.next()) {
                idfood = rs.getInt("mafood");
                idfood++;
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (idfood == -1) {
            idfood = 1;
        }
        System.out.println(idfood);
        String sql = "INSERT INTO food(mafood, foodname,giaban) VALUES (?,?,?)";

        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, idfood);
            pst.setString(2, item.getFoodname());
            pst.setFloat(3, item.getPrice());
            pst.executeUpdate();
            result = 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
            ex.printStackTrace();
        } finally {
            try {
                pst.close();

                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
                ex.printStackTrace();
            }
        }

        return result;
    }

    public int del(Food item) {
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = 0;

        String sql = "DELETE FROM food WHERE mafood = ?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getMafood());
            pst.executeUpdate();
            result = 1;

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
            ex.printStackTrace();
        } finally {
            try {
                pst.close();

                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
                ex.printStackTrace();
            }
        }
        return result;
    }

    public int edit(Food item, int price_current) {
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelFood.class.getName()).log(Level.SEVERE, null, ex);
        }
        int result = 0;

        try {
            String sql = "UPDATE food SET foodname = ?, giaban=?";
            StringBuffer sb = new StringBuffer(sql);
            sb.append(" WHERE mafood = ?");
            sql = sb.toString();
            System.out.println(sql);
            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, item.getFoodname());
            pst.setFloat(2, item.getPrice());
            pst.setInt(3, item.getMafood());
            pst.executeUpdate();  
            result = 1;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Lỗi câu lệnh SQL");
            ex.printStackTrace();
        } finally {
            try {
                pst.close();
                conn.close();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Lỗi đóng SQL");
                ex.printStackTrace();
            }
        }
        return result;
    }
}
