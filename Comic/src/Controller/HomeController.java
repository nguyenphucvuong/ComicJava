/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Comic.Category;
import Comic.Comic;
import Model.CategoryModel;
import Model.ChapterModel;
import Model.ComicModel;
import View.ComicView;
import View.HomeView;
import View.MenuView;
import View.ReadView;
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
public class HomeController {
    
    private ComicModel model;
    private HomeView view;

    
    private static int comicId;

    public static int getComicId() {
        return comicId;
    }

    public static void setComicId(int comicId) {
        HomeController.comicId = comicId;
    }

    
    
    
    public HomeController(ComicModel model, HomeView view) {
        this.model = model;
        this.view = view;

        tableListener();
        showDataTable();
        showTenLoai();
        //JOptionPane.showMessageDialog(view, " ");
        init();
        view.setTitle("Home");

        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    private void showDataTable() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_home().getModel();
        ArrayList<Comic> kq = model.layTatCaComic();
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Comic Id");
        tb.addColumn("Image");
        tb.addColumn("Comic name");
        //tb.addColumn("Category id");
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
        view.getTbl_home().getColumnModel().getColumn(1).setCellRenderer(imageRenderer);

        for (Comic co : kq) {
            tb.addRow(new Object[]{co.getIdComic(), co.getUrl_image(), co.getNameComic(), CategoryModel.categoryById(co.getIdCategory()).getNameCategory()});
        }
        tb.fireTableDataChanged();
    }
    
    private void showDataTableAfterFind() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_home().getModel();
        ArrayList<Comic> kq = model.timComicTheoTen(view.getTxt_tim().getText().toString());
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Comic Id");
        tb.addColumn("Image");
        tb.addColumn("Comic name");
        //tb.addColumn("Category id");
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
        view.getTbl_home().getColumnModel().getColumn(1).setCellRenderer(imageRenderer);

        for (Comic co : kq) {
            tb.addRow(new Object[]{co.getIdComic(), co.getUrl_image(), co.getNameComic(), CategoryModel.categoryById(co.getIdCategory()).getNameCategory()});
        }
        tb.fireTableDataChanged();
    }
    
    private void showDataTableCategory() {
        DefaultTableModel tb = (DefaultTableModel) view.getTbl_home().getModel();
        ArrayList<Comic> kq = model.layTheoLoai(maLoai());
        tb.setColumnCount(0);
        tb.setRowCount(0);
        tb.addColumn("Comic Id");
        tb.addColumn("Image");
        tb.addColumn("Comic name");
        //tb.addColumn("Category id");
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
        view.getTbl_home().getColumnModel().getColumn(1).setCellRenderer(imageRenderer);

        for (Comic co : kq) {
            tb.addRow(new Object[]{co.getIdComic(), co.getUrl_image(), co.getNameComic(), CategoryModel.categoryById(co.getIdCategory()).getNameCategory()});
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

            view.getCbo_loai().addItem(cat.getNameCategory());
        }
    }

    public int maLoai() {
        ArrayList<Category> a = CategoryModel.layTatCaCategory();
        //int index = view.getTbl_sach().getSelectedRow();
        for (Category cat : a) {
            if (cat.getNameCategory().equalsIgnoreCase(view.getCbo_loai().getSelectedItem().toString())) {
                return cat.getId();
            }
        }
        return 0;
    }

    public MouseAdapter tableListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                DefaultTableModel tb = (DefaultTableModel) view.getTbl_home().getModel();
                int index = view.getTbl_home().getSelectedRow();
                comicId = Integer.parseInt(tb.getValueAt(index, 0).toString());
                
                ChapterModel cM = new ChapterModel();
                ReadView hV = new ReadView();
                ReadController hC = new ReadController(cM, hV);
                
                view.dispose();
            }
        };
    }

    public void init() {
        view.getTbl_home().addMouseListener(tableListener());

        
        
        view.getBtn_f5().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
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
        
        view.getBtn_tim().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showDataTableAfterFind();
            }
        });
        
        view.getCbo_loai().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                showDataTableCategory();
            }
        });

    }
}
