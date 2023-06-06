/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Comic.Comic;
import Comic.Category;
import Model.CategoryModel;
import Model.ChapterModel;
import Model.ComicModel;
import Model.PageModel;
import View.ComicView;
import View.MenuView;
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
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author abcde
 */
public class ComicController {

    private ComicModel model;
    private ComicView view;

    //private CategoryModel catTemp;

    public ComicController(ComicModel model, ComicView view) {
        this.model = model;
        this.view = view;

        tableListener();
        showDataTable();
        showTenLoai();
        //JOptionPane.showMessageDialog(view, " ");
        init();
        view.setTitle("Comic");
        view.getBtn_xoa().setEnabled(false);
        view.getBtn_sua().setEnabled(false);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    private void showDataTable() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_comic().getModel();
        ArrayList<Comic> kq = model.layTatCaComic();
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Comic Id");
        tb.addColumn("Image");
        tb.addColumn("Comic name");
        tb.addColumn("Category id");
        tb.addColumn("Category");

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

        // Thiết lập renderer cho cột "Image"
        view.getTbl_comic().getColumnModel().getColumn(1).setCellRenderer(imageRenderer);

        for (Comic co : kq) {
            tb.addRow(new Object[]{co.getIdComic(), co.getUrl_image(), co.getNameComic(), co.getIdCategory(), CategoryModel.categoryById(co.getIdCategory()).getNameCategory()});
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
        ArrayList<Category> a = CategoryModel.layTatCaCategory();
        for (Category cat : a) {

            view.getCbo_category().addItem(cat.getNameCategory());
        }
    }

    public int maLoai() {
        ArrayList<Category> a = CategoryModel.layTatCaCategory();
        //int index = view.getTbl_sach().getSelectedRow();
        for (Category cat : a) {
            if (cat.getNameCategory().equalsIgnoreCase(view.getCbo_category().getSelectedItem().toString())) {
                return cat.getId();
            }
        }
        return 0;
    }

    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel tb = (DefaultTableModel) view.getTbl_comic().getModel();
                int index = view.getTbl_comic().getSelectedRow();
                //lay gia tri cua tung cot moi dong
                String comicId = view.getTbl_comic().getValueAt(index, 0).toString();
                String comicName = view.getTbl_comic().getValueAt(index, 2).toString();
                String category = view.getTbl_comic().getValueAt(index, 4).toString();
                String url = view.getTbl_comic().getValueAt(index, 1).toString();

                //xuat gia tri ra bang txt
                view.getLbl_comicid().setText(comicId);
                view.getTxt_name().setText(comicName);
                view.getCbo_category().setSelectedItem(category);
                view.getTxt_url().setText(url);

                //bat xóa sửa mới
                view.getBtn_xoa().setEnabled(true);
                view.getBtn_sua().setEnabled(true);
                //view.getBtn_f5().setEnabled(true);
            }
        };
    }

    public void init() {
        view.getTbl_comic().addMouseListener(tableListener());

        view.getBtn_them().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                String name = view.getTxt_name().getText();
                int cat = maLoai();
                String url = view.getTxt_url().getText();

                if (name.isEmpty() || url.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để thêm");
                } else {
                    Comic l = new Comic();
                    l.setIdComic(0);
                    l.setIdCategory(cat);
                    l.setNameComic(name);
                    l.setUrl_image(url);

                    if (model.them(l)) {

                        view.getTxt_name().setText("");
                        view.getLbl_comicid().setText("");
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
                String id = view.getLbl_comicid().getText();
                if (!id.isEmpty()) {//if (!test.isEmpty()) {
                    boolean b = true;
                    ArrayList<Integer> ds = ChapterModel.layIdChapter(Integer.parseInt(id));
                    for (Integer d : ds) {
                        b = PageModel.xoaIdChapter(d);
                    }
                    boolean a = model.xoa(Integer.parseInt(id)) && ChapterModel.xoaIdComic(Integer.parseInt(id));
                    if (a && b) {
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
                String id = view.getLbl_comicid().getText();
                String name = view.getTxt_name().getText();
                int cat = maLoai();
                String url = view.getTxt_url().getText();
                if (id.isEmpty() || url.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập đầy đủ dữ liệu để sửa");
                } else {
                    Comic l = new Comic();
                    l.setIdComic(Integer.parseInt(id));
                    l.setIdCategory(cat);
                    l.setNameComic(name);
                    l.setUrl_image(url);
                    if (model.sua(l)) {
                        view.getTxt_name().setText("");
                        view.getLbl_comicid().setText("");
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
        
        view.getBtn_f5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                view.getLbl_comicid().setText("");
                view.getTxt_name().setText("");
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
