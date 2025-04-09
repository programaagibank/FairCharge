# FairCharge - Sistema de Cobrança e Faturamento

> Repositório oficial do **FairCharge**, sistema desenvolvido para automatizar e otimizar o processo de **faturamento e cobrança bancária**, com interface gráfica e aplicação de regras financeiras em tempo real.  
> A apresentação do projeto está disponível neste link:  
> [Apresentação FairCharge](https://1drv.ms/p/c/80d7c904e44eaca4/EeGGJoyYgxxAvsVpmIJQhl4BpD2HHR9fa2NDespsTJreSQ?e=KVI6EJ)

👨‍💻 **Equipe de Desenvolvimento**  
[@GFavareto](https://github.com/GFavareto)  
[@Liliansod](https://github.com/Liliansod)  
[@Joao-PauloDev](https://github.com/Joao-PauloDev)  
[@tulionet](https://github.com/tulionet)  
[@igorViotto](https://github.com/igorViotto)

---

## 1. Visão Geral

O **FairCharge** é um sistema de cobrança e faturamento completo que permite:

- Geração de faturas e cobranças recorrentes ou avulsas;
- Aplicação automática de **multas e juros diários progressivos** em cobranças vencidas;
- Pagamento de faturas diretamente na interface por **duplo clique**;
- Geração de **relatórios de faturamento por cliente ou período**;
- Interface gráfica desenvolvida com JavaFX e Scene Builder.

---

## 2. Objetivos

- Automatizar o ciclo de faturamento e cobrança bancária.
- Reduzir inadimplência e atrasos com notificações e atualização de status.
- Prover uma interface simples e intuitiva para visualização e controle de cobranças.
- Permitir relatórios financeiros completos para análise de desempenho.

---

## 3. Público-Alvo

- **Bancos**, **financeiras** e **empresas** que realizam faturamento recorrente.
- Departamentos de cobrança com alto volume de transações.
- Equipes que precisam de uma ferramenta visual para controle financeiro interno.

---

## 4. Principais Funcionalidades

✅ **Interface Gráfica com JavaFX**  
Design mobile-first com foco em simplicidade e clareza.

✅ **Geração de Faturas**  
Realizada diretamente no sistema com base em contratos, tarifas e serviços prestados. As regras de geração são implementadas no código, com apoio de consultas SQL.

✅ **Aplicação de Multas e Juros**  
Cálculo com base na data de vencimento, feito via botão específico na interface.

✅ **Pagamento de Cobranças**  
Efetuado diretamente na interface por meio de **duplo clique**, que atualiza o status da cobrança para "Pago" dentro do sistema.

✅ **Relatórios de Faturamento**  
Filtros por cliente, mês e ano. Exibe totais de recebidos, pendentes e inadimplência.

✅ **Login Seguro**  
Tela de login integrada com controle de acesso por usuário.

---

## 5. Arquitetura do Projeto

- **Padrão MVC** (Model-View-Controller)
- Separação por pacotes: `controller`, `view`, `model`, `utils`, `database`
- Banco de Dados relacional com normalização das tabelas


---

## 6. Tecnologias Utilizadas

| Recurso              | Ferramenta/Versão            |
|----------------------|------------------------------|
| Linguagem            | Java 21         |
| Framework UI         | JavaFX      |
| Gerenciador de Build | Gradle                       |
| Banco de Dados       | MySQL|
| IDE                  | IntelliJ IDEA                |
| Versionamento        | Git + GitHub                 |
| Metodologia Ágil     | Scrum (gestão via Jira)      |

---

## 7. Telas do Sistema

- Tela de Login
- Tela Inicial
- Tela de Seleção de geração de relatórios de faturamento
- Relatórios de faturamento por banco ou cliente especifico
- Tela de Informações de Pagamentos
- Tela de gráfico de Pagamento
- Tela de Ajuste de Taxa diária e multa
- Tela de Histórico de taxa diária e multa
- Tela de Seleção de funcionalidades de cobranças
- Tela de Relatório das cobranças vencidas
- Tela de Relatório de todas as cobranças
- Tela de Gerenciador de Usuários 

As telas seguem a identidade visual do **AGIBANK**, com foco em responsividade e experiência do usuário.

---

## 8. Fluxo de Funcionamento

1. Usuário acessa o sistema e faz login.
2. É exibido a tela inicial com seleção dos botões de Relatório de Faturamento, Listar Pagamentos, Ajustar Taxas, Menu Cobranças
3. O usuário pode gerar cobranças, pagar cobranças, ajustar valores das taxas.
4. A qualquer momento, é possível gerar relatórios do banco ou cliente.
5. O status das cobranças é atualizado ao selecionar "Atualizar cobranças".

---

## 9. Exemplo de Interação Visual

**Exemplo: Pagamento de fatura vencida**

> O usuário navega até a tela de cobranças e **dá duplo clique** sobre uma fatura vencida. O sistema atualiza o status para "Pago".

**Exemplo: Geração de relatório de faturamento**

> Seleciona cliente e período → Clica em "Gerar Relatório" → Visualiza valores totais recebidos, pendentes e inadimplentes, com detalhamento por tipo de cobrança.

---

## 10. Benefícios da Solução

- Redução da inadimplência com gestão visual e interativa.
- Geração rápida de relatórios financeiros.
- Aplicação automatizada de multas e juros.
- Interface intuitiva e responsiva, sem curva de aprendizado.

---

## 11. Como Rodar o Projeto

```bash
# Clone o repositório
git clone https://github.com/seu-usuario/FairCharge.git

# Navegue até o projeto
cd FairCharge

# Rode o projeto via Gradle
./gradlew run
