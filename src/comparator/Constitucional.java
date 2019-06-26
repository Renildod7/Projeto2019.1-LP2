package comparator;

import java.io.Serializable;

import entities.Lei;

public class Constitucional implements EstrategiaOrdenacao {

	@Override
	public int compare(Lei l1, Lei l2) {
		return l1.geTipoDeLei().compareTo(l2.geTipoDeLei());
	}
}
