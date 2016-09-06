package jogo;

import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

/** Essa classe representa o objeto Plataforma.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class Plataforma extends Jogo {
	public final static int MAXIMUM_SCORE = 100000;
	public final static int TAXA_XP2 = 20;
	
	/**
	 * Construtor de Plataforma sem o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Plataforma(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {
		super(nome, preco);
	}

	/**
	 * Construtor de Plataforma com o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @param jogabilidades
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Plataforma(String nome, double preco, Set<Jogabilidade> jogabilidades) throws StringInvalidaException, PrecoInvalidoException {
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
		setVezesJogadas(getVezesJogadas() + 1);
		if (score > this.getMaiorScore()) {
			setMaiorScore(score);
		} else if (score > MAXIMUM_SCORE) {
			setMaiorScore(MAXIMUM_SCORE);
		}
		if (venceu) {
			setVezesConcluidas(getvezesConcluidas() + 1);
			return TAXA_XP2;
		}
		return 0;
	}

	public String toString() {
		String resultado = getNome() + " - Plataforma:" + FIM_DE_LINHA;
		resultado += super.toString();
		return resultado;
	}

}
