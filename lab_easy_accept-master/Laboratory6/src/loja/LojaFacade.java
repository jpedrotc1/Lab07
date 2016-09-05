package loja;

import easyaccept.EasyAccept;
import usuario.Usuario;
import excecoes.UpDownInvalidoException;
import excecoes.ValorInvalidoException;
import excecoes.StringInvalidaException;

public class LojaFacade {
	
	private LojaController loja;
	
	
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	public void criaUsuario(String nome, String login,String tipo){
		try {
			loja.criaUsuario(nome, login);
		}catch(Exception e){
			e.getMessage();
		}
	}
	public void upgrade(String login) throws Exception,StringInvalidaException,UpDownInvalidoException {
		try {
			loja.upgrade(login);
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
	}

	public void downgrade(String login) throws Exception,StringInvalidaException,UpDownInvalidoException {
		try {
			loja.downgrade(login);
		} catch (Exception e) {
			e.getMessage();
			throw e;
		}
	}

	
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		try{
			this.loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public void punir(String login, String nomeJogo, int score, boolean venceu)throws Exception, ValorInvalidoException{
			try {
				loja.punir(login, nomeJogo, score, venceu);
			} catch (Exception e) {
				System.out.println(e.getMessage());
				throw e;
			}
		}
	
	public void recompensar(String login, String nomeJogo, int score, boolean venceu) throws Exception, ValorInvalidoException{
			try {
				loja.recompensar(login, nomeJogo, score, venceu);
			} catch (Exception e) {
				e.getMessage();
				throw e;
			}
		}
	
	public void adicionaCredito(String login, double credito){
		try{
			this.loja.adicionaCredito(login, credito);
		}catch(Exception e){
			e.getMessage();
		}
	}
	
	public Usuario buscaUsuario(String login){
		try{
			return this.loja.buscaUsuario(login);
		}catch(Exception e){
			e.getMessage();
			return null;
		}
	}
	
	public double confereCredito(String login){
		try{
			return this.loja.confereCredito(login);
		}catch(Exception e){
			e.getMessage();
			return 0;
		}
	}
	
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
