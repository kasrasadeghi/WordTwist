/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wordtwist;

import apcscvm.CVMProgram;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 *
 * @author dstigant
 */
public class WordTwist
{

    public static void testControlView()
    {
        WordTwistModel m = new WordTwistDummyModel();
        
        WordTwistGUIView v = new WordTwistGUIView ();
        new CVMProgram( "Test CV", 800, 600, v, v, m ).start();
    }
    
    public static void testModel()
    {
        WordTwistModelImpl m = new WordTwistModelImpl();
        
        // uncomment the /* m */ and erase the nulls when your model class implements WordTwistModel
        WordTwistTextView v = new WordTwistTextView( m  );
        WordTwistTextControl c = new WordTwistTextControl( m );
        
        while ( true )
        {
            v.display();
            c.makeMove();
        }
    }
    
    public static void testModelUtility()
    {
        ArrayList<String> dict = WordTwistModelUtility.readDictionary();
        if ( dict == null )
        {
            System.out.println( "Dictionary is null" );
        }
        else
        {
            String w1 = dict.get( 0 );
            String w2 = dict.get( 1 );
            String w3 = dict.get( 2 );
            
            if ( w1.equals( "ABACUS" ) && w2.equals( "ABASE" ) && w3.equals( "ABASED" ) )
            {
                System.out.println( "Read Dictionary test passed" );
            }
            else
            {
                System.out.println( "Read Dictionary test failed" );
            }
        }
        
        ArrayList<String> sixLetterWords = WordTwistModelUtility.getSixLetterWords( dict );
        if ( sixLetterWords == null )
        {
            System.out.println( "Six letter words is null" );
        }
        else if ( sixLetterWords.isEmpty() )
        {
            System.out.println( "Six letter words is empty" );
        }
        else
        {
            boolean ok = true;
            for ( String s : sixLetterWords )
            {
                //ok = ok && s.length() == 6;
                ok &= s.length() == 6;
            }
            if ( !ok )
            {
                System.out.println( "Six letter words contains words that aren't 6 letters" );
            }
            else
            {
                System.out.println( "getSixLetterWords test passed" );
            }
            
            
        }
        ArrayList<String> hiddenWords = WordTwistModelUtility.getHiddenWords( "RUDDER" );
        if ( hiddenWords == null )
        {
            System.out.println( "Hidden words is null" );
        }
        else if ( hiddenWords.size() != 11 )
        {
            System.out.println( "Hidden words is wrong size" );
        }
        else
        {
            if ( !hiddenWords.contains("RUDDER") || !hiddenWords.contains("RUED") || !hiddenWords.contains("RUDE" ) )
            {
                System.out.println( "Hidden words isn't correct" );
            }
            else
            {
                System.out.println( "getHiddenWords test passed" );
            }
        }
        
    }
    
    private static void testUnusedInfo( WordTwistGUIView v, int x, int y, int er )
    {
        if ( v.getUnusedIndex(x, y) == er )
        {
            System.out.println( "Unused Test " + "( " + x + " , " + y + " ) passed" );
        }
        else
        {
            System.out.println( "Unused Test " + "( " + x + " , " + y + " ) failed" );
        }
    }
    
    private static void testUsedInfo( WordTwistGUIView v, int x, int y, int er )
    {
        if ( v.getUsedIndex(x, y, "MAMMA") == er )
        {
            System.out.println( "Used Test " + "( " + x + " , " + y + " ) passed" );
        }
        else
        {
            System.out.println( "Used Test " + "( " + x + " , " + y + " ) failed" );
        }
    }
    
    private static void testResetInfo( WordTwistGUIView v, int x, int y, boolean er )
    {
        if ( v.inResetButton(x, y) == er )
        {
            System.out.println( "Reset Test " + "( " + x + " , " + y + " ) passed" );
        }
        else
        {
            System.out.println( "Reset Test " + "( " + x + " , " + y + " ) failed" );
        }
    }
    
    private static void testSubmitInfo( WordTwistGUIView v, int x, int y, boolean er )
    {
        if ( v.inSubmitButton(x, y) == er )
        {
            System.out.println( "Submit Test " + "( " + x + " , " + y + " ) passed" );
        }
        else
        {
            System.out.println( "Submit Test " + "( " + x + " , " + y + " ) failed" );
        }
    }
    
    private static void testScrambleInfo( WordTwistGUIView v, int x, int y, boolean er )
    {
        if ( v.inScrambleButton(x, y) == er )
        {
            System.out.println( "Scramble Test " + "( " + x + " , " + y + " ) passed" );
        }
        else
        {
            System.out.println( "Scramble Test " + "( " + x + " , " + y + " ) failed" );
        }
    }
    
    
    public static void testViewInfo()
    {
        WordTwistDummyModel m = new WordTwistDummyModel();
        
        // remove null and uncomment the rest of the line below
        // when you have implemented the WordTwistView interface
        WordTwistGUIView v = new WordTwistGUIView();
        
        testUnusedInfo( v, 224, 371, -1 );
        testUnusedInfo( v, 287, 431, 1 );
        testUnusedInfo( v, 345, 367, -1 );
        testUnusedInfo( v, 411, 429, 3 );
        testUnusedInfo( v, 463, 363, -1 );
        testUnusedInfo( v, 527, 432, 5 );
        testUnusedInfo( v, 531, 369, -1 );
        testUnusedInfo( v, 278, 476, -1 );
        testUnusedInfo( v, 354, 476, -1 );
        testUnusedInfo( v, 491, 476, -1 );
        
        testUsedInfo( v, 224, 371, 0 );
        testUsedInfo( v, 287, 431, -1 );
        testUsedInfo( v, 345, 367, 2 );
        testUsedInfo( v, 411, 429, -1 );
        testUsedInfo( v, 463, 363, 4 );
        testUsedInfo( v, 527, 432, -1 );
        testUsedInfo( v, 531, 369, -1 );
        testUsedInfo( v, 278, 476, -1 );
        testUsedInfo( v, 354, 476, -1 );
        testUsedInfo( v, 491, 476, -1 );
        
        testResetInfo( v, 224, 371, false );
        testResetInfo( v, 287, 431, false );
        testResetInfo( v, 345, 367, false );
        testResetInfo( v, 411, 429, false );
        testResetInfo( v, 463, 363, false );
        testResetInfo( v, 527, 432, false );
        testResetInfo( v, 531, 369, false );
        testResetInfo( v, 278, 476, true );
        testResetInfo( v, 354, 476, false );
        testResetInfo( v, 491, 476, false );
        
        testSubmitInfo( v, 224, 371, false );
        testSubmitInfo( v, 287, 431, false );
        testSubmitInfo( v, 345, 367, false );
        testSubmitInfo( v, 411, 429, false );
        testSubmitInfo( v, 463, 363, false );
        testSubmitInfo( v, 527, 432, false );
        testSubmitInfo( v, 531, 369, false );
        testSubmitInfo( v, 278, 476, false );
        testSubmitInfo( v, 354, 476, true );
        testSubmitInfo( v, 491, 476, false );
        
        testScrambleInfo( v, 224, 371, false );
        testScrambleInfo( v, 287, 431, false );
        testScrambleInfo( v, 345, 367, false );
        testScrambleInfo( v, 411, 429, false );
        testScrambleInfo( v, 463, 363, false );
        testScrambleInfo( v, 527, 432, false );
        testScrambleInfo( v, 531, 369, false );
        testScrambleInfo( v, 278, 476, false );
        testScrambleInfo( v, 354, 476, false );
        testScrambleInfo( v, 491, 476, true );
        
    }
    
    public static void finalIntegration()
    {
        WordTwistModelImpl m = new WordTwistModelImpl();
        WordTwistGUIView v = new WordTwistGUIView();
        new CVMProgram( "Word Twist", 800, 600, v, v, m ).start();
    }
    
    public static void main(String[] args)
    {
        // when both parts are done, erase these lines...
        
        Scanner in = new Scanner( System.in );
        System.out.println( "Test Model (U)tility" );
        System.out.println( "Test (M)odel" );
        System.out.println( "Test (V)iew/Control" );
        System.out.println( "Test View (I)nfo" );
        
        String cmd = in.next().toUpperCase();
        
        if ( cmd.charAt(0) == 'U' )
        {
            testModelUtility();
        }
        else if ( cmd.charAt(0) == 'M' )
        {
            testModel();
        }
        else if ( cmd.charAt(0) == 'V' )
        {
            testControlView();
        }
        else if ( cmd.charAt(0) == 'I' )
        {
            testViewInfo();
        }
                
        // and uncomment this line:
         //finalIntegration();
           
        
    }
}
