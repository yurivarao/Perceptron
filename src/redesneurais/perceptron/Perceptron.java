package redesneurais.perceptron;
  
//import java.util.Random;

/*
Aluno: Yuri de Arruda Varão
Bloco: 6
*/

public class Perceptron {
    
    //Declarando as variáveis que serão usadas
    private double AUX;
    private int epocas;
    private final double taxaAprendizado;
    private double[] pesos = new double[4];
    private final double[][] matrizAprendizado;
    private final double[][] amostras;
    
    // Método construtor de inicialização os valores das variáveis e da matriz de aprendizado
    public Perceptron() {
    
    // Amostras de Aprendizados da rede
    this.matrizAprendizado = new double[][]{
        {-0.6508,0.1097,4.0009,-1.0000},  //Amostra 01 
        {-1.4492,0.8896,4.4005,-1.0000},  //Amostra 02 
        {2.0850,0.6876,12.0710,-1.0000},  //Amostra 03 
        {0.2626,1.1476,7.7985,1.0000},    //Amostra 04 
        {0.6418,1.0234,7.0427,1.0000},    //Amostra 05 
        {0.2569,0.6730,8.3265,-1.0000},   //Amostra 06 
        {1.1155,0.6043,7.4446,1.0000},    //Amostra 07 
        {0.0914,0.3399,7.0677,-1.0000},   //Amostra 08 
        {0.0121,0.5256,4.6316,1.0000},    //Amostra 09 
        {-0.0429,0.4660,5.4323,1.0000},   //Amostra 10 
        {0.4340,0.6870,8.2287,-1.0000},   //Amostra 11
        {0.2735,1.0287,7.1934,1.0000},    //Amostra 12 
        {0.4839,0.4851,7.4850,-1.0000},   //Amostra 13 
        {0.4089,-0.1267,5.5019,-1.0000},  //Amostra 14 
        {1.4391,0.1614,8.5843,-1.0000},   //Amostra 15 
        {-0.9115,-0.1973,2.1962,-1.0000}, //Amostra 16 
        {0.3654,1.0475,7.4858,1.0000},    //Amostra 17 
        {0.2144,0.7515,7.1699,1.0000},    //Amostra 18 
        {0.2013,1.0014,6.5489,1.0000},    //Amostra 19
        {0.6483,0.2183,5.8991,1.0000},    //Amostra 20
        {-0.1147,0.2242,7.2435,-1.0000},  //Amostra 21 
        {-0.7970,0.8795,3.8762,1.0000},   //Amostra 22
        {-1.0625,0.6366,2.4707,1.0000},   //Amostra 23 
        {0.5307,0.1285,5.6883,1.0000},    //Amostra 24 
        {-1.2200,0.7777,1.7252,1.0000},   //Amostra 25 
        {0.3957,0.1076,5.6623,-1.0000},   //Amostra 26 
        {-0.1013,0.5989,7.1812,-1.0000},  //Amostra 27 
        {2.4482,0.9455,11.2095,1.0000},   //Amostra 28 
        {2.0149,0.6192,10.9263,-1.0000},  //Amostra 29 
        {0.2012,0.2611,5.4631,1.0000},    //Amostra 30
    };
    
    // Amostras que serão classificadas pela rede
    this.amostras = new double[][]{
        {-0.3665,0.0620,5.9891},  //Amostra 01 
        {-0.7842,1.1267,5.5912},  //Amostra 02 
        {0.3012,0.5611,5.8234},   //Amostra 03 
        {0.7757,1.0648,8.0677},   //Amostra 04 
        {0.1570,0.8028,6.3040},   //Amostra 05 
        {-0.7014,1.0316,3.6005},  //Amostra 06 
        {0.3748,0.1536,6.1537},   //Amostra 07 
        {-0.6920,0.9404,4.4058},  //Amostra 08 
        {-1.3970,0.7141,4.9263},  //Amostra 09 
        {-1.8842,-0.2805,1.2548}, //Amostra 10 
    };
    
    // Inicialização dos pesos sinápticos com valores aleatórios
    /*
    // Valores aleatórios inteiros
    Random random = new Random();
    pesos[0] = random.nextInt(10);
    pesos[1] = random.nextInt(10);
    pesos[2] = random.nextInt(10);
    pesos[3] = random.nextInt(10);
    */
    // Valores aleatórios entre 0 e 1.
    pesos[0] = Math.random();
    pesos[1] = Math.random();
    pesos[2] = Math.random();
    pesos[3] = Math.random();
    
    // Taxa de aprendizado para correção dos pesos
    this.taxaAprendizado = 0.01;
    // Valor usado para representar o resultado da função de ativação
    this.AUX = 0;
    // Inicializa o numero de Epocas a serem executadas para encontrar a solução
    this.epocas = 0;
    }
    
    // Método da Função de Ativação responsável pelo somatório das entradas multiplicadas com seus respectivos pesos.
    double funcaoAtivacao(double x1, double x2, double x3) {
        AUX = (x1 * pesos[0]) + (x2 * pesos[1]) + (x3 * pesos[2]) + ((-1) * pesos[3]);
        if (AUX >= 0) {
            return 1.0000;
        }
        return -1.0000;
    }
    
    // Método para a correção de pesos
    void corrigirPeso(int i, double saida) {
        pesos[0] = pesos[0] + (taxaAprendizado * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][0]);
        pesos[1] = pesos[1] + (taxaAprendizado * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][1]);
        pesos[2] = pesos[2] + (taxaAprendizado * (matrizAprendizado[i][3] - saida) * matrizAprendizado[i][2]);
        pesos[3] = pesos[3] + (taxaAprendizado * (matrizAprendizado[i][3] - saida) * (-1));
    }
    
    // Método para treinamento da rede
    public void treinar() {
        //Variáveis locais usadas no método
        boolean treinou = true;
        double saida;
        
        // Exibe a epoca em que ocorre cada iteração
        System.out.printf("Epoca: %d\n", epocas + 1);
        // Exibe os pesos utilizados em cada epoca
        System.out.printf("Pesos Utilizados:\nPeso 1: %.2f Peso 2: %.2f Peso 3: %.2f Peso 4: %.2f\n\n", pesos[0], pesos[1], pesos[2], pesos[3]);
        // Laço usado para verificar todas as entradas
        for (int i = 0; i < matrizAprendizado.length; i++) {
            saida = funcaoAtivacao(matrizAprendizado[i][0], matrizAprendizado[i][1], matrizAprendizado[i][2]);
            // Mostrar resultados da saídas de cada epoca
            System.out.printf("Saída Obtida: %.4f -> Saída Desejada: %.4f\n", saida, matrizAprendizado[i][3]);
            if (saida != matrizAprendizado[i][3]) {
                corrigirPeso(i, saida);
                treinou = false;
            }
        }
        System.out.printf("\n==================================================\n\n");
        this.epocas++;
        
        // Verifica se o treinamento ja terminou
        if(treinou == false) {
            // Se ainda não ocorreu chama o método novamente
            treinar();
        }else{
            // Se o treinamento já ocorreu exibe msg
            System.out.printf("A saída obtida foi igual a desejada.\nA rede foi treinada. Total de %d epocas.\n", epocas);
            System.out.printf("\n==================================================\n\n");
        }
    }
    
    // Método para classicar amostras com a rede treinada
    public void classificacao(){
        double sinal;
        System.out.printf("Classificação das amostras de operação:\n");
        for(int j = 0; j < amostras.length; j++){
            sinal = funcaoAtivacao(amostras[j][0], amostras[j][1], amostras[j][2]);
            if(sinal == -1.0000){
                System.out.printf("Amostra %d pertence a classe P1\n", j+1);
            }else if(sinal == 1.0000){
                System.out.printf("Amostra %d pertence a classe P2\n", j+1);
            }
        }
        System.out.printf("Classificação das amostras finalizada!\n");
        System.out.printf("\n==================================================\n\nFim do Programa!\n");
    }
    
    public static void main(String[] args) {
        //Instancia um objeto 
        Perceptron perc = new Perceptron();
        // Chama o método para Treinar a rede Perceptron
        perc.treinar();
        // Chama o método para Classificar as amostras
        perc.classificacao();
    }
}
