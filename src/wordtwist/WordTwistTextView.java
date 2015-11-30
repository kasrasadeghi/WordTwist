

package wordtwist;


public class WordTwistTextView 
{
    private WordTwistModel model;
    
    public WordTwistTextView( WordTwistModel m )
    {
        model = m;
    }
    
    public void display()
    {
        System.out.println( "==========================================================" );
        for ( int i = 0; i < model.getWordBankSize(); i++ )
        {
            String w = model.getWordBankWord( i );
            if ( model.isWordBankWordFound(i))
            {
                for ( int j = 0; j < w.length(); j++ )
                {
                    System.out.print( w.charAt(j) + "*" );
                }
                System.out.println();
            }
            else
            {
                for ( int j = 0; j < w.length(); j++ )
                {
                    System.out.print( w.charAt(j) + " " );
                }
                System.out.println();
            }
        }
        System.out.println( "----------------------------------------------------------" );
        System.out.println( model.getCurWord() );
        for ( int i = 0; i < 6; i++ )
        {
            Character c = model.getUnusedLetter( i );
            if ( c == null )
            {
                System.out.print( " " );
            }
            else
            {
                System.out.print( c );
            }
        }
        System.out.println( "\t\t" + model.getScore() );
        System.out.println( "==========================================================" );
    }
}
