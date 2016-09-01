package usuario;

import java.util.Iterator;

import excecoes.StringInvalidaException;
import excecoes.ValorInvalidoException;
import jogo.Jogabilidade;
import jogo.Jogo;

public class Veterano extends Usuario {
	public static final double DESCONTO_VETERANO = 0.8;

	public Veterano(String nome, String login) throws StringInvalidaException {
		super(nome, login);
		setXp2(1000);
	}

	@Override
	public void compraJogo(Jogo jogo) throws Exception {
		double custo = jogo.getPreco() * DESCONTO_VETERANO;
		if (custo > this.getCredito()) {
			throw new ValorInvalidoException("Credito insuficiente para realizar a compra.");
		} else {
			int parteInteira =(int)( jogo.getPreco() - (jogo.getPreco() % 1));
			int bonusXp =  parteInteira * 15;
			setXp2(getXp2() + bonusXp);
			setCredito(getCredito() - custo);
			this.cadastraJogo(jogo);

		}
	}

	@Override
	public String toString() {
		String myString = this.getLogin() + FIM_DE_LINHA;
		myString += this.getNome() + " - Jogador Veterano " + this.getXp2() + FIM_DE_LINHA;
		myString += "Lista de Jogos:" + FIM_DE_LINHA;

		Iterator itr = getMeusJogos().iterator();
		while (itr.hasNext()) {
			Jogo j = (Jogo) itr.next();
			myString += j.toString();
		}
		myString += FIM_DE_LINHA;
		myString += "Total de pre�o dos jogos: R$ " + this.calculaPrecoTotal() + FIM_DE_LINHA;
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
			if(j.equals(Jogabilidade.ONLINE)){
				setXp2(getXp2() +10);
				
			}
			
			if(j.equals(Jogabilidade.COOPERATIVO)){
				setXp2(getXp2() +20);
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
			if(j.equals(Jogabilidade.OFFLINE)){
				setXp2(getXp2() -20);
				
			}
			
			if(j.equals(Jogabilidade.COMPETITIVO)){
				setXp2(getXp2() -20);
			}
			
		
	}
	}

}
