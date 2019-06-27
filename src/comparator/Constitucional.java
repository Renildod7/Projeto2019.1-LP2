package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.Lei;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior 
 *
 * Classe para comparar leis de acordo com o Constitucional.
 */
public class Constitucional implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		return l1.geTipoDeLei().compareTo(l2.geTipoDeLei());
	}
}
