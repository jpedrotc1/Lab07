package usuario;

import excecoes.ValorInvalidoException;
import jogo.Jogo;

/** Essa interface define o objeto Usuario.
 * 
 * @author João Pedro Travasso Costa - 115210098 - Turma 01
 *
 */

public interface TipoDeUsuarioIf {
	
	/** Definição do metodo que retorna o valor da compra no Usuario.
	 * 
	 * @param jogo 
	 * @return double - Desconto do Usuario.
	 * @throws Exception
	 */
	double compraJogo(Jogo jogo)throws ValorInvalidoException;
	
	/** Definição do metodo que retorna a punicao no Usuario.
	 * 
	 * @param jogo
	 * @return int - PerdaX2p do Usuario.
	 * @throws Exception
	 */
	int punir(Jogo jogo);
	
	/** Definição do metodo que retorna a recompensa no Usuario.
	 * 
	 * @param jogo
	 * @return int - GanhoX2p do Usuario.
	 * @throws Exception
	 */
	int recompensar (Jogo jogo);
	
	/** Definição do metodo que retorna a quantidade de X2p do usuario.
	 * 
	 * @param jogo
	 * @return int - PerdaX2p do Usuario.
	 */
	int getX2p(Jogo jogo);

}
