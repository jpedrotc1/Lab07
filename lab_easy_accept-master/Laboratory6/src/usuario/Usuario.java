package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.StringInvalidaException;
import excecoes.UpDownInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

/** Essa classe representa o objeto Usuario.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;
	private TipoDeUsuarioIf statusUsuario;

	/**
	 * Construtor de um objeto usuário
	 * 
	 * @param nome
	 * @param login
	 * @throws StringInvalidaException
	 */
	public Usuario(String nome, String login) throws StringInvalidaException {

		if (nome == null || nome.trim().isEmpty()) {
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		if (login == null || login.trim().isEmpty()) {
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}

		this.nome = nome;
		this.login = login;
		meusJogos = new HashSet<Jogo>();
		this.credito = 0;
		this.statusUsuario = new Noob();
	}
	/**
	 * Método responsável pela compra de um jogo
	 * 
	 * @param jogo
	 * @throws ValorInvalidoException
	 */
	public void compraJogo(Jogo jogo) throws ValorInvalidoException{
		if (this.credito < jogo.getPreco()) {
			throw new ValorInvalidoException("Dinheiro insuficiente.");
		}
		setXp2(getXp2() + statusUsuario.getX2p(jogo));
		setCredito(getCredito() - statusUsuario.compraJogo(jogo));//chamada polimórfica
		meusJogos.add(jogo);
	}

	/**
	 * Método responsável por realizar o upgrade de um usuario noob para veterano.
	 * 
	 * @throws UpDownInvalidoException
	 */
	public void upgrade() throws UpDownInvalidoException {
		if (xp2 < 1000) {
			throw new UpDownInvalidoException("Nao pode realizar upgrade x2p insuficiente!");
		}
		if (statusUsuario instanceof Veterano) {
			throw new UpDownInvalidoException("Nao pode realizar upgrade, usuario ja e veterano");
		}
		this.statusUsuario = new Veterano();
	}

	/**
	 * Método responsável por realizar o downgrade de um tipo Veterano para Noob.
	 * 
	 * @throws UpDownInvalidoException
	 */
	public void downgrade() throws UpDownInvalidoException {
		if (statusUsuario instanceof Noob) {
			throw new UpDownInvalidoException("Nao pode realizar downgrade usuario ja e noob");
		}
		this.statusUsuario = new Noob();
		
	}
	
	/**
	 * Método responsável por fazer a recompensa de um determinado usuário ao jogar um determinado jogo.
	 * 
	 * @param nomeJogo
	 * @param scoreObtido
	 * @param zerou
	 */
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		setXp2(getXp2() + statusUsuario.recompensar(jogo));//chamada polimórfica
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));//chamada polimórfica
	}

	/**
	 * Método responsável por fazer uma punição de um determinado usuário ao jogar um determinado jogo.
	 * 
	 * @param nomeJogo
	 * @param scoreObtido
	 * @param zerou
	 */
	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		setXp2(getXp2() - statusUsuario.punir(jogo));//chamada polimórfica
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));//chamada polimórfica
		
	}
	
	/**
	 * Define um novo valor para x2p
	 * 
	 * @param novoValor
	 */
	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	/**
	 * Retorna o valor de x2p
	 * 
	 * @return int - retorna o valor de x2p
	 */
	public int getXp2() {
		return this.xp2;
	}
	/**
	 * Adiciona um jogo
	 * 
	 * @param jogo
	 */
	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	/**
	 * Retorna o nome do usuario
	 * 
	 * @return String - retorna o nome do usuario
	 */
	public String getNome() {
		return nome;
	}
	
	/**
	 * Define um novo nome para o usuario
	 * 
	 * @param nome
	 * @throws StringInvalidaException
	 */
	public void setNome(String nome)throws StringInvalidaException {
		if(nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	/**
	 * Retorna o login de um usuario
	 * 
	 * @return String - retorna o login 
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Define um novo login para usuario
	 * 
	 * @param login
	 * @throws StringInvalidaException
	 */
	public void setLogin(String login)throws StringInvalidaException {
		if(login.trim().isEmpty() || login == null){
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}
		this.login = login;
	}

	/**
	 * Define um novo credito para usuario
	 * 
	 * @param novoValor
	 * @throws ValorInvalidoException
	 */
	public void setCredito(double novoValor)throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor nao pode ser menor que zero");
		}
		this.credito = novoValor;
	}

	/**
	 * Retorna o credito atual do usuario
	 * 
	 * @return double - retorna o credito
	 */
	public double getCredito() {
		return this.credito;
	}

	/**
	 * Método para buscar um determinado jogo
	 * 
	 * @param nomeJogo
	 * @return
	 */
	public Jogo buscaJogo(String nomeJogo) {
		Jogo buscado = null;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			if (achado.getNome().equals(nomeJogo)) {
				buscado = achado;
			}
		}
		return buscado;
	}

	/**
	 * Retorna um Set de jogos do usuario
	 * 
	 * @return Set<Jogo> - retorna um set de jogos
	 */
	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	/**
	 * Define o set de jogos do usuario
	 * 
	 * @param meusJogos
	 */
	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

	/**
	 * Calcula o preço total de jogos comprados
	 * 
	 * @return double - preço total de jogos
	 */
	public double calculaPrecoTotal() {
		double total = 0;
		Iterator itr = meusJogos.iterator();
		while (itr.hasNext()) {
			Jogo achado = (Jogo) itr.next();
			total += achado.getPreco();
		}
		return total;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Usuario) {
			Usuario temp = (Usuario) obj;
			return this.getNome().equals(temp.getNome()) && this.getLogin().equals(temp.getLogin());
		} else {
			return false;
		}
	}
	
	
	
	
	
}
