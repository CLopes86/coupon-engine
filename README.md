# 🎫 Coupon Engine - Sistema de Gestão de Cupões e Fidelização

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Status](https://img.shields.io/badge/Status-Em%20Desenvolvimento-yellow.svg)
![VS Code](https://img.shields.io/badge/Editor-VS%20Code-blue.svg?logo=visual-studio-code)

> Sistema inteligente de gestão de cupões de desconto e cartões de fidelização desenvolvido como projeto académico da disciplina de **Padrões e Desenho de Software(PDS)** no **Instituto Politécnico de Castelo Branco** - Curso de **Informática e Multimédia**.

---

## 📋 Índice

- [Sobre o Projeto](#sobre-o-projeto)
- [Funcionalidades](#funcionalidades)
- [Tecnologias Utilizadas](#tecnologias-utilizadas)
- [Arquitetura](#arquitetura)
- [Como Executar](#como-executar)
- [Conceitos Aplicados](#conceitos-de-programação-aplicados)
- [Roadmap](#roadmap)
- [Autor](#autor)

---

## Sobre o Projeto

O **Coupon Engine** é um sistema robusto de gestão de cupões de desconto para a cadeia de lojas fictícia **HonESTa**. O projeto demonstra a aplicação prática de conceitos de **Programação Orientada a Objetos** em Java.

### Destaques Técnicos

- Arquitetura em Camadas com separação clara de responsabilidades
- Programação Orientada a Objetos (Encapsulamento, Herança, Polimorfismo)
- Classes Abstratas para estruturar hierarquia de cupões
- Polimorfismo permitindo diferentes tipos de cupões com comportamento único
- Validação Robusta usando classe utilitária Validator
- Código Limpo seguindo convenções Java
- Sistema Extensível fácil de adicionar novos tipos de cupões

---

## Funcionalidades

### Cartões de Fidelização

- Gestão completa de cartões de clientes
- Acumulação de saldo através de cupões
- Ativação/desativação seletiva de cupões
- Validação automática de prazos de validade
- Atualização automática (remove cupões expirados)

### Sistema de Cupões (3 Tipos)

| Tipo | Descrição | Exemplo de Uso |
|------|-----------|----------------|
| **Cupão de Produtos** | Desconto em produtos específicos | 15% de desconto em Massas selecionadas |
| **Cupão de Marca** | Desconto em todos os produtos de uma marca | 10% em todos os produtos Albicereal |
| **Cupão de Compra** | Desconto aplicado a toda a compra | 5% em qualquer compra realizada |

**Regras Importantes:**

- Cupões NÃO são cumulativos - aplica-se sempre o de maior desconto
- Descontos são acumulados em saldo (não são descontos imediatos no preço)
- Cupões têm prazo de validade (início e fim)
- Sistema de priorização automática (maior desconto primeiro)

### Gestão de Vendas

- Processamento de vendas com múltiplos produtos
- Aplicação automática de cupões ativos
- Cálculo inteligente de descontos
- Acumulação de saldo no cartão do cliente
- Histórico de cupões utilizados

### Inventário

- Cadastro e gestão de produtos
- Armazenamento de informação: código, marca, modelo, preço
- Gestão de cupões disponíveis
- Gestão de cartões de clientes
- Preços armazenados em cêntimos (evita erros de arredondamento com decimais)

---

## Tecnologias Utilizadas

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| Java | 17+ | Linguagem principal |
| VS Code | Latest | IDE de desenvolvimento |
| Git | Latest | Controlo de versão |
| Linux | Ubuntu | Sistema Operativo |

**APIs Java Utilizadas:**

- `java.time` - Gestão de datas (LocalDate)
- `java.util` - Collections Framework (ArrayList, HashMap, List, Map)
- `java.io` - Leitura de ficheiros (BufferedReader, FileReader)
- `java.util.Objects` - Validação de objetos nulos

**Sem dependências externas** - Projeto utiliza apenas a biblioteca padrão do Java!

---

## Arquitetura

### Diagrama de Pacotes
```
┌─────────────────────────────────────────────────────────────┐
│                     coupon-engine                           │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │ cliente  │  │ comercio │  │   util   │  │   menu   │   │
│  │          │  │          │  │          │  │          │   │
│  │ Cartao   │  │ Produto  │  │Validator │  │   Main   │   │
│  │ Cupao*   │  │ Venda    │  │          │  │          │   │
│  │ Cupoes   │  │Inventario│  │          │  │          │   │
│  └──────────┘  └──────────┘  └──────────┘  └──────────┘   │
│       │             │              │              │        │
│       └─────────────┴──────────────┴──────────────┘        │
└─────────────────────────────────────────────────────────────┘
```

### Estrutura de Diretórios
```
coupon-engine/
│
├── src/                            # Código fonte
│   │
│   ├── cliente/                    # Camada de Cliente
│   │   ├── Cartao.java            # Gestão de cartões de fidelização
│   │   ├── Cupao.java             # Classe abstrata base para cupões
│   │   ├── CupaoProdutos.java     # Cupão para produtos específicos
│   │   ├── CupaoMarca.java        # Cupão para marca específica
│   │   └── CupaoCompra.java       # Cupão para toda a compra
│   │
│   ├── comercio/                   # Camada de Negócio
│   │   ├── ProdutoInfo.java       # Informação geral de produtos
│   │   ├── ProdutoVendido.java    # Produto numa venda específica
│   │   ├── Venda.java             # Gestão de vendas
│   │   └── Inventario.java        # Repositório de dados (memória)
│   │
│   ├── util/                       # Utilitários
│   │   └── Validator.java         # Validações centralizadas
│   │
│   └── menu/                       # Interface
│       └── Main.java              # Ponto de entrada da aplicação
│
├── dados/                          # Ficheiros de dados (TSV)
│   ├── produtos.hnt               # Lista de produtos
│   ├── cupoes.hnt                 # Lista de cupões
│   └── cartoes.hnt                # Lista de cartões
│
├── bin/                            # Ficheiros compilados (.class)
│
├── .gitignore                      # Ficheiros ignorados pelo Git
├── LICENSE                         # Licença MIT
└── README.md                       # Este ficheiro
```

---

## Como Executar

### Pré-requisitos

- **Java JDK 17 ou superior** instalado
- **VS Code** com Extension Pack for Java (recomendado)
- **Git** configurado (opcional)

### Verificar Instalação do Java
```bash
java -version    # Deve mostrar versão 17+
javac -version   # Deve mostrar versão 17+
```

---

### Método 1: Executar no VS Code (RECOMENDADO)

**1. Clone o repositório**
```bash
git clone https://github.com/CLopes86/coupon-engine.git
cd coupon-engine
```

**2. Abra no VS Code**
```bash
code .
```

**3. Execute a aplicação**

- Abra o ficheiro `Main.java` (em `src/menu/`)
- Clique no botão ▶️ **"Run"** que aparece acima do método `main()`
- Ou pressione `F5`

---

### Método 2: Executar via Terminal
```bash
# 1. Navegar para a pasta do projeto
cd coupon-engine

# 2. Compilar todo o projeto
javac -d bin -sourcepath src src/**/*.java

# 3. Executar a aplicação principal
java -cp bin menu.Main
```

---

### Método 3: Script de Compilação (Linux/Mac)

Crie um ficheiro `compile.sh`:
```bash
#!/bin/bash
echo "🔨 Compilando projeto..."
javac -d bin -sourcepath src src/**/*.java

if [ $? -eq 0 ]; then
    echo "✅ Compilação bem-sucedida!"
    echo "▶️  Executando aplicação..."
    java -cp bin menu.Main
else
    echo "❌ Erro na compilação!"
fi
```

Torne executável e execute:
```bash
chmod +x compile.sh
./compile.sh
```

---

## Conceitos de Programação Aplicados

### 1. Encapsulamento

Campos `private` protegem dados e o acesso é controlado via getters/setters com validação centralizada.
```java
public class ProdutoInfo {
    private String codigoBarras;  // Private - protegido
    private long preco;
    
    public long getPreco() {      // Getter público
        return preco;
    }
}
```

### 2. Herança

Classe abstrata `Cupao` como base e subclasses especializadas: `CupaoProdutos`, `CupaoMarca`, `CupaoCompra`.
```java
public abstract class Cupao {
    // Código comum a todos os cupões
}

public class CupaoProdutos extends Cupao {
    // Implementação específica
}
```

### 3. Polimorfismo

Mesmo método `abrange()` com comportamento diferente. Lista de `Cupao` pode conter qualquer tipo de cupão.
```java
List cupoes = new ArrayList<>();
cupoes.add(new CupaoProdutos(...));
cupoes.add(new CupaoMarca(...));
cupoes.add(new CupaoCompra(...));

// Polimorfismo: cada cupão executa seu próprio abrange()
for (Cupao c : cupoes) {
    c.abrange(produto);  // Comportamento depende do tipo real
}
```

### 4. Composição

- `ProdutoVendido` **tem um** `ProdutoInfo`
- `Venda` **tem uma lista de** `ProdutoVendido`
- `Cartao` **tem uma lista de** `Cupao`

### 5. Collections Framework

- `ArrayList` para listas dinâmicas
- `HashMap` para busca rápida por chave
- `Collections.unmodifiableList()` para proteção

---

## Roadmap

### Fase 1 - Concluída

- [x] Estrutura base de todas as classes
- [x] Implementação dos 3 tipos de cupões
- [x] Sistema de validação robusto
- [x] Leitura de ficheiros de dados
- [x] Sistema de aplicação de cupões
- [x] Documentação completa

### Fase 2 - Em Desenvolvimento

- [ ] Tratamento de erros mais robusto
- [ ] Melhorar interface de linha de comandos
- [ ] Sistema de relatórios básicos
- [ ] Validação adicional de casos extremos

### Fase 3 - Futuro

- [ ] Interface gráfica (Swing ou JavaFX)
- [ ] Persistência em ficheiros/base de dados
- [ ] Sistema de histórico de vendas
- [ ] Estatísticas de uso de cupões
- [ ] Exportação de relatórios (PDF, CSV)

---

## Métricas do Projeto

- **Linhas de Código:** ~1500+
- **Classes:** 15+
- **Métodos:** 100+
- **Packages:** 4 (cliente, comercio, util, menu)
- **Tempo de Desenvolvimento:** 3+ semanas

---

## Aprendizagens

Este projeto permitiu aprender e aplicar:

- Orientação a Objetos na prática
- Hierarquia de classes e herança
- Polimorfismo e classes abstratas
- Collections Framework (ArrayList, HashMap)
- Tratamento de exceções robusto
- Leitura e parse de ficheiros
- Git e controlo de versão
- Documentação de código (Javadoc)
- Boas práticas de programação Java
- Arquitetura em camadas

---

## Autor

**Cesaltino Lopes**

Estudante de **Informática e Multimédia**  
Instituto Politécnico de Castelo Branco

**Contactos:**
- GitHub: [CLopes86](https://github.com/CLopes86)
- LinkedIn: [Cesaltino Lopes](https://www.linkedin.com/in/cesaltino-lopes-55274b176/)
- Email: c.lopes46cv@gmail.com

---

## Licença

Este projeto está sob a licença **MIT**. Consulte o ficheiro [LICENSE](LICENSE) para mais detalhes.

---

## Agradecimentos

- Instituto Politécnico de Castelo Branco
- Docente de Padroes e Desenho de Software
- Comunidade Java
- Colegas de curso pelo apoio

---

## Recursos Utilizados

- [Documentação Oficial do Java](https://docs.oracle.com/en/java/)
- [Java Tutorial - Oracle](https://docs.oracle.com/javase/tutorial/)
- [Stack Overflow](https://stackoverflow.com/)
- Apontamentos da disciplina de PDS

---

**Se este projeto foi útil, considera dar uma estrela! ⭐**

**Desenvolvido com ☕ Java e 💙 no VS Code**

**2025 - Instituto Politécnico de Castelo Branco**
```
