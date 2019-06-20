package entities;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Plenario {
	
	private Set<Deputado> todosDeputados;
	
	public Plenario() {
		this.todosDeputados = new HashSet<>();
	}

	public void cadastrarDeputado(Deputado deputado) {
		this.todosDeputados.add(deputado);
	}
	
	public boolean votarPlenario(String statusGovernista, ProjetoDeLei lei, Deputado autorDaLei, Set<Deputado> deputadosPresentes, Set<String> base) {	
		int votosAFavor = votar(deputadosPresentes, base, lei, statusGovernista);
		
		switch (lei.getTipoDeLei()) {
		case PL:
			if (votosAFavor >= (Math.floor(deputadosPresentes.size() / 2) + 1)) {
				lei.votacaoPlenarioAprovado();
				lei.aprovarLei();
				autorDaLei.adicionaLeiAprovada();
				return true;
			} else {
				lei.votacaoPlenarioRejeitado();
				lei.rejeitarLei();
				return false;
			}
			
		case PLP:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if (votosAFavor >= (Math.floor(this.todosDeputados.size() / 2) + 1)) {
					lei.votacaoPlenarioAprovado();
					return true;
				} else {
					lei.votacaoPlenarioRejeitado();
					lei.rejeitarLei();
					return false;
				}
				
			case SEGUNDO_TURNO:
				if (votosAFavor >= (Math.floor(this.todosDeputados.size() / 2) + 1)) {
					lei.votacaoPlenarioAprovado();
					lei.aprovarLei();
					autorDaLei.adicionaLeiAprovada();
					return true;
				} else {
					lei.votacaoPlenarioRejeitado();
					lei.rejeitarLei();
					return false;
				}

			default:
				break;
			}
			
		case PEC:
			switch (lei.getStatusPlenario()) {
			case PRIMEIRO_TURNO:
				if (votosAFavor >= (Math.floor(3 * this.todosDeputados.size() / 5) + 1)) {
					lei.votacaoPlenarioAprovado();
					return true;
				} else {
					lei.votacaoPlenarioRejeitado();
					lei.rejeitarLei();
					return false;
				}
				
			case SEGUNDO_TURNO:
				if (votosAFavor >= (Math.floor(3 * this.todosDeputados.size() / 5) + 1)) {
					lei.votacaoPlenarioAprovado();
					lei.aprovarLei();
					autorDaLei.adicionaLeiAprovada();
					return true;
				} else {
					lei.votacaoPlenarioRejeitado();
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
