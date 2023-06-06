/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Comic.ChapterPS;
import DAO.ChapterPSDAO;

/**
 *
 * @author abcde
 */
public class ChapterPSModel {
    public static ChapterPS layPagesHienTai(int id_user, int id_comic){
        return ChapterPSDAO.layPagesHienTai(id_user, id_comic);
    }

    
   
    
    public static boolean them(ChapterPS cp){
        return ChapterPSDAO.them(cp);
    }
    
    public static boolean sua(ChapterPS cp){
        return ChapterPSDAO.sua(cp);
    }
    
    public static boolean xoa(int id_present){
        return ChapterPSDAO.xoa(id_present);
    }
}
