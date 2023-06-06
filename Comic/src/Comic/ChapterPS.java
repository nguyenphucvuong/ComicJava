/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comic;

/**
 *
 * @author abcde
 */
public class ChapterPS {
    private int idPresent;
    private int idUser;
    private int idComic;
    private Double chapterPresent;

    public ChapterPS(int idPresent,int idUser, int idComic, Double chapterPresent) {
        this.idPresent = idPresent;
        this.idUser = idUser;
        this.idComic = idComic;
        this.chapterPresent = chapterPresent;
    }

    public ChapterPS() {
    }

    
    
    public int getIdPresent() {
        return idPresent;
    }

    public void setIdPresent(int idPresent) {
        this.idPresent = idPresent;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public Double getChapterPresent() {
        return chapterPresent;
    }

    public void setChapterPresent(Double chapterPresent) {
        this.chapterPresent = chapterPresent;
    }
    
    
}
