/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comic;

/**
 *
 * @author abcde
 */
public class Page {
    private int id_chapter;
    private int id_page;
    private String url_image;

    public Page(int id_chapter, int id_page, String url_image) {
        this.id_chapter = id_chapter;
        this.id_page = id_page;
        this.url_image = url_image;
    }
    
    public Page() {
    }

    public int getId_chapter() {
        return id_chapter;
    }

    public void setId_chapter(int id_chapter) {
        this.id_chapter = id_chapter;
    }

    public int getId_page() {
        return id_page;
    }

    public void setId_page(int id_page) {
        this.id_page = id_page;
    }

    public String getUrl_image() {
        return url_image;
    }

    public void setUrl_image(String url_image) {
        this.url_image = url_image;
    }
    
    
    
    
}
