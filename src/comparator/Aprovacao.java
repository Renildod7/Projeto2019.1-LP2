package comparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.LeiComparator;
import interfaces.EstrategiaOrdenacao;

/**
 * Classe para comparar objetos do tipo LeiComparator de acordo com seu atributo aprovacoes.
 */

public class Aprovacao implements EstrategiaOrdenacao, Serializable{

	private static final long serialVersionUID = -3725156571681134165L;
	
	/**
	 * Comparador utilizado na ordenacao de objetos do tipo LeiComparator do maior para o menor de acordo com 
	 * o atributo aprovacoes.
	 */
	@Override
	public int compare(LeiComparator l1, LeiComparator l2) {
		return Integer.compare(l1.getAprovacoes(), l2.getAprovacoes()) * -1;
	}
	
	/**
	 * Recebe uma lista de objetos do tipo LeiComparator que foram ordenados de arcordo com o atributo aprovacoes 
	 * do maior para o menor, e retorna uma lista de LeiComparator contendo os objetos que ficaram empatados como os 
	 * maiores apos a ordenacao.
	 */
	@Override
	public List<LeiComparator> empatados(List<LeiComparator> list) {
		List<LeiComparator> empatados = new ArrayList<>();
		for (LeiComparator lei : list) {
			if(lei.getAprovacoes() == list.get(0).getAprovacoes()) {
				empatados.add(lei);
			}
		}
		return empatados;
	}

}
