/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Comic.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class CategoryDAO {
     
    public static ArrayList<Category> layTatCaCategory() {
        ArrayList<Category> dsCategory = new ArrayList<Category>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "select * from category";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Category l = new Category(rs.getInt(1), rs.getString(2));
                dsCategory.add(l);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsCategory;
    }
    

    
    
    public static Category categoryById(int id){
        Category category = new Category();
        String sql;
        Connection con;
        try {
            sql = "select * from category where id_category=?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                category = new Category(rs.getInt(1), rs.getString(2));
                
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return category;
    }
   
    //Hàm thêm sách
    public static boolean them(Category l) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into Category(name_category) values (?)";
            
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            
            ps.setString(1,l.getNameCategory());
            
            
            
            kq = ps.executeUpdate();
//            
//            ps.close();
//            con.close();
//            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);

    }

    
    //Hàm sửa Category
    public static boolean sua(Category l){
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update category Set name_category = ? where id_category = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);
            
            ps.setString(1,l.getNameCategory());
            ps.setInt(2,l.getId());
            
            
            kq = ps.executeUpdate();
            
//            ps.close();
//            con.close();
//            
        } catch (Exception e) {
            e.printStackTrace();
        }     
        return (kq != -1);    
    }
    
    //Hàm xóa sách
    public static boolean xoa(int id_category ){
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From category Where id_category = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);
            
            ps.setInt(1, id_category );
            
            kq = ps.executeUpdate();
            
//            ps.close();
//            con.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        return (kq != -1);
        
    }
    
    
}
