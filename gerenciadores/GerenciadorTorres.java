package gerenciadores;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import ajuda.CarregarSalvar;
import ajuda.Utilitarios;
import cenas.Jogando;
import inimigos.Inimigo;
import objetos.Torre;

public class GerenciadorTorres {

    private Jogando jogando;
    private BufferedImage[] imgsTorre;
    private ArrayList<Torre> torres = new ArrayList<>();
    private int quantidadeTorres = 0;

    public GerenciadorTorres(Jogando jogando) {
        this.jogando = jogando;
        carregarImgsTorre();
    }

    private void carregarImgsTorre() {
        BufferedImage atlas = CarregarSalvar.getAtlasSprites();
        imgsTorre = new BufferedImage[3];
        for (int i = 0; i < 3; i++)
            imgsTorre[i] = atlas.getSubimage((4 + i) * 32, 32, 32, 32);
    }

    public void addTorre(Torre torreSelecionada, int xPos, int yPos) {
        torres.add(new Torre(xPos, yPos, quantidadeTorres++, torreSelecionada.getTipoTorre()));
    }

    public void atualizar() {
        for (Torre t : torres) {
            t.atualizar();
            atacarInimigoSePerto(t);
        }
    }

    private void atacarInimigoSePerto(Torre t) {
        for (Inimigo e : jogando.getGerenciadorInimigos().getInimigos()) {
            if (e.estaVivo())
                if (estaInimigoNoAlcance(t, e)) {
                    if (t.isCooldownOver()) {
                        jogando.disparar(t, e);
                        t.resetCooldown();
                    }
                }
                else {
                }
        }
    }

    private boolean estaInimigoNoAlcance(Torre t, Inimigo e) {
        int alcance = Utilitarios.GetDistanciaHipotenusa(t.getX(), t.getY(), e.getX(), e.getY());
        return alcance < t.getAlcance();
    }

    public void desenhar(Graphics g) {
        for (Torre t : torres)
            g.drawImage(imgsTorre[t.getTipoTorre()], t.getX(), t.getY(), null);
    }

    public Torre getTorreEm(int x, int y) {
        for (Torre t : torres)
            if (t.getX() == x)
                if (t.getY() == y)
                    return t;
        return null;
    }

    public BufferedImage[] getImgsTorre() {
        return imgsTorre;
    }

}
