

package wordtwist;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;


public class WordTwistModelUtility 
{
    private static final ArrayList<String> dictionary = readDictionary();
    private static final ArrayList<String> sixLetterWords = getSixLetterWords( dictionary );
    
    
    // returns a list of all the words in WordTwist Words.txt
    public static ArrayList<String> readDictionary()
    {
        Scanner dictionaryFileScanner;
        try
        {
            dictionaryFileScanner = new Scanner( new File("WordTwist Words.txt") );
        } 
        catch (FileNotFoundException ex)
        {
            System.out.println( "Dictionary not found" );
            return null;
        }
        
        // read words from the scanner object into an array list and return the array list
        return null;
    }
    
    // filters out the six letter words from the input list
    public static ArrayList<String> getSixLetterWords( ArrayList<String> dictionary )
    {
        // create and return an array list of the six letter words in the dictionary
        // HINT: this is a filter function
        return null;
    }
    
    // chooses a random word from the list sixLetterWords
    public static String chooseRandomSixLetterWord()
    {
        return sixLetterWords.get( (int)(Math.random() * sixLetterWords.size() ) );
    }
    
    public static ArrayList<String> getDictionary() { return dictionary; }
    
    // returns all the words that can be formed from str
    public static ArrayList<String> getHiddenWords( String str )
    {
        // use the getPermutations function to get a list of all the possible letter
        // combinations that can be formed from str

        // then, use the dictionary to see which of those words are actually
        // legal english words.  return a list of these words
        // HINT: another filter function
        
        return null;
    }
    
    // getPermutations
    // consumes a string and returns a list of all the permutations of the letters of str.
    // for example, if str is "CAT", the result of getPermutations will be a list
    // containing the Strings "CAT", "CTA", "ACT", "ATC", "TCA", "TAC",
    // "CA", "CT", "AC", "AT", "TC", "TA", "C", "A", and "T" (in some order)
    public static ArrayList<String> getPermutations( String str )
    {
        if ( str.length() == 0 )
        {
            ArrayList<String> empty = new ArrayList<>();
            empty.add( "" );
            return empty;
        }
        
        ArrayList<String> scarlet = new ArrayList<>();
        
        for ( int i = 0; i < str.length(); i++ )
        {
            ArrayList<String> recur = getPermutations( str.substring(0, i) + str.substring(i+1) );
            for ( String s : recur )
            {
                String add = str.charAt(i) + s;
                if ( !scarlet.contains( add ) )
                {
                    scarlet.add( add );
                }
                if ( !scarlet.contains( s ) )
                {
                    scarlet.add( s );
                }
            }
        }
        return scarlet;
    }
    
    
}
