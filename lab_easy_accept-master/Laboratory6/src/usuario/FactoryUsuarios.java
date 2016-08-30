package usuario;

public class FactoryUsuarios {
	
	Usuario novoUsuario = null;
	
	
	public Usuario criaUsuarios(String nome,String login,String tipo)throws Exception{
		if(tipo.equalsIgnoreCase("Noob")){
			return new Noob(nome,login);
		}
		if(tipo.equalsIgnoreCase("Veterano")){
			return new Veterano(nome,login);
		}
		
		return novoUsuario;
	}
	
}
