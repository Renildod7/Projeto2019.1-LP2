package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.Lei;

public class Constitucional implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		return l1.geTipoDeLei().compareTo(l2.geTipoDeLei());
	}
}
