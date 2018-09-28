Webservice Rest
================================================
Webservice Rest utiliza o Spring boot.
Para executar o projeto é necessário executar o install do Maven.

Serviços
================================================

[url]/game/kills
--------------------------------------------------------------------------------------------------
Método GET que lê o arquivo de log e retorna as partidas com a pontuação de cada jogado.

[url]/game/kills/ranking
--------------------------------------------------------------------------------------------------
Método GET que lê o arquivo de log e retorna o ranking dos jogadores de acordo com o números de kills.

[url]/game/kills/means
--------------------------------------------------------------------------------------------------
Método GET que lê o arquivo de log e retorna as partidas com a pontuação por tipo de morte.