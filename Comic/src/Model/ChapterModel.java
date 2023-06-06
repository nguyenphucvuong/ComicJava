/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;


import Comic.Chapter;
import DAO.ChapterDAO;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class ChapterModel {
    
    public static ArrayList<Chapter> layTatCaChapter(int id_comic){
        return ChapterDAO.layTatCaChapter(id_comic);
    }
    
    public static ArrayList<Chapter> layTatCaChapterWithoutID(){
        return ChapterDAO.layTatCaChapterWithoutID();
    }

    public static Chapter ChapterById( int id_chapter){
        return ChapterDAO.chapterById( id_chapter);
    }
    
    public static boolean them(Chapter c){
        return ChapterDAO.them(c);
    }
    
    public static boolean sua(Chapter c){
        return ChapterDAO.sua(c);
    }
    
    public static boolean xoa(int id_Chapter){
        return ChapterDAO.xoa(id_Chapter);
    }
    
    public static boolean xoaIdComic(int id_comic){
        return ChapterDAO.xoaIdComic(id_comic);
    }
    
    public static Double layNumberChapter(int id_chapter){
        return ChapterDAO.layNumberChapter(id_chapter);
    }
    
     public static ArrayList<Integer> layIdChapter(int id_comic){
        return ChapterDAO.layIdChapter(id_comic);
    }
}
