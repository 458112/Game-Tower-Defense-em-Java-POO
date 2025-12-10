package inimigos;

import java.awt.Rectangle;
import gerenciadores.GerenciadorInimigos;
import static ajuda.Constantes.Direcao.*;

public abstract class Inimigo {

	protected GerenciadorInimigos gerenciadorInimigos;
	protected float x, y;
	protected Rectangle limites;
	protected int vida;
	protected int vidaMaxima;
	protected int ID;
	protected int tipoInimigo;
	protected int ultimaDirecao;
	protected boolean vivo = true;
	protected int limiteTickLento = 120;
	protected int tickLento = limiteTickLento;

	public Inimigo(float x, float y, int ID, int tipoInimigo, GerenciadorInimigos gerenciadorInimigos) {
		this.x = x;
		this.y = y;
		this.ID = ID;
		this.tipoInimigo = tipoInimigo;
		this.gerenciadorInimigos = gerenciadorInimigos;
		limites = new Rectangle((int) x, (int) y, 32, 32);
		ultimaDirecao = -1;
		definirVidaInicial();
	}

	private void definirVidaInicial() {
		vida = ajuda.Constantes.Inimigos.GetVidaInicial(tipoInimigo);
		vidaMaxima = vida;
	}

	public void sofrerDano(int dano) {
		this.vida -= dano;
		if (vida <= 0) {
			vivo = false;
			gerenciadorInimigos.recompensarJogador(tipoInimigo);
		}
	}

	public void matar() {
		vivo = false;
		vida = 0;
	}

	public void sofrerLentidao() {
		tickLento = 0;
	}

	public void mover(float velocidade, int direcao) {
		ultimaDirecao = direcao;

		if (tickLento < limiteTickLento) {
			tickLento++;
			velocidade *= 0.5f;
		}

		switch (direcao) {
		case ESQUERDA:
			this.x -= velocidade;
			break;
		case CIMA:
			this.y -= velocidade;
			break;
		case DIREITA:
			this.x += velocidade;
			break;
		case BAIXO:
			this.y += velocidade;
			break;
		}

		atualizarLimites();
	}

	private void atualizarLimites() {
		limites.x = (int) x;
		limites.y = (int) y;
	}

	public void definirPosicao(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public float getBarraDeVidaFloat() {
		return vida / (float) vidaMaxima;
	}

	public float getX() {
		return x;
	}

	public float getY() {
		return y;
	}

	public Rectangle getLimites() {
		return limites;
	}

	public int getVida() {
		return vida;
	}

	public int getID() {
		return ID;
	}

	public int getTipoInimigo() {
		return tipoInimigo;
	}

	public int getUltimaDirecao() {
		return ultimaDirecao;
	}

	public boolean estaVivo() {
		return vivo;
	}

	public boolean estaLento() {
		return tickLento < limiteTickLento;
	}
}
