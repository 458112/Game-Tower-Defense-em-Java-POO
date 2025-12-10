package cenas;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import principal.Jogo;
import ui.MeuBotao;
import static principal.EstadosJogo.*;

public class FimDeJogo extends CenaJogo implements MetodosCena {

	private MeuBotao bReiniciar, bMenu;

	public FimDeJogo(Jogo jogo) {
		super(jogo);
		iniciarBotoes();
	}

	private void iniciarBotoes() {

		int w = 150;
		int h = w / 3;
		int x = 640 / 2 - w / 2;
		int y = 300;
		int yOffset = 100;

		bMenu = new MeuBotao("Menu", x, y, w, h);
		bReiniciar = new MeuBotao("Reiniciar", x, y + yOffset, w, h);

	}

	@Override
	public void renderizar(Graphics g) {
		
		g.setFont(new Font("LucidaSans", Font.BOLD, 50));
		g.setColor(Color.red);
		g.drawString("Fim de Jogo!", 160, 80);

		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		bMenu.desenhar(g);
		bReiniciar.desenhar(g);
	}

	private void reiniciarJogo() {
		resetarTudo();
		DefinirEstadoJogo(JOGANDO);
	}

	private void resetarTudo() {
		jogo.getJogando().resetarTudo();
	}

	@Override
	public void mouseClicado(int x, int y) {
		if (bMenu.getLimites().contains(x, y)) {
			DefinirEstadoJogo(MENU);
			resetarTudo();
		} else if (bReiniciar.getLimites().contains(x, y))
			reiniciarJogo();
	}

	@Override
	public void mouseMovido(int x, int y) {
		bMenu.setMouseSobre(false);
		bReiniciar.setMouseSobre(false);

		if (bMenu.getLimites().contains(x, y))
			bMenu.setMouseSobre(true);
		else if (bReiniciar.getLimites().contains(x, y))
			bReiniciar.setMouseSobre(true);
	}

	@Override
	public void mousePressionado(int x, int y) {
		if (bMenu.getLimites().contains(x, y))
			bMenu.setMousePressionado(true);
		else if (bReiniciar.getLimites().contains(x, y))
			bReiniciar.setMousePressionado(true);

	}

	@Override
	public void mouseSolto(int x, int y) {
		bMenu.resetarBooleanos();
		bReiniciar.resetarBooleanos();

	}

	@Override
	public void mouseArrastado(int x, int y) {
		
	}

    @Override
    public void teclaPressionada(java.awt.event.KeyEvent e) {
        //TODO Auto-generated method stub
}

}
