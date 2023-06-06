/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Comic.Page;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class PageDAO {
    //Hàm lấy tất cả loại ra
    public static ArrayList<Page> layTatCaPage() {
        ArrayList<Page> dsPage = new ArrayList<Page>();
        String sql;
        Connection con;
        try {
            sql = "SELECT * FROM pages ORDER BY id_chapter,id_page ASC";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, id_chapter);
            
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Page P = new Page(rs.getInt(1), rs.getInt(2), rs.getString(3));
                dsPage.add(P);
            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPage;
    }

    public static ArrayList<Page> layTatCaPageWithId(int id_chapter) {
        ArrayList<Page> dsPage = new ArrayList<Page>();
        String sql;
        Connection con;
        try {
            sql = "SELECT * FROM pages where id_chapter=? ORDER BY id_chapter,id_page ASC";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_chapter);
            
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Page P = new Page(rs.getInt(1), rs.getInt(2), rs.getString(3));
                dsPage.add(P);
            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsPage;
    }

    //Hàm thêm sách
    public static boolean them(Page p) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into pages(id_chapter, url_image) values (?,?)";

            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setInt(1, p.getId_chapter());
            ps.setString(2, p.getUrl_image());


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

    //Hàm sửa Page
    public static boolean sua(Page p) {
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update pages Set id_chapter = ?, url_image = ? where id_page = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);

            ps.setInt(1, p.getId_chapter());
            ps.setString(2, p.getUrl_image());
            ps.setInt(3, p.getId_page());

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
    public static boolean xoa(int id_page) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From pages Where id_page = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);

            ps.setInt(1, id_page);

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (kq != -1);

    }
    
    public static boolean xoaIdChapter(int id_chapter) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From pages Where id_chapter = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);

            ps.setInt(1, id_chapter);

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (kq != -1);

    }

}
