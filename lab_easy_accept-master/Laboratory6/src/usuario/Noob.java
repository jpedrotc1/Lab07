package usuario;


import excecoes.ValorInvalidoException;
import jogo.Jogo;
import jogo.Jogabilidade;

/** Essa classe representa o objeto Noob.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */
public class Noob implements TipoDeUsuarioIf {
	public static final double DESCONTO_NOOB = 0.9;

	/**
	 *
	 *Método responsável pela compra de um jogo em Noob.
	 *@param jogo
	 *@return double - valor a ser descontado do crédito
	 *@throws Exception
	 *
	 */
	@Override
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_NOOB);
		return desconto;
	}
	
	/**
     *Calcula o x2p fornecido pela compra do jogo
     *
     *@param jogo
     *@return int - valor arrecadado na compra
     */
	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 10);
	}

	/**
	 *Método responsável por calcular a recompensa de x2p por jogar um determinado jogo.
	 *
	 *@param jogo
	 *@return int - quantidade de x2p ganha
	 *
	 */
	public int recompensar(Jogo jogo) {
		int ganhoX2p = 0;
		if (jogo.getJogabilidades().contains(Jogabilidade.OFFLINE)) {
			ganhoX2p += 30;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.MULTIPLAYER)) {
			ganhoX2p += 10;
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
		if (jogo.getJogabilidades().contains(Jogabilidade.ONLINE)) {
			perdaX2p += 10;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COOPERATIVO)) {
			perdaX2p += 50;
		}
		if (jogo.getJogabilidades().contains(Jogabilidade.COMPETITIVO)) {
			perdaX2p += 20;
		}
		return perdaX2p;
	}

	public String toString() {
		return "Jogador Noob: ";
	}
}