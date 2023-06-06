/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Comic.Comic;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author abcde
 */
public class ComicDAO {
    //Hàm lấy tất cả sách ra
    public static ArrayList<Comic> layTatCaComic() {
        ArrayList<Comic> dsComic = new ArrayList<Comic>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "select * from Comic";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comic s = new Comic();
                s.setIdComic(rs.getInt(1));
                s.setIdCategory(rs.getInt(2));
                s.setNameComic(rs.getString(3));
                s.setUrl_image(rs.getString(4));

                
                
                
                dsComic.add(s);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsComic;
    }
    
    public static ArrayList<Comic> timComicTheoTen(String name_comic) {//Select * From comic where name_comic like CONCAT('%', ?, '%')
        ArrayList<Comic> dsComic = new ArrayList<Comic>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "Select * From comic where name_comic like CONCAT('%', ?, '%')";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setString(1, name_comic);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comic s = new Comic();
                s.setIdComic(rs.getInt(1));
                s.setIdCategory(rs.getInt(2));
                s.setNameComic(rs.getString(3));
                s.setUrl_image(rs.getString(4));
                dsComic.add(s);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsComic;
    }

    //Hàm lấy sách theo mã loại
    public static ArrayList<Comic> layTheoLoai(int id_category) {
        ArrayList<Comic> dsComic = new ArrayList<Comic>();
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "select * from comic where id_category = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setInt(1, id_category);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Comic s = new Comic();
                s.setIdComic(rs.getInt(1));
                s.setIdCategory(rs.getInt(2));
                s.setNameComic(rs.getString(3));
                s.setUrl_image(rs.getString(4));
                
                dsComic.add(s);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return dsComic;
    }
    
    public static String layTenComic(int id_comic){
        String name = "";
        String sqlInsert;
        Connection con;
        try {
            sqlInsert = "select name_comic from comic where id_comic = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlInsert);

            ps.setInt(1, id_comic);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                name = rs.getString(1);
            }

//            rs.close();
//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return name;
    }

    //Hàm thêm sách
//    public static boolean them(String maComic, String tenComic, String loaiComic, String tacGia, String gia, Date ngayXB) {
    public static boolean them(Comic s) {
        int kq = -1;
        String sqlInsert;
        Connection con;

        try {
            sqlInsert = "insert into comic(id_category, name_comic, url_image) values (?,?,?)";
            con = DataBaseUtil.getConnection();
            //DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
            PreparedStatement ps = con.prepareStatement(sqlInsert);
            ps.setInt(1, s.getIdCategory());
            ps.setString(2, s.getNameComic());
            ps.setString(3, s.getUrl_image());
            
            

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);

    }

    //Hàm sửa Comic
//    public static boolean sua(String maComic, String tenComic, String loaiComic, String tacGia, Double gia, Date ngayXB) {
    public static boolean sua(Comic s) {
        int kq = -1;
        String sqlUpdate;
        Connection con;
        try {
            sqlUpdate = "Update Comic Set id_category = ?, name_comic = ?, url_image = ? where id_comic = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlUpdate);

            ps.setInt(1, s.getIdCategory());
            ps.setString(2, s.getNameComic());
            ps.setString(3, s.getUrl_image());
            ps.setInt(4, s.getIdComic());
            
            kq = ps.executeUpdate();

//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return (kq != -1);
    }

    //Hàm xóa sách
    public static boolean xoa(int id_comic) {
        int kq = -1;
        String sqlDel;
        Connection con;
        try {
            sqlDel = "Delete From comic Where id_comic  = ?";
            con = DataBaseUtil.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlDel);

            ps.setInt(1, id_comic );

            kq = ps.executeUpdate();

//            ps.close();
//            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return (kq != -1);

    }

    
    
}
