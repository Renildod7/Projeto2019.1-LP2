package entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class Comissao implements Serializable {
	
	private static final long serialVersionUID = 1L;
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
					if (proximoLocal.equals("-")) {
						lei.votacaoComissaoAprovado(proximoLocal);
						lei.aprovarLei();
						autorDaLei.adicionaLeiAprovada();
					return true;
					}
				} else {
					lei.votacaoComissaoRejeitado(proximoLocal);
					lei.rejeitarLei();
					return false;
				}
			} else {
				if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
					lei.votacaoComissaoAprovado(proximoLocal);
					return true;
				} else {
					lei.votacaoComissaoRejeitado(proximoLocal);
					return false;
				}
			}
			
		case PLP:
			if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
				lei.votacaoComissaoAprovado(proximoLocal);
				return true;
			} else {
				lei.votacaoComissaoRejeitado(proximoLocal);
				return false;
			}

		case PEC:
			if (votosAFavor >= (Math.floor(this.deputados.size() / 2) + 1)) {
				lei.votacaoComissaoAprovado(proximoLocal);
				return true;
			} else {
				lei.votacaoComissaoRejeitado(proximoLocal);
				return false;
			}
			
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
