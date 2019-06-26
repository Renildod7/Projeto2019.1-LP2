package entities;

import java.io.Serializable;

import comparator.EstrategiaOrdenacao;

/**
 * Classe responsavel pela implementacao da Interface CargoPolitico.
 */
public interface PessoaInterface extends Serializable {
	public String getPartido();
	public String getInteresses();
	public EstrategiaOrdenacao getEstrategiaOrdenacao();
	public void setEstrategia(EstrategiaOrdenacao estrategiaOrdenacao);
}
