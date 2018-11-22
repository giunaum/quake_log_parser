package br.com.teste.parser.util;

import br.com.teste.parser.exceptions.UtilException;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.text.Normalizer;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Classe utilitaria
 */
public class Util {

	/**
	 * Construtor privado para garantir que esta classe não seja instanciada.
	 */
	private Util() {
		throw new IllegalStateException("Classe utilitária! Não pode ser instanciada.");
	}

	/**
	 * Verifica se a {@link String} informada é nula, ou vazia.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isEmpty(String valor) {
		return valor == null || "".equals(valor);
	}

	/**
	 * Verifica se a {@link Collection} informada é nula, ou vazia.
	 *
	 * @param collection
	 * @return
	 */
	public static boolean isEmpty(Collection<?> collection) {
		return collection == null || collection.isEmpty();
	}

	/**
	 * Verifica se a {@link Map} informada é nula, ou vazia.
	 *
	 * @param c
	 * @return
	 */
	public static boolean isEmpty(Map<?, ?> c) {
		return c == null || c.isEmpty();
	}

	/**
	 * Verifica se o {@link Integer} informado é nulo, ou igual a zero.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isEmpty(Integer valor) {
		return valor == null || BigInteger.ZERO.intValue() == valor.intValue();
	}

	/**
	 * Verifica se o {@link Long} informado é nulo, ou igual a zero.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isEmpty(Long valor) {
		return valor == null || BigInteger.ZERO.longValue() == valor.longValue();
	}

	/**
	 * Verifica se o {@link Float} informado é nulo, ou igual a zero.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isEmpty(Float valor) {
		return valor == null || BigInteger.ZERO.floatValue() == valor.floatValue();
	}

	/**
	 * Verifica se o {@link Double} informado é nulo, ou igual a zero.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isEmpty(Double valor) {
		return valor == null || BigInteger.ZERO.doubleValue() == valor.doubleValue();
	}

	/**
	 * Verifica se a {@link String} informada não esta vazia, desconsiderando espaços no momento da validação.
	 *
	 * @param valor
	 * @return
	 */
	public static boolean isBlank(final String valor) {
		return isEmpty(valor) || valor.trim().length() == BigInteger.ZERO.intValue();
	}

	/**
	 * Remove acentos, cedilha e qualquer caracter especial fora do ASCII.
	 *
	 * @param entrada
	 * @return
	 */
	public static String normalizarString(String entrada) {
		if (isEmpty(entrada)) return entrada;
		entrada = Normalizer.normalize(entrada, Normalizer.Form.NFD);
		return entrada.replaceAll("[^\\p{ASCII}]", "");
	}

	/**
	 * Formata a {@link String} conforme os parâmetros fornecidos.
	 *
	 * @param isConcatenar
	 * @param entrada
	 * @param parametros
	 * @return
	 */
	public static String formatarString(boolean isConcatenar, String entrada, String... parametros) {
		String stringFormatada = entrada;

		if (!isBlank(entrada)) {
			if (isConcatenar) {
				stringFormatada = getStringComParametrosConcatenados(stringFormatada, parametros);
			} else {
				stringFormatada = MessageFormat.format(stringFormatada, parametros);
			}
		}

		return stringFormatada;
	}

	/**
	 * Inclui os parâmetros Concatenados conforme os parâmetros fornecidos.
	 *
	 * @param entrada
	 * @param parametros
	 * @return
	 */
	private static String getStringComParametrosConcatenados(String entrada, String... parametros) {
		String stringFormatada = entrada;

		String parametrosConcatenados = getParametrosConcatenados();
		if (parametrosConcatenados.trim().length() > BigInteger.ZERO.intValue()) {
			stringFormatada = MessageFormat.format(stringFormatada, parametros);
		}
		return stringFormatada;
	}

	/**
	 * Retorna os parâmetros separados por virgula concatenados em uma {@link String}.
	 *
	 * @param parametros
	 * @return
	 */
	private static String getParametrosConcatenados(String... parametros) {
		StringBuilder parametrosConcatenados = new StringBuilder();

		boolean isVirgula = false;
		for (String parametro : parametros) {
			if (isVirgula) {
				parametrosConcatenados.append(", ");
			}

			parametrosConcatenados.append(parametro);
			isVirgula = true;
		}

		return parametrosConcatenados.toString();
	}

	/**
	 * Retornar a {@link String} conforme os paramentros.
	 *
	 * @param paramentros
	 * @return
	 */
	public static String getStringComEspacos(String... paramentros) {
		StringBuilder string = new StringBuilder();
		Arrays.asList(paramentros).forEach(paramentro -> {
			if (Util.isEmpty(string.toString())) {
				string.append(paramentro);
			} else {
				string.append(" ").append(paramentro);
			}
		});

		return string.toString();
	}

	/**
	 * Recupera as linhas do arquivo.
	 *
	 * @param fileName
	 * @return
	 * @throws UtilException
	 */
	public static List<String> getLines(final String fileName) throws UtilException {
		Path path = Paths.get(fileName);
		try (Stream<String> input = Files.lines(path)) {
			return input.collect(Collectors.toList());
		} catch (IOException e) {
			throw new UtilException("Falha ao ler o arquivo", e);
		}
	}

	/**
	 * Retorna a {@link String} conforme o regex informado.
	 *
	 * @param regex
	 * @param line
	 * @return
	 */
	public static Matcher getMatcher(final String regex, final String line) {
		Pattern pattern = Pattern.compile(regex);
		return pattern.matcher(line);
	}
}
