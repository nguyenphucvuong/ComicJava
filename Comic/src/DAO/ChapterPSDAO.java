/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;


import Comic.ChapterPS;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class ChapterPSDAO {
    //Hàm lấy hien tai cua comic vua an
    public static ChapterPS layPagesHienTai(int id_user, int id_comic){
        ChapterPS pagePS = new ChapterPS();
        String sql;
        Connection con;
        try {
            sql = "select * from chapter_present where id_user = ? and id_comic  = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setInt(1, id_user);
            ps.setInt(2, id_comic);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                pagePS = new ChapterPS(rs.getInt(1),rs.getInt(2), rs.getInt(3), rs.getDouble(4));
                
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return pagePS;

    }
    

    

    //Hàm thêm sách
    public static boolean them(ChapterPS cp) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into chapter_present(id_user, id_comic, chapter_present) values (?,?,?)";

            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setInt(1, cp.getIdUser());
            ps.setInt(2, cp.getIdComic());
            ps.setDouble(2, cp.getChapterPresent());

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
    public static boolean sua(ChapterPS cp) {
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update chapter_present Set id_user = ?, id_comic = ?, chapter_present = ? where id_present = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);

            
            ps.setInt(1, cp.getIdUser());
            ps.setInt(2, cp.getIdComic());
            ps.setDouble(3, cp.getChapterPresent());
            ps.setInt(4, cp.getIdPresent());
            
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
    public static boolean xoa(int id_present) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From chapter_present Where id_present = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);

            ps.setInt(1, id_present);

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (kq != -1);

    }


}
