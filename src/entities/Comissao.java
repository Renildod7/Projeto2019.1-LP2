package entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Augusto Gomes dos Santos
 * @author Renildo Dantas Melo
 * @author Wander Medeiros de Brito Junior
 */

public class Comissao implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private Set<Deputado> deputados;
	
	/**
	 * Construtor de Comissao
	 * @param deputados deputados que fazem parte das comissoes.
	 */
	public Comissao(Set<Deputado> deputados) {
		this.deputados = deputados;
	}
	
	/**
	 * Metodo onde se Vota uma Comissao.
	 * 
	 * @param statusGovernista estados se a comissao e da base governista ou nao.
	 * @param proximoLocal proximo local a ser votado a comissao.
	 * @param lei lei a ser votada na comissao.
	 * @param autorDaLei autor da lei a ser votada
	 * @param base os partidos que fazem parte
	 * @return retorna se a lei foi aprovada ou nao.
	 */
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
	
	/**
	 * @param deputados deputados que estao na comissao
	 * @param base os partidos que fazem parte.
	 * @param lei lei que sera votada na comissao.
	 * @param statusGovernista Status onde idica se a comissao e da base governista ou nao.
	 * @return retorna votos a favor da votacao de uma comissao.
	 */
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
