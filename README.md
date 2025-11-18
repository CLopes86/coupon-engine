<div align="center">
  <img src="https://github.com/CLopes86.png" width="120" style="border-radius: 50%;" alt="Cesaltino Lopes"/>
  
  # 🎫 Coupon Engine
  
  ### Sistema Inteligente de Gestão de Cupões e Fidelização
  
  ![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
  ![OOP](https://img.shields.io/badge/OOP-100%25-blue?style=for-the-badge)
  ![Design Patterns](https://img.shields.io/badge/Design_Patterns-✓-green?style=for-the-badge)
  ![Status](https://img.shields.io/badge/Status-Completo-brightgreen?style=for-the-badge)
  
  **Projeto Académico | Padrões e Desenho de Software | IPCB 2024/2025**
  
  [📖 Documentação](#-índice) • [🚀 Como Executar](#-como-executar) • [💡 Conceitos](#-conceitos-de-programação-aplicados) • [👨‍💻 Autor](#-autor)
  
</div>

---

## 🎯 Sobre o Projeto

> Sistema robusto de gestão de cupões de desconto e cartões de fidelização para a cadeia de lojas fictícia **HonESTa**, desenvolvido como projeto académico da disciplina de **Padrões e Desenho de Software (PDS)** no **Instituto Politécnico de Castelo Branco**.

Este projeto demonstra a aplicação prática de **conceitos avançados de Programação Orientada a Objetos** e **Design Patterns** em Java, criando um sistema completo, extensível e manutenível.

### 🌟 Destaques Técnicos

<table>
  <tr>
    <td align="center">🏗️<br><b>Arquitetura<br>em Camadas</b></td>
    <td align="center">🎭<br><b>Polimorfismo<br>Completo</b></td>
    <td align="center">✅<br><b>Validação<br>Robusta</b></td>
    <td align="center">📚<br><b>Documentação<br>Javadoc</b></td>
  </tr>
  <tr>
    <td align="center">🔒<br><b>Encapsulamento<br>Total</b></td>
    <td align="center">🌳<br><b>Herança<br>Hierárquica</b></td>
    <td align="center">🧩<br><b>Composição<br>de Classes</b></td>
    <td align="center">🚀<br><b>Sistema<br>Extensível</b></td>
  </tr>
</table>

---

## 📋 Índice

- [⚡ Funcionalidades](#-funcionalidades)
- [🛠️ Tecnologias](#-tecnologias-utilizadas)
- [🏗️ Arquitetura](#-arquitetura)
- [🚀 Como Executar](#-como-executar)
- [💡 Conceitos OOP](#-conceitos-de-programação-aplicados)
- [📖 Exemplos de Uso](#-exemplos-de-uso)
- [🧪 Testes](#-testes)
- [📊 Métricas](#-métricas-do-projeto)
- [👨‍💻 Autor](#-autor)

---

## ⚡ Funcionalidades

### 💳 Sistema de Cartões de Fidelização

- ✅ Gestão completa de cartões de clientes
- ✅ Acumulação automática de saldo através de cupões
- ✅ Ativação e desativação seletiva de cupões
- ✅ Validação automática de prazos de validade
- ✅ Atualização inteligente (remove cupões expirados automaticamente)
- ✅ Histórico detalhado de cupões disponíveis e futuros

### 🎟️ Três Tipos de Cupões

<div align="center">

| Tipo | Ícone | Descrição | Exemplo |
|:----:|:-----:|-----------|---------|
| **Cupão de Produtos** | 📦 | Desconto em produtos específicos | 15% em Massas selecionadas |
| **Cupão de Marca** | 🏷️ | Desconto em todos os produtos de uma marca | 10% em produtos Albicereal |
| **Cupão de Compra** | 🛒 | Desconto aplicado ao total da compra | 5% em qualquer compra |

</div>

#### 🎯 Regras de Negócio

- ❌ **Não cumulativo** - Aplica-se sempre o cupão de maior desconto
- 💰 **Saldo acumulado** - Descontos são convertidos em saldo no cartão
- 📅 **Prazo de validade** - Cupões têm data de início e fim
- 🔝 **Priorização automática** - Sistema escolhe automaticamente o melhor desconto
- 🎫 **Um cupão por produto** - Cada produto só pode ter um cupão aplicado
- ✂️ **Remoção automática** - Cupões usados são removidos do cartão

### 🛍️ Gestão de Vendas

- 📝 Processamento de vendas com múltiplos produtos
- 🤖 Aplicação automática e inteligente de cupões ativos
- 🧮 Cálculo otimizado de descontos (maior desconto primeiro)
- 💵 Acumulação imediata de saldo no cartão do cliente
- 📊 Histórico completo de cupões utilizados por venda
- 📋 Relatório detalhado e formatado de cada transação

### 📦 Sistema de Inventário

- 🏪 Cadastro e gestão centralizada de produtos
- 🔖 Informação completa: código, marca, modelo, preço
- 🎫 Repositório de cupões disponíveis
- 💳 Gestão de cartões de clientes
- 💯 Preços em cêntimos (evita erros de arredondamento float)
- 🔍 Busca rápida e eficiente com HashMap

---

## 🛠️ Tecnologias Utilizadas

<div align="center">

### Stack Principal

![Java](https://img.shields.io/badge/Java-17+-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)
![VS Code](https://img.shields.io/badge/VS_Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)
![Git](https://img.shields.io/badge/Git-F05032?style=for-the-badge&logo=git&logoColor=white)
![Linux](https://img.shields.io/badge/Linux-FCC624?style=for-the-badge&logo=linux&logoColor=black)

</div>

### APIs Java Utilizadas
```java
java.time.*          // Gestão de datas (LocalDate)
java.util.*          // Collections (ArrayList, HashMap, List, Map)
java.io.*            // Leitura de ficheiros (BufferedReader, FileReader)
java.util.Objects    // Validação de objetos nulos
```

<div align="center">

**✨ Zero dependências externas - 100% Java Standard Library! ✨**

</div>

---

## 🏗️ Arquitetura

### 📊 Diagrama de Pacotes
```
┌─────────────────────────────────────────────────────────────┐
│                   🎫 Coupon Engine                          │
├─────────────────────────────────────────────────────────────┤
│                                                             │
│  ┌──────────┐  ┌──────────┐  ┌──────────┐  ┌──────────┐   │
│  │ 💳       │  │ 🏪       │  │ 🔧       │  │ 🖥️       │   │
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

### 🔗 Hierarquia de Classes (Cupões)
```
                Cupao (abstract)
              ┌──────────┴──────────┐
              │                     │
        CupaoProdutos         CupaoMarca
        📦 - abrangidos       🏷️ - marca
        - abrange()           - abrange()
              │
         CupaoCompra
        🛒 - abrange() → true
```

### 📁 Estrutura do Projeto
```
coupon-engine/
│
├── 📝 src/                         # Código fonte
│   ├── 💳 cliente/                 # Camada de Cliente
│   │   ├── Cartao.java            # Gestão de cartões
│   │   ├── Cupao.java             # ⭐ Classe abstrata base
│   │   ├── CupaoProdutos.java     # 📦 Cupão de produtos
│   │   ├── CupaoMarca.java        # 🏷️ Cupão de marca
│   │   └── CupaoCompra.java       # 🛒 Cupão de compra
│   │
│   ├── 🏪 comercio/                # Camada de Negócio
│   │   ├── ProdutoInfo.java       # Info de produtos
│   │   ├── ProdutoVendido.java    # Produto numa venda
│   │   ├── Venda.java             # Processamento de vendas
│   │   └── Inventario.java        # Repositório de dados
│   │
│   ├── 🔧 util/                    # Utilitários
│   │   └── Validator.java         # Validações centralizadas
│   │
│   └── 🖥️ menu/                    # Interface
│       └── Main.java              # Ponto de entrada
│
├── 📊 dados/                       # Ficheiros de dados (TSV)
│   ├── produtos.hnt
│   ├── cupoes.hnt
│   └── cartoes.hnt
│
├── 🔨 bin/                         # Compilados (.class)
├── 📄 .gitignore
├── 📜 LICENSE
└── 📖 README.md
```

---

## 🚀 Como Executar

### 📋 Pré-requisitos

- ☕ Java JDK **17 ou superior**
- 💻 VS Code com **Extension Pack for Java** (recomendado)
- 🔧 Git configurado (opcional)

### ✅ Verificar Instalação
```bash
java -version    # ✓ Deve mostrar versão 17+
javac -version   # ✓ Deve mostrar versão 17+
```

---

### 🎯 Método 1: VS Code (RECOMENDADO)

**1️⃣ Clone o repositório**
```bash
git clone https://github.com/CLopes86/coupon-engine.git
cd coupon-engine
```

**2️⃣ Abra no VS Code**
```bash
code .
```

**3️⃣ Execute**
- Abra `src/menu/Main.java`
- Clique no botão ▶️ **"Run"** acima do método `main()`
- Ou pressione **F5**

---

### 🖥️ Método 2: Terminal
```bash
# Navegar para o projeto
cd coupon-engine

# Compilar
javac -d bin -sourcepath src src/**/*.java

# Executar
java -cp bin menu.Main
```

---

### 🐧 Método 3: Script Bash (Linux/Mac)

Crie `run.sh`:
```bash
#!/bin/bash
echo "🔨 Compilando..."
javac -d bin -sourcepath src src/**/*.java

if [ $? -eq 0 ]; then
    echo "✅ Sucesso! Executando..."
    java -cp bin menu.Main
else
    echo "❌ Erro na compilação!"
fi
```

Execute:
```bash
chmod +x run.sh
./run.sh
```

---

## 💡 Conceitos de Programação Aplicados

### 1️⃣ Encapsulamento 🔒

**Dados protegidos com acesso controlado**
```java
public class ProdutoInfo {
    private String codigoBarras;  // 🔒 Protected
    private long preco;
    
    public long getPreco() {      // ✅ Public getter
        return preco;
    }
    
    public void setPreco(long preco) {  // ✅ Validated setter
        if (preco < 0) {
            throw new IllegalArgumentException("Preço inválido");
        }
        this.preco = preco;
    }
}
```

---

### 2️⃣ Herança 🌳

**Classe base abstrata com subclasses especializadas**
```java
public abstract class Cupao {
    protected String numero;
    protected float desconto;
    
    // 🎯 Método abstrato - subclasses implementam
    public abstract boolean abrange(ProdutoVendido p);
}

public class CupaoProdutos extends Cupao {
    @Override
    public boolean abrange(ProdutoVendido p) {
        return abrangidos.contains(p.getInfo());
    }
}
```

---

### 3️⃣ Polimorfismo 🎭

**Mesmo método, comportamentos diferentes!**
```java
List<Cupao> cupoes = new ArrayList<>();
cupoes.add(new CupaoProdutos(...));  // 📦
cupoes.add(new CupaoMarca(...));     // 🏷️
cupoes.add(new CupaoCompra(...));    // 🛒

// 🎭 Cada tipo executa seu próprio método
for (Cupao c : cupoes) {
    if (c.abrange(produto)) {  // Comportamento depende do tipo REAL
        System.out.println("Cupão aplicável!");
    }
}
```

---

### 4️⃣ Abstração 💭

**Contrato que subclasses devem seguir**
```java
public abstract class Cupao {
    // ❌ Não pode instanciar diretamente!
    
    // ✅ Método concreto (herdado por todos)
    public boolean estaValido() {
        return LocalDate.now().isBefore(fim);
    }
    
    // 🎯 Método abstrato (DEVE ser implementado)
    public abstract boolean abrange(ProdutoVendido p);
}
```

---

### 5️⃣ Composição 🧩

**Objetos contêm outros objetos (HAS-A)**
```java
public class Venda {
    private List<ProdutoVendido> produtos;  // HAS-A
}

public class ProdutoVendido {
    private ProdutoInfo info;  // HAS-A
}

public class Cartao {
    private List<Cupao> cupoes;  // HAS-A
}
```

---

### 6️⃣ Collections Framework 📚
```java
// 📋 ArrayList - Lista dinâmica
List<ProdutoVendido> produtos = new ArrayList<>();

// 🗺️ HashMap - Busca O(1)
Map<String, ProdutoInfo> produtos = new HashMap<>();

// 🔒 Lista imutável
return Collections.unmodifiableList(produtos);
```

---

## 📖 Exemplos de Uso

### 💼 Exemplo 1: Cupão de Produtos
```java
// Criar produtos abrangidos
List<ProdutoInfo> massas = new ArrayList<>();
massas.add(inventario.getProduto("202-006"));
massas.add(inventario.getProduto("202-007"));

// Criar cupão
Cupao cupao = new CupaoProdutos(
    "1001",
    "15% em massas",
    massas,
    0.15f,                          // 15% desconto
    LocalDate.now(),
    LocalDate.now().plusDays(7)
);

inventario.addCupao(cupao);
```

### 💼 Exemplo 2: Cupão de Marca
```java
Cupao cupao = new CupaoMarca(
    "1501",
    "10% em Albicereal",
    0.10f,
    LocalDate.now(),
    LocalDate.now().plusDays(30),
    "Albicereal"
);
```

### 💼 Exemplo 3: Processar Venda
```java
// Criar venda
Venda venda = new Venda();
inventario.vendeProduto(venda, "202-006");  // Massa
inventario.vendeProduto(venda, "125-011");  // Chocolate

// Buscar cartão
Cartao cartao = inventario.getCartao("10101");

// Ativar cupões
List<Cupao> cupoesAtivos = Arrays.asList(
    inventario.getCupao("1001"),
    inventario.getCupao("1003")
);
cartao.ativar(cupoesAtivos);

// ✨ Aplicar cupões e acumular saldo
cartao.usar(venda);

// Ver resultado
System.out.printf("Saldo: %.2f€%n", cartao.getSaldo() / 100.0);
```

---

## 🧪 Testes

### 🔬 Três Métodos de Teste

**1️⃣ testarProdutosMarca()**
- Testa cupões de produtos e marca
- Verifica priorização de descontos
- **Cupões:** Massas (15%), Chocolates (15%), Albicereal (10%)

**2️⃣ testarProdutosCompra()**
- Testa cupões específicos e cupão de compra
- Verifica cobertura de produtos sem cupão específico
- **Cupões:** Sumos (15%), Arroz (15%), Compra (10%)

**3️⃣ testarErros()**
- Valida tratamento de exceções
- **Testes:** Produto inexistente, Cupão inválido, Cartão não encontrado

### ▶️ Executar Testes

No `Main.java`:
```java
public static void main(String[] args) {
    Inventario inv = new Inventario();
    // ... carregar dados ...
    
    testarProdutosMarca(inv);      // ✅ Executar teste 1
    // testarProdutosCompra(inv);  // Teste 2
    // testarErros(inv);           // Teste 3
}
```

---

## 📊 Métricas do Projeto

<div align="center">

| Métrica | Valor |
|:--------|:-----:|
| **Linhas de Código** | ~2000+ |
| **Classes Principais** | 11 |
| **Métodos Públicos** | 120+ |
| **Packages** | 4 |
| **Cenários de Teste** | 3 |
| **Cobertura OOP** | 100% |
| **Tempo de Desenvolvimento** | 4 semanas |
| **Documentação** | ✅ Completa |

</div>

---

## 🎓 Aprendizagens

<div align="center">

### Conceitos Dominados Neste Projeto

</div>
```
✅ Encapsulamento      ✅ Herança             ✅ Polimorfismo
✅ Abstração           ✅ Composição          ✅ Design Patterns
✅ Collections         ✅ Exception Handling  ✅ File I/O
✅ Git & GitHub        ✅ Javadoc             ✅ Clean Code
✅ Arquitetura         ✅ Validações          ✅ Documentação
```

---

## 👨‍💻 Autor

<div align="center">
  <img src="https://github.com/CLopes86.png" width="180" style="border-radius: 50%; border: 4px solid #0366d6; box-shadow: 0 4px 8px rgba(0,0,0,0.2);" alt="Cesaltino Lopes"/>
  
  ### **Cesaltino Lopes**
  
  🎓 Estudante de **Informática e Multimédia**  
  🏫 **Instituto Politécnico de Castelo Branco**  
  📚 **Disciplina:** Padrões e Desenho de Software  
  📅 **Ano Letivo:** 2024/2025
  
  <br>
  
  [![GitHub](https://img.shields.io/badge/GitHub-CLopes86-181717?style=for-the-badge&logo=github)](https://github.com/CLopes86)
  [![LinkedIn](https://img.shields.io/badge/LinkedIn-Cesaltino%20Lopes-0077B5?style=for-the-badge&logo=linkedin)](https://linkedin.com/in/cesaltino-lopes-55274b176)
  [![Email](https://img.shields.io/badge/Email-clopes86cv-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:clopes86cv@gmail.com)
  
  <br>
  
  💻 **Foco:** Mobile Development com Flutter  
  🔥 **Paixão:** Criar aplicações que resolvem problemas reais  
  🚀 **Objetivo:** Junior Developer Position
  
</div>

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Veja [LICENSE](LICENSE) para detalhes.

---

## 🙏 Agradecimentos

- 🏫 **IPCB** - Pela excelente formação académica
- 👨‍🏫 **Docentes de PDS** - Pelo conhecimento partilhado
- ☕ **Comunidade Java** - Pelos recursos e documentação
- 💻 **Stack Overflow** - Pelas soluções e discussões

---

## 📚 Recursos Úteis

- [📖 Java Documentation](https://docs.oracle.com/en/java/)
- [📘 Java Tutorials - Oracle](https://docs.oracle.com/javase/tutorial/)
- [🎓 Effective Java - Joshua Bloch](https://www.oreilly.com/library/view/effective-java/9780134686097/)
- [💬 Stack Overflow - Java](https://stackoverflow.com/questions/tagged/java)

---

## 🤝 Contribuições

Contribuições são bem-vindas! Para contribuir:

1. 🍴 Fork o projeto
2. 🌿 Crie uma branch (`git checkout -b feature/NovaFeature`)
3. 💾 Commit as mudanças (`git commit -m 'Adiciona NovaFeature'`)
4. 📤 Push para a branch (`git push origin feature/NovaFeature`)
5. 🎉 Abra um Pull Request

---

<div align="center">

### 💡 "From idea to code: Turning concepts into functional applications"

<br>

**Se este projeto foi útil, deixa uma ⭐ no repositório!**

<br>

[![Made with Java](https://img.shields.io/badge/Made%20with-Java-ED8B00?style=for-the-badge&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![Built with VS Code](https://img.shields.io/badge/Built%20with-VS%20Code-007ACC?style=for-the-badge&logo=visual-studio-code&logoColor=white)](https://code.visualstudio.com/)
[![Powered by Coffee](https://img.shields.io/badge/Powered%20by-Coffee-brown?style=for-the-badge&logo=buy-me-a-coffee&logoColor=white)](https://github.com/CLopes86)

<br>

**Desenvolvido com ☕ Java, 💙 Paixão e 🧠 Dedicação**

**© 2025 Cesaltino Lopes | Instituto Politécnico de Castelo Branco**

</div>
```

---

