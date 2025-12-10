package ui;

import static principal.EstadosJogo.FIM_DE_JOGO;
import static principal.EstadosJogo.MENU;
import static principal.EstadosJogo.DefinirEstadoJogo;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.text.DecimalFormat;

import ajuda.Constantes.Torres;
import cenas.Jogando;
import objetos.Torre;

public class BarraAcao extends Barra {

	private Jogando jogando;
	private MeuBotao bMenu, bPausar;

	private MeuBotao[] botoesTorre;
	private Torre torreSelecionada;
	private Torre torreExibida;
	private MeuBotao venderTorre, atualizarTorre;

	private DecimalFormat formatador;

	private int ouro = 100;
	private boolean mostrarCustoTorre;
	private int tipoCustoTorre;

	private int vidas = 5;

	public BarraAcao(int x, int y, int largura, int altura, Jogando jogando) {
		super(x, y, largura, altura);
		this.jogando = jogando;
		formatador = new DecimalFormat("0.0");

		iniciarBotoes();
	}

	public void resetarTudo() {
		vidas = 5;
		tipoCustoTorre = 0;
		mostrarCustoTorre = false;
		ouro = 100;
		torreSelecionada = null;
		torreExibida = null;
	}

	private void iniciarBotoes() {

		bMenu = new MeuBotao("Menu", 2, 642, 100, 30);
		bPausar = new MeuBotao("Pausar", 2, 682, 100, 30);

		botoesTorre = new MeuBotao[3];
		int w = 50;
		int h = 50;
		int xStart = 110;
		int yStart = 650;
		int xOffset = (int) (w * 1.1f);

		for (int i = 0; i < botoesTorre.length; i++)
			botoesTorre[i] = new MeuBotao("", xStart + xOffset * i, yStart, w, h, i);

		venderTorre = new MeuBotao("Vender", 420, 702, 80, 25);
		atualizarTorre = new MeuBotao("Atualizar", 545, 702, 80, 25);
	}

	public void removerUmaVida() {
		vidas--;
		if (vidas <= 0)
			DefinirEstadoJogo(FIM_DE_JOGO);
	}

	private void desenharBotoes(Graphics g) {
		bMenu.desenhar(g);
		bPausar.desenhar(g);

		for (MeuBotao b : botoesTorre) {
			g.setColor(Color.gray);
			g.fillRect(b.x, b.y, b.largura, b.altura);
			g.drawImage(jogando.getGerenciadorTorres().getImgsTorre()[b.getId()], b.x, b.y, b.largura, b.altura, null);
			desenharFeedbackBotao(g, b);
		}
	}

	public void desenhar(Graphics g) {

		g.setColor(new Color(220, 123, 15));
		g.fillRect(x, y, largura, altura);

		desenharBotoes(g);

		desenharTorreExibida(g);

		desenharInfoWave(g);

		desenharQuantidadeOuro(g);

		if (mostrarCustoTorre)
			desenharCustoTorre(g);

		if (jogando.getJogoPausado()) {
			g.setColor(Color.black);
			g.drawString("Jogo Pausado!", 110, 790);
		}

		g.setColor(Color.black);
		g.drawString("Vidas: " + vidas, 110, 750);
	}

	private void desenharCustoTorre(Graphics g) {
		g.setColor(Color.gray);
		g.fillRect(280, 650, 120, 50);
		g.setColor(Color.black);
		g.drawRect(280, 650, 120, 50);

		g.drawString("" + getNomeCustoTorre(), 285, 670);
		g.drawString("Custo: " + getCustoCustoTorre() + "g", 285, 695);

		if (custoTorreMaiorQueOuroAtual()) {
			g.setColor(Color.RED);
			g.drawString("Sem Ouro", 270, 725);
		}

	}

	private boolean custoTorreMaiorQueOuroAtual() {
		return getCustoCustoTorre() > ouro;
	}

	private String getNomeCustoTorre() {
		return ajuda.Constantes.Torres.GetNome(tipoCustoTorre);
	}

	private int getCustoCustoTorre() {
		return ajuda.Constantes.Torres.GetCustoTorre(tipoCustoTorre);
	}

	private void desenharQuantidadeOuro(Graphics g) {
		g.drawString("Ouro: " + ouro + "g", 110, 725);

	}

	private void desenharInfoWave(Graphics g) {
		g.setColor(Color.black);
		g.setFont(new Font("LucidaSans", Font.BOLD, 20));
		desenharInfoTemporizadorWave(g);
		desenharInfoInimigosRestantes(g);
		desenharInfoWavesRestantes(g);

	}

	private void desenharInfoWavesRestantes(Graphics g) {
		int atual = jogando.getGerenciadorWaves().getIndiceWave();
		int tamanho = jogando.getGerenciadorWaves().getWaves().size();
		g.drawString("Wave " + (atual + 1) + " / " + tamanho, 425, 770);

	}

	private void desenharInfoInimigosRestantes(Graphics g) {
		int restantes = jogando.getGerenciadorInimigos().getQuantidadeInimigosVivos();
		g.drawString("Inimigos Restantes: " + restantes, 425, 790);
	}

	private void desenharInfoTemporizadorWave(Graphics g) {
		if (jogando.getGerenciadorWaves().temporizadorWaveIniciado()) {
			float tempoRestante = jogando.getGerenciadorWaves().getTempoRestante();
			String textoFormatado = formatador.format(tempoRestante);
			g.drawString("Tempo Restante: " + textoFormatado, 425, 750);
		}
	}

	private void desenharTorreExibida(Graphics g) {
		if (torreExibida != null) {
			g.setColor(Color.gray);
			g.fillRect(410, 645, 220, 85);
			g.setColor(Color.black);
			g.drawRect(410, 645, 220, 85);
			g.drawRect(420, 650, 50, 50);
			g.drawImage(jogando.getGerenciadorTorres().getImgsTorre()[torreExibida.getTipoTorre()], 420, 650, 50, 50, null);
			g.setFont(new Font("LucidaSans", Font.BOLD, 15));
			g.drawString("" + Torres.GetNome(torreExibida.getTipoTorre()), 480, 660);
			g.drawString("ID: " + torreExibida.getId(), 480, 675);
			g.drawString("Tier: " + torreExibida.getTier(), 560, 660);
			desenharBordaTorreExibida(g);
			desenharAlcanceTorreExibida(g);

			venderTorre.desenhar(g);
			desenharFeedbackBotao(g, venderTorre);

			if (torreExibida.getTier() < 3 && ouro >= getValorAtualizacao(torreExibida)) {
				atualizarTorre.desenhar(g);
				desenharFeedbackBotao(g, atualizarTorre);
			}

			if (venderTorre.getMouseSobre()) {
				g.setColor(Color.red);
				g.drawString("Vender por: " + getValorVenda(torreExibida) + "g", 480, 695);
			} else if (atualizarTorre.getMouseSobre() && ouro >= getValorAtualizacao(torreExibida)) {
				g.setColor(Color.blue);
				g.drawString("Atualizar por: " + getValorAtualizacao(torreExibida) + "g", 480, 695);
			}

		}

	}

	private int getValorAtualizacao(Torre torreExibida) {
		return (int) (ajuda.Constantes.Torres.GetCustoTorre(torreExibida.getTipoTorre()) * 0.3f);
	}

	private int getValorVenda(Torre torreExibida) {
		int custoAtualizacao = (torreExibida.getTier() - 1) * getValorAtualizacao(torreExibida);
		custoAtualizacao *= 0.5f;

		return ajuda.Constantes.Torres.GetCustoTorre(torreExibida.getTipoTorre()) / 2 + custoAtualizacao;
	}

	private void desenharAlcanceTorreExibida(Graphics g) {
		g.setColor(Color.white);
		g.drawOval(torreExibida.getX() + 16 - (int) (torreExibida.getAlcance() * 2) / 2, torreExibida.getY() + 16 - (int) (torreExibida.getAlcance() * 2) / 2, (int) torreExibida.getAlcance() * 2,
				(int) torreExibida.getAlcance() * 2);

	}

	private void desenharBordaTorreExibida(Graphics g) {
		g.setColor(Color.CYAN);
		g.drawRect(torreExibida.getX(), torreExibida.getY(), 32, 32);

	}

	public void exibirTorre(Torre t) {
		torreExibida = t;
	}

	private void torreVendidaClicada() {
		jogando.removerTorre(torreExibida);
		ouro += getValorVenda(torreExibida);
		torreExibida = null;
	}

	private void torreAtualizadaClicada() {
		jogando.atualizarTorre(torreExibida);
		ouro -= getValorAtualizacao(torreExibida);

	}

	private void alternarPausa() {
		jogando.setJogoPausado(!jogando.getJogoPausado());

		if (jogando.getJogoPausado())
			bPausar.setText("Despausar");
		else
			bPausar.setText("Pausar");

	}

	public void mouseClicado(int x, int y) {
		if (bMenu.getLimites().contains(x, y))
			DefinirEstadoJogo(MENU);
		else if (bPausar.getLimites().contains(x, y))
			alternarPausa();
		else {

			if (torreExibida != null) {
				if (venderTorre.getLimites().contains(x, y)) {
					torreVendidaClicada();

					return;
				} else if (atualizarTorre.getLimites().contains(x, y) && torreExibida.getTier() < 3 && ouro >= getValorAtualizacao(torreExibida)) {
					torreAtualizadaClicada();
					return;
				}
			}

			for (MeuBotao b : botoesTorre) {
				if (b.getLimites().contains(x, y)) {
					if (!ouroSuficienteParaTorre(b.getId()))
						return;

					torreSelecionada = new Torre(0, 0, -1, b.getId());
					jogando.setTorreSelecionada(torreSelecionada);
					return;
				}
			}
		}

	}

	private boolean ouroSuficienteParaTorre(int tipoTorre) {
		return ouro >= ajuda.Constantes.Torres.GetCustoTorre(tipoTorre);
	}

	public void mouseMovido(int x, int y) {
		bMenu.setMouseSobre(false);
		bPausar.setMouseSobre(false);
		mostrarCustoTorre = false;
		venderTorre.setMouseSobre(false);
		atualizarTorre.setMouseSobre(false);

		for (MeuBotao b : botoesTorre)
			b.setMouseSobre(false);

		if (bMenu.getLimites().contains(x, y))
			bMenu.setMouseSobre(true);
		else if (bPausar.getLimites().contains(x, y))
			bPausar.setMouseSobre(true);
		else {

			if (torreExibida != null) {
				if (venderTorre.getLimites().contains(x, y)) {
					venderTorre.setMouseSobre(true);
					return;
				} else if (atualizarTorre.getLimites().contains(x, y) && torreExibida.getTier() < 3) {
					atualizarTorre.setMouseSobre(true);
					return;
				}
			}

			for (MeuBotao b : botoesTorre)
				if (b.getLimites().contains(x, y)) {
					b.setMouseSobre(true);
					mostrarCustoTorre = true;
					tipoCustoTorre = b.getId();
					return;
				}
		}
	}

	public void mousePressionado(int x, int y) {
		if (bMenu.getLimites().contains(x, y))
			bMenu.setMousePressionado(true);
		else if (bPausar.getLimites().contains(x, y))
			bPausar.setMousePressionado(true);
		else {

			if (torreExibida != null) {
				if (venderTorre.getLimites().contains(x, y)) {
					venderTorre.setMousePressionado(true);
					return;
				} else if (atualizarTorre.getLimites().contains(x, y) && torreExibida.getTier() < 3) {
					atualizarTorre.setMousePressionado(true);
					return;
				}
			}

			for (MeuBotao b : botoesTorre)
				if (b.getLimites().contains(x, y)) {
					b.setMousePressionado(true);
					return;
				}
		}

	}

	public void mouseSolto(int x, int y) {
		bMenu.resetarBooleanos();
		bPausar.resetarBooleanos();
		for (MeuBotao b : botoesTorre)
			b.resetarBooleanos();
		venderTorre.resetarBooleanos();
		atualizarTorre.resetarBooleanos();
	}

	public void pagarTorre(int tipoTorre) {
		this.ouro -= ajuda.Constantes.Torres.GetCustoTorre(tipoTorre);

	}

	public void adicionarOuro(int recompensa) {
		this.ouro += recompensa;
	}

	public int getVidas() {
		return vidas;
	}

}
