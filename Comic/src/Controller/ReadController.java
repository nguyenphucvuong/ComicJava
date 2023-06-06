/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import Comic.Category;
import Comic.Chapter;
import Comic.Page;
import Model.CategoryModel;
import Model.ChapterModel;
import Model.ComicModel;
import Model.PageModel;
import View.HomeView;
import View.ReadView;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author abcde
 */
public class ReadController {

    private ChapterModel model;
    private ReadView view;

    int i = 0;

    public ReadController(ChapterModel model, ReadView view) {
        this.model = model;
        this.view = view;

        showTenLoai();
        setItemsForReadView();

        init();

        view.setTitle(ComicModel.layTenComic(HomeController.getComicId()));
        view.setVisible(true);
        view.setLocationRelativeTo(null);

        showDataPanel(); // Hiển thị danh sách hình ảnh ban đầu
    }

    public void showTenLoai() {

        ArrayList<Chapter> a = ChapterModel.layTatCaChapter(HomeController.getComicId());
        for (Chapter cat : a) {

            view.getCbo_chapter().addItem(String.valueOf(cat.getChapter_number()));
        }
    }

    public int maLoai() {
        ArrayList<Chapter> a = ChapterModel.layTatCaChapter(HomeController.getComicId());
        //int index = view.getTbl_sach().getSelectedRow();
        for (Chapter cat : a) {
            if (String.valueOf(cat.getChapter_number()).equalsIgnoreCase(view.getCbo_chapter().getSelectedItem().toString())) {
                return cat.getIdChapter();
            }
        }
        return 0;
    }

    public void setItemsForReadView() {
        if (view.getCbo_chapter().getSelectedItem() != null) {
            int chapterId = maLoai(); // Sử dụng maLoai() để lấy idChapter
            view.getLbl_chaptetId().setText(String.valueOf(chapterId));
            view.getLbl_chapter().setText(view.getCbo_chapter().getSelectedItem().toString());
            view.getLbl_comicName().setText(ComicModel.layTenComic(HomeController.getComicId()));
        }

    }

    // dieu khieu bat tat btn
    public void ctrlBtn() {
        if (i >= view.getCbo_chapter().getItemCount() - 1) {
            view.getBtn_nextChap().setEnabled(false);
        } else {
            view.getBtn_nextChap().setEnabled(true);
        }

        if (i >= view.getCbo_chapter().getItemCount() - 1 && i != 0) {
            view.getBtn_previousChap().setEnabled(true);
        } else {
            view.getBtn_previousChap().setEnabled(false);
        }
    }

    public void init() {

        view.getBtn_home().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComicModel cM = new ComicModel();
                HomeView hV = new HomeView();
                HomeController hC = new HomeController(cM, hV);

                view.dispose();
            }
        });

        view.getBtn_nextChap().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i++;
                if (i >= view.getCbo_chapter().getItemCount()) {
                    i = view.getCbo_chapter().getItemCount() - 1;
                }
                view.getCbo_chapter().setSelectedIndex(i);
                setItemsForReadView();
                ctrlBtn();
            }
        });

        view.getBtn_previousChap().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i--;
                if (i < 0) {
                    i = 0;
                }
                view.getCbo_chapter().setSelectedIndex(i);
                setItemsForReadView();
                ctrlBtn();
            }
        });

        view.getCbo_chapter().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                i = view.getCbo_chapter().getSelectedIndex();
                setItemsForReadView();
                ctrlBtn();
            }
        });

    }

    public void showDataPanel() {
        // Xóa hết các thành phần hiện tại trong panel
        view.getPn_images().removeAll();

        // Lấy danh sách các URL hình ảnh từ nguồn dữ liệu của bạn
        ArrayList<String> imageUrls = getImageUrls();

        // Tạo và thêm các thành phần JLabel chứa hình ảnh từ URL vào panel
        for (String imageUrl : imageUrls) {
            try {
                // Đọc hình ảnh từ URL
                URL url = new URL(imageUrl);
                BufferedImage image = ImageIO.read(url);

                // Thay đổi kích thước hình ảnh để phù hợp với kích thước của scroll panel
                int desiredWidth = view.getSp_images().getWidth(); // Lấy chiều rộng của scroll panel
                int desiredHeight = view.getSp_images().getHeight(); // Lấy chiều cao của scroll panel
                Image scaledImage = image.getScaledInstance(desiredWidth, desiredHeight, Image.SCALE_SMOOTH);

                // Tạo JLabel và thiết lập hình ảnh đã thay đổi kích thước
                JLabel label = new JLabel(new ImageIcon(scaledImage));

                // Thêm JLabel vào panel
                view.getPn_images().add(label);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Thiết lập layout cho panel
        view.getPn_images().setLayout(new FlowLayout(FlowLayout.LEFT));

        // Refresh panel
        view.getPn_images().revalidate();
        view.getPn_images().repaint();

        // Thêm panel vào scroll pane
        view.getSp_images().setViewportView(view.getPn_images());
    }

    public ArrayList<String> getImageUrls() {
        // TODO: Lấy danh sách các URL hình ảnh từ nguồn dữ liệu của bạn
        // Ví dụ:
        ArrayList<String> imageUrls = new ArrayList<>();
        int chapterId = Integer.parseInt(view.getLbl_chaptetId().getText()); // Lấy idChapter từ nhãn
        ArrayList<Page> pages = PageModel.layTatCaPageWithId(chapterId);
        for (Page page : pages) {
            imageUrls.add(page.getUrl_image());
        }
        return imageUrls;
    }
}
