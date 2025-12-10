package inimigos;

import gerenciadores.GerenciadorInimigos;
import static ajuda.Constantes.Inimigos.MORCEGO;

public class Morcego extends Inimigo {

	public Morcego(float x, float y, int ID, GerenciadorInimigos gerenciadorInimigos) {
		super(x, y, ID, MORCEGO, gerenciadorInimigos);
	}
}
