/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comic;

/**
 *
 * @author abcde
 */
public class Chapter {
    private int idComic;
    private int idChapter;
    private double chapter_number;
    //private String url_image;

    public Chapter(int idComic, int idChapter, double chapter_number) {
        this.idComic = idComic;
        this.idChapter = idChapter;
        this.chapter_number = chapter_number;
        //this.url_image = url_image;
    }
    
    public Chapter() {
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public int getIdChapter() {
        return idChapter;
    }

    public void setIdChapter(int idChapter) {
        this.idChapter = idChapter;
    }

    public double getChapter_number() {
        return chapter_number;
    }

    public void setChapter_number(double chapter_number) {
        this.chapter_number = chapter_number;
    }

//    public String getUrl_image() {
//        return url_image;
//    }
//
//    public void setUrl_image(String url_image) {
//        this.url_image = url_image;
//    }
    
    
    
    
}
