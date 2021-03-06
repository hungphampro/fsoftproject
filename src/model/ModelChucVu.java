/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import bean.ChucVu;
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
public class ModelChucVu {

    LibraryDBConnect libraryDBConnect;
    Connection conn;
    Statement st;
    PreparedStatement pst;
    ResultSet rs;

    public ModelChucVu() {
       
            libraryDBConnect = new LibraryDBConnect();
            
        
    }

    public ArrayList<ChucVu> getList(ChucVu fitem) {
        ArrayList<ChucVu> alItem = new ArrayList<ChucVu>();
        try {
            conn = libraryDBConnect.getConnectMySQL();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            String sql = "SELECT machucvu, tenchucvu, luongcoban FROM CHUCVU WHERE 1 ";
           
            if (fitem != null){
                 if ((fitem.getMachucvu()) > 0) {
                sql += " && machucvu = " + fitem.getMachucvu();
                }
                if (!("").equals(fitem.getChucvu())){
                    sql += " && tenchucvu LIKE '%" + fitem.getChucvu()+ "%'";
                }
            } 
            st = conn.createStatement();
            rs = st.executeQuery(sql);
            while (rs.next()) {
                ChucVu item = new ChucVu(rs.getInt(1), rs.getString(2), rs.getInt(3));
                alItem.add(item);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ModelChucVu.class.getName()).log(Level.SEVERE, null, ex);
        }
        return alItem;
    }

    public int add(ChucVu item) {
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

        String sql = "INSERT INTO chucvu( tenchucvu, luongcoban) values (?,?)";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);

            pst.setString(1, item.getChucvu());
            pst.setInt(2, item.getLuongcoban());

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

    public int edit(ChucVu item) {
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
        String sql = "UPDATE chucvu SET tenchucvu = ?, luongcoban=? WHERE machucvu = ?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setString(1, item.getChucvu());
            pst.setInt(2, item.getLuongcoban());
            pst.setInt(3, item.getMachucvu());
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

    public int del(ChucVu item) {
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

        String sql = "DELETE FROM chucvu WHERE machucvu = ?";
        try {

            pst = (PreparedStatement) conn.prepareStatement(sql);
            pst.setInt(1, item.getMachucvu());
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
