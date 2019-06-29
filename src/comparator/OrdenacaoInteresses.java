package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.LeiComparator;

/**
 * Classe para comparar objetos do tipo LeiComparator de acordo com o atributo interesses.
 */
public class OrdenacaoInteresses implements Comparator<LeiComparator>, Serializable{

	private static final long serialVersionUID = 342782454542657723L;
	
	/**
	 * Comparador utilizado na ordenacao de objetos do tipo LeiComparator do maior para o menor de acordo com 
	 * o atributo interesses.
	 */
	@Override
	public int compare(LeiComparator l1, LeiComparator l2) {
		return Integer.compare(l1.getQtdInteresses(), l2.getQtdInteresses()) * -1;
	}

}
