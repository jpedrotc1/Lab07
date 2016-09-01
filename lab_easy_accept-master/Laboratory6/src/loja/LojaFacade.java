package loja;

import easyaccept.EasyAccept;
import usuario.Usuario;

public class LojaFacade {
	
	private LojaController loja;
	
	
	public LojaFacade(){
		this.loja = new LojaController();
	}
	
	public void criaUsuario(String nome, String login,String tipo){
		try{
			this.loja.criaUsuario(nome, login,tipo);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void vendeJogo(String jogoNome, double preco, String jogabilidades, String estiloJogo, String loginUser){
		try{
			this.loja.vendeJogo(jogoNome, preco, jogabilidades, estiloJogo, loginUser);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public void registraJogada(String login, String nomeJogo, int score, boolean venceu){
		try{
			this.loja.registraJogada(login, nomeJogo, score, venceu);
		}catch (Exception e){
			e.getMessage();
		}
	}
	
	public void adicionaCredito(String login, double credito){
		try{
			this.loja.adicionaCredito(login, credito);
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	}
	
	public Usuario buscaUsuario(String login){
		try{
			return this.loja.buscaUsuario(login);
		}catch(Exception e){
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public void upgrade(String login){
		try{
			this.loja.upgrade(login);
		}catch(Exception e){
			System.out.println(e.getMessage());
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
