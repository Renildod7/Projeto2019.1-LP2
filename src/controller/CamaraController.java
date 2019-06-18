package controller;

import java.util.Map;
import java.util.Set;

import entities.Deputado;
import entities.Pl;
import entities.ProjetoDeLei;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa o controlador de comissoes.
 */
public class CamaraController {
	
	/**
	 * Map de comissoes cadastradas no sistema.
	 */
	private Map<String, Set<String>> comissoes;
	
	/**
	 * Construtor do controlador. Cria um novo HashMap.
	 */
	public CamaraController() {
		this.comissoes = new HashMap<>();
	}

	/**
	 * Metodo responsavel por cadastrar uma comissao de votacao no sistema.
	 * 
	 * @param tema		O tema da comissao.
	 * @param politicos Os dni's dos politicos que farao parte da comissao.
	 */
	public void cadastrarComissao(String tema, Set<String> politicos) {		
		this.comissoes.put(tema, politicos);
	}
	
	/**
	 * Metodo que identifica se uma comissao esta cadastrada no sistema.
	 * 
	 * @param tema O tema da comissao.
	 * 
	 * @return true caso a comissao esteja cadastrada e false caso contrario.
	 */
	public boolean containsComissao(String tema) {
		return this.comissoes.containsKey(tema);
	}  
	
	public Set<String> getComissao(String tema){
		return this.comissoes.get(tema);
	}

	public boolean votarComissao(String statusGovernista, String proximoLocal, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputados, Set<String> base) {
		int votosAFavor = votar(deputados, base, lei, statusGovernista);
		
		switch (lei.getTipoDeLei()) {	
		case PL:
			if (((Pl)lei).getConclusivo()) {
				if (votosAFavor >= (Math.floor(deputados.size() / 2) + 1)) {
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
				if (votosAFavor >= (Math.floor(deputados.size() / 2) + 1)) {
					return true;
				} else return false;
			}
			
		case PLP:
			lei.setLocalDeVotacao(proximoLocal);
			if (proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if (votosAFavor >= (Math.floor(deputados.size() / 2) + 1)) {
				return true;
			} else return false;

		case PEC:
			lei.setLocalDeVotacao(proximoLocal);
			if (proximoLocal.equals("plenario")) lei.plenario1oTurno();
			if (votosAFavor >= (Math.floor(deputados.size() / 2) + 1)) {
				return true;
			} else return false;
			
		default:
			break;
		}	
		return false;
	}	
	
	
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<Deputado> todosDeputados, Set<String> base) {	
		int votosAFavor = votar(deputadosPresentes, base, lei, statusGovernista);
		
		switch (lei.getTipoDeLei()) {
		case PL:
			if (votosAFavor >= (Math.floor(deputadosPresentes.size() / 2) + 1)) {
				lei.aprovarLei();
				autorDaLei.adicionaLeiAprovada();
				return true;
			} else {
				lei.rejeitarLei();
				return false;
			}
			
		case PLP:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if (votosAFavor >= (Math.floor(todosDeputados.size() / 2) + 1)) {
					lei.plenario2oTurno();
					return true;
				} else {
					lei.rejeitarLei();
					return false;
				}
				
			case SEGUNDO_TURNO:
				if (votosAFavor >= (Math.floor(todosDeputados.size() / 2) + 1)) {
					lei.aprovarLei();
					autorDaLei.adicionaLeiAprovada();
					return true;
				} else {
					lei.rejeitarLei();
					return false;
				}

			default:
				break;
			}
			
		case PEC:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if (votosAFavor >= (Math.floor(3 * deputadosPresentes.size() / 5) + 1)) {
					lei.plenario2oTurno();
					return true;
				} else {
					lei.rejeitarLei();
					return false;
				}
				
			case SEGUNDO_TURNO:
				if (votosAFavor >= (Math.floor(3 * deputadosPresentes.size() / 5) + 1)) {
					lei.aprovarLei();
					autorDaLei.adicionaLeiAprovada();
					return true;
				} else {
					lei.rejeitarLei();
					return false;
				}
				
			default:
				break;
			}

		default:
			break;
		}		
		return true;
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
