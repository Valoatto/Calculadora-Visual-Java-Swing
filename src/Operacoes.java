// classe que junta as operacoes matematicas
// separei dessa forma pra deixar o codigo da interface mais limpo
// e tambem porque assim fica facil de testar cada metodo no futuro
public class Operacoes {

    // soma simples de dois numeros
    public static double somar(double a, double b) {
        return a + b;
    }

    // subtracao
    public static double subtrair(double a, double b) {
        return a - b;
    }

    // multiplicacao
    public static double multiplicar(double a, double b) {
        return a * b;
    }

    // divisao - aqui trato divisao por zero
    // se b for zero, lanco uma excecao pra quem chamou tratar
    public static double dividir(double a, double b) {
        if (b == 0) {
            throw new ArithmeticException("Nao da pra dividir por zero");
        }
        return a / b;
    }

    // calcula a porcentagem de um valor (basicamente divide por 100)
    public static double porcentagem(double valor) {
        return valor / 100;
    }
}
