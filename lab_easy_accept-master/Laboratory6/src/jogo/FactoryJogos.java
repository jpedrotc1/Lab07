package jogo;

import java.util.Set;

public class FactoryJogos {
		
	public Jogo criaJogos(String nome,double preco,String tipo,Set<Jogabilidade> jogabilidades)throws Exception{
		
		if(tipo.equalsIgnoreCase("Luta")){
			return new Luta(nome,preco,jogabilidades);
		}
		if(tipo.equalsIgnoreCase("RPG")){
			return new Rpg(nome,preco,jogabilidades);
		}
		if(tipo.equalsIgnoreCase("Plataforma")){
			return new Plataforma(nome,preco,jogabilidades);
		}
		
		return null;
		
	}

}
