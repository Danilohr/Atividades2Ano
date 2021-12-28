
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class ManipuladorArquivo {

    private final String caminhoArquivo;
    private static Scanner scanner = new Scanner(System.in);

    public ManipuladorArquivo(String caminhoArquivo) {
        this.caminhoArquivo = caminhoArquivo;
    }

    public String lerTudo() throws IOException, FileNotFoundException {
        FileReader fileReader = new FileReader(caminhoArquivo);
        BufferedReader readerf = new BufferedReader(fileReader);
        String linha = readerf.readLine();
        StringBuilder construtorString = new StringBuilder();
        while (linha != null) {
            construtorString.append(linha);
            construtorString.append("\n");
            linha = readerf.readLine();
        }
        readerf.close();

        return construtorString.toString();
    }

    public void escrever(String texto, boolean apagarConteudoArquivo) throws IOException {
        FileWriter fw = new FileWriter(caminhoArquivo, !apagarConteudoArquivo);
        BufferedWriter fwriter = new BufferedWriter(fw);
        fwriter.write(texto + "\n");
        fwriter.close();
    }

    public void escrever(String texto) throws IOException {
        escrever(texto, false);
    }

    public static Integer menu() {
        System.out.println("*****Menu*****\n1.Mostrar conteúdo do arquivo\n"
                + "2.Adicionar nova linha\n3.Sair");
        int opcao = scanner.nextInt();
        return opcao;
    }

    public static void main(String[] args) {
        try {
            System.out.println("Digite o Caminho do arquivo de texto: (Ex:C:\\Users\\danil\\Desktop)");
            String caminho = scanner.nextLine();
            System.out.println("Digite o nome do arquivo de texto: (Ex:arquivo.txt");
            String nome = scanner.nextLine();
            caminho += "\\" + nome;
            ManipuladorArquivo manipuladorArquivo = new ManipuladorArquivo(caminho);

            int opcao = 0;
            while (opcao != 3) {
                opcao = menu();
                scanner.nextLine();
                switch (opcao) {
                    case 1:
                        System.out.println("\nConteúdo lido do aquivo: \n"
                                + manipuladorArquivo.lerTudo());
                        break;
                    case 2:
                        System.out.println("Digite uma informação para ser inserida ao arquivo: ");
                        manipuladorArquivo.escrever(scanner.nextLine());
                }
            }
            System.exit(0);
        } catch (IOException ex) {
            System.out.println("Erro: " + ex.getMessage());
        }
    }
}
