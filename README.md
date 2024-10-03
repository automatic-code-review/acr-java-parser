# acr-java-parser

## Visão Geral

Extensão utilizada específicamente para criação/revisão de regras personalizadas de código Java. Faz uso da lib [JavaParser](https://github.com/javaparser). O
objetivo é garantir padrões de codificação consistentes em múltiplos projetos.

## Funcionalidades

Atualmente, a extensão oferece suporte às seguintes regras de revisão de código:

1. **REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE**:
    - Garante que, se um atributo tiver um valor padrão, ele deve ser anotado corretamente.

2. **REQUIRE_PREFIX_ATTRIBUTE**:
    - Exige que certos atributos tenham um prefixo específico. Permite utilização de uma lista de regex configurável como condição.

3. **PREFER_PRIMITIVE_BOOLEAN**:
    - Sugere o uso de tipos booleanos primitivos em vez de objetos `Boolean`, para simplicidade e desempenho.

Cada uma dessas regras é implementada como um serviço, que define e mantém as condições necessárias para validação e aplicação.


## Arquivo config.json

```json
{
    "stage": "static",
    "language": "JAVA",
    "rules": [
        {
            "type": "REQUIRE_ANOTTATION_IF_ATRRIBUTE_HAS_DEFAULT_VALUE",
            "comment": "Adicionar a anotação `@Builder.Default` a variável `#{VARIAVEL_NAME}`<br>Arquivo: #{FILE_NAME}<br>Linha: #{LINE_NUMBER}"
        }
    ]
}
```

## Gerando o .jar

```
mvn package
```