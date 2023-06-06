/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import Comic.Category;
import DAO.CategoryDAO;
import java.util.ArrayList;

/**
 *
 * @author abcde
 */
public class CategoryModel {

    public CategoryModel() {
    }

    public static ArrayList<Category> layTatCaCategory() {
        return CategoryDAO.layTatCaCategory();
    }

    public static Category categoryById(int id) {
        return CategoryDAO.categoryById(id);
    }

    public static boolean them(Category c) {
        return CategoryDAO.them(c);
    }

    public static boolean sua(Category c) {
        return CategoryDAO.sua(c);
    }

    public static boolean xoa(int id_category) {
        return CategoryDAO.xoa(id_category);
    }
}
