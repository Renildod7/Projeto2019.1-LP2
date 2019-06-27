package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.Lei;

public class Interesses implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		return Integer.compare(l1.getQtdInteresses(), l2.getQtdInteresses());
	}

}
