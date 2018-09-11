/**
 * 
 */
package br.com.ilegra.testeSelecao.parse;

import br.com.ilegra.testeSelecao.domain.Line;

/**
 * @author tiago marzari
 * @since 10/09/2018
 */
public interface LineParse {
	
	public abstract Line parse(String[] data);

}
