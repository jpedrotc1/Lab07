package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

public interface TipoDeUsuarioIf {
	
	double compraJogo(Jogo jogo)throws ValorInvalidoException;
	int punir(Jogo jogo);
	int recompensar (Jogo jogo);
	int getX2p(Jogo jogo);

}
