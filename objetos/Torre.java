package objetos;

public class Torre {

	private int x, y, id, tipoTorre, cdTick, dano;
	private float alcance, tempoRecarga;
	private int tier;

	public Torre(int x, int y, int id, int tipoTorre) {
		this.x = x;
		this.y = y;
		this.id = id;
		this.tipoTorre = tipoTorre;
		tier = 1;
		definirDanoInicial();
		definirAlcancePadrao();
		definirTempoRecargaPadrao();
	}

	public void atualizar() {
		cdTick++;
	}

	public void atualizarTorre() {
		this.tier++;

		switch (tipoTorre) {
		case ajuda.Constantes.Torres.ARQUEIRO:
			dano += 2;
			alcance += 20;
			tempoRecarga -= 5;
			break;
		case ajuda.Constantes.Torres.CANHAO:
			dano += 5;
			alcance += 20;
			tempoRecarga -= 15;
			break;
		case ajuda.Constantes.Torres.MAGO:
			alcance += 20;
			tempoRecarga -= 10;
			break;
		}
	}

	public boolean isCooldownOver() {
		return cdTick >= tempoRecarga;
	}

	public void resetCooldown() {
		cdTick = 0;
	}

	private void definirTempoRecargaPadrao() {
		tempoRecarga = ajuda.Constantes.Torres.GetTempoRecargaPadrao(tipoTorre);
	}

	private void definirAlcancePadrao() {
		alcance = ajuda.Constantes.Torres.GetAlcancePadrao(tipoTorre);
	}

	private void definirDanoInicial() {
		dano = ajuda.Constantes.Torres.GetDanoInicial(tipoTorre);
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTipoTorre() {
		return tipoTorre;
	}

	public void setTipoTorre(int tipoTorre) {
		this.tipoTorre = tipoTorre;
	}

	public int getDano() {
		return dano;
	}

	public float getAlcance() {
		return alcance;
	}

	public float getTempoRecarga() {
		return tempoRecarga;
	}
	
	public int getTier() {
		return tier;
	}
}
