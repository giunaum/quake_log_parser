package br.com.teste.parser.to;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

/**
 * Classe que representa os detalhes do jogo.
 */
public class RankingTO implements Serializable {

	private final String player;
	private final Integer totalKills;

	/**
	 * Construtor da classe.
	 *
	 * @param player
	 * @param totalKills
	 */
	public RankingTO(String player, Integer totalKills) {
		this.player = player;
		this.totalKills = totalKills;
	}

	public String getPlayer() {
		return player;
	}

	public Integer getTotalKills() {
		return totalKills;
	}
}