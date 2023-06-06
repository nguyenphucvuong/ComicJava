/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import DAO.UsersDAO;
import Comic.User;
import Comic.User;
import DAO.UsersDAO;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class LoginModel {



    
    //Hàm lấy tất cả tk
    public ArrayList<User> layTatCaTk() {
        return UsersDAO.layTatCaTk();
    }
    
    //Hàm lấy 1 tài khoản
    public User layTk(String username, String password) {
        return UsersDAO.userLogin(username, password);
    }
    

    
    //Hàm thêm tk
    public static boolean them(User l){
        return UsersDAO.them(l);
    }

    //Hàm sửa tk
    public static boolean sua(User l){
        return UsersDAO.sua(l);
    }
    
}
