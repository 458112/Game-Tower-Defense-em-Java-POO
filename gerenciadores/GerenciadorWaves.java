package gerenciadores;

import java.util.ArrayList;
import java.util.Arrays;

import eventos.Wave;
import cenas.Jogando;

public class GerenciadorWaves {

	private Jogando jogando;
	private ArrayList<Wave> waves = new ArrayList<>();
	private int tickSpawnLimiteInimigo = 60 * 1;
	private int tickSpawnInimigo = tickSpawnLimiteInimigo;
	private int indiceInimigo, indiceWave;
	private int tickLimiteWave = 60 * 5;
	private int tickWave = 0;
	private boolean temporizadorWaveIniciado, temporizadorWaveAcabou;

	public GerenciadorWaves(Jogando jogando) {
		this.jogando = jogando;
		criarWaves();
	}

	public void atualizar() {
		if (tickSpawnInimigo < tickSpawnLimiteInimigo)
			tickSpawnInimigo++;

		if (temporizadorWaveIniciado) {
			tickWave++;
			if (tickWave >= tickLimiteWave) {
				temporizadorWaveAcabou = true;
			}
		}

	}

	public void aumentarIndiceWave() {
		indiceWave++;
		tickWave = 0;
		temporizadorWaveAcabou = false;
		temporizadorWaveIniciado = false;
	}

	public boolean temporizadorWaveAcabou() {

		return temporizadorWaveAcabou;
	}

	public void iniciarTemporizadorWave() {
		temporizadorWaveIniciado = true;
	}

	public int getProximoInimigo() {
		tickSpawnInimigo = 0;
		return waves.get(indiceWave).getListaInimigos().get(indiceInimigo++);
	}

	private void criarWaves() {
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 1, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 1, 1))));
		waves.add(new Wave(new ArrayList<Integer>(Arrays.asList(0, 0, 0, 0, 1, 1, 1, 1, 2))));
	}

	public ArrayList<Wave> getWaves() {
		return waves;
	}

	public boolean horaDeNovoInimigo() {
		return tickSpawnInimigo >= tickSpawnLimiteInimigo;
	}

	public boolean haMaisInimigosNaWave() {
		return indiceInimigo < waves.get(indiceWave).getListaInimigos().size();
	}

	public boolean haMaisWaves() {
		return indiceWave + 1 < waves.size();
	}

	public void resetarIndiceInimigo() {
		indiceInimigo = 0;
	}

	public int getIndiceWave() {
		return indiceWave;
	}

	public float getTempoRestante() {
		float ticksRestantes = tickLimiteWave - tickWave;
		return ticksRestantes / 60.0f;
	}

	public boolean temporizadorWaveIniciado() {
		return temporizadorWaveIniciado;
	}

    public Jogando getJogando() {
    return jogando;
    }

	public void resetar() {
		waves.clear();
		criarWaves();
		indiceInimigo = 0;
		indiceWave = 0;
		temporizadorWaveIniciado = false;
		temporizadorWaveAcabou = false;
		tickWave = 0;
		tickSpawnInimigo = tickSpawnLimiteInimigo;
	}

}
