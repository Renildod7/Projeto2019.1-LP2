package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.LeiComparator;

/**
 * Classe para comparar objetos do tipo LeiComparator de acordo com o momento em que foram criadas.
 */
public class Idade implements Comparator<LeiComparator>, Serializable{

	private static final long serialVersionUID = 2748872821359809637L;
	
	/**
	 * Comparador utilizado na ordenacao de objetos do tipo LeiComparator do maior para o menor de acordo com 
	 * o momento em que foram criados.
	 */
	@Override
	public int compare(LeiComparator l1, LeiComparator l2) {
		String codigol1 = l1.getCodigo();
		String codigol2 = l2.getCodigo();
		
		int anol1 = Integer.parseInt(codigol1.split("/")[1]);
		int anol2 = Integer.parseInt(codigol2.split("/")[1]);
		
		
		if(Integer.compare(anol1, anol2) == 0) {
			return l1.getDataDeCadastro().compareTo(l2.getDataDeCadastro());
		}else return Integer.compare(anol1, anol2);
	}

}
