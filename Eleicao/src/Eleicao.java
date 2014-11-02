import java.text.DecimalFormat;
import java.util.Scanner;

public class Eleicao {
	static Scanner e = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#0");

	public static void main(String[] args) {
		String[] candidatos = new String[] { "Aécio", "Dilma", "Marina" };
		String[] regiao = new String[] { "Sul", "Sudeste", "Centro Oeste",
				"Norte", "Nordeste" };

		int[][] totalVotos = captacaoVotos(candidatos, regiao);

		System.out.println("---------------Porcentagem dos Candidatos no total de votos-----------------");
	    double[] porceVoto = porcetagemVotos(totalVotos);

		for (int i = 0; i < 3; i++) {
			System.out.println(candidatos[i] + " = " + df.format(porceVoto[i]) + "%");
		}

		System.out.println("--------------Candidato mais votado por região---------");
		maisVotadoRegiao(regiao, candidatos, totalVotos);
		System.out.println("----------------2°-Turno-------------");
		ranking(candidatos, porceVoto);
	}

	public static int[][] captacaoVotos(String[] candidatos, String[] regiao) {
		int[][] votos = new int[5][3];

		for (int reg = 0; reg < 5; reg++) {
			for (int cand = 0; cand < 3; cand++) {
				System.out.print("Digite os votos do candidato: "
						+ candidatos[cand] + " - para a região " + regiao[reg]);
				votos[reg][cand] = e.nextInt();
			}
		}

		return votos;
	}

	public static double[] porcetagemVotos(int[][] totalVotos) {
		double[] porce = new double[4];
		double tot = 0, candidato = 0, soma = 0;
		for (int cand = 0; cand < 3; cand++) {
			for (int reg = 0; reg < 5; reg++) {
				tot += totalVotos[reg][cand];
				
			}

		}
		for (int cand = 0; cand < 3; cand++) {
			soma = 0;
			for (int reg = 0; reg < 5; reg++) {
				soma += totalVotos[reg][cand];

			}
			candidato = soma / tot * 100;
			porce[cand] = candidato;
			

		}
		

		return porce;
	}

	public static void maisVotadoRegiao(String[] regiao, String[] candidatos,
			int[][] totalVotos) {
		int melhorV = 0;
		int candidato = 0;

		for (int reg = 0; reg < 5; reg++) {
			melhorV = 0;
			for (int cand = 0; cand < 3; cand++) {
				if (totalVotos[reg][cand] > melhorV) {
					melhorV = totalVotos[reg][cand];
					candidato = cand;
				}
			}
			System.out.println("Regiao  " + regiao[reg]
					+ " Candidato mais votado - "
					+ candidatos[candidato].toUpperCase());
		}

	}

	public static void ranking(String[] candidatos, double[] porceVoto) {
		double auxP = 0;
		String auxC = null;
		for (int cand = 0; cand < 3; cand++) {
			for (int compa = 0; compa < 2; compa++) {
				if (porceVoto[compa] < porceVoto[compa + 1]) {
					auxP = porceVoto[compa];
					auxC = candidatos[compa];

					porceVoto[compa] = porceVoto[compa + 1];
					candidatos[compa] = candidatos[compa + 1];

					porceVoto[compa + 1] = auxP;
					candidatos[compa + 1] = auxC;

				}

			}
		}

		for (int i = 0; i < 2; i++) {
			System.out.println("Candidato: - " + candidatos[i] + " com "
					+ df.format(porceVoto[i]) + "% dos votos");
		}

	}
}
