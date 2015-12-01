package wordtwist;

import java.util.ArrayList;
import java.util.stream.Collectors;
import static wordtwist.WordTwistModelUtility.*;

public class WordTwistModelImpl implements WordTwistModel {

    // instance variables go here:
    // you need to keep track of the answer words, the currently formed word,
    // the unused letters, the base word (ie the 6 letter word), the time remaining
    // and the score

    private ArrayList<Character> unusedLetters = new ArrayList<>();
    private ArrayList<Character> usedLetters = new ArrayList<>();
    private int time;
    private int score;
    private ArrayList<Word> words;

    public WordTwistModelImpl() {
        // initialize the score to 0
        score = 0;
        initializeState(WordTwistModelUtility.chooseRandomSixLetterWord());
//        initializeState( "MOBBED" );
    }

    // initializes the state of the model based on the original 6-letter word
    private void initializeState(String word) {
        // get the hidden words
        // create the word bank
        words = getHiddenWords(word).stream().map(x -> new Word(x)).collect(Collectors.toCollection(ArrayList::new));
        
        
        // initialize the unused characters to be the characters in the word
        for (int i = 0; i < word.length(); ++i) //            System.out.println(word.charAt(i));
        {
            unusedLetters.add(word.charAt(i));
        }
//        scrambleUnusedLetters();
        this.time = 120000;
        // save the base word
        // initilize the current word
        // set time remaining to 2 minutes
        
//        time = 10000;
    }

    @Override
    public Character getUnusedLetter(int index) {
        return (index < unusedLetters.size() && index > -1)? unusedLetters.get(index) : ' ';
    }

    @Override
    public String getCurWord() {
        return usedLetters.stream()
                .map(a -> String.valueOf(a))
                .collect(Collectors.joining(""));
    }

    @Override
    public int getWordBankSize() {
        return words.size();
    }

    @Override
    public String getWordBankWord(int index) {
        return words.get(index).value;
    }

    @Override
    public boolean isWordBankWordFound(int index) {
        return words.get(index).found;
    }

    @Override
    public void useLetter(int index) {
        Character temp = unusedLetters.get(index);
        usedLetters.add(temp);
        unusedLetters.set(index, ' ');
    }

    @Override
    public void unuseLetter(int index) {
        for (int i = 0; i < 6; ++i)
            if (unusedLetters.get(i) == ' '){
                unusedLetters.set(i, usedLetters.remove(index));
                return;
            }
    }

    @Override
    public void submit() {
        Word curWord = new Word(getCurWord());
        
        if (curWord.length() < 3 || words.indexOf(curWord) == -1) {reset(); return;}
        
        if (words.get(words.indexOf(curWord)).found == false) {
            if (words.contains(curWord)) {
                words.get(words.indexOf(curWord)).found = true;
                switch (usedLetters.size()) {
                    case 3: score +=  50;break;
                    case 4: score +=  75;break;
                    case 5: score += 100;break;
                    case 6: score += 200;break;
                }
            }
        }
        if (words.stream().allMatch(word -> word.found))
            score += 1000;
        reset();
    }
    
    @Override
    public boolean kcontains(String curText) {
        return getHiddenWords(words.get(0).value).contains(curText);
    }
    
    @Override
    public void ksubmit(String curText) {
        Word curWord = new Word(curText);

        if (words.get(words.indexOf(curWord)).found == false) {
            
            if (words.contains(curWord)) {
                words.get(words.indexOf(curWord)).found = true;
            
                switch (curText.length()) {
                    case 3: score +=  50;break;
                    case 4: score +=  75;break;
                    case 5: score += 100;break;
                    case 6: score += 200;break;
                }
            }
        }
        reset();
    }

    @Override
    public void reset() {
//        System.out.println("reset");
        while (usedLetters.size() > 0) unuseLetter(0);
    }

    @Override
    public void scrambleUnusedLetters() {
        Character letter;
        for (int i = 0; i < 100; ++i) {
            letter = unusedLetters.remove((int) (Math.random() * unusedLetters.size()));
            unusedLetters.add(letter);
        }
    }

    @Override
    public void reduceTime(int dt) {
        if (!roundOver())
            time -= dt;
    }

    @Override
    public int getTime() {
        return time;
    }

    @Override
    public int getScore() {
        return score;
    }

    @Override
    public boolean roundOver() {
        if (words.stream().allMatch(word -> word.found))
            return true;
        
        return time <= 0;
    }

    @Override
    public boolean gameOver() {
        boolean noSixfound = words.stream()
                .filter(x -> x.value.length() == 6)
                .filter(x -> x.found)
                .count() == 0;

        return roundOver() && noSixfound;
    }

    @Override
    public void startNewRound() {
        
        reset();
        unusedLetters.clear();
        if (gameOver()) score = 0;
        
        initializeState(WordTwistModelUtility.chooseRandomSixLetterWord());
    }
}
