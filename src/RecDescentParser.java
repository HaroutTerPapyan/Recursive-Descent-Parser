

/**
 * Harout Ter-Papyan
 * Navin Row
 * RecDescentParser.java
 */
import java.util.*;
  
  public class RecDescentParser
  { 
      public char nextchar;
      public String inputString;
      private int pos;
      
      public RecDescentParser ( String s)
      {
         inputString = s;
         pos= 0;
         System.out.println("\nParsing:  " + s);
      }
      //do not change
      public void start()throws Exception
      {
         try {
            getChar();
            S();
            System.out.println("Successful parse");
         }
         catch (Exception e)
         {
            System.out.println("Unsuccessful parse");
         }
      
      }
      //do not change
      public void getChar()throws Exception
      {
         //skip over blank chars
         while ( pos < inputString.length() 
               && inputString.charAt(pos) == ' ')
         {
   
               pos++;
         }
         
         if( pos < inputString.length() )
         {
               nextchar = inputString.charAt(pos);
               System.out.println("getChar:  " + pos + "  "  + nextchar);
               pos++;
         }
         else
              error();
      
      }

      public void S()throws Exception
      {
    	  STList();
         if (nextchar == '$')
         {
             match('$');
         }
         else
             error();
      }
      
      public void STList() throws Exception
      {
          ST();
          if(nextchar == ';') 
          {
        	  match(';');
              STList();
          }
      }
      
      public void ST() throws Exception
      {
          N();
          if(nextchar == '=')
          {
              match('=');
              E();
          }
      }
        
              
      public void E()throws Exception
      {
        
            T();
            if( nextchar == '+')
            {
               match('+');
               E();
            }
            else if( nextchar == '-')
            {
                match('-');
                E();
            }                         
      }
       
      public void T()throws Exception
      {
           
            F();
            if( nextchar == '*')
            {
              match('*');  
              T();
            }
            else if ( nextchar == '/')
            {
                match('/');
                T();
            }
      }

        
      public void F()throws Exception
      {
         if(Character.isLetter(nextchar))
         {
             match(nextchar);
             return;
         }
         else if (nextchar == '(')
         {
            match('(');
            E();
            match(')');
         }
         else
            I();
      }
      
      public void I() throws Exception
      {
          if(Character.isDigit(nextchar))
          {
              match(nextchar);
              I();
              return;
          }
      }
        
      public void N() throws Exception 
      {
          if(Character.isLetter(nextchar))
          {
              match(nextchar);
              return;
          }
          else
              error();
      }
     
      public void error() throws Exception
      {
         System.out.println( "Syntax error at position : " 
                  + pos  +  "  with character: " + nextchar);
         throw new Exception ("Syntax Error");
      }
        
      public void match( char u) throws Exception
      {
         if(nextchar == u)
         {
            if ( u != '$')
               getChar();
               return ;
         }
         else
           error();
      }
        
        
      public static void main( String[] args)throws Exception
      {
         /*
            RecDescentParser rdp = 
                        new RecDescentParser("x * y + 5  * (9 + y) $"  );
            rdp.start();
                   
           
            RecDescentParser rdp2 = 
                        new RecDescentParser("x * y (+ 5 ) * (9 + y) $"  );
            rdp2.start();
           
             RecDescentParser rdp3 = 
                         new RecDescentParser("x * y + 5  * (9 + y) $"  );
            rdp3.start();
             
            RecDescentParser rdp4 = 
                         new RecDescentParser("x * 8 "  );
            rdp4.start();

         */
          
          RecDescentParser rdp5 =
                          new RecDescentParser("x = y * (a + 2 - c) + A $");
          rdp5.start();
          
          RecDescentParser rdp6 =
                          new RecDescentParser("z = a + (b + 1)) $");
          rdp6.start();
          
          RecDescentParser rdp7 =
                          new RecDescentParser("u = 7 ; v = a + 8 ; x = 3$");
          rdp7.start();
          
          RecDescentParser rdp8 =
                          new RecDescentParser("c = z + 341 + 5 ; x = 100$");
          rdp8.start();
      }
    
  
  }


/*
run:

Parsing:  x = y * (a + 2 - c) + A $
getChar:  0  x
getChar:  2  =
getChar:  4  y
getChar:  6  *
getChar:  8  (
getChar:  9  a
getChar:  11  +
getChar:  13  2
getChar:  15  -
getChar:  17  c
getChar:  18  )
getChar:  20  +
getChar:  22  A
getChar:  24  $
Successful parse

Parsing:  z = a + (b + 1)) $
getChar:  0  z
getChar:  2  =
getChar:  4  a
getChar:  6  +
getChar:  8  (
getChar:  9  b
getChar:  11  +
getChar:  13  1
getChar:  14  )
getChar:  15  )
Syntax error at position : 16  with character: )
Unsuccessful parse

Parsing:  u = 7 ; v = a + 8 ; x = 3$
getChar:  0  u
getChar:  2  =
getChar:  4  7
getChar:  6  ;
getChar:  8  v
getChar:  10  =
getChar:  12  a
getChar:  14  +
getChar:  16  8
getChar:  18  ;
getChar:  20  x
getChar:  22  =
getChar:  24  3
getChar:  25  $
Successful parse

Parsing:  c = z + 341 + 5 ; x = 100$
getChar:  0  c
getChar:  2  =
getChar:  4  z
getChar:  6  +
getChar:  8  3
getChar:  9  4
getChar:  10  1
getChar:  12  +
getChar:  14  5
getChar:  16  ;
getChar:  18  x
getChar:  20  =
getChar:  22  1
getChar:  23  0
getChar:  24  0
getChar:  25  $
Successful parse
BUILD SUCCESSFUL (total time: 0 seconds)
*/