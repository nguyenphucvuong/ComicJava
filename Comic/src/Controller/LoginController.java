/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ForgotPassView;
import Comic.User;
import Model.LoginModel;
import View.LoginView;
import View.MenuView;
import View.RegisterView;
import Comic.User;
import Model.LoginModel;
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
public class LoginController {

    private LoginModel model;
    private LoginView view;

    private static User user;

    public static User getUser() {
        return user;
    }

    public static void logoutUser() {
        user = null;
    }

    public LoginController(LoginModel model) {
        this.model = model;

        init();
        setTKMK();
        view.setTitle("Login");
        //view.getLbl_fgpass().setVisible(false);
        //view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    public LoginController(LoginModel model, LoginView view) {
        this.model = model;
        this.view = view;

        init();
        setTKMK();
        view.setTitle("Login");
        //view.getLbl_fgpass().setVisible(false);
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    public void setTKMK() {
        ArrayList<User> a = model.layTatCaTk();

        for (User login : a) {
            view.getTxt_username().setText(login.getUsername());
            view.getTxt_pass().setText(login.getPassword());
            break;
        }

    }

    public MouseAdapter mouseForgotPassListener() {
        return new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginModel lM = new LoginModel();
                ForgotPassView fPass = new ForgotPassView();
                ForgotPassController rC = new ForgotPassController(lM, fPass);
                //a.setVisible(true);

                view.setVisible(false);

            }
        };
    }

    public void init() {
        view.getLbl_fgpass().addMouseListener(mouseForgotPassListener());
//        view.getLbl_changepass().addMouseListener(mouseChangePassListener());

        //nút đăng nhập
        view.getBtn_dangnhap().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = view.getTxt_username().getText();
                String password = new String(view.getTxt_pass().getPassword());
                user = model.layTk(view.getTxt_username().getText(), new String(view.getTxt_pass().getPassword()));
                if (user.getUsername() != null) {
                    MenuView mV = new MenuView();
                    MenuController sC = new MenuController(mV);
                    view.dispose(); // Đóng màn hình đăng nhập
                } else {
                    JOptionPane.showMessageDialog(view, "Sai tài khoản hoặc mật khẩu");
                }
            }
        });
        view.getBtn_dangky().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginModel lM = new LoginModel();
                RegisterView rV = new RegisterView();
                RegisterController rC = new RegisterController(lM, rV);
                //a.setVisible(true);

                view.dispose();
            }
        });
    }
}
