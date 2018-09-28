package br.com.teste.parser.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa os detalhes do jogo.
 */
public class GameTO implements Serializable {

	private final Integer totalKills;
	private final List<String> players;
	private final HashMap<String, Integer> kills;

	/**
	 * Construtor da classe.
	 *
	 * @param totalKills
	 * @param players
	 * @param kills
	 */
	public GameTO(Integer totalKills, List<String> players, HashMap<String, Integer> kills) {
		this.totalKills = totalKills;
		this.players = players;
		this.kills = kills;
	}

	public Integer getTotalKills() {
		return totalKills;
	}

	public List<String> getPlayers() {
		return players;
	}

	public HashMap<String, Integer> getKills() {
		return kills;
	}
}