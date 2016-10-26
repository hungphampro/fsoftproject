/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;


import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import library.LibraryName;
import bean.NhapHang;
import controller.ControllerThongKe;

/**
 *
 * @author HungPham
 */
public class PnThongKeTheoNgay extends javax.swing.JPanel implements ActionListener {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	int crow = -1;
    /**
     * Creates new form PnViTri
     */
    ControllerThongKe controller;
    JTable table;
    DefaultTableModel model;

    public PnThongKeTheoNgay(String title) {
        initComponents();
        LibraryName lbName = new LibraryName();
        tfTitleCenter.setForeground(lbName.getColor_Title_PnCenter());
        tfTitleCenter.setFont(lbName.getFont_Title_PnCenter());
        tfTitleCenter.setText(title);
        pnFoodInTacVu.setBackground(lbName.getColor_small_panel());
        JSplitPane sp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,null, pnViTriCenter);
        sp.setOneTouchExpandable(true);
        //add(pnViTriTop, BorderLayout.NORTH);
        add(sp, BorderLayout.CENTER);
        model = new DefaultTableModel() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			public boolean isCellEditable(int row, int column) {
                return false;
            }

            public Class<?> getColumnClass(int col) {
                if (col == 0 || col == 2) {
                    return Integer.class;
                } else {
                    return super.getColumnClass(col);
                }
            }
        };

        controller = new ControllerThongKe();

        loadTable(null);
    }

    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

       
        pnViTri = new javax.swing.JPanel();
        pnViTriTop = new javax.swing.JPanel();
        tfTitleCenter = new javax.swing.JLabel();
        pnFoodInTacVu = new javax.swing.JPanel();
        pnViTriCenter = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbKho = new javax.swing.JTable();
        setLayout(new java.awt.BorderLayout());

        pnViTri.setLayout(new java.awt.BorderLayout());

        pnViTriTop.setBackground(new java.awt.Color(255, 255, 153));
        pnViTriTop.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tfTitleCenter.setForeground(new java.awt.Color(0, 0, 204));
        tfTitleCenter.setText("Quản lý vị trí");
        pnViTriTop.add(tfTitleCenter);

        pnViTri.add(pnViTriTop, java.awt.BorderLayout.NORTH);

        pnViTriCenter.setLayout(new java.awt.BorderLayout());
        
        tbKho.setAutoCreateRowSorter(true);
        tbKho.setBackground(new java.awt.Color(153, 255, 153));
        tbKho.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ) 
        );
        jScrollPane1.setViewportView(tbKho);
        tbKho.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
          
        pnViTriCenter.add(jScrollPane1, java.awt.BorderLayout.CENTER);
        pnViTri.add(pnViTriCenter, java.awt.BorderLayout.CENTER);
        
        add(pnViTri, java.awt.BorderLayout.CENTER);
        
    }// </editor-fold>                                                                


    // Variables declaration - do not modify                     
    
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPanel pnFoodInTacVu;
   
    private javax.swing.JPanel pnViTri;
    private javax.swing.JPanel pnViTriCenter;
    private javax.swing.JPanel pnViTriTop;
    private javax.swing.JTable tbKho;
   
    private javax.swing.JLabel tfTitleCenter;
    // End of variables declaration                   

    @Override
    public void actionPerformed(ActionEvent e) {
        /*if (e.getSource() == btNhapHangThem) {
            NhapHang item = new NhapHang(0, tfNhapHangName.getText(), Integer.parseInt(tfNhapHangLuong.getText()));
            if (controller.add(item) > 0) {
                loadTable(null);
                JOptionPane.showMessageDialog(null, "Thêm thành công");
            }
        } else if (e.getSource() == btNhapHangSua) {
            NhapHang item = new NhapHang(Integer.parseInt(tfNhapHangId.getText()), tfNhapHangName.getText(), Integer.parseInt(tfNhapHangLuong.getText()));
            crow = tbNhapHang.getSelectedRow();
            if (controller.edit(item) > 0) {
                loadTable(null);
                JOptionPane.showMessageDialog(null, "Sửa thành công");
                tbNhapHang.setRowSelectionInterval(crow, crow);
            }
        } else if (e.getSource() == btNhapHangNhapLai){
            resetForm();
        } else if (e.getSource() == btNhapHangXoa){
             int id = 0;
            try{
                id = Integer.parseInt(tfNhapHangId.getText());
            }
            catch (NumberFormatException ex){
                System.out.println("Phải nhập số vào ID");
            }
            NhapHang item = new NhapHang(id, tfNhapHangNameFind.getText(), 0);
            if (controller.del(item) > 0) {
                loadTable(null);
                JOptionPane.showMessageDialog(null, "Xóa thành công");
            }*/
        /*} else if (e.getSource() == btNhapHangFind){
            int id = 0;
            try{
                id = Integer.parseInt(tfNhapHangIdFind.getText());
            }
            catch (NumberFormatException ex){
                System.out.println("Phải nhập số vào ID");
            }
            NhapHang cvFind = new NhapHang(id, tfNhapHangNameFind.getText(), 0);
            loadTable(cvFind);
            
        } */
    }

    public void loadTable(NhapHang item) {
        controller.loadTable(tbKho, model);
        controller.setWidthHeightTable(tbKho);
    }
}
