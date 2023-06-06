/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Comic.Comic;
import DAO.ComicDAO;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class ComicModel {

    public ComicModel() {
    }
        
    public static ArrayList<Comic> layTatCaComic(){
        return ComicDAO.layTatCaComic();
    }
    
    public static ArrayList<Comic> layTheoLoai(int id_category){
        return ComicDAO.layTheoLoai(id_category);
    }
    
    public static boolean them(Comic s){
        return ComicDAO.them(s);
    }
    
    public static boolean sua(Comic s){
        return ComicDAO.sua(s);
    }
    
    public static boolean xoa(int id_comic){
        return ComicDAO.xoa(id_comic);
    }
    
    public static ArrayList<Comic> timComicTheoTen(String name_comic){
        return ComicDAO.timComicTheoTen(name_comic);
    }
    
    public static String layTenComic(int id_comic){
        return ComicDAO.layTenComic(id_comic);
    }
}
