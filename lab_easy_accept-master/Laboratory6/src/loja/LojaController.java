package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpgradeInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.FactoryJogos;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;


public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabildades;
	private FactoryJogos fabricaJogos;

	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
		this.fabricaJogos = new FactoryJogos();
	}
	
	public void criaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario usuario = new Usuario(nome, login);
		meusUsuarios.add(usuario);
	}

	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser)throws Exception {
			Usuario buscado = this.buscaUsuario(loginUser);
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo jogoVendido = this.criaJogo(jogoNome,estiloJogo,preco,tiposJogabilidades);
			buscado.compraJogo(jogoVendido);
	}

	public void punir(String login, String nome, int score, boolean zerou) throws Exception{
		Usuario usuario = buscaUsuario(login);
		usuario.punir(nome, score, zerou);
	}
	public void recompensar(String login, String nome, int score, boolean zerou) throws Exception {
		Usuario usuario = buscaUsuario(login);
		usuario.recompensar(nome, score, zerou);
	}
	
	public void adicionaCredito(String login, double credito)throws Exception {
		if (credito < 0) {
			throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			Usuario user = this.buscaUsuario(login);
			user.setCredito(user.getCredito() + credito);
		
	}

	public Usuario buscaUsuario(String login)throws Exception {
		Usuario buscado = null;
			for (int i = 0; i < meusUsuarios.size(); i++) {
				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);
				}
			}
		return buscado;
	}

	public void upgrade(String login) throws Exception {
		Usuario antigo = buscaUsuario(login);
		antigo.upgrade();
	}
	
	public void downgrade(String login) throws Exception{
		Usuario antigo = buscaUsuario(login);
		antigo.downgrade();
	}

	public double confereCredito(String login)throws Exception {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();
	}

	public int getX2p(String login)throws Exception {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}

	private Set<Jogabilidade> createJogabilidades(String names1) {
		Set<Jogabilidade> jogabilidades = new HashSet<Jogabilidade>();

		String[] listofNames = names1.split(" ");

		for (int i = 0; i < listofNames.length; i++) {
			String element = listofNames[i].toUpperCase();
			if (element != null) {
				Jogabilidade tipojogabilidade = mapJogabildades.get(element);
				jogabilidades.add(tipojogabilidade);
			}
		}

		return jogabilidades;

	}

	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}
		
	private Jogo criaJogo(String nome,String tipo,double preco,Set<Jogabilidade> jogabilidades)throws Exception{
			return this.fabricaJogos.criaJogos(nome, preco,tipo, jogabilidades);
	
	}

	@Override
	public String toString(){
		String myString = "";
		
		for(Usuario usuario: meusUsuarios){
			myString += usuario.toString();
		}
		
		return "=== Central P2-CG ===" +FIM_DE_LINHA + FIM_DE_LINHA + myString + FIM_DE_LINHA + FIM_DE_LINHA + "-------------------";
		
	}
	
}
