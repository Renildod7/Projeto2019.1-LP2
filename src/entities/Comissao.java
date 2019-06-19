package entities;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Comissao {
	
	private Set<Deputado> deputados;
	
	public Comissao(Set<Deputado> deputados) {
		this.deputados = deputados;
	}
	
	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, Set<String> base) {
		int votosAFavor = votar(this.deputados, base, lei, statusGovernista);
		
		switch (lei.getTipoDeLei()) {	
		case PL:
			if (((Pl)lei).getConclusivo()) {
				if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
					lei.setLocalDeVotacao(proximoLocal);
					if (proximoLocal.equals("-")) {
						lei.aprovarLei();
						autorDaLei.adicionaLeiAprovada();
						return true;
					}
				} else {
					lei.rejeitarLei();
					return false;
				}
			} else {
				lei.setLocalDeVotacao(proximoLocal);
				if (proximoLocal.equals("plenario")) lei.plenario1oTurno();
				if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
					return true;
				} else return false;
			}
			
		case PLP:
			lei.setLocalDeVotacao(proximoLocal);
			if (proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
				return true;
			} else return false;

		case PEC:
			lei.setLocalDeVotacao(proximoLocal);
			if (proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
				return true;
			} else return false;
			
		default:
			break;
		}	
		return false;
	}
	
	private int votar(Set<Deputado> deputados, Set<String> base, ProjetoDeLei lei, String statusGovernista) {
		int votosAFavor = 0;
		for(Deputado d : deputados) {
			String partidoDeputado = d.getPartido();
			
			switch (statusGovernista) {
			case "GOVERNISTA":
				if(base.contains(partidoDeputado)) votosAFavor ++;
				break;
				
			case "OPOSICAO":
				if(!base.contains(partidoDeputado)) votosAFavor ++;
				break;
				
			case "LIVRE":
				List<String> leiInteresses  = Arrays.asList(lei.getInteresses().split(","));
				List<String> deputadoInteresses = Arrays.asList(d.getInteresses().split(","));
				
				for(String interesse : deputadoInteresses) {
					if(leiInteresses.contains(interesse)) {
						votosAFavor ++;
						break;
					}
				}
				break;
				
			default:
				break;
			}
		}			
		return votosAFavor;
	}
}
