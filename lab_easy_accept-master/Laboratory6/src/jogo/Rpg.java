package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

/** Essa classe representa o objeto Rpg.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class Rpg extends Jogo{
	public final static int TAXA_XP2 = 10;
	
	/**
	 * Construtor de RPG sem o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Rpg(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}
	
	/**
	 * Construtor de RPG com o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @param jogabilidades
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Rpg (String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
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
		return TAXA_XP2;
	}
	
	public String toString() {
		String resultado = getNome() + " - RPG:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
