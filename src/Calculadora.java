import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// classe que monta a janela e cuida dos cliques nos botoes
// implementa ActionListener pra escutar os botoes
public class Calculadora extends JFrame implements ActionListener {

    // visor que mostra os numeros na tela
    private JTextField visor;

    // variaveis pra guardar o estado da calculadora
    private double numero1 = 0;          // primeiro numero da operacao
    private String operador = "";        // qual operador foi escolhido
    private boolean novoNumero = true;   // controla se vou comecar um numero novo

    // paleta de cores que escolhi pra deixar o visual clean
    private final Color CORFUNDO = new Color(245, 245, 245);
    private final Color CORVISOR = new Color(40, 40, 40);
    private final Color CORTEXTOVISOR = Color.WHITE;
    private final Color CORBOTAONUMERO = Color.WHITE;
    private final Color CORBOTAOOPERADOR = new Color(74, 144, 226);
    private final Color CORBOTAOFUNCAO = new Color(220, 220, 220);
    private final Color CORBOTAOIGUAL = new Color(245, 166, 35);
    private final Color CORTEXTOPRETO = new Color(50, 50, 50);
    private final Color CORBORDA = new Color(225, 225, 225);

    // construtor - aqui monto toda a janela
    public Calculadora() {
        // configuracoes basicas da janela
        setTitle("Calculadora");
        setSize(360, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // centraliza na tela do usuario
        setResizable(false);

        // uso BorderLayout pra colocar o visor em cima e os botoes embaixo
        setLayout(new BorderLayout(10, 10));
        getContentPane().setBackground(CORFUNDO);

        // chamo os metodos que montam cada parte da tela
        criarVisor();
        criarPainelBotoes();
    }

    // monta o visor que aparece no topo da janela
    private void criarVisor() {
        visor = new JTextField("0");
        visor.setEditable(false); // nao deixo o usuario digitar direto no visor
        visor.setHorizontalAlignment(JTextField.RIGHT); // numeros alinhados a direita
        visor.setFont(new Font("SansSerif", Font.PLAIN, 36));
        visor.setBackground(CORVISOR);
        visor.setForeground(CORTEXTOVISOR);
        visor.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        visor.setPreferredSize(new Dimension(360, 110));

        add(visor, BorderLayout.NORTH);
    }

    // monta o painel com a grade de botoes
    private void criarPainelBotoes() {
        JPanel painel = new JPanel();
        // GridLayout: 5 linhas, 4 colunas, com 8px de espaco entre os botoes
        painel.setLayout(new GridLayout(5, 4, 8, 8));
        painel.setBackground(CORFUNDO);
        painel.setBorder(BorderFactory.createEmptyBorder(0, 15, 15, 15));

        // ordem dos botoes igual aparecem na tela (esquerda pra direita, cima pra baixo)
        String[] textos = {
            "C",   "DEL", "%", "/",
            "7",   "8",   "9", "*",
            "4",   "5",   "6", "-",
            "1",   "2",   "3", "+",
            "+/-", "0",   ".", "="
        };

        // crio cada botao na ordem certa e adiciono no painel
        for (String texto : textos) {
            JButton botao = criarBotao(texto);
            painel.add(botao);
        }

        add(painel, BorderLayout.CENTER);
    }

    // metodo auxiliar pra criar um botao ja com o estilo certo
    // assim nao precisei repetir tudo pra cada botao
    private JButton criarBotao(String texto) {
        JButton botao = new JButton(texto);
        botao.setFont(new Font("SansSerif", Font.PLAIN, 18));
        botao.setFocusPainted(false); // tira o contorno feio quando o botao fica selecionado
        botao.addActionListener(this);
        botao.setBorder(BorderFactory.createLineBorder(CORBORDA, 1));

        // escolho a cor de acordo com o tipo do botao
        if (texto.equals("=")) {
            botao.setBackground(CORBOTAOIGUAL);
            botao.setForeground(Color.WHITE);
        } else if (texto.equals("+") || texto.equals("-") ||
                   texto.equals("*") || texto.equals("/")) {
            botao.setBackground(CORBOTAOOPERADOR);
            botao.setForeground(Color.WHITE);
        } else if (texto.equals("C") || texto.equals("DEL") ||
                   texto.equals("%") || texto.equals("+/-")) {
            botao.setBackground(CORBOTAOFUNCAO);
            botao.setForeground(CORTEXTOPRETO);
        } else {
            // sobra os numeros e o ponto
            botao.setBackground(CORBOTAONUMERO);
            botao.setForeground(CORTEXTOPRETO);
        }

        return botao;
    }

    // metodo que recebe TODOS os cliques de botao
    // a partir do texto do botao eu descubro o que fazer
    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();

        if (comando.equals("C")) {
            limpar();
        } else if (comando.equals("DEL")) {
            apagar();
        } else if (comando.equals("=")) {
            calcular();
        } else if (comando.equals("%")) {
            calcularPorcentagem();
        } else if (comando.equals("+/-")) {
            inverterSinal();
        } else if (comando.equals("+") || comando.equals("-") ||
                   comando.equals("*") || comando.equals("/")) {
            definirOperacao(comando);
        } else {
            // se nao caiu em nenhum if acima, eh numero ou ponto
            digitarNumero(comando);
        }
    }

    // adiciona o numero (ou o ponto) no visor
    private void digitarNumero(String digito) {
        // se eh um numero novo, comeco com o visor limpo
        if (novoNumero) {
            visor.setText("");
            novoNumero = false;
        }

        // evita que o usuario digite dois pontos no mesmo numero (tipo 1.2.3)
        if (digito.equals(".") && visor.getText().contains(".")) {
            return;
        }

        // se vai colocar ponto e o visor esta vazio, deixa "0." pra ficar mais bonito
        if (digito.equals(".") && visor.getText().isEmpty()) {
            visor.setText("0.");
            return;
        }

        // se o visor esta so com "0", troca pelo digito (evita ficar tipo "05")
        if (visor.getText().equals("0")) {
            visor.setText(digito);
        } else {
            visor.setText(visor.getText() + digito);
        }
    }

    // guarda o primeiro numero e o operador escolhido
    private void definirOperacao(String op) {
        // se ja tem uma operacao pendente, calculo primeiro
        // exemplo: 5 + 3 + 2 -> quando aperto o segundo "+" ja calculo 5+3
        if (!operador.isEmpty() && !novoNumero) {
            calcular();
        }
        // converto o texto do visor pra double pra conseguir trabalhar com decimais
        numero1 = Double.parseDouble(visor.getText());
        operador = op;
        novoNumero = true;
    }

    // faz a conta quando o usuario aperta "="
    private void calcular() {
        // se nao tem operador, nao tem o que calcular
        if (operador.isEmpty()) {
            return;
        }

        double numero2 = Double.parseDouble(visor.getText());
        double resultado = 0;

        // chamo o metodo certo da classe Operacoes de acordo com o operador
        // uso try/catch pra tratar erro de divisao por zero
        try {
            if (operador.equals("+")) {
                resultado = Operacoes.somar(numero1, numero2);
            } else if (operador.equals("-")) {
                resultado = Operacoes.subtrair(numero1, numero2);
            } else if (operador.equals("*")) {
                resultado = Operacoes.multiplicar(numero1, numero2);
            } else if (operador.equals("/")) {
                resultado = Operacoes.dividir(numero1, numero2);
            }

            visor.setText(formatarResultado(resultado));
        } catch (ArithmeticException ex) {
            // aqui caio se o usuario tentou dividir por zero
            visor.setText("Erro");
        }

        // limpo o operador e marco que o proximo numero comeca novo
        operador = "";
        novoNumero = true;
    }

    // calcula a porcentagem do numero atual no visor
    private void calcularPorcentagem() {
        try {
            double valor = Double.parseDouble(visor.getText());
            double resultado = Operacoes.porcentagem(valor);
            visor.setText(formatarResultado(resultado));
            novoNumero = true;
        } catch (NumberFormatException ex) {
            // se o visor estiver com "Erro", a conversao falha, entao volto pra zero
            visor.setText("0");
        }
    }

    // limpa tudo - volta pro estado inicial
    private void limpar() {
        visor.setText("0");
        numero1 = 0;
        operador = "";
        novoNumero = true;
    }

    // apaga so o ultimo digito (funciona como o backspace)
    private void apagar() {
        String texto = visor.getText();
        // se tem um digito so ou estiver com "Erro", volto pra "0"
        if (texto.length() <= 1 || texto.equals("Erro")) {
            visor.setText("0");
            novoNumero = true;
        } else {
            // tiro o ultimo caractere usando substring
            visor.setText(texto.substring(0, texto.length() - 1));
        }
    }

    // troca o sinal do numero atual (positivo vira negativo e vice-versa)
    private void inverterSinal() {
        try {
            double valor = Double.parseDouble(visor.getText());
            valor = valor * -1;
            visor.setText(formatarResultado(valor));
        } catch (NumberFormatException ex) {
            // se nao conseguir converter (ex: visor com "Erro"), nao faz nada
        }
    }

    // formata o resultado pra ficar mais bonito no visor
    // se for um numero inteiro, mostro sem o ".0" no final
    // se for decimal, mostro normal
    private String formatarResultado(double numero) {
        if (numero == (long) numero) {
            return String.valueOf((long) numero);
        } else {
            return String.valueOf(numero);
        }
    }
}
