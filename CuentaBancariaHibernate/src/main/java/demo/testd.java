package demo;

import resources.Estadisticas;

public class testd {

	public static void main(String[] args) {
		Estadisticas estadisticasA = new Estadisticas();
		estadisticasA.setContadorInternoClientesEncontrados(1);

		Estadisticas estadisticasB = new Estadisticas();
		estadisticasB.setContadorInternoClientesEncontrados(7);

		System.out.println("A:" + estadisticasA + "\n B: " + estadisticasB);

		estadisticasA = estadisticasB;

		System.out.println("A:" + estadisticasA + "\n B: " + estadisticasB);

		estadisticasB.setContadorInternoClientesEncontrados(5);

		System.out.println("A:" + estadisticasA + "\n B: " + estadisticasB);

		estadisticasA = (Estadisticas) estadisticasB.clone();

		System.out.println("A:" + estadisticasA + "\n B: " + estadisticasB);

		estadisticasB.setContadorInternoClientesEncontrados(8);

		System.out.println("A:" + estadisticasA + "\n B: " + estadisticasB);

		estadisticasB = null;

	}

}
