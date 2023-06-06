/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Comic.Chapter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class ChapterDAO {

    
    public static ArrayList<Chapter> layTatCaChapter(int id_comic) {
        ArrayList<Chapter> dsChapter = new ArrayList<Chapter>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "SELECT * FROM `chapter` WHERE id_comic=? ORDER BY id_comic, chapter_number ASC";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, id_comic);
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chapter l = new Chapter(rs.getInt(1), rs.getInt(2),rs.getDouble(3));
                dsChapter.add(l);
            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChapter;
    }
    
    public static ArrayList<Chapter> layTatCaChapterWithoutID() {
        ArrayList<Chapter> dsChapter = new ArrayList<Chapter>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "SELECT * FROM chapter ORDER BY id_comic, chapter_number ASC";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            
            
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Chapter l = new Chapter(rs.getInt(1), rs.getInt(2),rs.getDouble(3));
                dsChapter.add(l);
            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsChapter;
    }

    public static Chapter chapterById( int id_chapter) { //public static Chapter chapterById(int id_comic, int id_chapter) {
        Chapter chapter = new Chapter();
        String sql;
        Connection con;
        try {
            sql = "select * from chapter where id_chapter = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, id_comic);
            ps.setInt(1, id_chapter);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                chapter = new Chapter(rs.getInt(1), rs.getInt(2),rs.getDouble(3));

            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chapter;
    }

    //Hàm thêm sách
    public static boolean them(Chapter l) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into chapter(id_comic, chapter_number) values (?,?)";

            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setInt(1, l.getIdComic());
            ps.setDouble(2, l.getChapter_number());

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

    //Hàm sửa chapter
    public static boolean sua(Chapter l) {
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update chapter Set id_comic = ?, chapter_number = ? where id_chapter = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);

            ps.setInt(1, l.getIdComic());
            ps.setDouble(2, l.getChapter_number());
            ps.setInt(3, l.getIdChapter());

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
    public static boolean xoa(int id_chapter) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From chapter Where id_chapter = ?";
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
    
    public static boolean xoaIdComic(int id_comic) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From chapter Where id_comic = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);

            ps.setInt(1, id_comic);

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return (kq != -1);

    }
    
    
    public static Double layNumberChapter(int id_chapter) {
        Double kq = -1.0;
        String sql;
        Connection con;
        try {
            sql = "select * from chapter where id_chapter = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, id_comic);
            ps.setInt(1, id_chapter);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                kq = rs.getDouble(1);

            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return kq;
    }

    public static ArrayList<Integer> layIdChapter(int id_comic) {
        ArrayList<Integer> dsId = new ArrayList<>();
        String sql;
        Connection con;
        try {
            sql = "select id_chapter from chapter where id_comic = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setInt(1, id_comic);
            ps.setInt(1, id_comic);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dsId.add(rs.getInt(1));

            }

//            rs.close();
//            ps.close();
//            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsId;
    }
}
