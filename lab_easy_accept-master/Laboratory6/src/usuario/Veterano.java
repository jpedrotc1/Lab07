package usuario;


import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

/** Essa classe representa o objeto Veterano.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class Veterano  implements TipoDeUsuarioIf {
	public static final double DESCONTO_VETERANO = 0.8;
	
	/**
	 *
	 *Método responsável pela compra de um jogo em Veterano.
	 *@param jogo
	 *@return double - valor a ser descontado do crédito
	 *@throws Exception
	 *
	 */
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_VETERANO);
		return desconto;

	}
	
    /**
     *Calcula o x2p fornecido pela compra do jogo
     *
     *@param jogo
     *@return int - valor arrecadado na compra
     */
	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 15);
	}

	/**
	 *Método responsável por calcular a recompensa de x2p por jogar um determinado jogo.
	 *
	 *@param jogo
	 *@return int - quantidade de x2p ganha
	 *
	 */
	@Override
	public int recompensar(Jogo jogo) {
		int ganhoX2p = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			ganhoX2p += 10;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			ganhoX2p += 20;
		}
		return ganhoX2p;
	}
	
	/**
	 * Métdo responsável por calcular a punição de x2p por um determinado jogo.
	 * 
	 * @param jogo
	 * @return int - perda de x2p
	 * 
	 */
	@Override
	public int punir(Jogo jogo) {
		int perdaX2p = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			perdaX2p += 20;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			perdaX2p += 20;
		}
		return perdaX2p;

	}

	public String toString() {
		return "Jogador Veterano: ";
	}

}


