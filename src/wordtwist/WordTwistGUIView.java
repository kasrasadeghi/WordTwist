

package wordtwist;


import apcscvm.DefaultControl;
import apcscvm.GraphicsUtilityFunctions;
import apcscvm.View;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;


public class WordTwistGUIView extends DefaultControl<WordTwistModel> implements View<WordTwistModel>
{
    // add member variables for two fonts here
    
    // constructor:
    // initialize the font objects
    public WordTwistGUIView()
    {
    }
    
    // draws a letter tile with its ULC at the given coordinates
    // the particular attributes of the letter tile are also supplied:
    // the contents of the letter tile
    // the size of the letter tile
    // the font to use
    // the background color to use
    private void drawLetterTile( Graphics g, int ULCx, int ULCy, char c, int size, Font f, Color col )
    {
    }

    // draws a big letter tile (by calling drawLetterTile)
    private void drawBigLetterTile( Graphics g, int ULCx, int ULCy, char c )
    {
    }

    // draws a small letter tile
    private void drawSmallLetterTile( Graphics g, int ULCx, int ULCy, char c, Color col )
    {
    }
    
    // draws a button with the given text in the given location with the given dimensions
    private void drawButton( Graphics g, int ULCx, int ULCy, int w, int h, String text ) 
    {
    }

    // draw the time left
    private void drawTime( Graphics g, int msLeft )
    {
    }

    // draws the score
    private void drawScore(Graphics g, int score) {
    }
    
    // draws the round over message
    private void drawRoundOverMessage( Graphics g, boolean roundOver, boolean gameOver )
    {
    }
     
    @Override
    public void paint(WordTwistModel model, Graphics g, int w, int h ) {
        // display the unused letters
        
        // display the used letters forming the current word
        
        // display the answer blanks for the hidden words
        
        // draw the reset, submit and scramble buttons

        // display the time, score and round over messages
                
    }

    // returns the index within the unused character area
    // if the coordinates are outside this area, return -1
    public int getUnusedIndex( int x, int y )
    {
        return -1;
    }

    // returns the index within the used character area
    // if the coordinates are outside this area, reaturn -1
    public int getUsedIndex( int x, int y, String curWord )
    {
        return -1;
    }

    // returns true if the coordinates are inside the reset button (false otherwise)
    public boolean inResetButton( int x, int y )
    {
        return false;        
    }
    
    // returns true if the coordinates are inside the submit button (false otherwise)
    public boolean inSubmitButton( int x, int y )
    {
        return false;
    }
    
    // returns true if the coordinates are inside the scramble button
    public boolean inScrambleButton( int x, int y )
    {
        return false;
    }

    // returns true if the coordinates are inside the message button and the round is over
    public boolean isMessageButton( int x, int y, boolean roundOver )
    {
        return false;
    }

    @Override
    public void handleMouseClick( WordTwistModel model, int ea, MouseEvent me )
    {
        // use the functions above to determine where the user clicked
        // based on that, determine what action to take:
        // use a letter, unuse a letter, submit, reset, scramble etc.
    }
   
    public void handleTimePassage( WordTwistModel model, int ea, int dt )
    {
        // forward this on to the model so it can decrease its timer.
    }
    
}

