/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import bean.Food;

import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import model.ModelFood;

/**
 *
 * @author Bi
 */
public class ControllerFood {

    ModelFood modelItem;

    public ControllerFood() {
        modelItem = new ModelFood();
    }

    public void loadTable(JTable table, DefaultTableModel model, Food fitem) {
        table.setModel(model);
        model.setDataVector(vRows(fitem), vCols());
    }
    

    public void setWidthHeightTable(JTable tbFood) {
        
    }

    public Vector<String> vCols() {
        Vector<String> vCols = new Vector<String>();
        vCols.add("ID");
        vCols.add("Thức Uống");
        vCols.add("Giá Bán");
        return vCols;
    }

    @SuppressWarnings("rawtypes")
	public Vector<Vector<Comparable>> vRows(Food fitem) {
        Vector<Vector<Comparable>> vRows = new Vector<Vector<Comparable>>();
        ArrayList<Food> flist = modelItem.getList(fitem);
        for (Food item : flist) {
            Vector<Comparable> v = new Vector<Comparable>();
            v.add(item.getMafood());
            v.add(item.getFoodname());
            v.add(item.getPrice());
            vRows.add(v);
        }
        return vRows;

    }

    public int add(Food item) {
        int result = 0;
        if (isValid(item, "add")) {
            modelItem.add(item);
            result = 1;
        }
        return result;
    }

    public int del(Food item) {
        int result = 0;
        if (isValid(item, "del")) {
            modelItem.del(item);
            result = 1;
        }
        return result;
    }

    private boolean isValid(Food item, String method) {
        switch (method) {
            case "add":
                if ("".equals(item.getFoodname())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập chức vụ");
                    return false;
                }
                if ("".equals(item.getPrice())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá");
                    return false;
                }
                break;
            case "del":
                if (item.getMafood() == 0) {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn một dòng để xóa");
                    return false;
                }
                break;
            case "edit":
                if ("".equals(item.getFoodname())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập chức vụ");
                    return false;
                }
                if ("".equals(item.getPrice())) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập giá");
                    return false;
                }
                break;

        }
        return true;
    }

    public int edit(Food item, int price_current) {
        int result = 0;
        if (isValid(item, "edit")) {
            modelItem.edit(item, price_current);
            result = 1;
        }
        return result;
    }

}
