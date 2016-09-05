package usuario;

import excecoes.PrecoInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano  implements TipoDeUsuarioIf {
	public static final double DESCONTO_VETERANO = 0.8;

	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_VETERANO);
		return desconto;

	}

	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 15);
	}

	@Override
	public int recompensar(Jogo jogo) {
		int ganhoX2p = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.ONLINE)) {
			ganhoX2p += 10;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)) {
			ganhoX2p += 20;
		}
		return ganhoX2p;
	}

	@Override
	public int punir(Jogo jogo) {
		int perdaX2p = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)) {
			perdaX2p += 20;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)) {
			perdaX2p += 20;
		}
		return perdaX2p;

	}

	public String toString() {
		return "Jogador Veterano: ";
	}

}


