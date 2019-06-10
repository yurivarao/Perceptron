package redesneurais.perceptron;
  
import java.util.Random;

public class Perceptron {
    
    //Declarando as variáveis que serão usadas
    private double AUX;
    private int epocas;
    private final double taxaAprendizado;
    private double[] pesos = new double[3];
    private final int[][] matrizAprendizado = new int[4][3];
    
    
    // Método construtor de inicialização os valores das variáveis e da matriz de aprendizado
    public Perceptron() {
    // Primeiro valor
    this.matrizAprendizado[0][0] = 0; // entrada 1
    this.matrizAprendizado[0][1] = 0; // entrada 2
    this.matrizAprendizado[0][2] = 0; // valor esperado
    // Segundo valor
    this.matrizAprendizado[1][0] = 0;
    this.matrizAprendizado[1][1] = 1;
    this.matrizAprendizado[1][2] = 0;
    // Terceiro valor
    this.matrizAprendizado[2][0] = 1;
    this.matrizAprendizado[2][1] = 0;
    this.matrizAprendizado[2][2] = 0;
    // Quarto valor
    this.matrizAprendizado[3][0] = 1;
    this.matrizAprendizado[3][1] = 1;
    this.matrizAprendizado[3][2] = 1;
    // Inicialização dos pesos sinápticos com valores aleatórios
    Random random = new Random();
    pesos[0] = random.nextInt(10);
    pesos[1] = random.nextInt(10);
    pesos[2] = random.nextInt(10);
    
    /*
    pesos[0] = Math.random();
    pesos[1] = Math.random();
    pesos[2] = Math.random();
    */
    
    // Taxa de aprendizado para correção dos pesos
    this.taxaAprendizado = 1;
    // Valor usado para representar o resultado da função de ativação
    this.AUX = 0;
    // Inicializa o numero de Epocas a serem executadas para encontrar a solução
    this.epocas = 0;
    }
    
    // Método da Função de Ativação responsável pelo somatório das entradas multiplicadas com seus respectivos pesos.
    int funcaoAtivacao(int x1, int x2) {
        AUX = (x1 * pesos[0]) + (x2 * pesos[1]) + ((-1) * pesos[2]);
        if (AUX >= 0) {
            return 1;
        }
        return 0;
    }
    
    // Método para a correção de pesos
    void corrigirPeso(int i, int saida) {
        pesos[0] = pesos[0] + (taxaAprendizado * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][0]);
        pesos[1] = pesos[1] + (taxaAprendizado * (matrizAprendizado[i][2] - saida) * matrizAprendizado[i][1]);
        pesos[2] = pesos[2] + (taxaAprendizado * (matrizAprendizado[i][2] - saida) * (-1));
    }
    
    // Método para treinamento
    public void treinar() {
        //Variáveis locais usadas no método
        boolean treinou = true;
        int saida;
        
        // Exibe a epoca em que ocorre cada iteração
        System.out.printf("Epoca: %d\n", epocas + 1);
        // Exibe os pesos utilizados em cada epoca
        System.out.printf("Pesos Utilizados:\nPeso 1: %.3f Peso 2: %.3f Peso 3: %.3f\n\n", pesos[0], pesos[1], pesos[2]);
        // Laço usado para verificar todas as entradas
        for (int i = 0; i < matrizAprendizado.length; i++) {
            saida = funcaoAtivacao(matrizAprendizado[i][0], matrizAprendizado[i][1]);
            // Mostrar resultados de cada epoca
            System.out.printf("Saída Obtida: %d -> Saída Desejada: %d\n", saida, matrizAprendizado[i][2]);
            if (saida != matrizAprendizado[i][2]) {
                corrigirPeso(i, saida);
                treinou = false;
            }
        }
        System.out.printf("\n========================================\n\n");
        this.epocas++;
        
        // Verifica se o treinamento ja terminou
        if(treinou == false) {
            // Se ainda não ocorreu chama o método novamente
            treinar();
        }else{
            // Se já ocorreu exibe msg de finalização
            System.out.printf("A saída obtida foi igual a desejada. Total de %d epocas.\nFim do Programa!\n", epocas);
        }
    }
    
    public static void main(String[] args) {
        //Instancia um objeto chamando o método para Treinar a rede Perceptron
        Perceptron perc = new Perceptron();
        perc.treinar();
    }
}
