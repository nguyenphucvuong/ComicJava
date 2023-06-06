/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;



import Comic.Chapter;
import Comic.Comic;

import Model.ChapterModel;
import Model.ComicModel;
import View.ChapterView;
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
public class ChapterController {
    private ChapterModel model;
    private ChapterView view;

    
    public ChapterController(ChapterModel model, ChapterView view) {
        this.model = model;
        this.view = view;

        tableListener();
        showDataTable();
        showTenLoai();
        //JOptionPane.showMessageDialog(view, " ");
        init();
        view.setTitle("Chapter");
        view.getBtn_xoa().setEnabled(false);
        view.getBtn_sua().setEnabled(false);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    private void showDataTable() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_chapter().getModel();
        ArrayList<Chapter> kq = new ArrayList<>();
        ArrayList<Comic> cm = ComicModel.layTatCaComic();
        
        for (Comic comic : cm) {
            ArrayList<Chapter> temp = model.layTatCaChapter(comic.getIdComic());
            kq.addAll(temp);
        }
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Comic id");
        tb.addColumn("Comic name");
        tb.addColumn("Chapter id");
        tb.addColumn("Chapter number");

        for (Chapter cp : kq) {

            tb.addRow(new Object[]{cp.getIdComic(), ComicModel.layTenComic(cp.getIdComic()),cp.getIdChapter(), cp.getChapter_number()});
        }
        tb.fireTableDataChanged();
    }
    
    public void showTenLoai() {
        ArrayList<Comic> a = ComicModel.layTatCaComic();
        for (Comic cat : a) {

            view.getCbo_comic().addItem(cat.getNameComic());
        }
    }

    public int maLoai() {
        ArrayList<Comic> a = ComicModel.layTatCaComic();
        //int index = view.getTbl_sach().getSelectedRow();
        for (Comic cat : a) {
            if (cat.getNameComic().equalsIgnoreCase(view.getCbo_comic().getSelectedItem().toString())) {
                return cat.getIdComic();
            }
        }
        return 0;
    }
    

    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel tb = (DefaultTableModel) view.getTbl_chapter().getModel();
                int index = view.getTbl_chapter().getSelectedRow();
                //lay gia tri cua tung cot moi dong
                String idComic = view.getTbl_chapter().getValueAt(index, 0).toString();
                String nameComic = view.getTbl_chapter().getValueAt(index, 1).toString();
                String idChapter = view.getTbl_chapter().getValueAt(index, 2).toString();
                String number = view.getTbl_chapter().getValueAt(index, 3).toString();

                //xuat gia tri ra bang txt
                view.getLbl_chapterid().setText(idChapter);
                view.getCbo_comic().setSelectedItem(nameComic);
                view.getTxt_number().setText(number);
                

                //bat xóa sửa mới
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                //view.getBtn_f5().setEnabled(true);
            }
        };
    }
    
    

    private void init() {
        view.getTbl_chapter().addMouseListener(tableListener());

        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                int idComic = maLoai();
                String number = view.getTxt_number().getText();

                if ( view.getTxt_number().getText().isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để thêm");
                } else {
                    Chapter l = new Chapter();
                    l.setIdComic(idComic);
                    l.setChapter_number(Double.parseDouble(number));

                    if (model.them(l)) {

                        view.getLbl_chapterid().setText("");
                        view.getTxt_number().setText("");

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
                String id = view.getLbl_chapterid().getText();
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
                String id = view.getLbl_chapterid().getText();
                int idComic = maLoai();
                String number = view.getTxt_number().getText();
                if (id.isEmpty() || number.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để sửa");
                } else {
                    Chapter l = new Chapter();
                    l.setIdChapter(Integer.parseInt(id));
                    l.setIdComic(idComic);
                    l.setChapter_number(Double.parseDouble(number));
                    if (model.sua(l)) {
                        view.getLbl_chapterid().setText("");
                        view.getTxt_number().setText("");
                        
                        
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
                view.getLbl_chapterid().setText("");
                view.getTxt_number().setText("");

                
                
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
