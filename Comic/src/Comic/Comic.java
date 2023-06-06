/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Comic;

/**
 *
 * @author abcde
 */
public class Comic {
    private int idComic;
    private int idCategory;
    private String nameComic;
    private String url_image;

    public Comic(int idComic, int idCategory, String nameComic, String url_image) {
        this.idComic = idComic;
        this.idCategory = idCategory;
        this.nameComic = nameComic;
        this.url_image = url_image;
    }

    public Comic() {
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public int getIdCategory() {
        return idCategory;
    }

    public void setIdCategory(int idCategory) {
        this.idCategory = idCategory;
    }

    public String getNameComic() {
        return nameComic;
    }

    public void setNameComic(String nameComic) {
        this.nameComic = nameComic;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }


    
    
    
    
    
}
