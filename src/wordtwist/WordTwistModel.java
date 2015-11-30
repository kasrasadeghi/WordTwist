
package wordtwist;

/**
 *
 * @author dstigant
 */
interface WordTwistModel
{
    // returns the letter at index (0-5) that hasn’t been used yet or
    // null if that letter has already been used to form the current word 
    Character getUnusedLetter( int index );
    
    // returns the current word being formed by the user
    String getCurWord();

    // returns the number of words in the word bank
    int getWordBankSize();
    
    // returns the word bank word at index
    String getWordBankWord( int index );
    
    // returns true if the word at index has been found 
    // by the user, false otherwise
    boolean isWordBankWordFound( int index );
    
    // returns the amount of time left in the round in milliseconds
    int getTime();
    
    // returns the current score
    int getScore();
    
    // returns  true if the round is over (ie all words have been found or the 
    // time limit for the round has elapsed)
    boolean roundOver();
    
    // returns true if the game is over (ie the current round finished without the 
    // user finding at least one 6 letter word).
    boolean gameOver();
    
    // moves the letter at the given index from the unused letters to the 
    // end of the current word
    void useLetter( int index );
    
    // moves the letter at the given index from the current word to the 
    // first empty space in the unused letters
    void unuseLetter( int index );
    
    // submits the current word.  If the word is in the word bank and hasn’t already 
    // been found, the score should be increased (50/75/100/200 pts for words of length 3/4/5/6).  
    // Regardless, the current word is reset so that all tiles are unused
    void submit();
    
    void ksubmit(String s);
    boolean kcontains(String s);
    
    // resets all letters in the current word to the unused letters
    void reset();
    
    // scrambles all letters in the unused letters
    void scrambleUnusedLetters();
    
    // reduces the time left in the round by dt milliseconds
    void reduceTime( int dt );
    
    // picks a new 6 letter word, gets a new word bank, resets the round time, 
    // and, if the game was over, the score.
    void startNewRound();

}
