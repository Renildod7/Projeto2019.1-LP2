package entities;

import java.io.Serializable;

/**
 * Classe responsavel pela implementacao da Interface CargoPolitico.
 */
public interface PessoaInterface extends Serializable {
	public String getPartido();
	public String getInteresses();
}
