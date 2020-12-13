package com.br.main;

import java.util.List;

public class Individuo implements Comparable<Individuo> {
	private int[] cromossomo;
	private double fitness = 0;
	private double energia = 0;
	private double proteina = 0;
	private double lipideo = 0;
	private double colesterol = 0;
	private double carboidrato = 0;
	private double fibra = 0;
	private double calcio = 0;
	private double magnesio = 0;
	private double fosforo = 0;
	private double ferro = 0;
	private double sodio = 0;
	private double potassio = 0;
	private double zinco = 0;
	private double retinol = 0;
	private double tiamina = 0;
	private double riboflavir = 0;
	private double vitaminaC = 0;
	
	@Override 
	public int compareTo(Individuo ind) { 
		if (this.fitness < ind.getFitness()) { 
			return -1; 
		} if (this.fitness > ind.getFitness()) { 
			return 1; 
		} 
		return 0; 
	 }
	
	public Individuo(int numeroCromossoso) {
		this.cromossomo = new int[numeroCromossoso];
	}
	
	public double getFitness() {
		return fitness;
	}
	public void setFitness(double fitness) {
		this.fitness = fitness;
	}
	public int[] getCromossomo() {
		return cromossomo;
	}
	public void setCromossomo(int[] cromossomo) {
		this.cromossomo = cromossomo;
	}
	public double getEnergia() {
		return energia;
	}
	public void setEnergia(double energia) {
		this.energia = energia;
	}
	public double getProteina() {
		return proteina;
	}
	public void setProteina(double proteina) {
		this.proteina = proteina;
	}
	public double getLipideo() {
		return lipideo;
	}
	public void setLipideo(double lipideo) {
		this.lipideo = lipideo;
	}
	public double getColesterol() {
		return colesterol;
	}
	public void setColesterol(double colesterol) {
		this.colesterol = colesterol;
	}
	public double getCarboidrato() {
		return carboidrato;
	}
	public void setCarboidrato(double carboidrato) {
		this.carboidrato = carboidrato;
	}
	public double getFibra() {
		return fibra;
	}
	public void setFibra(double fibra) {
		this.fibra = fibra;
	}
	public double getCalcio() {
		return calcio;
	}
	public void setCalcio(double calcio) {
		this.calcio = calcio;
	}
	public double getMagnesio() {
		return magnesio;
	}
	public void setMagnesio(double magnesio) {
		this.magnesio = magnesio;
	}
	public double getFosforo() {
		return fosforo;
	}
	public void setFosforo(double fosforo) {
		this.fosforo = fosforo;
	}
	public double getFerro() {
		return ferro;
	}
	public void setFerro(double ferro) {
		this.ferro = ferro;
	}
	public double getSodio() {
		return sodio;
	}
	public void setSodio(double sodio) {
		this.sodio = sodio;
	}
	public double getPotassio() {
		return potassio;
	}
	public void setPotassio(double potassio) {
		this.potassio = potassio;
	}
	public double getZinco() {
		return zinco;
	}
	public void setZinco(double zinco) {
		this.zinco = zinco;
	}
	public double getRetinol() {
		return retinol;
	}
	public void setRetinol(double retinol) {
		this.retinol = retinol;
	}
	public double getTiamina() {
		return tiamina;
	}
	public void setTiamina(double tiamina) {
		this.tiamina = tiamina;
	}
	public double getRiboflavir() {
		return riboflavir;
	}
	public void setRiboflavir(double riboflavir) {
		this.riboflavir = riboflavir;
	}
	public double getVitaminaC() {
		return vitaminaC;
	}
	public void setVitaminaC(double vitaminaC) {
		this.vitaminaC = vitaminaC;
	}
	
	public void calculaNutrientes(List<Alimento> alimentos) {
		for(int i = 0; i < this.cromossomo.length; i++) {
			this.energia += cromossomo[i] * alimentos.get(i).getEnergia();
			this.proteina += cromossomo[i] * alimentos.get(i).getProteina();
			this.lipideo += cromossomo[i] * alimentos.get(i).getLipideo();
			this.colesterol += cromossomo[i] * alimentos.get(i).getColesterol();
			this.carboidrato += cromossomo[i] * alimentos.get(i).getCarboidrato();
			this.fibra += cromossomo[i] * alimentos.get(i).getFibra();
			this.calcio += cromossomo[i] * alimentos.get(i).getCalcio();
			this.magnesio += cromossomo[i] * alimentos.get(i).getMagnesio();
			this.fosforo += cromossomo[i] * alimentos.get(i).getFosforo();
			this.ferro += cromossomo[i] * alimentos.get(i).getFerro();
			this.sodio += cromossomo[i] * alimentos.get(i).getSodio();
			this.potassio += cromossomo[i] * alimentos.get(i).getPotassio();
			this.zinco += cromossomo[i] * alimentos.get(i).getZinco();
			this.retinol += cromossomo[i] * alimentos.get(i).getRetinol();
			this.tiamina += cromossomo[i] * alimentos.get(i).getTiamina();
			this.riboflavir += cromossomo[i] * alimentos.get(i).getRiboflavir();
			this.vitaminaC += cromossomo[i] * alimentos.get(i).getVitaminaC();
		}
	}
	
	public void calculaFitness(double minCarb, double minCal, double maxCal, double maxLip) {
		fitness = carboidrato;
		if(this.carboidrato < minCarb) {
			fitness += (minCarb - this.carboidrato)*carboidrato;
		}
		if(this.colesterol > 290) {
			fitness += (this.colesterol - 290)*this.colesterol;
		}
		if(this.sodio > 2000) {
			fitness += (this.sodio - 2000) * this.sodio;
		}
		if(this.proteina < 28.4) {
			fitness += (28.4 - this.proteina) * this.proteina * 100;
		}
		if(this.proteina > 38.4) {
			fitness += (this.proteina - 38.4) * this.proteina * 100;
		}
		if(this.lipideo > maxLip) {
			fitness += (this.lipideo - maxLip) * this.lipideo;
		}
		if(this.fibra < 30) {
			fitness += (30 - this.fibra) * this.fibra;
		}
		if(this.calcio < 850) {
			fitness += (850 - this.calcio) * this.calcio;
		}
		if(this.magnesio < 400) {
			fitness += (400 - this.magnesio) * this.magnesio;
		}
		if(this.fosforo < 700) {
			fitness += (700 - this.fosforo) * this.fosforo;
		}
		if(this.ferro < 18) {
			fitness += (18 - this.ferro) * this.ferro * 100;
		}
		if(this.potassio < 3500) {
			fitness += (3500 - this.potassio) * this.potassio;
		}
		if(this.zinco < 15) {
			fitness += (15 - this.zinco) * this.zinco * 100;
		}
		if(this.retinol < 80) {
			fitness += (80 - this.retinol) * this.retinol * 100;
		}
		if(this.tiamina < 0.98) {
			fitness += (0.98 - this.tiamina) * this.tiamina;
		}
		if(this.riboflavir < 1.35) {
			fitness += (1.35 - this.riboflavir) * this.riboflavir;
		}
		if(this.vitaminaC < 200) {
			fitness += (200 - this.vitaminaC) * this.vitaminaC;
		}
		if(this.energia < minCal) {
			fitness += (minCal - this.energia) * this.energia;
		}
		if(this.energia > maxCal) {
			fitness += (this.energia - maxCal) * this.energia;
		}
	}
	
	public void calculaFitnessMenor(double minCarb, double minCal, double maxCal, double maxLip) {
		fitness = carboidrato;
		if(this.carboidrato < minCarb) {
			fitness += (minCarb - this.carboidrato);
		}
		if(this.colesterol > 290) {
			fitness += (this.colesterol - 290);
		}
		if(this.sodio > 2000) {
			fitness += (this.sodio - 2000);
		}
		if(this.proteina < 28.4) {
			fitness += (28.4 - this.proteina) * 100;
		}
		if(this.proteina > 38.4) {
			fitness += (this.proteina - 38.4) * 100;
		}
		if(this.lipideo > maxLip) {
			fitness += (this.lipideo - maxLip);
		}
		if(this.fibra < 30) {
			fitness += (30 - this.fibra);
		}
		if(this.calcio < 850) {
			fitness += (850 - this.calcio);
		}
		if(this.magnesio < 400) {
			fitness += (400 - this.magnesio);
		}
		if(this.fosforo < 700) {
			fitness += (700 - this.fosforo);
		}
		if(this.ferro < 18) {
			fitness += (18 - this.ferro) * 100;
		}
		if(this.potassio < 3500) {
			fitness += (3500 - this.potassio);
		}
		if(this.zinco < 15) {
			fitness += (15 - this.zinco) * 100;
		}
		if(this.retinol < 80) {
			fitness += (80 - this.retinol) * 100;
		}
		if(this.tiamina < 0.98) {
			fitness += (0.98 - this.tiamina);
		}
		if(this.riboflavir < 1.35) {
			fitness += (1.35 - this.riboflavir);
		}
		if(this.vitaminaC < 200) {
			fitness += (200 - this.vitaminaC);
		}
		if(this.energia < minCal) {
			fitness += (minCal - this.energia);
		}
		if(this.energia > maxCal) {
			fitness += (this.energia - maxCal);
		}
	}
	
	}
