/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtwist;

import java.util.Objects;

/**
 *
 * @author Sarah M
 */
public class Word {
    public String value;
    public boolean found;
    
    public Word(String value, boolean found) {
        this.value = value;
        this.found = found;
    }
    
    public Word(String value) {
        this(value, false);
    }
//    
//    public boolean equals(Object o) {
//        if (!(o instanceof Point))
//            return false;
//        Point p = (Point)o;
//        return (p.x == this.x && p.y == this.y);
//    }
    
    @Override
    public boolean equals(Object word) {
        //TODO
        if (!(word instanceof Word)){
            return false;
        }
        Word tempword = (Word)word;
        return (tempword.value.equals(this.value)) && (tempword.found == this.found);
    }

    @Override
    public int hashCode() {
        //apparently you multiply the hashes by prime numbers because it makes good code or something.
        //hashcodes are things that make all objects into a 32-bit signed integer.
        //basically let netbeans autogenerate this because we're not good enough at code to do this on the fly.
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.value);
        hash = 97 * hash + (this.found ? 1 : 0);
        return hash;
    }
}
