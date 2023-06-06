/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controller;

import View.ForgotPassView;
import Comic.User;
import Model.LoginModel;
import View.LoginView;
import Comic.User;
import Model.LoginModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;


import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author abcde
 */
public class ForgotPassController {

    private LoginModel model;
    private ForgotPassView view;

    public ForgotPassController(LoginModel model, ForgotPassView view) {
        this.model = model;
        this.view = view;

        init();
        view.setTitle("Quên mật khẩu");
        view.setVisible(true);
        view.setLocationRelativeTo(null);
    }

    public void init() {
        view.getBtn_submit().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<User> login = model.layTatCaTk();
                String mail = view.getTxt_mail().getText();
                String username = view.getTxt_tk().getText();
//                JOptionPane.showMessageDialog(view, view.getTxt_mail().getText());
//                JOptionPane.showMessageDialog(view, view.getTxt_tk().getText());
                boolean test = false;
                if (mail.isEmpty() || username.isEmpty()) {
                    JOptionPane.showMessageDialog(view, "Nhập tài khoản và mail đã đăng ký để lấy lại mật khẩu");
                } else {
                    User b = new User();
                    for (User l : login) {
                        if (l.getMail().equals(mail) && l.getUsername().equals(username)) {
                            test = true;
                            b = l;
                            break;
                        }
                    }
                    if (test) {

                        try {
                            final String fromEmail = "abcdefgirltb@gmail.com";
                            // Mat khai email cua ban
                            final String password = "xlhgzkixsggzuzst";
                            // dia chi email nguoi nhan
                            final String toEmail = mail;
                            final String subject = "Mail quên mật khẩu";
                            final String body = "Username: "+b.getUsername()+"\nPassword: "+b.getPassword()+"\nTen: "+b.getName()+"\nMail: "+b.getMail();
                            Properties props = new Properties();
                            props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                            props.put("mail.smtp.port", "587"); //TLS Port
                            props.put("mail.smtp.auth", "true"); //enable authentication
                            props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
                            Authenticator auth = new Authenticator() {
                                @Override
                                protected PasswordAuthentication getPasswordAuthentication() {
                                    return new PasswordAuthentication(fromEmail, password);
                                }
                            };
                            Session session = Session.getInstance(props, auth);
                            MimeMessage msg = new MimeMessage(session);
                            //set message headers
                            msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
                            msg.addHeader("format", "flowed");
                            msg.addHeader("Content-Transfer-Encoding", "8bit");

                            try {
                                msg.setFrom(new InternetAddress(fromEmail, "Phúc Vương"));
                            } catch (UnsupportedEncodingException ex) {
                                Logger.getLogger(ForgotPassController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            msg.setReplyTo(InternetAddress.parse(fromEmail, false));
                            msg.setSubject(subject, "UTF-8");
                            msg.setText(body, "UTF-8");
                            msg.setSentDate(new Date());
                            msg.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail, false));
                            Transport.send(msg);
                            JOptionPane.showMessageDialog(view, "Đã gửi thông tin vào mail: "+mail);
                        } catch (MessagingException mex) {
                            mex.printStackTrace();
                        }
                    } else {
                        JOptionPane.showMessageDialog(view, "Không tìm thấy tài khoản hoặc mail vừa nhập");
                    }

                }
            }
        });

        //nút trờ lại
        view.getBtn_back().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.setVisible(false);
                LoginModel lM = new LoginModel();
                LoginView lV = new LoginView();
                LoginController lC = new LoginController(lM, lV);
            }
        });
    }

}
