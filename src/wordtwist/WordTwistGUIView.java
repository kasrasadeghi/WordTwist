

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
    private Font bigFont;
    private Font smallFont;
    private Color BG = Color.GREEN;
    private int cx = 200;
    private int cy = 400;
    
    // constructor:
    // initialize the font objects
    public WordTwistGUIView()
    {
        bigFont = new Font( "SansSerif", Font.PLAIN, 30);
        smallFont = new Font ("SansSerif", Font.PLAIN, 15);
    }
    
    private void drawStringBox( Graphics g, int tlx, int tly, String s, int w, int h, Font f, Color col )
    {
        g.setFont(f);
        g.setColor(col);
        g.fillRect(tlx, tly, w, h);
        g.setColor(Color.BLACK);
        g.drawRect(tlx, tly, w, h);
        
        GraphicsUtilityFunctions.drawStringWithFontInRectangle(g, s, f, tlx, tly, w, h );
    }
    
    private void drawLetterTile( Graphics g, int tlx, int tly, char c, int size, Font f, Color col )
    {
        drawStringBox(g, tlx, tly, c + "", size, size, f, col);
    }

    // draws a big letter tile (by calling drawLetterTile)
    private void drawBigLetterTile( Graphics g, int tlx, int tly, char c )
    {
        if (c != ' ')
            drawLetterTile(g, tlx, tly, c, 55, bigFont, BG);
    }

    // draws a small letter tile
    private void drawSmallLetterTile( Graphics g, int tlx, int tly, char c, Color col )
    {
        drawLetterTile(g, tlx, tly, c, 20, smallFont, col);
    }
    
    // draws a button with the given text in the given location with the given dimensions
    private void drawButton( Graphics g, int tlx, int tly, String text ) 
    {
        drawStringBox(g, tlx, tly, text, 120, 20, smallFont, BG); 
    }

    // draw the time left
    private void drawTime( Graphics g, int msLeft )
    {
        int time = msLeft / 1000;
        String timeString = time/60 + ":" + ((time%60 < 10) ? "0" + time%60:time%60 );
        drawStringBox(g, cx - 185, cy, timeString, 125, 55, bigFont, BG);
        
    }

    // draws the score
    private void drawScore(Graphics g, int score) 
    {
        drawStringBox(g, cx - 185, cy + 55, "" + score, 125, 55, bigFont, BG);
    }
    
    // draws the round over message
    private void drawRoundOverMessage( Graphics g, boolean roundOver, boolean gameOver )
    {
        if (roundOver) {
            String text = "";
            if (gameOver)
                text = "Game Over. Restart?";
            else text = "Next Round";
            drawStringBox(g, cx, cy - 60, text, 360, 55, bigFont, BG);
        }
    }
    
    @Override
    public void paint(WordTwistModel model, Graphics g, int w, int h ) {
        // display the unused letters
        for (int i = 0; i < 6; ++i) {
            drawBigLetterTile(g, cx + 60 * i, cy, model.getUnusedLetter(i));
        }
        
        // display the used letters forming the current word
        for (int i = 0; i < model.getCurWord().length(); ++i) {
            drawBigLetterTile(g, cx + 60 * i, cy - 60, model.getCurWord().charAt(i));
        }
        
        // display the answer blanks for the hidden words
        for (int i = 0; i < model.getWordBankSize(); ++i) {
            String word = model.getWordBankWord(i);
            for (int j = 0; j < word.length(); ++j)
                drawSmallLetterTile(g, 10 + 25*j + (i/12) * 175, 10 + 25*(i%12),
//                        (model.isWordBankWordFound(i))?word.charAt(j): ' ', BG);
                        (model.isWordBankWordFound(i))?word.charAt(j): word.toLowerCase().charAt(j), BG);
        }
        drawStringBox(g, cx + 200, cy + 500, model.getWordBankWord(0), 125, 55, bigFont, BG);
        
        // draw the reset, submit and scramble buttons
        drawButton(g, cx, cy + 60, "Reset");
        drawButton(g, cx + 120, cy + 60, "Submit");
        drawButton(g, cx + 240, cy + 60, "Scramble");
        
        // display the time, score and round over messages
        drawTime(g, model.getTime());
        drawScore(g, model.getScore());
        drawRoundOverMessage(g, model.roundOver(), model.gameOver());
    }

    // returns the index within the unused character area
    // if the coordinates are outside this area, return -1
    public int getUnusedIndex( int mx, int my )
    {
        for (int i = 0; i < 6; ++i) 
            if ( mx > cx + 60*i && mx < cx + 60*i + 55 && my > cy && my < cy + 55)
                return i;
        return -1;
    }

    // returns the index within the used character area
    // if the coordinates are outside this area, reaturn -1
    public int getUsedIndex( int mx, int my, String curWord )
    {
        for (int i = 0; i < curWord.length(); ++i) 
            if ( mx > cx + 60*i && mx < cx + 60*i + 55 && my > cy - 60 && my < cy - 5)
                return i;
        return -1;
    }

    // returns true if the coordinates are inside the reset button (false otherwise)
    public boolean inResetButton( int mx, int my )
    {
        //cx, cy - 55, text, 180, 55
        return my > cy + 60 && my < cy + 80 && mx > cx && mx < cx + 120;        
    }
    
    // returns true if the coordinates are inside the submit button (false otherwise)
    public boolean inSubmitButton( int mx, int my )
    {
        return my > cy + 60 && my < cy + 80 && mx > cx + 120 && mx < cx + 240;
    }
    
    // returns true if the coordinates are inside the scramble button
    public boolean inScrambleButton( int mx, int my )
    {
        return my > cy + 60 && my < cy + 80 && mx > cx + 240 && mx < cx + 360;
    }

    // returns true if the coordinates are inside the message button and the round is over
    public boolean inMessageButton( int mx, int my, boolean roundOver )
    {
        return mx > cx && my > cy - 60 && mx < cx + 360 && my < cy - 5 && roundOver;
    }

    @Override
    public void handleMouseClick( WordTwistModel model, int ea, MouseEvent me )
    {
        // use the functions above to determine where the user clicked
        // based on that, determine what action to take:
        // use a letter, unuse a letter, submit, reset, scramble etc.
        int x = me.getX();
        int y = me.getY();
        if (model.getUnusedLetter(getUnusedIndex(x, y)) != ' ')
            model.useLetter(getUnusedIndex(x, y));
        if (getUsedIndex(x, y, model.getCurWord()) != -1)
            model.unuseLetter(getUsedIndex(x, y, model.getCurWord()));
        if (inResetButton(x, y)) model.reset();
        if (inSubmitButton(x, y)) model.submit();
        if (inScrambleButton(x, y)) model.scrambleUnusedLetters();
        if (inMessageButton(x, y, model.roundOver())) model.startNewRound();
    }
   
    @Override
    public void handleTimePassage( WordTwistModel model, int ea, int dt )
    {
        // forward this on to the model so it can decrease its timer.
        model.reduceTime(dt);
    }
    
}

