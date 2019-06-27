package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.Lei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior 
 * 
 * Classe para comparar Leis de acordo com os interesses.
 *
 */
public class Interesses implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		return Integer.compare(l1.getQtdInteresses(), l2.getQtdInteresses());
	}

}
