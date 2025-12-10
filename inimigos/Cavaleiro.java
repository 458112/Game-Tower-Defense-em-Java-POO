package inimigos;

import gerenciadores.GerenciadorInimigos;
import static ajuda.Constantes.Inimigos.CAVALEIRO;

public class Cavaleiro extends Inimigo {

	public Cavaleiro(float x, float y, int ID, GerenciadorInimigos gerenciadorInimigos) {
		super(x, y, ID, CAVALEIRO, gerenciadorInimigos);
	}
}
