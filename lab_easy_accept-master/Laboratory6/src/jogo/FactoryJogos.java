package jogo;

import java.util.Set;


import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

/** Essa classe representa a factory de Jogos.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class FactoryJogos {
	
	/**
	 * 
	 * Método responsável pela criação de um jogo a partir de uma especificação de tipo.
	 * 
	 * 
	 * @param nome
	 * @param preco
	 * @param tipo
	 * @param jogabilidades
	 * @return Jogo - retorna um jogo criado
	 * @throws PrecoInvalidoException
	 * @throws StringInvalidaException
	 */
		
	public Jogo criaJogos(String nome,double preco,String tipo,Set<Jogabilidade> jogabilidades)throws PrecoInvalidoException, StringInvalidaException{
		
		if(tipo.equalsIgnoreCase("Luta")){
			return new Luta(nome,preco,jogabilidades);
		}
		if(tipo.equalsIgnoreCase("RPG")){
			return new Rpg(nome,preco,jogabilidades);
		}
		if(tipo.equalsIgnoreCase("Plataforma")){
			return new Plataforma(nome,preco,jogabilidades);
		}
		
		return null;
		
	}
	
}
