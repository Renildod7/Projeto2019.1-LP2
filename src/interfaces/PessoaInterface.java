package interfaces;

import java.io.Serializable;

/**
 * Interface de classes que representam uma pessoa.
 */
public interface PessoaInterface extends Serializable {
	public String getPartido();
	public String getInteresses();
	public EstrategiaOrdenacao getEstrategiaOrdenacao();
	public void setEstrategia(EstrategiaOrdenacao estrategiaOrdenacao);
}
