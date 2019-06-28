package interfaces;

import java.io.Serializable;
import java.util.Comparator;
import java.util.List;

import entities.LeiComparator;

/**
 * Interface de comparators de objetos do tipo LeiComparator
 */
public interface EstrategiaOrdenacao extends Comparator<LeiComparator>, Serializable{
	
	/**
	 * Recebe uma lista de objetos do tipo LeiComparator que foram ordenados de arcordo com o criterio de ordenacao do comparator utiizado 
	 * do maior para o menor, e retorna uma lista de LeiComparator contendo os objetos que ficaram empatados como os maiores apos a ordenacao.
	 */
	public List<LeiComparator> empatados(List<LeiComparator> list);
}
