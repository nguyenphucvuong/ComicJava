/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

//import comic.ChangePassView;
//import comic.LoaiModel;
//import comic.LoaiView;
import Comic.User;
import Model.CategoryModel;
import Model.ChapterModel;
import Model.ComicModel;
import Model.LoginModel;
import View.LoginView;
import View.MenuView;
import Model.LoginModel;
import Model.PageModel;
import View.CategoryView;
import View.ChangePassView;
import View.ChapterView;
import View.ComicView;
import View.HomeView;
import View.PageView;
//import comic.SachModel;
//import comic.SachView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import javax.swing.JOptionPane;

/**
 *
 * @author abcde
 */
public class MenuController {
//    private SachModel sModel;
//    private SachView sView;
//    private LoaiModel lModel;
//    private LoaiView lView;
//    private LoginModel loModel;
//    private LoginView loView;

    private MenuView view;
    
    //public static User user = LoginController.getUser();
            
    public MenuController(MenuView view) {
        this.view = view;

        init();
        view.setVisible(true);
        view.setTitle("Menu");
        view.setLocationRelativeTo(null);
        //view.getLbl_username().setText(user.getUsername());
        
        if (LoginController.getUser().getPermission() != 0) {
            view.getLbl_category().setVisible(true);
            view.getLbl_chapter().setVisible(true);
            view.getLbl_chapterps().setVisible(true);
            view.getLbl_comic().setVisible(true);
            view.getLbl_page().setVisible(true);
        }else {
            view.getLbl_category().setVisible(false);
            view.getLbl_chapter().setVisible(false);
            view.getLbl_chapterps().setVisible(false);
            view.getLbl_comic().setVisible(false);
            view.getLbl_page().setVisible(false);
        }
    }

    public MouseAdapter mouseChangePassListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginModel lM = new LoginModel();
                ChangePassView cPass = new ChangePassView();
                ChangePassController cC = new ChangePassController(lM, cPass);
                cPass.setVisible(true);

                view.dispose();

            }
        };
    }

    public MouseAdapter mouseHomeListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComicModel cM = new ComicModel();
                HomeView hV = new HomeView();
                HomeController hC = new HomeController(cM, hV);
                
                view.dispose();

            }
        };
    }
    
    

    public MouseAdapter mouseCategoryListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CategoryModel cM = new CategoryModel();
                CategoryView cV = new CategoryView();
                CategoryController cC = new CategoryController(cM, cV);
                
                view.dispose();

            }
        };
    }
    
    public MouseAdapter mouseComicListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ComicModel cM = new ComicModel();
                ComicView cV = new ComicView();
                ComicController cC = new ComicController(cM, cV);
                
                view.dispose();

            }
        };
    }
    public MouseAdapter mouseChapterListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ChapterModel cM = new ChapterModel();
                ChapterView cV = new ChapterView();
                ChapterController cC = new ChapterController(cM, cV);
                
                
                view.dispose();

            }
        };
    }
//    public MouseAdapter mouseChapterPSListener() {
//        return new java.awt.event.MouseAdapter() {
//            @Override
//            public void mouseClicked(java.awt.event.MouseEvent evt) {
//                
//                
//                view.dispose();
//
//            }
//        };
//    }
    public MouseAdapter mousePageListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                PageModel pM = new PageModel();
                PageView pV = new PageView();
                PageController pC = new PageController(pM, pV);
                
                view.dispose();

            }
        };
    }

    public void init() {
        
        view.getLbl_home().addMouseListener(mouseHomeListener());
        view.getLbl_category().addMouseListener(mouseCategoryListener());
        view.getLbl_changepass().addMouseListener(mouseChangePassListener());
        view.getLbl_comic().addMouseListener(mouseComicListener());
        view.getLbl_chapter().addMouseListener(mouseChapterListener());
        //view.getLbl_chapterps().addMouseListener(mouseCategoryListener());
        view.getLbl_page().addMouseListener(mousePageListener());
        
        view.getBtn_logout().addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginModel lM = new LoginModel();
                LoginView lV = new LoginView();
                LoginController lC = new LoginController(lM, lV);
                //a.setVisible(true);
                LoginController.logoutUser();
                view.dispose();
            }
        });
    }

}
