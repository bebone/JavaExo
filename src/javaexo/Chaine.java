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
    private char[] _aChaine;
    private int _nTaille;
    private static final int _nTailleMax = 1000;

    // *** CONSTRUCTEURS
    public Chaine() {
        _aChaine = new char[_nTailleMax];
        _nTaille = 0;
    }

    // Surcharge 1
    public Chaine(String s) {
        this(); // ?? on appelle le premier constructeur?? - Prof !
        int lg = s.length();
        if (lg > _nTailleMax) {
            _nTaille = _nTailleMax;
        } else {
            _nTaille = lg;
        }

        for (int i = 0; i < _nTaille; i++) {
            _aChaine[i] = s.charAt(i); // OK [Compris]
        }
        _aChaine = s.toCharArray(); // ?? ça marche si on fait ça sans passer par une boucle ??

    }

    // Surcharge 2
    public Chaine(Chaine c) { // On créé un objet Chaine qui attend en paramètre un autre objet de type Chaine
        this();
        _nTaille = c.getTaille(); // OK
        for (int i = 0; i < _nTaille; i++) {
            _aChaine[i] = c.getCar(i); // OK
        }

    }

    //GETTERS
    public int getTaille() {
        return _nTaille;
    }

    public char getCar(int pos) {
        if (pos >= _nTaille) {
            return 0;
        } else {
            return _aChaine[pos];
        }
    }

    public Chaine getChaine(int pos, int lg) {
        String s = getString(pos, lg);
        return new Chaine(s); // appelle la surcharge (1 paramètre string attendu)
    }

    public String getString(int pos, int lg) {
        if (pos + lg >= _nTaille) {
            lg = _nTaille - pos; // ?
        }
        if (lg > 0) {
            return new String(_aChaine, pos, lg); // Class String (char[] value, int offset, int count)
        } else {
            return ""; // VIDE
        }

    }

    //SETTERS
    public void setCar(int pos, char c) {
        if (pos < _nTaille) {
            _aChaine[pos] = c; // OK
        }
    }

    public void setChaine(Chaine c, int pos) {
        int lg = c.getTaille();
        if (pos + lg >= _nTailleMax) {
            lg = _nTailleMax - pos; // ?
        }
        for (int i = 0; i < lg; i++) {
            _aChaine[pos + i] = c.getCar(i);
        }
        _nTaille = Math.max(_nTaille, pos + lg);

    }

    public void concat(Chaine c) {
        int lg = c.getTaille();
        if (_nTaille + lg >= _nTailleMax) {
            lg = _nTailleMax - _nTaille;
        }
        for (int i = 0; i < lg; i++) {
            _aChaine[_nTaille + i] = c.getCar(i);
            _nTaille += lg;
        }

    }

    public void tronque(int lg) { //tableau avec nouvelle taille (inférieure)

        if (lg < _nTaille) {
            _nTaille = lg; // OK
        }
    }

    public boolean egale(String s) {
        return s.equals(convert()); // (on ne peut pas faire == avec String car réf. d'objet )
    }

    public boolean egale(Chaine c) {

        if (_nTaille != c.getTaille()) {
            return false;
        }

        for (int i = 0; i < _nTaille; i++) {
            if (_aChaine[i] != c.getCar(i)) {
                return false;
            }
        }
        return true; // OK
    }

    public String convert() {
        //return _aChaine.toString(); OK avec ça ?
        return new String(_aChaine, 0, _nTaille);
    }

    public int recherche(Chaine c) { // Recherche avec un objet Chaine / objet Chaine ..
        /* On a l'objet A Chaine qui appelle la fonction recherche avec objet Chaine B
        _nTaille correspond à la _nTaille de l'objet A (?) */
        int lg = c.getTaille();
        for (int i = 0; i < _nTaille - lg; i++) {
            int j = 0;
            while (j < lg && c.getCar(i) == _aChaine[i + 1]) {
                j++;
            }
            if (j == lg) {
                return j;
            }

        }
        return -1;
    }

    public void affiche() {
        System.out.println(convert());
    }

    public void insere(Chaine c, int pos, int lg) {
        decale(pos, lg);
        lg = Math.min(c.getTaille(), _nTailleMax - pos);

        for (int i = 0; i < lg; i++) {
            _aChaine[pos + i] = c.getCar(i);
        }

    }

    // *** AUXILIAIRES
    private void decale(int pos, int lg) {

        for (int i = _nTaille - 1; i >= pos; i--) {
            if (i + lg < _nTailleMax) {
                _aChaine[i + lg] = _aChaine[i];
            }
            _nTaille = Math.min(_nTailleMax, _nTaille + lg);
        }

    }

}
