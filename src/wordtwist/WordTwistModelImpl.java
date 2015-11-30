

package wordtwist;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static wordtwist.WordTwistModelUtility.*;

public class WordTwistModelImpl implements WordTwistModel
{
    // instance variables go here:
    // you need to keep track of the answer words, the currently formed word,
    // the unused letters, the base word (ie the 6 letter word), the time remaining
    // and the score
    private ArrayList<String> wordsS;
    private ArrayList<Character> unusedLetters = new ArrayList<>();
    private ArrayList<Character> usedLetters = new ArrayList<>();
    private int time;
    private int score;
    private ArrayList<Word> words;
   
    public WordTwistModelImpl()
    {
        // initialize the score to 0
        score = 0;
        initializeState( WordTwistModelUtility.chooseRandomSixLetterWord() );
//        initializeState( "MOBBED" );
    }
    
    // initializes the state of the model based on the original 6-letter word
    private void initializeState( String word )
    {
        // get the hidden words
        
        // create the word bank
        words = getHiddenWords(word).stream().map(x -> new Word(x)).collect(Collectors.toCollection(ArrayList::new));
        
        // initialize the unused characters to be the characters in the word
        for (int i = 0; i < word.length(); ++i)
//            System.out.println(word.charAt(i));
            unusedLetters.add(word.charAt(i));
        scrambleUnusedLetters();
        
        // save the base word
        // initilize the current word
        
        // set time remaining to 2 minutes
        this.time = 120000;
    }
    
    @Override
    public Character getUnusedLetter( int index )
    {
        return unusedLetters.get(index);
    }
    
    @Override
    public String getCurWord()
    {
        return usedLetters.stream()
                .filter(x -> x != ' ')
                .map(a -> String.valueOf(a))
                .collect(Collectors.joining(""));
    }
    
    @Override
    public int getWordBankSize()
    {
        return words.size();
    }
    
    @Override
    public String getWordBankWord( int index )
    {
        return words.get(index).value;
    }
    
    @Override
    public boolean isWordBankWordFound( int index )
    {
        return words.get(index).found;
    }
    
    
    @Override
    public void useLetter( int index )
    {
        usedLetters.add(unusedLetters.remove(index));
    }
       
    @Override
    public void unuseLetter( int index )
    {
        unusedLetters.add(usedLetters.remove(index));
    }
    
    @Override
    public void submit()
    {
        Word curWord = new Word(getCurWord());
        
        if (words.get(wordsS.indexOf(curWord)).found == false)
        switch(usedLetters.size()) {
            case 3: score+= 50; break;
            case 4: score+= 75; break;
            case 5: score+= 100; break;
            case 6: score+= 200; break;
        }
        if (wordsS.contains(curWord) && words.get(wordsS.indexOf(curWord)).found == false )
            words.get(wordsS.indexOf(curWord)).found = true;
    }
    
    @Override
    public void reset()
    {
        usedLetters.forEach(x -> unusedLetters.add(x));
        usedLetters.clear();
    }
    
    @Override
    public void scrambleUnusedLetters()
    {
        Character letter;
        for (int i = 0; i < 100; ++i){
            letter = unusedLetters.remove((int)(Math.random() * unusedLetters.size()));
            unusedLetters.add(letter);
        }
    }
    
    @Override
    public void reduceTime( int dt )
    {
        time -= dt;
    }
    
    @Override
    public int getTime()
    {
        return time/1000;
    }
    
    @Override
    public int getScore()
    {
        return score;
    }
    
    @Override
    public boolean roundOver()
    {
        if (!words.stream().noneMatch((word) -> (!word.found))) 
            return false;
        return time <= 0;
    }
    
    @Override
    public boolean gameOver()
    {
        boolean sixfound = words.stream()
                .filter(x -> x.value.length() == 6)
                .filter(x -> x.found)
                .count() > 0;
        
        return roundOver() && sixfound;
    }
    
    @Override
    public void startNewRound()
    {
        if (gameOver()) score = 0;
        initializeState( WordTwistModelUtility.chooseRandomSixLetterWord() );
    }
}