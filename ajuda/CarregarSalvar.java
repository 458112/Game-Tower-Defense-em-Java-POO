package ajuda;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.imageio.ImageIO;

import objetos.PontoCaminho;

public class CarregarSalvar {

    private static final String PASTA_BASE =
            System.getProperty("user.home") + "/meuJogoNiveis/";

    static {
        File dir = new File(PASTA_BASE);
        if (!dir.exists()) dir.mkdirs();
    }

    public static BufferedImage getAtlasSprites() {
        try (InputStream is = CarregarSalvar.class.getClassLoader()
                .getResourceAsStream("spriteatlas.png")) {
            if (is == null) return null;
            return ImageIO.read(is);
        } catch (IOException e) {
            return null;
        }
    }

    public static void CriarNivel(String nome, int[] idArr) {
        File novoNivel = new File(PASTA_BASE + nome + ".txt");

        if (novoNivel.exists()) return;

        try {
            novoNivel.createNewFile();
            EscreverNoArquivo(novoNivel, idArr,
                    new PontoCaminho(0, 0),
                    new PontoCaminho(0, 0));
        } catch (IOException e) {}
    }

    public static void SalvarNivel(String nome, int[][] nivel,
                                   PontoCaminho inicio,
                                   PontoCaminho fim) {

        File arquivoNivel = new File(PASTA_BASE + nome + ".txt");
        if (!arquivoNivel.exists()) return;

        int[] nivel1D = Utilitarios.Vetor2DparaVetor1Dint(nivel);
        EscreverNoArquivo(arquivoNivel, nivel1D, inicio, fim);
    }

    private static void EscreverNoArquivo(File f,
                                          int[] idArr,
                                          PontoCaminho inicio,
                                          PontoCaminho fim) {
        try (PrintWriter pw = new PrintWriter(f)) {

            pw.println(idArr.length);

            for (Integer id : idArr)
                pw.println(id);

            pw.println(inicio.getxCord());
            pw.println(inicio.getyCord());
            pw.println(fim.getxCord());
            pw.println(fim.getyCord());

        } catch (FileNotFoundException e) {}
    }

    private static ArrayList<Integer> LerDoArquivo(File arquivo) {
        ArrayList<Integer> lista = new ArrayList<>();

        try (Scanner sc = new Scanner(arquivo)) {
            while (sc.hasNextLine())
                lista.add(Integer.parseInt(sc.nextLine().trim()));
        } catch (FileNotFoundException e) {}

        return lista;
    }

    public static int[][] GetDadosNivel(String nome) {

        File arquivoNvl = new File(PASTA_BASE + nome + ".txt");
        if (!arquivoNvl.exists()) return null;

        ArrayList<Integer> lista = LerDoArquivo(arquivoNvl);
        if (lista.size() < 404) return null;

        int tamanho = lista.get(0);
        ArrayList<Integer> blocoIDs =
                new ArrayList<>(lista.subList(1, tamanho + 1));

        return Utilitarios.ArrayListParaMatriz2Dint(blocoIDs, 20, 20);
    }

    public static ArrayList<PontoCaminho> GetPontosCaminhoNivel(String nome) {

        File arquivoNvl = new File(PASTA_BASE + nome + ".txt");
        if (!arquivoNvl.exists()) return null;

        ArrayList<Integer> lista = LerDoArquivo(arquivoNvl);
        int tamanho = lista.get(0);

        int inicioX = lista.get(tamanho + 1);
        int inicioY = lista.get(tamanho + 2);
        int fimX =     lista.get(tamanho + 3);
        int fimY =     lista.get(tamanho + 4);

        ArrayList<PontoCaminho> pontos = new ArrayList<>();
        pontos.add(new PontoCaminho(inicioX, inicioY));
        pontos.add(new PontoCaminho(fimX, fimY));

        return pontos;
    }
}
