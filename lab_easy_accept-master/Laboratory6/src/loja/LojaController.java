package loja;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import excecoes.BuscaInvalidaException;
import excecoes.PrecoInvalidoException;
import excecoes.StringInvalidaException;
import excecoes.UpDownInvalidoException;
import excecoes.ValorInvalidoException;
import jogo.FactoryJogos;
import jogo.Jogabilidade;
import jogo.Jogo;
import usuario.Usuario;

/** Essa classe representa o Controller.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class LojaController {
	public static final String FIM_DE_LINHA = System.lineSeparator();
	private List<Usuario> meusUsuarios;
	private HashMap<String, Jogabilidade> mapJogabildades;
	private FactoryJogos fabricaJogos;

	/**
	 * Construtor de LojaController
	 * 
	 */
	public LojaController() {
		this.meusUsuarios = new ArrayList<Usuario>();
		this.initializeMap();
		this.fabricaJogos = new FactoryJogos();
	}
	
	/**
	 * Método responsável pela criação de um novo usuário 
	 * 
	 * @param nome
	 * @param login
	 * @throws StringInvalidaException
	 */
	public void criaUsuario(String nome, String login) throws StringInvalidaException {
		Usuario usuario = new Usuario(nome, login);
		meusUsuarios.add(usuario);
	}

	/**
	 * Método responsável pela venda de um jogo à um determinado usuario
	 * 
	 * @param jogoNome
	 * @param preco
	 * @param jogabilidades
	 * @param estiloJogo
	 * @param loginUser
	 * @throws StringInvalidaException
	 * @throws BuscaInvalidaException
	 * @throws PrecoInvalidoException
	 */
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser)throws StringInvalidaException,BuscaInvalidaException, PrecoInvalidoException {
			Usuario buscado = this.buscaUsuario(loginUser);
			Set<Jogabilidade> tiposJogabilidades = this.createJogabilidades(jogabilidades);
			Jogo jogoVendido = this.criaJogo(jogoNome,estiloJogo,preco,tiposJogabilidades);
			buscado.compraJogo(jogoVendido);//chamada polimórfica
	}

	/**
	 * Método que realiza a punição em um usario que joga um determinado jogo
	 * 
	 * @param login
	 * @param nome
	 * @param score
	 * @param zerou
	 * @throws BuscaInvalidaException
	 * @throws ValorInvalidoException
	 */
	public void punir(String login, String nome, int score, boolean zerou) throws BuscaInvalidaException,ValorInvalidoException{
		Usuario usuario = buscaUsuario(login);
		usuario.punir(nome, score, zerou);//chamada polimórfica
	}
	
	/**
	 * Método que recompensa um usuario que joga um determinado jogo
	 * 
	 * @param login
	 * @param nome
	 * @param score
	 * @param zerou
	 * @throws BuscaInvalidaException
	 * @throws ValorInvalidoException
	 */
	public void recompensar(String login, String nome, int score, boolean zerou) throws BuscaInvalidaException,ValorInvalidoException {
		Usuario usuario = buscaUsuario(login);
		usuario.recompensar(nome, score, zerou);//chamada polimórfica
	}
	
	/**
	 * Adiciona credito em um determinado usuario
	 * 
	 * @param login
	 * @param credito
	 * @throws BuscaInvalidaException
	 * @throws ValorInvalidoException
	 */
	public void adicionaCredito(String login, double credito)throws BuscaInvalidaException,ValorInvalidoException {
		if (credito < 0) {
			throw new ValorInvalidoException("Credito nao pode ser negativo");
			}
			Usuario user = this.buscaUsuario(login);
			user.setCredito(user.getCredito() + credito);
		
	}

	/**
	 * Procura um determinado usuario na lista de usuarios da loja
	 * 
	 * @param login
	 * @return
	 * @throws BuscaInvalidaException
	 */
	public Usuario buscaUsuario(String login)throws BuscaInvalidaException {
		Usuario buscado = null;
			for (int i = 0; i < meusUsuarios.size(); i++) {
				if (meusUsuarios.get(i).getLogin().equals(login)) {
					buscado = meusUsuarios.get(i);
				}
			}
		return buscado;
	}

	/**
	 * Realiza o upgrade de um usuario do tipo noob para veterano
	 * 
	 * @param login
	 * @throws BuscaInvalidaException
	 * @throws UpDownInvalidoException
	 */
	public void upgrade(String login) throws BuscaInvalidaException, UpDownInvalidoException {
		Usuario antigo = buscaUsuario(login);
		antigo.upgrade();
	}
	
	/**
	 * Realiza o downgrade de um usuario do tipo veterano para noob
	 * 
	 * @param login
	 * @throws BuscaInvalidaException
	 * @throws UpDownInvalidoException
	 */
	public void downgrade(String login) throws BuscaInvalidaException, UpDownInvalidoException{
		Usuario antigo = buscaUsuario(login);
		antigo.downgrade();
	}

	/**
	 * Confere o credito de um determinado usuario
	 * 
	 * @param login
	 * @return double - credito do usuario procurado
	 * @throws BuscaInvalidaException
	 */
	public double confereCredito(String login)throws BuscaInvalidaException {
			Usuario procurado = this.buscaUsuario(login);
			return procurado.getCredito();
	}

	/**
	 * Retorna o valor de x2p de um determinado usuario
	 * 
	 * @param login
	 * @return int - x2p do usuario procurado
	 * @throws BuscaInvalidaException
	 */
	public int getX2p(String login)throws BuscaInvalidaException {
		Usuario buscado = this.buscaUsuario(login);
		return buscado.getXp2();
	}

	/**
	 * Cria uma Set de jogabilidades
	 * 
	 * @param names1
	 * @return Set<Jogabilidade> - set de jogabilidades
	 */
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

	/**
	 * Inicializa o map de jogabilidades
	 * 
	 */
	private void initializeMap() {
		this.mapJogabildades = new HashMap<String, Jogabilidade>();
		mapJogabildades.put("ONLINE", Jogabilidade.ONLINE);
		mapJogabildades.put("OFFLINE", Jogabilidade.OFFLINE);
		mapJogabildades.put("COMPETITIVO", Jogabilidade.COMPETITIVO);
		mapJogabildades.put("COOPERATIVO", Jogabilidade.COOPERATIVO);
		mapJogabildades.put("MULTIPLAYER", Jogabilidade.MULTIPLAYER);

	}
	
	/**
	 * Cria um jogo utilizando a fábrica de jogos
	 * 
	 * @param nome
	 * @param tipo
	 * @param preco
	 * @param jogabilidades
	 * @return Jogo - novo jogo
	 * @throws StringInvalidaException
	 * @throws PrecoInvalidoException
	 */
	private Jogo criaJogo(String nome,String tipo,double preco,Set<Jogabilidade> jogabilidades)throws StringInvalidaException,PrecoInvalidoException{
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
