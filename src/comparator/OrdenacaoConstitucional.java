package comparator;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import entities.LeiComparator;
import interfaces.EstrategiaOrdenacao;

/**
 * Classe para comparar objetos do tipo LeiComparator de acordo com o tipoDelei.
 */
public class OrdenacaoConstitucional implements EstrategiaOrdenacao, Serializable{

	private static final long serialVersionUID = -2339390370337652975L;
	
	/**
	 * Comparador utilizado na ordenacao de objetos do tipo LeiComparator do maior para o menor de acordo com 
	 * o atributo tipoDeLei.
	 */
	@Override
	public int compare(LeiComparator l1, LeiComparator l2) {
		return l1.geTipoDeLei().compareTo(l2.geTipoDeLei()) * -1;
	}
	
	/**
	 * Recebe uma lista de objetos do tipo LeiComparator que foram ordenados de arcordo com o atributo tipoDeLei 
	 * do maior para o menor, e retorna uma lista de LeiComparator contendo os objetos que ficaram empatados como os 
	 * maiores apos a ordenacao.
	 */
	public List<LeiComparator> empatados(List<LeiComparator> list){
		List<LeiComparator> empatados = new ArrayList<>();
		
		for (LeiComparator lei : list) {
			if(lei.geTipoDeLei().equals(list.get(0).geTipoDeLei())) {
				empatados.add(lei);
			}
		}
		return empatados;
		
	}
}
