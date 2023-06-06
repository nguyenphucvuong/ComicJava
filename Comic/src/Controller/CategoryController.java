/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Comic.Category;
import Model.CategoryModel;
import View.CategoryView;
import View.MenuView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abcde
 */
public class CategoryController {

    private CategoryModel model;
    private CategoryView view;

    public CategoryController(CategoryModel model, CategoryView view) {
        this.model = model;
        this.view = view;

        tableListener();
        showDataTable();
        //JOptionPane.showMessageDialog(view, " ");
        init();
        view.setTitle("Category");
        view.getBtn_xoa().setEnabled(false);
        view.getBtn_sua().setEnabled(false);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    private void showDataTable() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_category().getModel();
        ArrayList<Category> kq = model.layTatCaCategory();
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Mã loại");
        tb.addColumn("Tên loại");

        for (Category cp : kq) {

            tb.addRow(new Object[]{cp.getId(), cp.getNameCategory()});
        }
        tb.fireTableDataChanged();
    }
    

    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel tb = (DefaultTableModel) view.getTbl_category().getModel();
                int index = view.getTbl_category().getSelectedRow();
                //lay gia tri cua tung cot moi dong
                String id = view.getTbl_category().getValueAt(index, 0).toString();
                String name = view.getTbl_category().getValueAt(index, 1).toString();

                //xuat gia tri ra bang txt
                view.getLbl_id().setText(id);
                view.getTxt_category().setText(name);

                //bat xóa sửa mới
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                //view.getBtn_f5().setEnabled(true);
            }
        };
    }

    private void init() {
        view.getTbl_category().addMouseListener(tableListener());

        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                String name = view.getTxt_category().getText();

                if (name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để thêm loại");
                } else {
                    Category l = new Category();
                    l.setId(0);
                    l.setNameCategory(name);

                    if (model.them(l)) {

                        view.getLbl_id().setText("");
                        view.getTxt_category().setText("");

                        JOptionPane.showMessageDialog(view, "Thêm thành công!!!");
                        view.getBtn_xoa().setEnabled(false);
                        view.getBtn_sua().setEnabled(false);
                        showDataTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Thêm không thành công!!!");
                    }

                }

            }
        });

        //nút xóa
        view.getBtn_xoa().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String id = view.getLbl_id().getText();
                if (!id.isEmpty()) {//if (!test.isEmpty()) {
                    boolean a = model.xoa(Integer.parseInt(id));
                    if (a) {
                        JOptionPane.showMessageDialog(view, "Xóa thành công");
                        showDataTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Xóa không thành công");
                    }
                } else {
                    JOptionPane.showMessageDialog(view, "Lỗi gì đó!");
                }
            }
        });

        //nút sửa
        view.getBtn_sua().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                String id = view.getLbl_id().getText();
                String name = view.getTxt_category().getText();
                if (id.isEmpty() || name.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để sửa loại");
                } else {
                    Category l = new Category();
                    l.setId(Integer.parseInt(id));
                    l.setNameCategory(name);
                    if (model.sua(l)) {
                        view.getLbl_id().setText("");
                        view.getTxt_category().setText("");
                        JOptionPane.showMessageDialog(view, "Sửa thành công!!!");
                        view.getBtn_xoa().setEnabled(false);
                        view.getBtn_sua().setEnabled(false);
                        showDataTable();
                    } else {
                        JOptionPane.showMessageDialog(view, "Sửa không thành công!!!");
                    }
                }
            }
        });

        //nút mới
        view.getBtn_f5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.getLbl_id().setText("");
                view.getTxt_category().setText("");

                
                
                view.getBtn_them().setEnabled(true);
                //bat xóa sửa mới
                view.getBtn_xoa().setEnabled(false);
                view.getBtn_sua().setEnabled(false);
                showDataTable();
            }
        });
        
        view.getBtn_menu().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                MenuView mV = new MenuView();
                MenuController sC = new MenuController(mV);
                view.dispose(); // Đóng màn hình đăng nhập
            }
        });
    }

}
