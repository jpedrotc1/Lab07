package usuario;


import excecoes.ValorInvalidoException;
import jogo.Jogo;
import jogo.Jogabilidade;

public class Noob implements TipoDeUsuarioIf {
	public static final double DESCONTO_NOOB = 0.9;

	@Override
	public double compraJogo(Jogo jogo) throws ValorInvalidoException {
		double desconto = jogo.getPreco() - (jogo.getPreco() * DESCONTO_NOOB);
		return desconto;
	}

	public int getX2p(Jogo jogo) {
		return (int) (jogo.getPreco() * 10);
	}

	public int recompensar(Jogo jogo) {
		int ganhoX2p = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.OFFLINE)) {
			ganhoX2p += 30;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.MULTIPLAYER)) {
			ganhoX2p += 10;
		}
		return ganhoX2p;

	}

	@Override
	public int punir(Jogo jogo) {
		int perdaX2p = 0;
		if (jogo.getJogabilidade().contains(Jogabilidade.ONLINE)) {
			perdaX2p += 10;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COOPERATIVO)) {
			perdaX2p += 50;
		}
		if (jogo.getJogabilidade().contains(Jogabilidade.COMPETITIVO)) {
			perdaX2p += 20;
		}
		return perdaX2p;
	}

	public String toString() {
		return "Jogador Noob: ";
	}
}