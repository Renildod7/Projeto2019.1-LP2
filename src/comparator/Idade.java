package comparator;

import java.io.Serializable;
import java.util.Comparator;

import entities.Lei;

public class Idade implements Comparator<Lei>, Serializable{

	@Override
	public int compare(Lei l1, Lei l2) {
		String codigol1 = l1.getCodigo();
		String codigol2 = l2.getCodigo();
		
		int anol1 = Integer.parseInt(codigol1.split("/")[1]);
		int anol2 = Integer.parseInt(codigol2.split("/")[1]);
		
		int numl1 = Integer.parseInt(codigol1.split(" ")[1].split("/")[0]);
		int numl2 = Integer.parseInt(codigol2.split(" ")[1].split("/")[0]);
		
		
		if(Integer.compare(anol1, anol2) == 0) {
			return Integer.compare(numl1, numl2);
		}else return Integer.compare(anol1, anol2);
	}

}
