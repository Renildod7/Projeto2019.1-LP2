package entities;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import entities.pessoa.Deputado;
import entities.projetodelei.Pl;
import entities.projetodelei.ProjetoDeLei;

/**
 * Classe que representa uma comissao de deputados.
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
	 * Realiza a votacao de um determinado projeto de lei.
	 * 
	 * @param statusGovernista Status do projeto de lei em relacao ao governo atual.
	 * @param proximoLocal Proximo local onde o projeto de lei sera votado.
	 * @param lei Projeto de lei que sera votado.
	 * @param autorDaLei Autor do projeto de lei.
	 * @param base Partidos que fazem parte da base do atual governo.
	 * 
	 * @return Boleano indicando se o projeto de lei foi aprovado ou nao na comissao.
	 */
	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, Set<String> base) {
		int votosAFavor = votar(base, lei, statusGovernista);	
		
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
	 * Contabiliza quantos votos a favor um projeto de lei possui na comissao.
	 * 
	 * @param base Partidos que fazem parte da base do governo
	 * @param lei Projeto de lei que sera votado
	 * @param statusGovernista Status do projeto de lei em relacao ao atual governo.
	 * 
	 * @return retorna Quantidade de votos a faver que o projeto de lei recebeu.
	 */
	private int votar(Set<String> base, ProjetoDeLei lei, String statusGovernista) {
		int votosAFavor = 0;
		for(Deputado d : this.deputados) {
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
