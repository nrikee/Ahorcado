
/** 
  * Contiene la logica del ahorcado
  *
  * https://github.com/nrikee
  */
  public class Penjat {	
	private String[] 	database;
	private char[]		lletresGastades;
 	private int			lletresG;
	private String 		paraula,
						paraulaFormatada;
	private int			intentosRestants;
    private final int 	maxIntentos = 6;
 	
	public Penjat( ){
		boolean acabar = false;
				while (!acabar){			
					try{
						database 			= IO.toArrayFitxer(th);
						paraula 			= paraulaAleatoria();
						paraulaFormatada 	= posarBarrabaixes();
						intentosRestants 	= maxIntentos;
						lletresGastades 	= new char[30];
						lletresG=0;
						acabar = true;
			} catch(Exception w){ acabar = false;}
		}
	}	
	
	//======================= Metodes PUBLICS ============================//
	/**
 	  * Canvia la paraula per una de nova
	  *
	  * @return la paraula nova
	  */
	  public String novaParaula(){		
		boolean b = false;
		while ( !b )
			try{
				paraula 			= paraulaAleatoria();
				paraulaFormatada 	= posarBarrabaixes();
				intentosRestants 	= maxIntentos;
     			lletresGastades 	= new char[30];
				lletresG=0;
				b = true;
			} catch (Exception e) { b=false; }
			return paraulaFormatada;
	}	
	
	/**
      * Fa els canvis necessaris davant una nova lletra
	  *
	  * @param c lletra introduida
	  * @return si apareix o no la lletra en la paraula
	  */	
	  public boolean set ( char c ){	
		if ( apareix (c) ){
			paraulaFormatada = plenarBarrabaixes(c);
			lletresGastades[lletresG++] = c;
			return true;
		} else {
			if ( !lletraGastada(c) )
				intentosRestants--;
			lletresGastades[ lletresG++ ] = c;
			return false;
		}	
	}	
	
	public boolean isWin(){
		boolean b = true;
		for(int a=0; a<paraulaFormatada.length(); a+=2)
			if ( paraulaFormatada.charAt(a) == '_' ) 
				b=false;
		return b;
	}

	/**	 
	  * Torna els intents restants
 	  *
	  * @return intents restants
	  */	
	public int getIntentos(){		
		return intentosRestants;
	}

	/**
	  * Torna la puntuacio
	  */
	  public int getPuntuacion(){
		if ( !isWin() )
			return -1;
		else
			return getIntentos();
	}
	/**
	  * Torna la paraula
	  *
	  * @return la paraula
	  */	
	public String getParaula(){
		return paraula;
	}
	
	/**
	  * Torna la representacio de la paraula ahorcada
	  *
	  * @return la representacio de la paraula ahorcada
	  */
	public String getParaulaFormatada(){
		return paraulaFormatada;
	}

	public String pista(){
		char c='-';
		while ( true && !isWin() ){
			c = paraula.charAt( (int)(Math.random()*paraula.length()) );
			if ( !lletraGastada(c) ){
				set( c );
				return c+"";
			}
		}
		return c+"";
	}

	/**
	  * Busca si un caracter apareix en la String paraula. Torna un boolean.
	  */
	public boolean apareix(char c){
		return cuantesVegades(c)>0;
	}


	//================================= Metodes PRIVATS ====================================//

	private boolean lletraGastada(char c){
		int x = 0;
		for(int a=0; a<lletresG; a++)
			if ( lletresGastades[a] == c ) 
				x++;
		return x>0;
	}

	/**
	  * Trau una paraula aleatoria del database i la torna. Descarta paraules amb menys de 4 car?acters.
	  */	
	private String paraulaAleatoria(){
		int a= (int) (Math.random()*database.length);
		return database[a];
	}

	/**
	  * Torna una string amb una barrabaixa per cada lletra, separades per un espai.
	  * 	Ex: casa -> _ _ _ _
	  */
	private String posarBarrabaixes(){
		String retorn = "";
		for(int a=0; a<paraula.length(); a++){
			retorn += "_ ";
		}
		return retorn;
	}

	/**
	  * Torna el nombre de vegades que apareix un caracter en una String.
	  */	
	private int cuantesVegades(char carac){
		int x = 0;
		for(int a=0; a<paraula.length(); a++)
			if ( paraula.charAt(a) == carac ) 
				x++;
		return x;
	}
	
	/**
	  * Torna un array de tipus int amb la posicio corresponent a una lletra donada en paraulaFormatada.
	  *	Ex: 'a', "c a s a" -> {1,3}
	  * 			 "0123456"
	  * 			 "0 1 2 3"
	  */	
	private int[] posicionsCaracterF(char carac){		int[] retorn = new int[ cuantesVegades( carac ) ];
		int x = 0;
		for(int a=0; a<paraula.length(); a++){
			if ( paraula.charAt(a) == carac ){
				retorn[x] = 2*a;
				x++;
			}
		}
		return retorn;
	}

	/**
	  * Torna una string igual a la donada, pero amb certes posicions cambiades.
	  */
	private String plenarBarrabaixes(char c){
		char[] ch = paraulaFormatada.toCharArray();
		int[] pos = posicionsCaracterF(c);
		for ( int a=0; a<pos.length; a++ ){
			ch [ pos[a] ] = c;
		}
		return String.valueOf(ch);
	}
}
