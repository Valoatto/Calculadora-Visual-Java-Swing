# calculadora-visual-java-swing
Minha primeira calculadora em Java! Calculadora desktop em Java com Swing. Projeto acadêmico com interface limpa, operações básicas, porcentagem e tratamento de divisão por zero.
Esse projeto foi criado como prática durante meus estudos em Análise e Desenvolvimento de Sistemas, com o objetivo de reforçar conceitos de Programação Orientada a Objetos, manipulação de eventos e construção de interfaces gráficas.

---

# Sobre o projeto

Esse foi um dos meus primeiros projetos com interface gráfica em Java. Focamos em praticar conceitos importantes enquanto construíamos algo visualmente bonito e funcional.

Durante o desenvolvimento, aproveitei para estudar e praticar:

- Estrutura básica de interfaces gráficas com Swing
- Organização de componentes usando layouts
- Manipulação de eventos com `ActionListener`
- Conversão de valores (`String` ↔ `double`)
- Separação entre lógica da interface e lógica matemática
- Tratamento de erros utilizando `try/catch`

---

# Funcionalidades

- Soma
- Subtração
- Multiplicação
- Divisão
- Porcentagem
- Limpar visor (`C`)
- Apagar último dígito (`DEL`)
- Inverter sinal (`+/-`)
- Tratamento de divisão por zero
- Suporte a números decimais
- Operações em sequência

---

# Tecnologias utilizadas

- **Java** (Java 8 ou superior)
- **Swing** (biblioteca padrão do Java para interfaces gráficas)

Não é necessário instalar bibliotecas externas. Apenas o JDK já é suficiente para executar o projeto.

---

# Estrutura do projeto

```bash
calculadora-visual/
├── src/
│   ├── Main.java
│   ├── Calculadora.java
│   └── Operacoes.java
├── .gitignore
└── README.md
```

### Organização dos arquivos

| Arquivo | Responsabilidade |
| --- | --- |
| `Main.java` | Inicializa a aplicação |
| `Calculadora.java` | Interface gráfica e eventos dos botões |
| `Operacoes.java` | Métodos das operações matemáticas |

---

# Como o código foi organizado

Tentei separar cada parte do projeto em arquivos diferentes para deixar o código mais limpo e fácil de entender.

## Main.java

Responsável apenas por iniciar a aplicação e abrir a janela principal.

## Operacoes.java

Contém toda a lógica matemática da calculadora. Separei essa parte da interface para evitar deixar tudo em um único arquivo e facilitar futuras melhorias.:

- soma
- subtração
- multiplicação
- divisão
- porcentagem

## Calculadora.java

Responsável pela interface gráfica. Os botões utilizam `ActionListener` para identificar qual ação deve ser executada:

- criação da janela
- visor
- botões
- cores
- eventos de clique

---

# Lógica principal

A calculadora funciona utilizando três variáveis principais:

| Variável | Função |
| --- | --- |
| `numero1` | Guarda o primeiro número |
| `operador` | Guarda a operação escolhida |
| `novoNumero` | Controla quando iniciar um novo número no visor |

Fluxo básico:

1. O usuário digita um número
2. Escolhe uma operação
3. O valor é armazenado
4. O segundo número é digitado
5. Ao clicar em `=`, o cálculo é realizado
6. O resultado aparece no visor

---

# Tratamento de erros

Para evitar que o programa quebre em divisões por zero, utilizei `try/catch`. Quando isso acontece, a calculadora mostra `"Erro"` no visor ao invés de encerrar a aplicação.

---

# O que aprendi com esse projeto

Durante esse projeto consegui praticar bastante:

- Uso de `BorderLayout` e `GridLayout`
- Manipulação de eventos com `ActionListener`
- Conversão entre texto e números
- Tratamento de exceções com `try/catch`
- Organização de código em múltiplas classes
- Criação de interfaces gráficas no Java
- Separação entre lógica visual e lógica matemática

Também aproveitei para treinar organização de projeto e estrutura de repositório no GitHub.

---

# Melhorias futuras

Algumas ideias que pretendo adicionar futuramente:

- Suporte ao teclado
- Histórico de operações
- Tema escuro
- Operações científicas
- Melhorias visuais
- Testes unitários
- Gerar versão executável `.jar`
