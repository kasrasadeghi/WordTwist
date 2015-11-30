/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtwist;

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
    @Override
    public boolean equals(Object word) {
        //TODO
        word = (Word)word;
        return (word.value == value) && (word.found == found);
    }
}
