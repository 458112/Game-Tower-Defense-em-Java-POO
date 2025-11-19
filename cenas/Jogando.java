package cenas;

import static ajuda.Constantes.Blocos.BLOCO_GRAMA;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import ajuda.CarregarSalvar;
import gerenciadores.GerenciadorInimigos;
import gerenciadores.GerenciadorProjeteis;
import gerenciadores.GerenciadorTorres;
import gerenciadores.GerenciadorWaves;
import objetos.PontoCaminho;
import objetos.Torre;
import principal.Jogo;
import ui.BarraAcao;
import inimigos.Inimigo;

public class Jogando extends CenaJogo implements MetodosCena {

    private int[][] nvl;
    private PontoCaminho inicio, fim;

    private GerenciadorInimigos gerenciadorInimigos;
    private GerenciadorTorres gerenciadorTorres;
    private GerenciadorProjeteis gerenciadorProjeteis;
    private GerenciadorWaves gerenciadorWaves;

    private BarraAcao barraAcao;
    private Torre torreSelecionada;
    private int mouseX, mouseY;

    public Jogando(Jogo jogo) {
        super(jogo);
        carregarNivelPadrao();

        barraAcao = new BarraAcao(0, 640, 640, 160, this);
        gerenciadorInimigos = new GerenciadorInimigos(this, inicio, fim);
        gerenciadorTorres = new GerenciadorTorres(this);
        gerenciadorProjeteis = new GerenciadorProjeteis(this);
        gerenciadorWaves = new GerenciadorWaves(this);
    }

    private void carregarNivelPadrao() {
        nvl = CarregarSalvar.GetDadosNivel("new_level");
        ArrayList<PontoCaminho> pontos = CarregarSalvar.GetPontosCaminhoNivel("new_level");
        inicio = pontos.get(0);
        fim = pontos.get(1);
    }

    public void atualizar() {
    atualizarTick();

    gerenciadorWaves.atualizar();

    if (gerenciadorWaves.prontoParaNovoInimigo()) {
        if (gerenciadorWaves.haMaisInimigos()) {
            gerenciadorInimigos.addInimigo(gerenciadorWaves.getProximoInimigo());
        }
    }

    gerenciadorInimigos.atualizar();
    gerenciadorTorres.atualizar();
    gerenciadorProjeteis.atualizar();
    }

    @Override
    public void renderizar(Graphics g) {
        desenharNivel(g);
        barraAcao.desenhar(g);
        gerenciadorInimigos.desenhar(g);
        gerenciadorTorres.desenhar(g);
        gerenciadorProjeteis.desenhar(g);
        desenharTorreSelecionada(g);
        desenharDestaque(g);
    }

    private void desenharNivel(Graphics g) {
        for (int y = 0; y < nvl.length; y++) {
            for (int x = 0; x < nvl[y].length; x++) {
                int id = nvl[y][x];
                if (eAnimacao(id)) g.drawImage(getSprite(id, indiceAnimacao), x * 32, y * 32, null);
                else g.drawImage(getSprite(id), x * 32, y * 32, null);
            }
        }
    }

    private void desenharTorreSelecionada(Graphics g) {
        if (torreSelecionada != null)
            g.drawImage(gerenciadorTorres.getImgsTorre()[torreSelecionada.getTipoTorre()], mouseX, mouseY, null);
    }

    private void desenharDestaque(Graphics g) {
        g.setColor(Color.WHITE);
        g.drawRect(mouseX, mouseY, 32, 32);
    }

    @Override
    public void mouseClicado(int x, int y) {
        if (y >= 640)
            barraAcao.mouseClicado(x, y);
        else {
            if (torreSelecionada != null) {
                if (eBlocoGrama(mouseX, mouseY)) {
                    if (getTorreEm(mouseX, mouseY) == null) {
                        gerenciadorTorres.addTorre(torreSelecionada, mouseX, mouseY);
                        torreSelecionada = null;
                    }
                }
            } else {
                Torre t = getTorreEm(mouseX, mouseY);
                barraAcao.exibirTorre(t);
            }
        }
    }

    @Override
    public void mouseMovido(int x, int y) {
        if (y >= 640)
            barraAcao.mouseMovido(x, y);
        else {
            mouseX = (x / 32) * 32;
            mouseY = (y / 32) * 32;
        }
    }

    @Override
    public void mousePressionado(int x, int y) {
        if (y >= 640)
            barraAcao.mousePressionado(x, y);
    }

    @Override
    public void mouseSolto(int x, int y) {
        barraAcao.mouseSolto(x, y);
    }

    @Override
    public void mouseArrastado(int x, int y) {}

    public void teclaPressionada(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
            torreSelecionada = null;
    }

    private boolean eBlocoGrama(int x, int y) {
        int cx = x / 32;
        int cy = y / 32;

        if (cx < 0 || cx > 19) return false;
        if (cy < 0 || cy > 19) return false;

        int id = nvl[cy][cx];
        int tipo = jogo.getGerenciadorBlocos().getBloco(id).getTipoBloco();
        return tipo == BLOCO_GRAMA;
    }

    private Torre getTorreEm(int x, int y) {
        return gerenciadorTorres.getTorreEm(x, y);
    }

    public int getTipoBloco(int x, int y) {
        int cx = x / 32;
        int cy = y / 32;

        if (cx < 0 || cx > 19) return 0;
        if (cy < 0 || cy > 19) return 0;

        int id = nvl[cy][cx];
        return jogo.getGerenciadorBlocos().getBloco(id).getTipoBloco();
    }

    public void disparar(Torre t, Inimigo i) {
    gerenciadorProjeteis.novoProjetil(t, i);
}

    public void setNivel(int[][] nvl) {
        this.nvl = nvl;
    }

    public void setTorreSelecionada(Torre torreSelecionada) {
        this.torreSelecionada = torreSelecionada;
    }

    public GerenciadorTorres getGerenciadorTorres() {
        return gerenciadorTorres;
    }

    public GerenciadorInimigos getGerenciadorInimigos() {
        return gerenciadorInimigos;
    }

    public GerenciadorProjeteis getGerenciadorProjeteis() {
        return gerenciadorProjeteis;
    }

    public GerenciadorWaves getGerenciadorWaves() {
        return gerenciadorWaves;
    }
}
