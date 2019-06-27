package comparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import entities.Lei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior 
 *
 * Classe para Comparar leis de acordo com suas conclusoes.
 */
public class Conclusao implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		return Integer.compare(l1.getTramitacao(), l2.getTramitacao());		
	}

}
