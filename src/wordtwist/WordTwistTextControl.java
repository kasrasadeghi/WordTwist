

package wordtwist;

import java.util.Scanner;


public class WordTwistTextControl 
{
    private WordTwistModel model;
    private Scanner in;
    
    public WordTwistTextControl( WordTwistModel m )
    {
        model = m;
        in = new Scanner( System.in );
    }
    
    public void makeMove()
    {
        String s = in.nextLine().trim();
        Scanner ss = new Scanner( s );
        if( ss.hasNextInt() )
        {
            int i = ss.nextInt();
            
            if ( i < 0 || s.charAt(0) == '-' )
            {
                model.unuseLetter( -i );
            }
            else if ( i >= 0 )
            {
                model.useLetter( i );
            }
        }
        else
        {
            String cmd = ss.next();
            if ( cmd.startsWith( "su" ) )
            {
                model.submit();
            }
            else if ( cmd.startsWith( "r" ) )
            {
                model.reset();
            }
            else if ( cmd.startsWith( "sc"))
            {
                model.scrambleUnusedLetters();
            }
        }
    }
}
