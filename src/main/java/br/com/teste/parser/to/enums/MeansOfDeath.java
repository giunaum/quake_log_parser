package br.com.teste.parser.to.enums;

/**
 * Enum que representa as mortes do jogo.
 */
public enum MeansOfDeath {
	MOD_UNKNOWN(0),
	MOD_SHOTGUN(1),
	MOD_GAUNTLET(2),
	MOD_MACHINEGUN(3),
	MOD_GRENADE(4),
	MOD_GRENADE_SPLASH(5),
	MOD_ROCKET(6),
	MOD_ROCKET_SPLASH(7),
	MOD_PLASMA(8),
	MOD_PLASMA_SPLASH(9),
	MOD_RAILGUN(10),
	MOD_LIGHTNING(11),
	MOD_BFG(12),
	MOD_BFG_SPLASH(13),
	MOD_WATER(14),
	MOD_SLIME(15),
	MOD_LAVA(16),
	MOD_CRUSH(17),
	MOD_TELEFRAG(18),
	MOD_FALLING(19),
	MOD_SUICIDE(20),
	MOD_TARGET_LASER(21),
	MOD_TRIGGER_HURT(22),
	MISSIONPACK(23),
	MOD_NAIL(24),
	MOD_CHAINGUN(25),
	MOD_PROXIMITY_MINE(26),
	MOD_KAMIKAZE(27),
	MOD_JUICED(28),
	MOD_GRAPPLE(29);

	private final int id;

	/**
	 * Construtor da classe.
	 *
	 * @param id
	 */
	MeansOfDeath(final int id) {
		this.id = id;
	}

	/**
	 * Recupera o {@link MeansOfDeath} conforme o valor fornecido.
	 *
	 * @param value
	 * @return
	 */
	public static MeansOfDeath getByValue(final int value) {
		for (final MeansOfDeath meansOfDeath : values()) {
			if (value == meansOfDeath.getId()) {
				return meansOfDeath;
			}
		}

		return null;
	}

	/**
	 * Recupera o {@link MeansOfDeath} conforme o valor fornecido.
	 *
	 * @param value
	 * @return
	 */
	public static MeansOfDeath getByValue(final String value) {
		for (final MeansOfDeath meansOfDeath : values()) {
			if (value.equals(meansOfDeath.toString())) {
				return meansOfDeath;
			}
		}

		return null;
	}

	public int getId() {
		return this.id;
	}
}