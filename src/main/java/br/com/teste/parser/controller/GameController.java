package br.com.teste.parser.controller;

import br.com.teste.parser.business.GameBusiness;
import br.com.teste.parser.exceptions.GameBusinessException;
import br.com.teste.parser.to.GameKillByMeansTO;
import br.com.teste.parser.to.GameTO;
import br.com.teste.parser.to.RankingTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.List;

/**
 * Classe de controle responsável pela manipulação das informações de {@link GameTO}.
 */
@Controller
@RestController
@RequestMapping(value = "/game")
public class GameController implements Serializable {

	@Autowired
	private GameBusiness gameBusiness;

	/**
	 * Gera o relatório de kills por partida.
	 *
	 * @return
	 */
	@GetMapping(value = "/kills")
	@ResponseBody
	public List<GameTO> relatorioKills() throws GameBusinessException {
		return gameBusiness.relatorioKills();
	}

	/**
	 * Gera o relatório de kills por partida.
	 *
	 * @return
	 */
	@GetMapping(value = "/kills/ranking")
	@ResponseBody
	public List<RankingTO> relatorioKillsRanking() throws GameBusinessException {
		return gameBusiness.relatorioKillsRanking();
	}

	/**
	 * Gera o relatório de kills por tipo.
	 *
	 * @return
	 */
	@GetMapping(value = "/kills/means")
	@ResponseBody
	public List<GameKillByMeansTO> relatorioKillsByMeans() throws GameBusinessException {
		return gameBusiness.relatorioKillsByMeans();
	}
}
