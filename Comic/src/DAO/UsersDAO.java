/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Comic.User;
import Comic.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class UsersDAO {
    
    //Hàm lấy tất cả tk ra
    public static ArrayList<User> layTatCaTk() {
        ArrayList<User> dsLoai = new ArrayList<User>();
        String sql;
        Connection con;
        try {
            sql = "select * from users";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                User l = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
                dsLoai.add(l);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsLoai;
    }
    
    //Hàm lấy user đã đăng nhập
    public static User userLogin(String username, String password){
        User user = new User();
        String sql;
        Connection con;
        try{
            sql = "select * from users where username=? and password=?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6));
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
        return user;
    }
    

    //Hàm thêm tk
    public static boolean them(User l) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into users(username, password, name, mail, permission) values (?,?,?,?,?)";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1,l.getUsername());
            ps.setString(2,l.getPassword());
            ps.setString(3,l.getName());
            ps.setString(4,l.getMail());
            ps.setInt(5,0);
            
            kq = ps.executeUpdate();
//            
//            ps.close();
//            con.close();
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);

    }

    
    //Hàm sửa tk
    public static boolean sua(User l){
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update users Set password = ? where username = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);
            
            ps.setString(1,l.getPassword());
            ps.setString(2,l.getUsername());
            
            
            kq = ps.executeUpdate();
            
//            ps.close();
//            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }     
        return (kq != -1);    
    }
    
}
