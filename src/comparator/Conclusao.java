package comparator;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import entities.Lei;

public class Conclusao implements EstrategiaOrdenacao{

	@Override
	public int compare(Lei l1, Lei l2) {
		List<String> tramitacoesL1 = l1.getTramitacao();
		List<String> tramitacoesL2 = l2.getTramitacao();
		
		String ccjcl1 = tramitacoesL1.get(0).split(" ")[0];
		String ccjcl2 = tramitacoesL2.get(0).split(" ")[0];
		
		Collections.reverse(tramitacoesL1);
		Collections.reverse(tramitacoesL2);
		
		if(tramitacoesL1.get(0).equals("EM VOTACAO (Plenario - 2o turno)")) {
			if(tramitacoesL2.get(0).equals("EM VOTACAO (Plenario - 2o turno)")) {
				if(Integer.compare(tramitacoesL1.size(), tramitacoesL2.size()) == 0) {
					if(ccjcl1.equals("APROVADO")) {
						if(ccjcl2.equals("APROVADO")) return 0;
					}else return -1;
					if(ccjcl2.equals("APROVADO")) {
						return 1;
					}else return 0;
				}else return Integer.compare(l1.getAprovacoes(), l2.getAprovacoes());
			}else return -1;
		}
		
		if(tramitacoesL1.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
			if(tramitacoesL2.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
				if(Integer.compare(tramitacoesL1.size(), tramitacoesL2.size()) == 0) {
					if(ccjcl1.equals("APROVADO")) {
						if(ccjcl2.equals("APROVADO")) return 0;
					}else return -1;
					if(ccjcl2.equals("APROVADO")) {
						return 1;
					}else return 0;
				}else return Integer.compare(l1.getAprovacoes(), l2.getAprovacoes());
			}else return -1;
		}
		
		if(Integer.compare(l1.getAprovacoes(), l2.getAprovacoes()) == 0) {
			if(ccjcl1.equals("APROVADO")) {
				if(ccjcl2.equals("APROVADO")) return 0;
			}else return -1;
			if(ccjcl2.equals("APROVADO")) {
				return 1;
			}else return 0;
		}else return Integer.compare(l1.getAprovacoes(), l2.getAprovacoes());
		
//		if(tramitacoesL2.get(0).equals("EM VOTACAO (Plenario - 2o turno)")) {
//			if(!tramitacoesL1.get(0).equals("EM VOTACAO (Plenario - 2o turno)")) {
//				return 1;
//			}
//		}
//		
//		if(tramitacoesL1.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
//			if(tramitacoesL2.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
//				return Integer.compare(tramitacoesL1.size(), tramitacoesL2.size());
//			}return -1;
//
//		}
//		
//		if(tramitacoesL2.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
//			if(tramitacoesL1.get(0).equals("EM VOTACAO (Plenario - 1o turno)")) {
//				return Integer.compare(tramitacoesL1.size(), tramitacoesL2.size());
//			}return 1;
//
//		}
//		
//		return Integer.compare(tramitacoesL1.size(), tramitacoesL2.size());
		
	}

}
