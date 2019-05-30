package entities;

/**
 * Representacao de Partido onde se tem nome.
 * 
 * @author Wander Medeiros de Brito Junior
 *
 */
public class Partido implements Comparable<Partido> {
	
	private String nome;
	
	
	/**
	 * Metodo de Construcao da Classe Partido
	 * @param nome E o nome do Partido
	 */
	public Partido(String nome) {
		this.nome = nome;
	}
	
	/**
	 * Metodo onde se Retorna o Nome do Partido
	 * @return
	 */
	public String getNome() {
		return this.nome;
	}
	
	/**
	 * Metodo de comparacao de qual String Nome e
	 */
	public int compareTo(Partido o) {
		
		return this.getNome().compareTo(o.getNome());
	}
	

	
	/**
	 *Metodo usado para gerar o Codigo que sera usado na Tabela Hash
	 */
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
		return result;
	}

	
	/**
	 *Metodo de Comparacao se um objeto do tipo Partido e igual a outro Objeto do mesmo tipo.
	 */
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Partido other = (Partido) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	
	/**
	 *Metodo onde se Retorna a Representacao do Partido em formato de String
	 */
	public String toString() {
		return  nome ;
	}
	
}
