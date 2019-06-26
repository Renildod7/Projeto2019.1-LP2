package comparator;

import java.io.Serializable;

import entities.Lei;

public class Aprovacao implements EstrategiaOrdenacao{

	@Override
	public int compare(Lei l1, Lei l2) {
		return Integer.compare(l1.getAprovacoes(), l1.getAprovacoes());
	}

}
