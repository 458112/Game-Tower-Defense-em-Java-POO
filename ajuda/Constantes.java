package ajuda;

public class Constantes {

	public static class Projeteis {
		public static final int FLECHA = 0;
		public static final int CORRENTE = 1;
		public static final int BOMBA = 2;

		public static float GetVelocidade(int tipo) {
			switch (tipo) {
			case FLECHA:
				return 8f;
			case BOMBA:
				return 4f;
			case CORRENTE:
				return 6f;
			}
			return 0f;
		}
	}

	public static class Torres {
		public static final int CANHAO = 0;
		public static final int ARQUEIRO = 1;
		public static final int MAGO = 2;
		
		public static int GetCustoTorre(int tipoTorre) {
			switch (tipoTorre) {
			case CANHAO:
				return 65;
			case ARQUEIRO:
				return 35;
			case MAGO:
				return 50;
			}
			return 0;
		}

		public static String GetNome(int tipoTorre) {
			switch (tipoTorre) {
			case CANHAO:
				return "Canhão";
			case ARQUEIRO:
				return "Arqueiro";
			case MAGO:
				return "Mago";
			}
			return "";
		}

		public static int GetDanoInicial(int tipoTorre) {
			switch (tipoTorre) {
			case CANHAO:
				return 15;
			case ARQUEIRO:
				return 5;
			case MAGO:
				return 0;
			}
			return 0;
		}

		public static float GetAlcancePadrao(int tipoTorre) {
			switch (tipoTorre) {
			case CANHAO:
				return 75;
			case ARQUEIRO:
				return 120;
			case MAGO:
				return 100;
			}
			return 0;
		}

		public static float GetTempoRecargaPadrao(int tipoTorre) {
			switch (tipoTorre) {
			case CANHAO:
				return 120;
			case ARQUEIRO:
				return 35;
			case MAGO:
				return 50;
			}
			return 0;
		}
	}

	public static class Direcao {
		public static final int ESQUERDA = 0;
		public static final int CIMA = 1;
		public static final int DIREITA = 2;
		public static final int BAIXO = 3;
	}

	public static class Inimigos {
		public static final int ORC = 0;
		public static final int MORCEGO = 1;
		public static final int CAVALEIRO = 2;
		public static final int LOBO = 3;
		
		public static int GetRecompensa(int tipoInimigo) {
			switch (tipoInimigo) {
			case ORC:
				return 5;
			case MORCEGO:
				return 5;
			case CAVALEIRO:
				return 25;
			case LOBO:
				return 10;
			}
			return 0;
		}

		public static float GetVelocidade(int tipoInimigo) {
			switch (tipoInimigo) {
			case ORC:
				return 0.5f;
			case MORCEGO:
				return 0.7f;
			case CAVALEIRO:
				return 0.45f;
			case LOBO:
				return 0.85f;
			}
			return 0;
		}

		public static int GetVidaInicial(int tipoInimigo) {
			switch (tipoInimigo) {
			case ORC:
				return 85;
			case MORCEGO:
				return 100;
			case CAVALEIRO:
				return 400;
			case LOBO:
				return 125;
			}
			return 0;
		}
	}

	public static class Blocos {
		public static final int BLOCO_AGUA = 0;
		public static final int BLOCO_GRAMA = 1;
		public static final int BLOCO_ESTRADA = 2;
	}

}
