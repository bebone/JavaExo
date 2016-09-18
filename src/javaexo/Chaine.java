/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaexo;

/**
 *
 * @author anais classe pour gérer une chaîne de caractères la visibilité et
 * l'encapsulation
 */
public class Chaine {

    // propriété(s)
    private String maChaine;

    //constructeur
    public Chaine(String getChaine) {
        this.maChaine = getChaine;

    }

    public String getMachaine() {
        if (this.maChaine.length() > 15) {
            return ("Phrase trop longue");

        } else {
            return this.maChaine;
        }

    }

    public void setMachaine(String getChaine) {
        this.maChaine = getChaine;
    }

}
