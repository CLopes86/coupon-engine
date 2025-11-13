# 🎫 Coupon Engine - Sistema de Gestão de Cupões e Fidelização

![Java](https://img.shields.io/badge/Java-17+-orange.svg)
![License](https://img.shields.io/badge/License-MIT-blue.svg)
![Status](https://img.shields.io/badge/Status-Completo-brightgreen.svg)
![VS Code](https://img.shields.io/badge/Editor-VS%20Code-blue.svg?logo=visual-studio-code)

> Sistema inteligente de gestão de cupões de desconto e cartões de fidelização desenvolvido como projeto académico da disciplina de **Padrões e Desenho de Software (PDS)** no **Instituto Politécnico de Castelo Branco** - Curso de **Informática e Multimédia**.

---

## 📋 Índice

- [Sobre o Projeto](#-sobre-o-projeto)
- [Funcionalidades](#-funcionalidades)
- [Tecnologias Utilizadas](#-tecnologias-utilizadas)
- [Arquitetura](#-arquitetura)
- [Como Executar](#-como-executar)
- [Conceitos Aplicados](#-conceitos-de-programação-aplicados)
- [Exemplos de Uso](#-exemplos-de-uso)
- [Testes](#-testes)
- [Roadmap](#-roadmap)
- [Métricas](#-métricas-do-projeto)
- [Autor](#-autor)
- [Licença](#-licença)

---

## 🎯 Sobre o Projeto

O **Coupon Engine** é um sistema robusto de gestão de cupões de desconto para a cadeia de lojas fictícia **HonESTa**. O projeto demonstra a aplicação prática de conceitos avançados de **Programação Orientada a Objetos** em Java.

### 🌟 Destaques Técnicos

✅ **Arquitetura em Camadas** - Separação clara de responsabilidades  
✅ **POO Completa** - Encapsulamento, Herança, Polimorfismo e Abstração  
✅ **Classes Abstratas** - Estrutura hierárquica de cupões  
✅ **Polimorfismo** - Diferentes tipos de cupões com comportamento único  
✅ **Validação Robusta** - Classe utilitária Validator centralizada  
✅ **Código Limpo** - Seguindo convenções Java e boas práticas  
✅ **Sistema Extensível** - Fácil adicionar novos tipos de cupões  
✅ **Documentação Completa** - Javadoc em todas as classes  

---

## ⚡ Funcionalidades

### 💳 Cartões de Fidelização

- ✅ Gestão completa de cartões de clientes
- ✅ Acumulação de saldo através de cupões
- ✅ Ativação/desativação seletiva de cupões
- ✅ Validação automática de prazos de validade
- ✅ Atualização automática (remove cupões expirados)
- ✅ Histórico de cupões disponíveis e futuros

### 🎟️ Sistema de Cupões (3 Tipos)

| Tipo | Ícone | Descrição | Exemplo de Uso |
|------|-------|-----------|----------------|
| **Cupão de Produtos** | 📦 | Desconto em produtos específicos | 15% em Massas selecionadas |
| **Cupão de Marca** | 🏷️ | Desconto em todos os produtos de uma marca | 10% em produtos Albicereal |
| **Cupão de Compra** | 🛒 | Desconto aplicado a toda a compra | 5% em qualquer compra |

#### 🎯 Regras Importantes

- ❌ Cupões **NÃO são cumulativos** - aplica-se sempre o de maior desconto
- 💰 Descontos são **acumulados em saldo** (não descontos imediatos)
- 📅 Cupões têm **prazo de validade** (início e fim)
- 🔝 Sistema de **priorização automática** (maior desconto primeiro)
- 🎫 Cada produto só pode ter **1 cupão** aplicado
- ✂️ Cupões usados são **automaticamente removidos**

### 🛍️ Gestão de Vendas

- 📝 Processamento de vendas com múltiplos produtos
- 🤖 Aplicação automática de cupões ativos
- 🧮 Cálculo inteligente de descontos
- 💵 Acumulação de saldo no cartão do cliente
- 📊 Histórico de cupões utilizados
- 📋 Relatório detalhado por venda

### 📦 Inventário

- 🏪 Cadastro e gestão de produtos
- 🔖 Informação completa: código, marca, modelo, preço
- 🎫 Gestão de cupões disponíveis
- 💳 Gestão de cartões de clientes
- 💯 Preços em cêntimos (evita erros de arredondamento)
- 🔍 Busca rápida por HashMap

---

## 🛠️ Tecnologias Utilizadas

### Principais

| Tecnologia | Versão | Uso |
|------------|--------|-----|
| ☕ **Java** | 17+ | Linguagem principal |
| 💻 **VS Code** | Latest | IDE de desenvolvimento |
| 🔧 **Git** | Latest | Controlo de versão |
| 🐧 **Linux** | Ubuntu | Sistema Operativo |

### APIs Java Utilizadas

- `java.time.*` - Gestão de datas (LocalDate)
- `java.util.*` - Collections Framework (ArrayList, HashMap, List, Map)
- `java.io.*` - Leitura de ficheiros (BufferedReader, FileReader)
- `java.util.Objects` - Validação de objetos nulos

**✨ Sem dependências externas** - Projeto utiliza apenas a biblioteca padrão do Java!

---

## 🏗️ Arquitetura

### 📊 Diagrama de Pacotes

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

### 📁 Estrutura de Diretórios

```
coupon-engine/
│
├── src/                            # 📝 Código fonte
│   │
│   ├── cliente/                    # 💳 Camada de Cliente
│   │   ├── Cartao.java            # Gestão de cartões de fidelização
│   │   ├── Cupao.java             # Classe abstrata base para cupões
│   │   ├── CupaoProdutos.java     # Cupão para produtos específicos
│   │   ├── CupaoMarca.java        # Cupão para marca específica
│   │   └── CupaoCompra.java       # Cupão para toda a compra
│   │
│   ├── comercio/                   # 🏪 Camada de Negócio
│   │   ├── ProdutoInfo.java       # Informação geral de produtos
│   │   ├── ProdutoVendido.java    # Produto numa venda específica
│   │   ├── Venda.java             # Gestão de vendas
│   │   └── Inventario.java        # Repositório de dados (memória)
│   │
│   ├── util/                       # 🔧 Utilitários
│   │   └── Validator.java         # Validações centralizadas
│   │
│   └── menu/                       # 🖥️ Interface
│       └── Main.java              # Ponto de entrada da aplicação
│
├── dados/                          # 📊 Ficheiros de dados (TSV)
│   ├── produtos.hnt               # Lista de produtos
│   ├── cupoes.hnt                 # Lista de cupões
│   └── cartoes.hnt                # Lista de cartões
│
├── bin/                            # 🔨 Ficheiros compilados (.class)
│
├── .gitignore                      # 🚫 Ficheiros ignorados pelo Git
├── LICENSE                         # 📄 Licença MIT
└── README.md                       # 📖 Este ficheiro
```

### 🔗 Hierarquia de Classes (Cupões)

```
           Cupao (abstract)
        ┌──────────┴──────────┐
        │                     │
  CupaoProdutos         CupaoMarca
    - abrangidos          - marca
    - abrange()           - abrange()
        │
   CupaoCompra
    - abrange() → true
```

---

## 🚀 Como Executar

### 📋 Pré-requisitos

- ☕ **Java JDK 17 ou superior** instalado
- 💻 **VS Code** com Extension Pack for Java (recomendado)
- 🔧 **Git** configurado (opcional)

### ✅ Verificar Instalação do Java

```bash
java -version    # Deve mostrar versão 17+
javac -version   # Deve mostrar versão 17+
```

---

### 🎯 Método 1: Executar no VS Code (RECOMENDADO)

**1️⃣ Clone o repositório**
```bash
git clone https://github.com/CLopes86/coupon-engine.git
cd coupon-engine
```

**2️⃣ Abra no VS Code**
```bash
code .
```

**3️⃣ Execute a aplicação**

- Abra o ficheiro `Main.java` (em `src/menu/`)
- Clique no botão ▶️ **"Run"** que aparece acima do método `main()`
- Ou pressione `F5`

---

### 🖥️ Método 2: Executar via Terminal

```bash
# 1️⃣ Navegar para a pasta do projeto
cd coupon-engine

# 2️⃣ Compilar todo o projeto
javac -d bin -sourcepath src src/**/*.java

# 3️⃣ Executar a aplicação principal
java -cp bin menu.Main
```

---

### 🐧 Método 3: Script de Compilação (Linux/Mac)

Crie um ficheiro `run.sh`:

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
chmod +x run.sh
./run.sh
```

---

## 💡 Conceitos de Programação Aplicados

### 1️⃣ Encapsulamento

Campos `private` protegem dados e o acesso é controlado via getters/setters.

```java
public class ProdutoInfo {
    private String codigoBarras;  // 🔒 Private - protegido
    private long preco;
    
    public long getPreco() {      // ✅ Getter público
        return preco;
    }
    
    public void setPreco(long preco) {  // ✅ Setter com validação
        if (preco < 0) {
            throw new IllegalArgumentException("Preço não pode ser negativo");
        }
        this.preco = preco;
    }
}
```

---

### 2️⃣ Herança

Classe abstrata `Cupao` como base e subclasses especializadas.

```java
public abstract class Cupao {
    // 📦 Código comum a todos os cupões
    protected String numero;
    protected float desconto;
    
    public abstract boolean abrange(ProdutoVendido p);
}

public class CupaoProdutos extends Cupao {
    // 🎯 Implementação específica
    @Override
    public boolean abrange(ProdutoVendido p) {
        return abrangidos.contains(p.getInfo());
    }
}
```

---

### 3️⃣ Polimorfismo

Mesmo método `abrange()` com comportamento diferente!

```java
List<Cupao> cupoes = new ArrayList<>();
cupoes.add(new CupaoProdutos(...));  // 📦
cupoes.add(new CupaoMarca(...));     // 🏷️
cupoes.add(new CupaoCompra(...));    // 🛒

// 🎭 Polimorfismo: cada cupão executa seu próprio abrange()
for (Cupao c : cupoes) {
    if (c.abrange(produto)) {
        // Comportamento depende do tipo REAL em runtime
        System.out.println("Cupão aplicável!");
    }
}
```

---

### 4️⃣ Abstração

Classe abstrata define contrato que subclasses devem seguir.

```java
public abstract class Cupao {
    // ❌ Não pode instanciar: new Cupao() dá erro!
    
    // ✅ Método concreto (todas as subclasses herdam)
    public boolean estaValido() {
        return LocalDate.now().isBefore(fim);
    }
    
    // 🎯 Método abstrato (subclasses DEVEM implementar)
    public abstract boolean abrange(ProdutoVendido p);
}
```

---

### 5️⃣ Composição

Classes **têm** outras classes dentro:

- `ProdutoVendido` **tem um** `ProdutoInfo` 📦
- `Venda` **tem uma lista de** `ProdutoVendido` 🛒
- `Cartao` **tem uma lista de** `Cupao` 💳

```java
public class Venda {
    private List<ProdutoVendido> produtos;  // HAS-A relationship
}
```

---

### 6️⃣ Collections Framework

```java
// 📋 ArrayList - Lista dinâmica
List<ProdutoVendido> produtos = new ArrayList<>();

// 🗺️ HashMap - Busca rápida por chave
Map<String, ProdutoInfo> produtos = new HashMap<>();

// 🔒 Proteção - Lista não modificável
return Collections.unmodifiableList(produtos);
```

---

## 📖 Exemplos de Uso

### Exemplo 1: Criar e Usar Cupão de Produtos

```java
// Criar lista de produtos abrangidos
List<ProdutoInfo> massas = new ArrayList<>();
massas.add(inventario.getProduto("202-006"));
massas.add(inventario.getProduto("202-007"));

// Criar cupão
Cupao cupao = new CupaoProdutos(
    "1001",                          // número
    "15% em massas",                 // descrição
    massas,                          // produtos
    0.15f,                           // 15% desconto
    LocalDate.now(),                 // válido de hoje
    LocalDate.now().plusDays(7)      // até 7 dias
);

// Adicionar ao inventário
inventario.addCupao(cupao);
```

---

### Exemplo 2: Criar Cupão de Marca

```java
Cupao cupao = new CupaoMarca(
    "1501",
    "10% em Albicereal",
    0.10f,
    LocalDate.now(),
    LocalDate.now().plusDays(30),
    "Albicereal"  // marca específica
);
```

---

### Exemplo 3: Processar Venda com Cupões

```java
// Criar venda
Venda venda = new Venda();
inventario.vendeProduto(venda, "202-006");  // Massa
inventario.vendeProduto(venda, "125-011");  // Chocolate

// Buscar cartão e cupões
Cartao cartao = inventario.getCartao("10101");
List<Cupao> cupoesAtivos = Arrays.asList(
    inventario.getCupao("1001"),  // Cupão de massas
    inventario.getCupao("1003")   // Cupão de chocolates
);

// Ativar e usar cupões
cartao.ativar(cupoesAtivos);
cartao.usar(venda);  // ✨ Mágica acontece aqui!

// Ver saldo acumulado
System.out.println("Saldo: " + cartao.getSaldo() / 100.0 + "€");
```

---

## 🧪 Testes

O sistema inclui **3 métodos de teste** no Main:

### 1️⃣ testarProdutosMarca()

Testa cupões de produtos e marca.

**Cupões:** Massas (15%), Chocolates (15%), Albicereal (10%)  
**Produtos:** 10 produtos variados  
**Resultado:** Aplica maior desconto quando produto tem múltiplos cupões

---

### 2️⃣ testarProdutosCompra()

Testa cupões de produtos e compra.

**Cupões:** Sumos (15%), Arroz (15%), Compra (10%)  
**Produtos:** 10 produtos (alguns com cupão específico, outros não)  
**Resultado:** Cupão de compra cobre produtos sem cupão específico

---

### 3️⃣ testarErros()

Testa tratamento de erros.

**Testa:** Produto inexistente, Cupão inexistente, Cupão não válido, Cartão inexistente  
**Resultado:** Sistema lança exceções apropriadas

---

### 📊 Executar Testes

No `Main.java`, descomente o teste desejado:

```java
public static void main(String[] args) {
    Inventario inv = new Inventario();
    // ... carregar dados ...
    
    testarProdutosMarca(inv);      // ✅ Teste 1
    // testarProdutosCompra(inv);  // Teste 2
    // testarErros(inv);           // Teste 3
}
```

---

## 🗺️ Roadmap

### ✅ Fase 1 - Concluída

- [x] Estrutura base de todas as classes
- [x] Implementação dos 3 tipos de cupões
- [x] Sistema de validação robusto
- [x] Leitura de ficheiros de dados
- [x] Sistema de aplicação de cupões
- [x] Documentação Javadoc completa
- [x] Testes funcionais
- [x] README completo

### 🚧 Fase 2 - Futuro Próximo

- [ ] Interface gráfica (JavaFX)
- [ ] Persistência em base de dados
- [ ] Sistema de relatórios avançados
- [ ] Exportação (PDF, CSV)
- [ ] Testes unitários (JUnit)

### 🔮 Fase 3 - Futuro

- [ ] API REST
- [ ] Aplicação web
- [ ] Sistema de notificações
- [ ] Dashboard de estatísticas
- [ ] Integração com sistemas de pagamento

---

## 📊 Métricas do Projeto

| Métrica | Valor |
|---------|-------|
| **Linhas de Código** | ~2000+ |
| **Classes** | 11 |
| **Métodos** | 120+ |
| **Packages** | 4 |
| **Ficheiros de Teste** | 3 cenários |
| **Cobertura de Conceitos OOP** | 100% |
| **Tempo de Desenvolvimento** | 4 semanas |
| **Documentação Javadoc** | 100% |

---

## 🎓 Aprendizagens

Este projeto permitiu aprender e aplicar:

✅ **POO na Prática** - Encapsulamento, Herança, Polimorfismo, Abstração  
✅ **Design Patterns** - Hierarquia de classes, Composição  
✅ **Collections Framework** - ArrayList, HashMap, interfaces List/Map  
✅ **Tratamento de Exceções** - Validações robustas  
✅ **I/O em Java** - Leitura e parse de ficheiros  
✅ **Git e Versionamento** - Controlo de versão eficaz  
✅ **Documentação** - Javadoc e README profissional  
✅ **Boas Práticas** - Código limpo e manutenível  
✅ **Arquitetura** - Separação em camadas  

---

## 👨‍💻 Autor

<div align="center">
  <img src="https://github.com/CLopes86.png" width="150" style="border-radius: 50%; border: 3px solid #0366d6;" alt="Cesaltino Lopes"/>
  
  ### **Cesaltino Lopes**
  
  [![GitHub](https://img.shields.io/badge/GitHub-CLopes86-181717?style=for-the-badge&logo=github)](https://github.com/CLopes86)
  [![LinkedIn](https://img.shields.io/badge/LinkedIn-Cesaltino%20Lopes-0077B5?style=for-the-badge&logo=linkedin)](https://www.linkedin.com/in/cesaltino-lopes-55274b176/)
  [![Email](https://img.shields.io/badge/Email-c.lopes46cv%40gmail.com-D14836?style=for-the-badge&logo=gmail&logoColor=white)](mailto:c.lopes46cv@gmail.com)
  
  🎓 **Estudante de Informática e Multimédia**  
  🏫 **Instituto Politécnico de Castelo Branco**  
  📚 **Disciplina:** Padrões e Desenho de Software (PDS)  
  📅 **Ano Letivo:** 2024/2025
</div>

---

## 📄 Licença

Este projeto está sob a licença **MIT**. Consulte o ficheiro [LICENSE](LICENSE) para mais detalhes.

```
MIT License

Copyright (c) 2025 Cesaltino Lopes

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction...
```

---

## 🙏 Agradecimentos

- 🏫 **Instituto Politécnico de Castelo Branco** - Pela formação académica
- 👨‍🏫 **Docentes de PDS** - Pela orientação e conhecimento partilhado
- ☕ **Comunidade Java** - Pela documentação e recursos


---

## 📚 Recursos Utilizados

- [📖 Documentação Oficial do Java](https://docs.oracle.com/en/java/)
- [📘 Java Tutorial - Oracle](https://docs.oracle.com/javase/tutorial/)
- [💬 Stack Overflow](https://stackoverflow.com/)
- [📓 Apontamentos da disciplina de PDS](https://github.com/CLopes86/coupon-engine/wiki)

---

## 🌟 Contribuições

Contribuições são bem-vindas! Se quiser melhorar este projeto:

1. 🍴 Fork o projeto
2. 🌿 Crie uma branch (`git checkout -b feature/MinhaFeature`)
3. 💾 Commit suas mudanças (`git commit -m 'Adiciona MinhaFeature'`)
4. 📤 Push para a branch (`git push origin feature/MinhaFeature`)
5. 🎉 Abra um Pull Request

---

<div align="center">

### ⭐ Se este projeto foi útil, considera dar uma estrela!

**Desenvolvido com ☕ Java, 💙 Paixão e 🧠 Dedicação**

**2025 © Instituto Politécnico de Castelo Branco**

---

[![Made with Love](https://img.shields.io/badge/Made%20with-❤️-red.svg)](https://github.com/CLopes86/coupon-engine)
[![Java](https://img.shields.io/badge/Java-ED8B00?style=flat&logo=openjdk&logoColor=white)](https://www.oracle.com/java/)
[![VS Code](https://img.shields.io/badge/VS%20Code-007ACC?style=flat&logo=visual-studio-code&logoColor=white)](https://code.visualstudio.com/)

</div>