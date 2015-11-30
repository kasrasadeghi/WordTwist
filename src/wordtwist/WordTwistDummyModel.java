/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package wordtwist;

import java.util.ArrayList;

/**
 *
 * @author DSTIGANT
 */
public class WordTwistDummyModel implements WordTwistModel
{
    private ArrayList<String> wbws;
    
    public WordTwistDummyModel()
    {
        wbws = new ArrayList<>();
        wbws.add( "BLOOMS" );
        wbws.add( "BLOOM" );
        wbws.add( "BOOM" );
        wbws.add( "BOO" );
        wbws.add( "BOOS"  );
        wbws.add( "BOOMS"  );
        
    }
    
    public String getCurWord()
    {
        return "MAMMA";
    }
    
    public int getWordBankSize()
    {
        return wbws.size();
    }
    
    public String getWordBankWord( int i )
    {
        return wbws.get( i );
    }
    
    public int getTime()
    {
        return 1 * 60 * 1000 + 35 * 1000;
    }
    
    public int getScore()
    {
        return 358;
    }
    
    public boolean roundOver()
    {
        return false;
    }
    
    public boolean gameOver()
    {
        return false;
    }

    @Override
    public Character getUnusedLetter(int index)
    {
        return "BLOOMS".charAt( index );
    }

    @Override
    public boolean isWordBankWordFound(int index)
    {
        if ( index == 0 ) return false;
        if ( index == 1 ) return true;
        if ( index == 2 ) return true;
        if ( index == 3 ) return false;
        if ( index == 4 ) return false;
        if ( index == 5 ) return true;
        
        return false;
    }

    @Override
    public void useLetter(int index)
    {
        System.out.println( "Using letter " + index + " : " + ("BLOOMS".charAt(index) ) );
    }

    @Override
    public void unuseLetter(int index)
    {
        System.out.println( "Unusing letter " + index + " : " + ("MAMMA".charAt(index) ) );
    }

    @Override
    public void submit()
    {
        System.out.println( "Submitting word" );
    }
    
    @Override
    public void ksubmit(String s) {
        
    }

    @Override
    public boolean kcontains(String s) {
        return false;
    }

    @Override
    public void reset()
    {
        System.out.println( "Resetting" );
    }

    @Override
    public void scrambleUnusedLetters()
    {
        System.out.println( "Scrambling" );
    }

    @Override
    public void reduceTime(int dt)
    {
        System.out.println( "Reducing time" );
    }

    @Override
    public void startNewRound()
    {
        System.out.println( "Starting new round" );
    }

    
    
}
