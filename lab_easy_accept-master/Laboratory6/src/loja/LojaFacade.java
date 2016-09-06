package loja;

import easyaccept.EasyAccept;
import usuario.Usuario;
import excecoes.UpDownInvalidoException;
import excecoes.ValorInvalidoException;
import excecoes.StringInvalidaException;

/** Essa classe representa a fachada.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public class LojaFacade {
	
	private LojaController loja;
	
	/**
	 * Construtor da fachada
	 * 
	 */
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	/**
	 * Delega criaUsuario de lojaController
	 * 
	 * @param nome
	 * @param login
	 * @param tipo
	 */
	public void criaUsuario(String nome, String login,String tipo){
		try {
			loja.criaUsuario(nome, login);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Delega upgrade de lojaController
	 * 
	 * @param login
	 * @throws Exception
	 * @throws StringInvalidaException
	 * @throws UpDownInvalidoException
	 */
	public void upgrade(String login) throws Exception,StringInvalidaException,UpDownInvalidoException {
		try {
			loja.upgrade(login);
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
	}

	/**
	 * Delega downgrade de lojaController
	 * 
	 * @param login
	 * @throws Exception
	 * @throws StringInvalidaException
	 * @throws UpDownInvalidoException
	 */
	public void downgrade(String login) throws Exception,StringInvalidaException,UpDownInvalidoException {
		try {
			loja.downgrade(login);
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
	}

	/**
	 * Delega vende jogo de lojaController
	 * 
	 * @param jogoNome
	 * @param preco
	 * @param jogabilidades
	 * @param estiloJogo
	 * @param loginUser
	 */
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		try{
			this.loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Delega punir de lojaController
	 * 
	 * @param login
	 * @param nomeJogo
	 * @param score
	 * @param venceu
	 * @throws Exception
	 * @throws ValorInvalidoException
	 */
	public void punir(String login, String nomeJogo, int score, boolean venceu)throws Exception, ValorInvalidoException{
			try {
				loja.punir(login, nomeJogo, score, venceu);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
	
	/**
	 * Delega recompensar de lojaController
	 * 
	 * @param login
	 * @param nomeJogo
	 * @param score
	 * @param venceu
	 * @throws Exception
	 * @throws ValorInvalidoException
	 */
	public void recompensar(String login, String nomeJogo, int score, boolean venceu) throws Exception, ValorInvalidoException{
			try {
				loja.recompensar(login, nomeJogo, score, venceu);
			} catch (Exception e) {
				e.getMessage();
				throw e;
			}
		}
	
	/**
	 * Delega adicionaCredito de lojaController
	 * 
	 * @param login
	 * @param credito
	 */
	public void adicionaCredito(String login, double credito){
		try{
			this.loja.adicionaCredito(login, credito);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	/**
	 * Delega buscaUsuario de lojaController e retorna o mesmo
	 * 
	 * @param login
	 * @return Usuario - retorna o usuario procurado
	 */
	public Usuario buscaUsuario(String login){
		try{
			return this.loja.buscaUsuario(login);
		}catch(Exception e){
			e.getMessage();
			return null;
		}
	}
	
	/**
	 * Delega confereCredito de lojaController e retorna o valor do credito
	 * 
	 * @param login
	 * @return double - valor de credito de usuario
	 */
	public double confereCredito(String login){
		try{
			return this.loja.confereCredito(login);
		}catch(Exception e){
			e.getMessage();
			return 0;
		}
	}
	
	/**
	 * Delega getX2p de lojaController e retorna o valor de x2p correspondente 
	 * 
	 * @param login
	 * @return
	 */
	public int getX2p(String login){
		try{
			return this.loja.getX2p(login);
		}catch(Exception e){
			e.getMessage();
			return 0;
		}
	}
	
	public String lojaInfo(){
		return this.loja.toString();
	}
	public static void main(String[] args) {
		args = new String[] { "loja.LojaFacade", "acceptance_test/us1.txt", "acceptance_test/us2.txt",  "acceptance_test/us3.txt" };
		EasyAccept.main(args);
	}

}
