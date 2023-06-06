/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Comic;

import Controller.LoginController;
import Model.LoginModel;
import View.LoginView;
import java.text.ParseException;

/**
 *
 * @author abcde
 */
public class Main {
    public static void main(String[] args) throws ParseException {
//        SachModel sM = new SachModel();
//        SachView sV = new SachView();
//        SachController sC = new SachController(sM, sV);
        
        LoginModel lM = new LoginModel();
        LoginView lV = new LoginView();
        LoginController lC = new LoginController(lM,lV); 


    }
}
