import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MaquinaPilha {
    public static void main(String[] args) {
        String arquivoDeEntrada = args[0];

        try {
            List<String> codigo = leArquivo(arquivoDeEntrada);
            double resultado = processaCodigo(codigo);
            System.out.println("Resultado: " + resultado);
        } catch (Exception e) {
            System.out.println("Erro na compilação:\n" + e);
        }
    }
    public static List<String> leArquivo(String nomeDoArquivo) {
        List<String> codigo = new ArrayList<String>(); //Armazena cada linha em "codigo"
        System.out.println("Lendo o arquivo: " + nomeDoArquivo);

        try {
            BufferedReader br = new BufferedReader(new FileReader(nomeDoArquivo));
            String linha = br.readLine();

            while (linha != null) {
                codigo.add(linha);
                linha = br.readLine();
            }
            br.close();
        } catch (Exception e) {
            System.out.println("Erro na leitura do arquivo:\n" + e);
        }
        return codigo;
    }

    public static double processaCodigo(List<String> comandos) {
        Stack<Double> pilha = new Stack<Double>(); //Armazena os valores durante o processo
        double resultado = 0.0;

        System.out.println("Processando o código: " + comandos);

        for (String comando: comandos) {
            if (comando.startsWith("PUSH")) {
                double arg = Double.parseDouble(comando.split(" ")[1]);
                pilha.push(arg);
            } else if (comando.startsWith("SUM")) {
                double arg1 = pilha.pop();
                double arg2 = pilha.pop();
                resultado = arg2 + arg1;
                pilha.push(resultado);
            } else if (comando.startsWith("SUB")) {
                double arg1 = pilha.pop();
                double arg2 = pilha.pop();
                resultado = arg2 - arg1;
                pilha.push(resultado);
            } else if (comando.startsWith("MULT")) {
                double arg1 = pilha.pop();
                double arg2 = pilha.pop();
                resultado = arg2 * arg1;
                pilha.push(resultado);
            } else if (comando.startsWith("DIV")) {
                double arg1 = pilha.pop();
                double arg2 = pilha.pop();
                resultado = arg2 / arg1;
                pilha.push(resultado);
            } else if (comando.startsWith("PRINT")) {
                resultado = pilha.pop();
            }
        }
        double resultadoDefinitivo = pilha.pop();
        return resultadoDefinitivo;
    }
}
