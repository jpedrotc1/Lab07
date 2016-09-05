package usuario;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpDownInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.Jogo;

public class Usuario {

	public static final String FIM_DE_LINHA = System.lineSeparator();

	private String nome;
	private String login;
	private Set<Jogo> meusJogos;
	private double credito;
	private int xp2;
	private TipoDeUsuarioIf statusUsuario;

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
	
	public void compraJogo(Jogo jogo) throws ValorInvalidoException{
		if (this.credito < jogo.getPreco()) {
			throw new ValorInvalidoException("Dinheiro insuficiente.");
		}
		setXp2(getXp2() + statusUsuario.getX2p(jogo));
		setCredito(getCredito() - statusUsuario.compraJogo(jogo));//Chamada Polimorfica.
		meusJogos.add(jogo);
	}

	public void upgrade() throws UpDownInvalidoException {
		if (xp2 < 1000) {
			throw new UpDownInvalidoException("Nao pode realizar upgrade x2p insuficiente!");
		}
		if (statusUsuario instanceof Veterano) {
			throw new UpDownInvalidoException("Nao pode realizar upgrade, usuario ja e veterano");
		}
		this.statusUsuario = new Veterano();
	}

	public void downgrade() throws UpDownInvalidoException {
		if (statusUsuario instanceof Noob) {
			throw new UpDownInvalidoException("Nao pode realizar downgrade usuario ja e noob");
		}
		this.statusUsuario = new Noob();
		
	}
	
	public void recompensar(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		setXp2(getXp2() + statusUsuario.recompensar(jogo));//Chamada Polimorfica
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));
	}

	public void punir(String nomeJogo, int scoreObtido, boolean zerou) {
		Jogo jogo = buscaJogo(nomeJogo);
		setXp2(getXp2() - statusUsuario.punir(jogo));//Chamada Polimorfica
		setXp2(getXp2() + jogo.registraJogada(scoreObtido, zerou));
		
	}
	
	public void setXp2(int novoValor) {
		this.xp2 = novoValor;
	}

	public int getXp2() {
		return this.xp2;
	}

	public void cadastraJogo(Jogo jogo) {
		this.meusJogos.add(jogo);
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome)throws StringInvalidaException {
		if(nome.trim().isEmpty() || nome == null){
			throw new StringInvalidaException("Nome nao pode ser nulo ou vazio.");
		}
		this.nome = nome;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login)throws StringInvalidaException {
		if(login.trim().isEmpty() || login == null){
			throw new StringInvalidaException("Login nao pode ser nulo ou vazio.");
		}
		this.login = login;
	}

	public void setCredito(double novoValor)throws ValorInvalidoException {
		if(novoValor < 0){
			throw new ValorInvalidoException("Valor nao pode ser menor que zero");
		}
		this.credito = novoValor;
	}

	public double getCredito() {
		return this.credito;
	}

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

	public Set<Jogo> getMeusJogos() {
		return meusJogos;
	}

	public void setMeusJogos(Set<Jogo> meusJogos) {
		this.meusJogos = meusJogos;
	}

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
