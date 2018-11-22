package br.com.teste.parser.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Classe de constantes.
 */
@Component
public class Constante {

	@Value("${game.file.name}")
	private String fileName;

	@Value("${game.regex.player}")
	private String regexPlayer;

	@Value("${game.player.index.begin}")
	private String playerIndexBegin;

	@Value("${game.player.index.end}")
	private String playerIndexEnd;

	@Value("${game.player.world}")
	private String playerWorld;

	@Value("${game.init}")
	private String InitGame;

	@Value("${game.end}")
	private String endGame;

	@Value("${game.killed}")
	private String killed;

	@Value("${game.regex.killed}")
	private String regexKilled;

	@Value("${game.mean.of.death.index.begin}")
	private String meanOfDeathIndexBegin;

	private Constante() {
	}

	public String getFileName() {
		return fileName;
	}

	public String getRegexPlayer() {
		return regexPlayer;
	}

	public String getPlayerIndexBegin() {
		return playerIndexBegin;
	}

	public String getPlayerIndexEnd() {
		return playerIndexEnd;
	}

	public String getPlayerWorld() {
		return playerWorld;
	}

	public String getInitGame() {
		return InitGame;
	}

	public String getEndGame() {
		return endGame;
	}

	public String getKilled() {
		return killed;
	}

	public String getRegexKilled() {
		return regexKilled;
	}

	public String getMeanOfDeathIndexBegin() {
		return meanOfDeathIndexBegin;
	}
}
