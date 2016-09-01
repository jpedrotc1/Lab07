package usuario;

import java.util.Iterator;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;
import jogo.Jogabilidade;

public class Noob extends Usuario {
	public static final double DESCONTO_NOOB = 0.9;

	public Noob(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setXp2(0);
	}

	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_NOOB;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 10;
			setXp2(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}

	}

	@Override
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Noob" + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de preço dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
		myString += "--------------------------------------------";
		return myString;
	}

	@Override
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) throws Exception {
		Jogo jogo = this.buscaJogo(nomeJogo);
		
		if (jogo == null) {
			throw new Exception();
		}
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));
		
		for(Jogabilidade j : jogo.getJogabilidades()){
			if(j.equals(Jogabilidade.OFFLINE)){
				setXp2(getXp2() +30);
				
			}
			
			if(j.equals(Jogabilidade.MULTIPLAYER)){
				setXp2(getXp2() +10);
			}
			
	  }
	}

	@Override
	public void punir(String nomeJogo, int scoreObtido, boolean zerou) throws Exception {
		
		Jogo jogo = this.buscaJogo(nomeJogo);
		
		if (jogo == null) {
			throw new Exception();
		}
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));
		
		for(Jogabilidade j : jogo.getJogabilidades()){
			
			if(j.equals(Jogabilidade.ONLINE)){
				setXp2(getXp2() -10);
				
			}
			
			if(j.equals(Jogabilidade.COMPETITIVO)){
				setXp2(getXp2() -20);
			}
			
			if(j.equals(Jogabilidade.COOPERATIVO)){
				setXp2(getXp2() - 50);
			}
			
			
		}
		
	}

}