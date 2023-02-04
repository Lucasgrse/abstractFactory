import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Random;

interface Impressora {
    void imprimir(String texto);
}

class ImpressoraTela implements Impressora {
    @Override
    public void imprimir(String texto) {
        System.out.println(texto);
    }
}

class ImpressoraArquivo implements Impressora {
    @Override
    public void imprimir(String texto) {
        try (OutputStream os = new FileOutputStream("output.txt");
             PrintStream ps = new PrintStream(os)) {
            ps.println(texto);
        } catch (Exception e) {
            System.out.println("Erro ao escrever no arquivo");
        }
    }
}

interface FabricaImpressora {
    Impressora criarImpressora();
}

class FabricaImpressoraTela implements FabricaImpressora {
    @Override
    public Impressora criarImpressora() {
        return new ImpressoraTela();
    }
}

class FabricaImpressoraArquivo implements FabricaImpressora {
    @Override
    public Impressora criarImpressora() {
        return new ImpressoraArquivo();
    }
}

public class abstractFactory {
    public static void main(String[] args) {
        FabricaImpressora[] fabricas = {new FabricaImpressoraTela(), new FabricaImpressoraArquivo()};
        Random random = new Random();
        FabricaImpressora fabricaEscolhida = fabricas[random.nextInt(fabricas.length)];
        Impressora impressora = fabricaEscolhida.criarImpressora();
        impressora.imprimir("Hello, World!");
    }
}