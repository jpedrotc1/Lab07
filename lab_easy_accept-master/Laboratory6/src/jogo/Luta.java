package jogo;

import java.util.Set;


import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

/** Essa classe representa o objeto Luta.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class Luta extends Jogo{

	/**
	 * Construtor de Luta sem o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Luta(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}
	
	/**
	 * Construtor de Luta com o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @param jogabilidades
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Luta (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco, jogabilidades);
	}
	
	/**
	 * Método responsável por registrar uma jogada
	 * 
	 * @param score
	 * @param venceu
	 * @return int - x2p ganho
	 * 
	 */
	@Override
	public int registraJogada(int score, boolean venceu) {
		setVezesJogadas(getVezesJogadas()+ 1);
		if(score > this.getMaiorScore()){
			setMaiorScore(score);
		}
		if(venceu){
			setVezesConcluidas(getvezesConcluidas() + 1);
			
		}
		return score/1000;
	}
	
	public String toString() {
		String resultado = getNome() + " - Luta:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}
}
