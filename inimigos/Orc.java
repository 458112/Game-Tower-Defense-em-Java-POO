package inimigos;

import gerenciadores.GerenciadorInimigos;
import static ajuda.Constantes.Inimigos.ORC;

public class Orc extends Inimigo {

	public Orc(float x, float y, int ID, GerenciadorInimigos gerenciadorInimigos) {
		super(x, y, ID, ORC, gerenciadorInimigos);
	}
}
