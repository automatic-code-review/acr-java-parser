# acr-java-parser

Arquivo config.json

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

# Gerando o .jar
```
mvn package
```