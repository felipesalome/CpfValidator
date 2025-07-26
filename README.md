# Documentação: Validador de CPF - Regra de Negócio

[![Test Coverage](https://raw.githubusercontent.com/felipesalome/CpfValidator/main/badges/jacoco.svg)](https://github.com/felipesalome/CpfValidator/actions)
[![Branches Coverage](https://raw.githubusercontent.com/felipesalome/CpfValidator/main/badges/branches.svg)](https://github.com/felipesalome/CpfValidator/actions)

## 1. Introdução
O validador de CPF é um componente que verifica a autenticidade de números do Cadastro de Pessoas Físicas brasileiro. Esta documentação explica exclusivamente a regra de negócio e a abordagem TDD para implementação, **sem qualquer menção a código**.

## 2. Regras de Validação

### 2.1. Pré-processamento
- Remover todos os caracteres não numéricos (pontos, hífens, espaços)
- Verificar se o resultado possui exatamente 11 dígitos
- Rejeitar sequências com todos os dígitos repetidos (ex: 111.111.111-11)

### 2.2. Cálculo do Primeiro Dígito Verificador
1. Multiplicar cada um dos 9 primeiros dígitos por um peso decrescente iniciando em 10:
   - Dígito 1 × 10
   - Dígito 2 × 9
   - ...
   - Dígito 9 × 2
2. Somar todos os resultados das multiplicações
3. Calcular o resto da divisão da soma por 11
4. Se resto < 2: primeiro dígito verificador deve ser 0
5. Se resto ≥ 2: primeiro dígito verificador deve ser 11 - resto

### 2.3. Cálculo do Segundo Dígito Verificador
1. Incluir o primeiro dígito verificador como 10º dígito
2. Multiplicar cada um dos 10 dígitos por um peso decrescente iniciando em 11:
   - Dígito 1 × 11
   - Dígito 2 × 10
   - ...
   - Dígito 10 × 2
3. Somar todos os resultados das multiplicações
4. Calcular o resto da divisão da soma por 11
5. Se resto < 2: segundo dígito verificador deve ser 0
6. Se resto ≥ 2: segundo dígito verificador deve ser 11 - resto

### 2.4. Validação Final
Comparar os dígitos verificadores calculados com os dois últimos dígitos do CPF fornecido:
- Se coincidirem → CPF válido
- Se divergirem → CPF inválido

## 3. Fluxo de Validação
1. Receber entrada (string)
2. Aplicar pré-processamento
3. Verificar tamanho e dígitos repetidos
4. Calcular primeiro dígito verificador
5. Calcular segundo dígito verificador
6. Comparar dígitos calculados com dígitos reais
7. Retornar resultado booleano

## 4. Abordagem TDD (Test-Driven Development)

### 4.1. Ciclo de Desenvolvimento
Seguir rigorosamente o ciclo **Red-Green-Refactor**:
1. **Red**: Escrever teste falho
2. **Green**: Implementar mínima funcionalidade para passar no teste
3. **Refactor**: Melhorar a implementação mantendo os testes passando

### 4.2. Casos de Teste Essenciais
**Grupo 1: Pré-processamento**
- Entrada com pontos e hífen válidos
- Entrada com espaços e caracteres especiais
- Entrada com menos de 11 dígitos
- Entrada com mais de 11 dígitos
- Entrada com caracteres não numéricos misturados

**Grupo 2: Dígitos Repetidos**
- Todos os dígitos iguais
- Sequências inválidas conhecidas

**Grupo 3: Cálculo de Dígitos Verificadores**
- CPFs conhecidos válidos (ex: 529.982.247-25)
- CPFs com primeiro dígito verificador inválido
- CPFs com segundo dígito verificador inválido
- CPFs com ambos dígitos verificadores inválidos

**Grupo 4: Casos Especiais**
- Entrada vazia
- Entrada nula
- Entrada com letras misturadas
- CPFs válidos com diferentes formatações

### 4.3. Estratégia de Implementação TDD
1. **Fase 1: Pré-processamento**
   - Escrever testes para limpeza de caracteres especiais
   - Implementar funcionalidade de sanitização
   - Escrever testes para verificação de tamanho
   - Implementar validação de tamanho

2. **Fase 2: Validação Inicial**
   - Escrever testes para dígitos repetidos
   - Implementar verificação de dígitos idênticos

3. **Fase 3: Cálculo de Verificadores**
   - Escrever testes para cálculo do primeiro dígito
   - Implementar algoritmo do primeiro verificador
   - Escrever testes para cálculo do segundo dígito
   - Implementar algoritmo do segundo verificador

4. **Fase 4: Integração**
   - Escrever testes para fluxo completo com CPFs válidos
   - Escrever testes para fluxo completo com CPFs inválidos
   - Implementar integração dos módulos

### 4.4. Critérios de Aceitação
- 100% de cobertura de casos conhecidos
- Rejeição de todos os CPFs inválidos do grupo de teste
- Aceitação de todos os CPFs válidos do grupo de teste
- Tratamento adequado de entradas malformadas
- Execução de todos os testes com sucesso antes do refactor

## 5. Considerações Finais
O validador deve seguir estritamente o algoritmo oficial de cálculo de dígitos verificadores estabelecido pela Receita Federal. A implementação deve ser insensível a formatações, porém rigorosa na validação matemática. A abordagem TDD garantirá que cada componente seja validado incrementalmente antes da integração final.