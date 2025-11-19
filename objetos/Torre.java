package objetos;

public class Torre {

    private int x, y, id, tipoTorre, cdTick, dano;
    private float alcance, tempoRecarga;

    public Torre(int x, int y, int id, int tipoTorre) {
        this.x = x;
        this.y = y;
        this.id = id;
        this.tipoTorre = tipoTorre;
        definirDanoInicial();
        definirAlcancePadrao();
        definirTempoRecargaPadrao();
    }

    public void atualizar() {
        cdTick++;
    }

    public boolean isCooldownOver() {
        return cdTick >= tempoRecarga;
    }

    public void resetCooldown() {
        cdTick = 0;
    }

    private void definirTempoRecargaPadrao() {
        tempoRecarga = ajuda.Constantes.Torres.GetTempoRecargaPadrao(tipoTorre);
    }

    private void definirAlcancePadrao() {
        alcance = ajuda.Constantes.Torres.GetAlcancePadrao(tipoTorre);
    }

    private void definirDanoInicial() {
        dano = ajuda.Constantes.Torres.GetDanoInicial(tipoTorre);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTipoTorre() {
        return tipoTorre;
    }

    public void setTipoTorre(int tipoTorre) {
        this.tipoTorre = tipoTorre;
    }

    public int getDano() {
        return dano;
    }

    public float getAlcance() {
        return alcance;
    }

    public float getTempoRecarga() {
        return tempoRecarga;
    }
}
