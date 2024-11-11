package moeda;

import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;



public class ComverteMoeda {

	// Tabela de taxas de câmbio fictícias (1 unidade da moeda para reais BRL)
	private static final Map<String, Double> TAXAS_DE_CAMBIO = new HashMap<>();

	static {
		// Inicializando as taxas de câmbio fictícias
		TAXAS_DE_CAMBIO.put("USD", 5.77); // Dólar para real
		TAXAS_DE_CAMBIO.put("EUR", 6.15); // Euro para real
		TAXAS_DE_CAMBIO.put("JPY", 0.036); // Iene para real
		TAXAS_DE_CAMBIO.put("GBP", 7.42); // Libra para real
	}

	public static void main(String[] args) {
		// Exibir mensagem de boas-vindas e moedas disponíveis
		JOptionPane.showMessageDialog(null, "Bem-vindo ao Conversor de Moedas!"
				+ "\nMoedas Disponíveis: USD (Dólar), EUR (Euro), JPY (Iene), GBP (Libra)");

		// Escolha da moeda de origem
		String moedaOrigem = JOptionPane.showInputDialog("Escolha a moeda de origem (ex: USD):");
		if (moedaOrigem != null) {
			moedaOrigem = moedaOrigem.toUpperCase();
		}

		// Escolha da moeda de destino
		String moedaDestino = JOptionPane.showInputDialog("Escolha a moeda de destino (ex: BRL para Reais):");
		if (moedaDestino != null) {
			moedaDestino = moedaDestino.toUpperCase();
		}

		// Verificação se as moedas são válidas
		if (!TAXAS_DE_CAMBIO.containsKey(moedaOrigem)
				|| (!moedaDestino.equals("BRL") && !TAXAS_DE_CAMBIO.containsKey(moedaDestino))) {
			JOptionPane.showMessageDialog(null, "Moeda inválida. Por favor, tente novamente.");
			return;
		}

		// Solicitar valor a ser convertido
		String valorStr = JOptionPane.showInputDialog("Digite o valor a ser convertido:");
		double valor = 0.0;

		try {
			valor = Double.parseDouble(valorStr);
		} catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Valor inválido. Por favor, insira um número válido.");
			return;
		}

		// Converter o valor
		double valorConvertido = converteMoeda(valor, moedaOrigem, moedaDestino);

		// Exibir o resultado
		JOptionPane.showMessageDialog(null, String.format("Resultado: %.2f %s é igual a %.2f %s.", valor, moedaOrigem,
				valorConvertido, moedaDestino));
	}

	/**
	 * Método para converter valores entre moedas.
	 *
	 * @param valor        O valor a ser convertido
	 * @param moedaOrigem  A moeda de origem
	 * @param moedaDestino A moeda de destino
	 * @return O valor convertido
	 */
	public static double converteMoeda(double valor, String moedaOrigem, String moedaDestino) {
		// Converter o valor para reais
		double taxaOrigemParaBRL = TAXAS_DE_CAMBIO.get(moedaOrigem);
		double valorEmBRL = valor * taxaOrigemParaBRL;

		// Se a moeda de destino é BRL, retorna o valor em reais
		if (moedaDestino.equals("BRL")) {
			return valorEmBRL;
		}

		// Caso contrário, converte de reais para a moeda de destino
		double taxaDestinoParaBRL = TAXAS_DE_CAMBIO.get(moedaDestino);
		return valorEmBRL / taxaDestinoParaBRL;
	}
}
