package entities;

import java.io.Serializable;
import java.util.Comparator;


/**
 * Classe responsavel pela implementacao da Interface CargoPolitico.
 */
public interface PessoaInterface extends Serializable {
	public String getPartido();
	public String getInteresses();
	public Comparator getEstrategiaOrdenacao();
	public void setEstrategia(Comparator estrategiaOrdenacao);
}
