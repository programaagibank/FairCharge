# FairCharge - Sistema de Cobrança e Faturamento
> Repositório do projeto FairCharge, no qual será atualizado de acordo com o desenvolvimento da aplicação.  
> Está disponível a apresentação inicial em slides, através do link a seguir. - [ApresentaçãoInicial](https://1drv.ms/p/c/80d7c904e44eaca4/EeGGJoyYgxxAvsVpmIJQhl4BpD2HHR9fa2NDespsTJreSQ?e=KVI6EJ)

Equipe do projeto  - 
[@GFavareto](https://github.com/GFavareto)
[@Liliansod](https://github.com/Liliansod)
[@Joao-PauloDev](https://github.com/Joao-PauloDev)
[@tulionet](https://github.com/tulionet)
[@igorViotto](https://github.com/igorViotto)

</br>

# 1. Apresentação do Projeto
O FairCharge é um sistema de cobrança e faturamento desenvolvido para auxiliar bancos e instituições financeiras no **gerenciamento de cobranças, inadimplência e faturamento**.
Ele garante que todas as cobranças sejam registradas corretamente, monitoradas em tempo real e atualizadas conforme o status de pagamento.
</br>
</br>

# 2. Objetivos
- Automatizar o processo de cobrança, garantindo maior eficiência financeira.  
- Monitorar pagamentos recebidos e inadimplências de clientes.  
- Aplicar juros e multas em cobranças vencidas de forma automática.  
- Gerar relatórios financeiros detalhados para auditoria e controle de faturamento.  
- Integrar-se com sistemas de terceiros, como plataformas de pagamentos e análise de risco.  

</br>

# 3. Público-Alvo
O FairCharge é voltado para **instituições financeiras, bancos e empresas** que necessitam de:  
- uma solução eficiente para cobrança e faturamento.
-  Automatização no processo de cobrança.
-  Redução da inadimplência e melhor previsão financeira.

</br>

# 4. Principais Funcionalidades
**Registro de Cobranças** - Cadastro de cobranças automáticas (tarifas, empréstimos, boletos) com valores, datas de vencimento e status.  

**Gerenciamento de Pagamentos** - Atualiza cobranças pagas através da comunicação com sistemas externos.  

**Aplicação de Juros e Multas** - Cálculo automático de encargos sobre cobranças vencidas.  

**Monitoramento de DDA** - Registro de cobranças eletrônicas sem necessidade de boletos físicos.  

**Relatórios Financeiros** - Geração de relatórios sobre valores cobrados, recebidos e inadimplência.  

**Integração com Outros Sistemas** - Conexão com sistemas de pagamentos e análise de risco.  

</br>

# 5. Requisitos Técnicos
- Linguagem de Programação: **Java (versão 8 ou superior)**.  
- Banco de Dados: **MySQL (DBeaver)**.  
- IDE: **IntelliJ IDEA**.  
- Controle de Versão: **Git (repositório no GitHub)**.  
- Plataforma: **Back-end executado no servidor e acessado via banco**.  

</br>

# 6. Fluxo de Uso
1. O sistema <ins>recebe dados de cobranças</ins> de sistemas externos (exemplo: Empréstimos, Tarifas Bancárias). 

2. <ins>Registra as cobranças no banco de dados</ins>, definindo o status inicial como pendente.  

3. <ins>A cobrança pode ser consultada</ins> a qualquer momento no banco de dados.  

4. <ins>Se houver pagamento, o status é atualizado para pago automaticamente</ins> através da integração com o sistema de pagamentos.  

5. <ins>Caso a cobrança não seja paga</ins> até a data de vencimento, <ins>juros e multas são aplicados</ins> automaticamente.  

6. O <ins>sistema gera um relatório de faturamento</ins> com todas as cobranças, pagamentos e inadimplências.

</br>

# 7. Exemplo de Interação via CLI
Gerar Relatório de Faturamento

Entrada no Terminal:
````
Bem-vindo ao FairCharge - Sistema de Cobrança e Faturamento  
Selecione uma opção:  
1 - Gerar relatório de faturamento  
2 - Listar cobranças pendentes  
3 - Sair  
> 1  
````
Saída no Terminal:
```
Relatório de Faturamento - Mês: Março/2024  
------------------------------------------------  
Total de cobranças registradas: 150  
Total recebido: R$ 250.000,00  
Total pendente: R$ 50.000,00  
Total inadimplente: R$ 10.000,00  

Detalhamento por categoria:  
Empréstimos: R$ 200.000,00 recebidos / R$ 30.000,00 pendentes  
Tarifas Bancárias: R$ 30.000,00 recebidos / R$ 10.000,00 pendentes  
Boletos Empresariais: R$ 20.000,00 recebidos / R$ 10.000,00 inadimplentes  
------------------------------------------------  
Fim do Relatório.
```

# 8. Benefícios
- Automatiza o processo de cobrança, reduzindo erros manuais.
- Facilita a gestão de inadimplência, garantindo previsibilidade financeira.
- Gera relatórios detalhados, permitindo análise de faturamento e desempenho financeiro.
- Permite integração flexível, facilitando conexão com outros sistemas bancários.

