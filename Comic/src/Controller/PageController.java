/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Comic.Chapter;
import Comic.Page;
import Model.ChapterModel;
import Model.ComicModel;
import Model.PageModel;
import View.MenuView;
import View.PageView;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author abcde
 */
public class PageController {

    private PageModel model;
    private PageView view;

    public PageController(PageModel model, PageView view) {
        this.model = model;
        this.view = view;

        tableListener();
        showDataTable();
        showTenLoai();
        //JOptionPane.showMessageDialog(view, " ");
        init();
        view.setTitle("Page");
        view.getBtn_xoa().setEnabled(false);
        view.getBtn_sua().setEnabled(false);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    private void showDataTable() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_page().getModel();
        ArrayList<Page> kq = model.layTatCaPage();

        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Chapter id");
        tb.addColumn("Chapter number");
        tb.addColumn("Page id");
        tb.addColumn("image");

        DefaultTableCellRenderer imageRenderer = new DefaultTableCellRenderer() {
            @Override

            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                JLabel label = new JLabel();
                if (value != null) {
                    // Tạo một ImageIcon từ URL
                    ImageIcon icon = createImageIcon(value.toString());
                    if (icon != null) {
                        // Lấy kích thước cột
                        int width = table.getColumnModel().getColumn(column).getWidth();

                        // Tính toán chiều cao dựa trên tỉ lệ 746/485
                        int height = (int) ((width * 1.0) * (746.0 / 485.0));

                        // Điều chỉnh kích thước của hình ảnh
                        Image originalImage = icon.getImage();
                        Image scaledImage = originalImage.getScaledInstance(width, height, Image.SCALE_SMOOTH);
                        ImageIcon scaledIcon = new ImageIcon(scaledImage);

                        // Thiết lập hình ảnh vào JLabel
                        label.setIcon(scaledIcon);
                        // Căn giữa hình ảnh trong ô
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setVerticalAlignment(JLabel.CENTER);

                        // Set row height
                        table.setRowHeight(row, height);
                    }
                }
                return label;
            }

            @Override
            public void setBounds(int x, int y, int width, int height) {
                // Tính toán lại chiều cao dựa trên tỉ lệ 746/485
                int newHeight = (int) ((width * 1.0) * (746.0 / 485.0));
                super.setBounds(x, y, width, newHeight);
            }

        };
        view.getTbl_page().getColumnModel().getColumn(3).setCellRenderer(imageRenderer);
        for (Page cp : kq) {

            tb.addRow(new Object[]{cp.getId_chapter(), ChapterModel.layNumberChapter(cp.getId_chapter()), cp.getId_page(), cp.getUrl_image()});
        }
        tb.fireTableDataChanged();
    }

    private ImageIcon createImageIcon(String url) {
        try {
            // Tạo một URL từ đường dẫn
            URL imageUrl = new URL(url);
            // Đọc hình ảnh từ URL
            Image image = ImageIO.read(imageUrl);
            // Tạo một ImageIcon từ hình ảnh
            ImageIcon icon = new ImageIcon(image);
            return icon;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void showTenLoai() {
        ArrayList<Chapter> a = ChapterModel.layTatCaChapterWithoutID();
        for (Chapter cat : a) {
            view.getCbo_chapter().addItem(String.valueOf(cat.getIdChapter()));
        }
    }

//    public int maLoai() {
//        ArrayList<Page> a = ComicModel.layTatCaComic();
//        
//        for (Page cat : a) {
//            if (cat.getNameComic().equalsIgnoreCase(view.getCbo_comic().getSelectedItem().toString())) {
//                return cat.getIdComic();
//            }
//        }
//        return 0;
//    }
    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel tb = (DefaultTableModel) view.getTbl_page().getModel();
                int index = view.getTbl_page().getSelectedRow();
                //lay gia tri cua tung cot moi dong
                String idChapter = view.getTbl_page().getValueAt(index, 0).toString();
                String idPage = view.getTbl_page().getValueAt(index, 2).toString();
                String url = view.getTbl_page().getValueAt(index, 3).toString();

                //xuat gia tri ra bang txt
                view.getLbl_pageid().setText(idPage);
                view.getTxt_url().setText(url);
                view.getCbo_chapter().setSelectedItem(idChapter);

                //bat xóa sửa mới
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                //view.getBtn_f5().setEnabled(true);
            }
        };
    }

    private void init() {
        view.getTbl_page().addMouseListener(tableListener());

        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String idChapter = view.getCbo_chapter().getSelectedItem().toString();
                String url = view.getTxt_url().getText();

                if (url.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để thêm");
                } else {
                    Page l = new Page();
                    l.setId_chapter(Integer.parseInt(idChapter));
                    l.setUrl_image(url);

                    if (model.them(l)) {

                        view.getLbl_pageid().setText("");
                        view.getTxt_url().setText("");

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
                String id = view.getLbl_pageid().getText();
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
                String idPage = view.getLbl_pageid().getText();
                String idChapter = view.getCbo_chapter().getSelectedItem().toString();
                String url = view.getTxt_url().getText();

                if (idPage.isEmpty() || url.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để sửa");
                } else {
                    Page l = new Page();
                    l.setId_page(Integer.parseInt(idPage));
                    l.setId_chapter(Integer.parseInt(idChapter));
                    l.setUrl_image(url);

                    if (model.sua(l)) {
                        view.getLbl_pageid().setText("");
                        view.getTxt_url().setText("");

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
                view.getLbl_pageid().setText("");
                view.getTxt_url().setText("");

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
