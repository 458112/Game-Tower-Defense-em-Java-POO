package objetos;

import java.awt.geom.Point2D;

public class Projetil {

    private Point2D.Float pos;
    private int id, tipoProjetil, dano;
    private float velX, velY, rotacao;
    private boolean ativo = true;

    public Projetil(float x, float y, float velX, float velY, int dano, float rotacao, int id, int tipoProjetil) {
        pos = new Point2D.Float(x, y);
        this.velX = velX;
        this.velY = velY;
        this.dano = dano;
        this.rotacao = rotacao;
        this.id = id;
        this.tipoProjetil = tipoProjetil;
    }

    public void mover() {
        pos.x += velX;
        pos.y += velY;
    }

    public Point2D.Float getPosicao() {
        return pos;
    }

    public void setPos(Point2D.Float pos) {
        this.pos = pos;
    }

    public int getId() {
        return id;
    }

    public int getTipoProjetil() {
        return tipoProjetil;
    }

    public boolean estaAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public int getDano() {
        return dano;
    }

    public float getRotacao() {
        return rotacao;
    }
}
