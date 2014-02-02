package com.nrikee.clases_aux;

/**
 * Reads from a list of words in a new line each one
 * 
 * 
 * Esta obra está licenciada bajo la Licencia Creative Commons Atribución 3.0 Unported. 
 * 
 * https://github.com/nrikee
 */
import java.io.InputStream;
import java.util.Scanner;

public class IO{
	private static final int 	MIN = 4, MAX = 10, TAM_ARRAY = 240;
	private static final String	fileName = "rae.txt"; 

	/**
	 * Reads from a file that contains a list of words in a new line each one
	 */
	public static String[] toArrayFitxer(Activity a){
		try{
			File f		= new File ( fileName );
			Scanner in	= new Scanner( f );
			
			String[] ret = new String[TAM_ARRAY];
			int count = 0;
			String s;
			
			in.nextLine();
			while ( in.hasNextLine() && count<TAM_ARRAY ) {
				try{
					s = in.nextLine().split(" ")[0];
					if ( s.length() > MIN && s.length() <= MAX && !s.contains("\t") ){
						ret[count] = clean(s);
						count++;
					}
				}
				catch(Exception e) { }
			}
			
			in.close();

			return ret;
		} catch (Exception e) { }
		
		return null;
	}
	
   /**
     *	Clean up a string 
     */
   private static String clean(String s){
           String   strangeChar = "áàéèíìóòúù",
                    coolChar	= "aaeeiioouu";
           for ( int a=0; a<strangeChar.length(); a++)
                   s = s.replace ( strangeChar.charAt(a), coolChar.charAt(a));
           return s;
   }

}
