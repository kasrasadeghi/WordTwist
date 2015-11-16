

package wordtwist;

import java.util.ArrayList;


public class WordTwistModelImpl implements WordTwistModel
{
    // instance variables go here:
    // you need to keep track of the answer words, the currently formed word,
    // the unused letters, the base word (ie the 6 letter word), the time remaining
    // and the score
   
    public WordTwistModelImpl()
    {
        // initialize the score to 0
        
        //initializeState( WordTwistModelUtility.chooseRandomSixLetterWord() );
        initializeState( "MOBBED" );
    }
    
    // initializes the state of the model based on the original 6-letter word
    private void initializeState( String word )
    {
        // get the hidden words
        
        // create the word bank

        // initialize the unused characters to be the characters in the word

        // save the base word

        // initilize the current word
        
        // set time remaining to 2 minutes
        
    }
    
    @Override
    public Character getUnusedLetter( int index )
    {
        return ' ';
    }
    
    @Override
    public String getCurWord()
    {
        return "";
    }
    
    @Override
    public int getWordBankSize()
    {
        return 0;
    }
    
    @Override
    public String getWordBankWord( int index )
    {
        return "";
    }
    
    @Override
    public boolean isWordBankWordFound( int index )
    {
        return false;
    }
    
    
    @Override
    public void useLetter( int index )
    {
    }
       
    @Override
    public void unuseLetter( int index )
    {
    
    }
    
    public void submit()
    {
        
    }
    
    @Override
    public void reset()
    {
    }
    
    @Override
    public void scrambleUnusedLetters()
    {
    }
    
    @Override
    public void reduceTime( int dt )
    {
    }
    
    @Override
    public int getTime()
    {
        return 0;
    }
    
    @Override
    public int getScore()
    {
        return 0;
    }
    
    @Override
    public boolean roundOver()
    {
        return false;
    }
    
    @Override
    public boolean gameOver()
    {
        return false;
    }
    
    @Override
    public void startNewRound()
    {
 
    }
}