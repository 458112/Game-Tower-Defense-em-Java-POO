package cenas;

import static principal.EstadosJogo.*;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

import principal.Jogo;
import ui.MeuBotao;

public class Configuracoes extends CenaJogo implements MetodosCena {

    private MeuBotao bMenu;

    public Configuracoes(Jogo jogo) {
        super(jogo);
        iniciarBotoes();
    }

    private void iniciarBotoes() {
        bMenu = new MeuBotao("Menu", 2, 2, 100, 30);
    }

    @Override
    public void renderizar(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(0, 0, 640, 640);

        desenharBotoes(g);
    }

    private void desenharBotoes(Graphics g) {
        bMenu.desenhar(g);
    }

    @Override
    public void mouseClicado(int x, int y) { 
        if (bMenu.getLimites().contains(x, y))
            DefinirEstadoJogo(MENU);
    }

    @Override
    public void mouseMovido(int x, int y) { 
        bMenu.setMouseSobre(false);
        if (bMenu.getLimites().contains(x, y))
            bMenu.setMouseSobre(true);
    }

    @Override
    public void mousePressionado(int x, int y) { 
        if (bMenu.getLimites().contains(x, y))
            bMenu.setMousePressionado(true);
    }

    @Override
    public void mouseSolto(int x, int y) { 
        resetarBotoes();
    }

    private void resetarBotoes() {
        bMenu.resetarBooleanos();
    }

    @Override
    public void mouseArrastado(int x, int y) { 
       
    }

    @Override
    public void teclaPressionada(KeyEvent e) {

    }


}