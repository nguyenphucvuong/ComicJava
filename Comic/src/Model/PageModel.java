/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Comic.Page;
import DAO.PageDAO;

import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class PageModel {

    public static ArrayList<Page> layTatCaPage() {
        return PageDAO.layTatCaPage();
    }
    
    public static ArrayList<Page> layTatCaPageWithId(int id_chapter) {
        return PageDAO.layTatCaPageWithId(id_chapter);
    }
    
    public static boolean them(Page p) {
        return PageDAO.them(p);
    }
    
    public static boolean sua(Page p) {
        return PageDAO.sua(p);
    }
    
    public static boolean xoa(int id_page) {
        return PageDAO.xoa(id_page);
    }
    
   public static boolean xoaIdChapter(int id_chapter) {
        return PageDAO.xoaIdChapter(id_chapter);
    }
    
}
