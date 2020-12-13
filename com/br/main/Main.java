package com.br.main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Main {

	public static void main(String[] args) {
		
		//Individuo 1
		/*double minCarb = 202.50;
		double minCal = 1800;
		double maxCal = 1960;
		double maxLip = 60;*/
		
		//Individuo 2
		/*double minCarb = 236.50;
		double minCal = 2100;
		double maxCal = 2310;
		double maxLip = 60;*/
		
		//Individuo 3
		double minCarb = 337.50;
		double minCal = 3000;
		double maxCal = 3300;
		double maxLip = 100;
		
		int tamanhoPopulacao = 100;
		int numeroGeracoes = 100000;
		int numeroFilhos = 70;
		int taxaMutacao = 10;

		List<Alimento> alimentos = carregaAlimentos();
		//List<Individuo> populacao = geraPopulacaoInicialRandomico(tamanhoPopulacao, alimentos);
		List<Individuo> populacao = geraPopulacaoInicialRandomicoGruposAlimentares(tamanhoPopulacao, alimentos);
		
		System.out.println("aqui");
		
		calculaNutrientesDieta(populacao, alimentos);
		calculaFitness(populacao, minCarb, minCal, maxCal, maxLip);
		
		ag(populacao, alimentos, minCarb, minCal, maxCal, maxLip, numeroGeracoes, numeroFilhos, taxaMutacao, tamanhoPopulacao);
		
		System.out.println("FIM");

	}
	
	public static List<Alimento> carregaAlimentos() {
		List<Alimento> alimentos = new ArrayList<>();
		try {
			BufferedReader buffRead = new BufferedReader(new FileReader("Alimentos_proteinas.txt"));
			String linha;
			linha = buffRead.readLine();
			while(true) {
				if(linha != null) {
					String[] array = new String[19];
					array = linha.split(" ");
					Alimento alimento = new Alimento();
					alimento.setIndex(Integer.parseInt(array[0]));
					alimento.setEnergia(Double.parseDouble(array[1]));
					alimento.setProteina(Double.parseDouble(array[2]));
					alimento.setLipideo(Double.parseDouble(array[3]));
					alimento.setColesterol(Double.parseDouble(array[4]));
					alimento.setCarboidrato(Double.parseDouble(array[5]));
					alimento.setFibra(Double.parseDouble(array[6]));
					alimento.setCalcio(Double.parseDouble(array[7]));
					alimento.setMagnesio(Double.parseDouble(array[8]));
					alimento.setFosforo(Double.parseDouble(array[9]));
					alimento.setFerro(Double.parseDouble(array[10]));
					alimento.setSodio(Double.parseDouble(array[11]));
					alimento.setPotassio(Double.parseDouble(array[12]));
					alimento.setZinco(Double.parseDouble(array[13]));
					alimento.setRetinol(Double.parseDouble(array[14]));
					alimento.setTiamina(Double.parseDouble(array[15]));
					alimento.setRiboflavir(Double.parseDouble(array[16]));
					alimento.setVitaminaC(Double.parseDouble(array[17]));
					alimento.setGrupoAlimentar(Integer.parseInt(array[18]));
					alimentos.add(alimento);
				} else {
					break;
				}
				linha = buffRead.readLine();
			}
			buffRead.close();
			buffRead = new BufferedReader(new FileReader("Nomes_Alimentos.txt"));
			linha = buffRead.readLine();
			while(true) {
				if(linha != null) {
					String[] array = new String[2];
					array = linha.split("; ");
					for(Alimento al: alimentos) {
						if(al.getIndex() == Integer.parseInt(array[0])) {
							al.setNome(array[1]);
							break;
						}
					}
				} else {
					break;
				}
				linha = buffRead.readLine();
			}
			buffRead.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return alimentos;
	}

	public static List<Individuo> geraPopulacaoInicialRandomico(int tamanhoPopulacao, List<Alimento> alimentos) {
		List<Individuo> populacao = new ArrayList<>();
		Random rand = new Random();
		for(int i = 0; i < tamanhoPopulacao; i++) {
			Individuo individuo = new Individuo(alimentos.size());
			for(int j = 1; j <= 8; j++) {
				if(j == 1) {
					int quantidade = 6;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona > 27) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 150 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 2) {
					int quantidade = 3;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 28 || seleciona > 71) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 15 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 3) {
					int quantidade = 3;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 72 || seleciona > 115) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 70 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 4) {
					int quantidade = 1;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 116 || seleciona > 126) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 73 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 5) {
					int quantidade = 1;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 127 || (seleciona > 144 && seleciona != 158 && seleciona != 159)) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 190 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 6) {
					int quantidade = 3;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 145 || seleciona > 157) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 120 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 7) {
					int quantidade = 1;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(3);
						if(seleciona == 0) {
							seleciona = 160;
						} else if(seleciona == 1) {
							seleciona = 161;
						} else {
							seleciona = 162;
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 110 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				} else if(j == 8) {
					int quantidade = 1;
					while(quantidade > 0) {
						int seleciona = rand.nextInt(alimentos.size());
						while(seleciona < 163) {
							seleciona = rand.nextInt(alimentos.size());
						}
						int quantidadeSelecionada = 1;
						quantidade--;
						while(quantidadeSelecionada * alimentos.get(seleciona).getEnergia() <= 55 && quantidade > 0) {
							quantidadeSelecionada++;
							quantidade--;
						}
						individuo.getCromossomo()[seleciona] = quantidadeSelecionada;
					}
				}
			}
			populacao.add(individuo);
		}
		return populacao;
	}
	
	public static List<Individuo> geraPopulacaoInicialRandomicoGruposAlimentares(int tamanhoPopulacao, List<Alimento> alimentos) {
		List<Individuo> populacao = new ArrayList<>();
		Random rand = new Random();
		for(int i = 0; i < tamanhoPopulacao; i++) {
			Individuo individuo = new Individuo(alimentos.size());
			for(int j = 0; j < alimentos.size(); j++) {
				int seleciona = rand.nextInt(2);
				if(seleciona == 1) {
					individuo.getCromossomo()[j] = rand.nextInt(5) + 1;
				}
			}
			populacao.add(individuo);
		}
		return populacao;
	}
	
	public static void calculaNutrientesDieta(List<Individuo> populacao, List<Alimento> alimentos) {
		for(Individuo ind: populacao) {
			ind.calculaNutrientes(alimentos);
		}
	}
	
	public static void calculaFitness(List<Individuo> populacao, double minCarb, double minCal, double maxCal, double maxLip) {
		for(Individuo ind : populacao) {
			//ind.calculaFitness(minCarb, minCal, maxCal, maxLip);
			ind.calculaFitnessMenor(minCarb, minCal, maxCal, maxLip);
		}
	}
	
	public static void ag(List<Individuo> populacao, List<Alimento> alimentos, double minCarb, double minCal, double maxCal, double maxLip, int numeroGeracoes, int numeroFilhos, int taxaMutacao, int tamanhoPopulacao) {
		for(int i = 0; i < numeroGeracoes; i++) {
			List<Individuo> filhos = new ArrayList<>();
			for(int j = 0; j < numeroFilhos; j++) {
				int pai1 = torneio3(populacao);
				int pai2 = torneio3(populacao);
				while(pai1 == pai2) {
					pai2 = torneio3(populacao);
				}
				//crossoverDoisPontos(filhos, populacao.get(pai1), populacao.get(pai2));
				crossoverCincoPontos(filhos, populacao.get(pai1), populacao.get(pai2));
			}
			for(Individuo filho : filhos) {
				mutacao(filho, taxaMutacao);
			}
			calculaNutrientesDieta(filhos, alimentos);
			calculaFitness(filhos, minCarb, minCal, maxCal, maxLip);
			
			for(Individuo filho : filhos) {
				populacao.add(filho);
			}
			Collections.sort(populacao);
			for(int j = populacao.size()-1; j >= tamanhoPopulacao; j--) {
				populacao.remove(j);
			}
		}
	}
	
	public static int torneio3(List<Individuo> populacao) {
		Random rand = new Random();
		int sorteio1 = rand.nextInt(populacao.size());
		int sorteio2 = rand.nextInt(populacao.size());
		while(sorteio1 == sorteio2) {
			sorteio2 = rand.nextInt(populacao.size());
		}
		int sorteio3 = rand.nextInt(populacao.size());
		while(sorteio3 == sorteio1 || sorteio3 == sorteio2) {
			sorteio3 = rand.nextInt(populacao.size());
		}
		if(populacao.get(sorteio1).getFitness() <= populacao.get(sorteio2).getFitness() && populacao.get(sorteio1).getFitness() <= populacao.get(sorteio3).getFitness()) {
			return sorteio1;
		} else if(populacao.get(sorteio2).getFitness() <= populacao.get(sorteio1).getFitness() && populacao.get(sorteio2).getFitness() <= populacao.get(sorteio3).getFitness()) {
			return sorteio2;
		} else {
			return sorteio3;
		}
	}
	
	public static void crossoverDoisPontos(List<Individuo> filhos, Individuo pai1, Individuo pai2) {
		Random rand = new Random();
		Individuo filho1 = new Individuo(pai1.getCromossomo().length);
		Individuo filho2 = new Individuo(pai1.getCromossomo().length);
		int corte1 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		int corte2 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		if(corte1 == corte2) {
			corte2 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		}
		if(corte2 < corte1) {
			int aux = corte1;
			corte1 = corte2;
			corte2 = aux;
		}
		for(int i = 0; i < pai1.getCromossomo().length; i++) {
			if(i <= corte1) {
				filho1.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho2.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else if(i > corte1 && i <= corte2) {
				filho2.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho1.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else {
				filho1.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho2.getCromossomo()[i] = pai2.getCromossomo()[i];
			}
		}
		filhos.add(filho1);
		filhos.add(filho2);
	}
	
	public static void crossoverCincoPontos(List<Individuo> filhos, Individuo pai1, Individuo pai2) {
		Random rand = new Random();
		Individuo filho1 = new Individuo(pai1.getCromossomo().length);
		Individuo filho2 = new Individuo(pai1.getCromossomo().length);
		int corte1 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		int corte2 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		int corte3 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		int corte4 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		int corte5 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		while(corte1 == corte2 || corte2 == corte3 || corte2 == corte4 || corte2 == corte5) {
			corte2 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		}
		while(corte1 == corte3 || corte2 == corte3 || corte3 == corte4 || corte3 == corte5) {
			corte3 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		}
		while(corte1 == corte4 || corte2 == corte4 || corte3 == corte4 || corte5 == corte4) {
			corte4 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		}
		while(corte1 == corte5 || corte2 == corte5 || corte3 == corte5 || corte5 == corte4) {
			corte5 = rand.nextInt(pai1.getCromossomo().length - 1) + 1;
		}
		int maiorCorte = -1;
		int menorCorte = -1;
		if(corte1 < corte2 && corte1 < corte3 && corte1 < corte4 && corte1 < corte5) {
			menorCorte = corte1;
		} else if(corte2 < corte1 && corte2 < corte3 && corte2 < corte4 && corte2 < corte5) {
			menorCorte = corte2;
			corte2 = corte1;
		} else if(corte3 < corte1 && corte3 < corte2 && corte3 < corte4 && corte3 < corte5) {
			menorCorte = corte3;
			corte3 = corte1;
		} else if(corte4 < corte1 && corte4 < corte3 && corte4 < corte2 && corte4 < corte5) {
			menorCorte = corte4;
			corte4 = corte1;
		} else if(corte5 < corte1 && corte5 < corte3 && corte5 < corte4 && corte5 < corte2) {
			menorCorte = corte5;
			corte5 = corte1;
		}
		
		corte1 = menorCorte;
		
		if(corte1 > corte2 && corte1 > corte3 && corte1 > corte4 && corte1 > corte5) {
			maiorCorte = corte1;
			corte1 = corte5;
		} else if(corte2 > corte1 && corte2 > corte3 && corte2 > corte4 && corte2 > corte5) {
			maiorCorte = corte2;
			corte2 = corte5;
		} else if(corte3 > corte1 && corte3 > corte2 && corte3 > corte4 && corte3 > corte5) {
			maiorCorte = corte3;
			corte3 = corte5;
		} else if(corte4 > corte1 && corte4 > corte3 && corte4 > corte2 && corte4 > corte5) {
			maiorCorte = corte4;
			corte4 = corte5;
		} else if(corte5 > corte1 && corte5 > corte3 && corte5 > corte4 && corte5 > corte2) {
			maiorCorte = corte5;
		}
		
		corte5 = maiorCorte;
		
		if(corte2 < corte3 && corte2 < corte4) {
			menorCorte = corte2;
		} else if(corte3 < corte2 && corte3 < corte4) {
			menorCorte = corte3;
			corte3 = corte2;
		} else if(corte4 < corte2 && corte4 < corte3) {
			menorCorte = corte4;
			corte4 = corte2;
		}
		
		corte2 = menorCorte;
		
		if(corte2 > corte3 && corte2 > corte4) {
			maiorCorte = corte2;
			corte2 = corte4;
		} else if(corte3 > corte2 && corte3 > corte4) {
			maiorCorte = corte3;
			corte3 = corte4;
		} else if(corte4 > corte2 && corte4 > corte3) {
			maiorCorte = corte4;
		}
		
		corte4 = maiorCorte;
		
		if(!(corte1 < corte2 && corte2 < corte3 && corte3 < corte4 && corte4 < corte5)) {
			System.out.println("deu ruim");
		}
		for(int i = 0; i < pai1.getCromossomo().length; i++) {
			if(i <= corte1) {
				filho1.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho2.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else if(i > corte1 && i <= corte2) {
				filho2.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho1.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else if(i > corte2 && i <= corte3) {
				filho1.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho2.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else if(i > corte3 && i <= corte4) {
				filho2.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho1.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else if(i > corte4 && i <= corte5) {
				filho1.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho2.getCromossomo()[i] = pai2.getCromossomo()[i];
			} else {
				filho2.getCromossomo()[i] = pai1.getCromossomo()[i];
				filho1.getCromossomo()[i] = pai2.getCromossomo()[i];
			}
		}
		filhos.add(filho1);
		filhos.add(filho2);
	}
	
	public static void mutacao(Individuo ind, int taxaMutacao) {
		Random rand = new Random();
		for(int i = 0; i < ind.getCromossomo().length; i++) {
			int chance = rand.nextInt(101);
			if(chance < taxaMutacao) {
				if(ind.getCromossomo()[i] == 0) {
					ind.getCromossomo()[i]++;
				} else if(ind.getCromossomo()[i] == 5) {
					ind.getCromossomo()[i]--;
				} else {
					int aumentaDiminui = rand.nextInt(2);
					if(aumentaDiminui == 0) {
						ind.getCromossomo()[i]--;
					} else {
						ind.getCromossomo()[i]++;
					}
				}
			}
		}
	}
}
