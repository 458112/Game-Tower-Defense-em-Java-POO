package gerenciadores;

import java.util.ArrayList;
import java.util.Arrays;

import eventos.Wave;
import cenas.Jogando;

public class GerenciadorWaves {

    private Jogando jogando;

    private ArrayList<Wave> waves = new ArrayList<>();
    private int tickSpawnLimite = 60 * 1;
    private int tickSpawn = tickSpawnLimite;

    private int indiceInimigo = 0;
    private int indiceWave = 0;

    public GerenciadorWaves(Jogando jogando) {
        this.jogando = jogando;
        criarWaves();
    }

    public void atualizar() {
        if (tickSpawn < tickSpawnLimite)
            tickSpawn++;
    }

    public boolean prontoParaNovoInimigo() {
        return tickSpawn >= tickSpawnLimite;
    }

    public boolean haMaisInimigos() {
        return indiceInimigo < waves.get(indiceWave).getListaInimigos().size();
    }

    public int getProximoInimigo() {
        tickSpawn = 0;
        return waves.get(indiceWave).getListaInimigos().get(indiceInimigo++);
    }

    private void criarWaves() {
        waves.add(
                new Wave(
                        new ArrayList<Integer>(Arrays.asList(
                            0,0,0,0,0,0,0,0,0,1
                        ))
                )
        );
    }

    public ArrayList<Wave> getWaves() {
        return waves;
    }
}
