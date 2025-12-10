package inimigos;

import gerenciadores.GerenciadorInimigos;
import static ajuda.Constantes.Inimigos.LOBO;

public class Lobo extends Inimigo {

	public Lobo(float x, float y, int ID, GerenciadorInimigos gerenciadorInimigos) {
		super(x, y, ID, LOBO, gerenciadorInimigos);
	}
}
