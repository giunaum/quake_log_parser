package br.com.teste.parser.business;

import br.com.teste.parser.exceptions.GameBusinessException;
import br.com.teste.parser.exceptions.UtilException;
import br.com.teste.parser.to.GameKillByMeansTO;
import br.com.teste.parser.to.GameTO;
import br.com.teste.parser.to.RankingTO;
import br.com.teste.parser.to.enums.MeansOfDeath;
import br.com.teste.parser.util.Constante;
import br.com.teste.parser.util.Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

@Component
public class GameBusiness {

	@Autowired
	Constante constante;

	/**
	 * Gera o relatório de kills por partida.
	 *
	 * @return
	 * @throws GameBusinessException
	 */
	public List<GameTO> relatorioKills() throws GameBusinessException {
		try {
			List<String> lines = Util.getLines(constante.getFileName());
			return getGames(lines);
		} catch (UtilException e) {
			throw new GameBusinessException(e.getMessage(), e);
		}
	}

	/**
	 * Gera o relatório de kills por partida Rankiada.
	 *
	 * @return
	 * @throws GameBusinessException
	 */
	public List<RankingTO> relatorioKillsRanking() throws GameBusinessException {
		try {
			List<String> lines = Util.getLines(constante.getFileName());
			List<String> players = getPlayers(lines);
			HashMap<String, Integer> kills = getKillsByPlayers(lines, players);
			List<RankingTO> killsRanking = new ArrayList<>();
			kills.entrySet().forEach(kill -> killsRanking.add(new RankingTO(kill.getKey(), kill.getValue())));
			return killsRanking.stream().sorted(Comparator.comparing(RankingTO::getTotalKills).reversed()).collect(Collectors.toList());
		} catch (UtilException e) {
			throw new GameBusinessException(e.getMessage(), e);
		}
	}

	/**
	 * Gera o relatório de kills por tipo.
	 *
	 * @return
	 * @throws GameBusinessException
	 */
	public List<GameKillByMeansTO> relatorioKillsByMeans() throws GameBusinessException {
		try {
			List<String> lines = Util.getLines(constante.getFileName());
			return getGamesKillsByMeans(lines);
		} catch (UtilException e) {
			throw new GameBusinessException(e.getMessage(), e);
		}
	}

	/**
	 * Recupera os Games.
	 *
	 * @param lines
	 * @return
	 */
	private List<GameTO> getGames(final List<String> lines) {
		List<GameTO> games = new ArrayList<>();
		List<String> linesAux = new ArrayList<>();
		Integer totalKills = BigInteger.ZERO.intValue();
		boolean isEndGame = true;

		for (String line : lines) {
			if (line.contains(constante.getInitGame())) {
				linesAux = new ArrayList<>();
				totalKills = BigInteger.ZERO.intValue();
				isEndGame = false;
			}

			linesAux.add(line);

			if (line.contains(constante.getKilled())) {
				totalKills++;
			}

			if (line.contains(constante.getEndGame()) && !isEndGame) {
				List<String> players = getPlayers(linesAux);
				HashMap<String, Integer> kills = getKillsByPlayers(linesAux, players);
				games.add(new GameTO(totalKills, players, kills));
				isEndGame = true;
			}
		}

		return games;
	}

	/**
	 * Recupera os Games.
	 *
	 * @param lines
	 * @return
	 */
	private List<GameKillByMeansTO> getGamesKillsByMeans(final List<String> lines) {
		List<GameKillByMeansTO> games = new ArrayList<>();
		List<String> linesAux = new ArrayList<>();
		boolean isEndGame = true;

		for (String line : lines) {
			if (line.contains(constante.getInitGame())) {
				linesAux = new ArrayList<>();
				isEndGame = false;
			}

			linesAux.add(line);

			if (line.contains(constante.getEndGame()) && !isEndGame) {
				List<MeansOfDeath> meansOfDeaths = getMeansOfDeaths(linesAux);
				HashMap<String, Integer> kills = getKillsByMeans(linesAux, meansOfDeaths);
				games.add(new GameKillByMeansTO(kills));
				isEndGame = true;
			}
		}

		return games;
	}

	/**
	 * Recupera os kills de cada jogador por partida.
	 *
	 * @param lines
	 * @param players
	 * @return
	 */
	private HashMap<String, Integer> getKillsByPlayers(final List<String> lines, final List<String> players) {
		HashMap<String, Integer> kills = new HashMap<>();

		players.forEach(player -> kills.put(player, BigInteger.ZERO.intValue()));

		lines.forEach(line -> {
			players.forEach(player -> {
				if (line.contains(constante.getPlayerWorld()) && line.contains(Util.getStringComEspacos(constante.getKilled(), player))) {
					int nKills = kills.get(player) - BigInteger.ONE.intValue();
					kills.replace(player, nKills);
				} else if (line.contains(Util.getStringComEspacos(player, constante.getKilled()))) {
					int nKills = kills.get(player) + BigInteger.ONE.intValue();
					kills.replace(player, nKills);
				}
			});
		});

		return kills;
	}

	/**
	 * Recupera os kills de cada tipo de morte por partida.
	 *
	 * @param lines
	 * @param meansOfDeaths
	 * @return
	 */
	private HashMap<String, Integer> getKillsByMeans(final List<String> lines, final List<MeansOfDeath> meansOfDeaths) {
		HashMap<String, Integer> kills = new HashMap<>();

		meansOfDeaths.forEach(meansOfDeath -> {
			if (meansOfDeath != null) {
				kills.put(meansOfDeath.toString(), BigInteger.ZERO.intValue());
			}
		});

		lines.forEach(line -> {
			meansOfDeaths.forEach(meansOfDeath -> {
				if (meansOfDeath != null && line.contains(Util.getStringComEspacos(constante.getMeanOfDeathIndexBegin(), meansOfDeath.toString()))) {
					int nKills = kills.get(meansOfDeath.toString()) + BigInteger.ONE.intValue();
					kills.replace(meansOfDeath.toString(), nKills);
				}
			});
		});

		return kills;
	}

	/**
	 * Recupera os jogadores.
	 *
	 * @param lines
	 * @return
	 */
	private List<String> getPlayers(final List<String> lines) {
		List<String> players = new ArrayList<>();

		lines.forEach(line -> {
			Matcher matcher = Util.getMatcher(constante.getRegexPlayer(), line);
			if (matcher.find()) {
				String playerName = matcher.group();
				playerName = playerName.substring(playerName.indexOf(constante.getPlayerIndexBegin()) + 2, playerName.indexOf(constante.getPlayerIndexEnd()));
				players.add(playerName);
			}
		});

		return players.stream().distinct().collect(Collectors.toList());
	}

	/**
	 * Recupera os tipos de morte.
	 *
	 * @param lines
	 * @return
	 */
	private List<MeansOfDeath> getMeansOfDeaths(final List<String> lines) {
		List<MeansOfDeath> meansOfDeaths = new ArrayList<>();

		lines.forEach(line -> {
			Matcher matcher = Util.getMatcher(constante.getRegexKilled(), line);
			if (matcher.find()) {
				String meansOfDeathName = matcher.group();
				meansOfDeathName = meansOfDeathName.substring(meansOfDeathName.indexOf(constante.getMeanOfDeathIndexBegin()) + 3);
				meansOfDeaths.add(MeansOfDeath.getByValue(meansOfDeathName));
			}
		});

		return meansOfDeaths.stream().distinct().collect(Collectors.toList());
	}
}
