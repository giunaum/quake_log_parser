package br.com.teste.parser.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa os detalhes do jogo.
 */
public class GameKillByMeansTO implements Serializable {

	private final HashMap<String, Integer> killsByMeans;

	/**
	 * Construtor da classe.
	 *
	 * @param killsByMeans
	 */
	public GameKillByMeansTO(HashMap<String, Integer> killsByMeans) {
		this.killsByMeans = killsByMeans;
	}

	public HashMap<String, Integer> getKillsByMeans() {
		return killsByMeans;
	}
}