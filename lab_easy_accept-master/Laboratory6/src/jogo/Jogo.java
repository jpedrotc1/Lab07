package jogo;

import java.util.HashSet;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;

public abstract class Jogo {
	/** Essa classe representa o objeto Jogo.
	 * 
	 * @author João Pedro Travasso Costa - 115210098 - Turma 01
	 *
	 */
	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private double preco;
	private int vezesJogadas;
	private int vezesConcluidas;
	private int maiorScore;
	Set<Jogabilidade> jogabilidades;

	/**
	 * Construtor de jogo sem o set de jogabilidades.
	 * 
	 * @param nome
	 * @param preco
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Jogo(String nome, double preco) throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		jogabilidades = new HashSet<Jogabilidade>();
	}

	/**
	 * Construtor de jogo com o set de jogabilidades
	 * 
	 * @param nome
	 * @param preco
	 * @param jogabilidades
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public Jogo(String nome, double preco, Set<Jogabilidade> jogabilidades)
			throws StringInvalidaException, PrecoInvalidoException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (preco < 0) {
			throw new PrecoInvalidoException("Preco nao pode ser negativo");
		}

		this.nome = nome;
		this.preco = preco;
		this.vezesConcluidas = 0;
		this.vezesJogadas = 0;
		this.maiorScore = 0;
		this.jogabilidades = jogabilidades;
	}

	public abstract int registraJogada(int score, boolean venceu);

	/**
	 * Retorna o preço do jogo
	 * 
	 * @return double - preço do jogo
	 */
	public double getPreco() {
		return this.preco;
	}

	/**
	 * Retorna o nome do jogo
	 * 
	 * @return String - nome do jogo
	 */
	public String getNome() {
		return this.nome;
	}

	/**
	 * Retorna o maior scroe obtido no jogo
	 * 
	 * @return int - maior score
	 */
	public int getMaiorScore() {
		return this.maiorScore;
	}

	/**
	 * Define um novo maior score
	 * 
	 * @param novoScore
	 */
	public void setMaiorScore(int novoScore) {
		this.maiorScore = novoScore;
	}

	/**
	 * Retorna o número de vezes que zerou um determinado jogo
	 * 
	 * @return int - quantas vezes zerou
	 */
	public int getvezesConcluidas() {
		return this.vezesConcluidas;
	}

	/**
	 * Define o número de vezes que zerou o jogo
	 * 
	 * @param novaQuantidade
	 */
	public void setVezesConcluidas(int novaQuantidade) {
		this.vezesConcluidas = novaQuantidade;
	}

	/**
	 * Retorna a quantidade de vezes que um jogo foi jogado
	 * 
	 * @return int - quantidade de vezes jogado
	 */
	public int getVezesJogadas() {
		return this.vezesJogadas;
	}

	/**
	 * Define a quantidade de jogadas de um jogo
	 * 
	 * @param novaQuantidade
	 */
	public void setVezesJogadas(int novaQuantidade) {
		this.vezesJogadas = novaQuantidade;
	}
	
	/**
	 * Retorna o set de jogabilidades de um determinado jogo
	 * 
	 * @return Set<Jogabilidade> - set de jogabilidades
	 */
	public Set<Jogabilidade> getJogabilidades(){
		return this.jogabilidades;
	}
	
	@Override
	public String toString() {
		String resultado = "==> Jogou " + getVezesJogadas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Zerou " + getvezesConcluidas() + " vez(es)" + FIM_DE_LINHA;
		resultado += "==> Maior Score: " + getMaiorScore() + FIM_DE_LINHA;
		return resultado;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Jogo) {
			Jogo temp = (Jogo) obj;

			return this.getNome().equals(temp.getNome()) && this.getPreco() == temp.getPreco();

		} else {
			return false;
		}

	}
}
