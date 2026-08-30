# Descubra 🔎

Aplicativo Android desenvolvido para o minicurso **"Arquitetura e boas práticas para aplicações Android: do app simples à arquitetura escalável"**, realizado durante a Semana de Computação do IFSP São Carlos.

O **Descubra** é um aplicativo simples que consulta a API pública da Wikipédia e apresenta ao usuário informações sobre um artigo aleatório.

A proposta do projeto é começar com uma aplicação pequena e funcional e, ao longo do minicurso, explorar como ela pode evoluir conforme surgem novas necessidades.

---

## 🎯 Sobre o minicurso

Neste minicurso, vamos partir de um aplicativo Android propositalmente simples, concentrado inicialmente em uma única `Activity`.

A partir dele, vamos identificar problemas e necessidades que surgem naturalmente durante a evolução de um produto e utilizar esses problemas como ponto de partida para discutir conceitos de arquitetura e boas práticas.

Entre os temas abordados estão:

* Gerenciamento de estado;
* `ViewModel`;
* Separação de responsabilidades;
* Repository;
* Services;
* Interfaces;
* Inversão de dependência;
* princípios SOLID;
* evolução e manutenção de aplicações.

Ao final do minicurso, a principal ideia não é memorizar uma estrutura de pastas, apresentando uma arquitetura como uma receita pronta, mas entender **por que determinadas decisões podem fazer sentido conforme um software evolui.**

---

## 📱 O aplicativo

Ao abrir o aplicativo, o usuário encontra uma tela simples com a opção de descobrir algo novo.

Ao tocar no botão, o aplicativo realiza uma requisição para a API da Wikipédia e apresenta:

* título do artigo;
* descrição/resumo do artigo.

A aplicação também possui estados básicos de carregamento e erro.

### Fluxo inicial

```text
Usuário
   ↓
"Descobrir"
   ↓
Aplicativo
   ↓
API da Wikipédia
   ↓
Artigo aleatório
   ↓
Título + descrição
```

---

## 🛠️ Tecnologias

O projeto utiliza:

| Tecnologia            | Versão                         |
| --------------------- | ------------------------------ |
| Android Studio        | Quail 3 — 2026.1.3 Patch 1     |
| Kotlin                | 2.4.10                         |
| Android Gradle Plugin | 9.3.2                          |
| Gradle                | Wrapper fornecido pelo projeto |
| Java / JDK            | 11                             |
| Compile SDK           | 37                             |
| Target SDK            | 37                             |
| Min SDK               | 30                             |
| Jetpack Compose BOM   | 2026.08.00                     |
| Compose Material 3    | Gerenciado pelo BOM            |
| AndroidX Core KTX     | 1.19.0                         |
| Activity Compose      | 1.13.0                         |
| Lifecycle Runtime KTX | 2.11.0                         |
| Retrofit              | 3.0.0                          |
| Gson                  | 2.13.2                         |
| OkHttp                | 5.1.0                          |
| Kotlin Coroutines     | 1.10.2                         |
| Core Splashscreen     | 1.2.0                          |

> As versões acima correspondem ao ambiente utilizado na preparação do minicurso. Recomenda-se utilizar as versões indicadas para reduzir problemas de compatibilidade durante a atividade.

---

### Sistema operacional

O projeto foi preparado e validado em:

* macOS 15.6.1
* Apple Silicon (M2)

O projeto utiliza ferramentas e bibliotecas multiplataforma e também poderá ser executado em Windows ou Linux, desde que o ambiente seja compatível com as versões especificadas acima.

---

## 📥 Como obter o projeto

Clone o repositório:

```bash
git clone https://github.com/alinegtcl/descubra-android.git
```

Abra o projeto no **Android Studio**.

Após abrir o projeto:

1. Aguarde a sincronização do Gradle;
2. Confirme que não existem erros no projeto;
3. Aguarde o download das dependências;
4. Conecte um dispositivo Android ou inicie um emulador;
5. Execute o aplicativo.

### Alternativa

Durante o minicurso também será disponibilizada uma cópia do projeto em pendrive para os participantes que tiverem dificuldades para clonar o repositório ou acessar a internet.

---

## 🌐 API utilizada

O aplicativo utiliza a API pública da Wikipédia:

```text
https://pt.wikipedia.org/api/rest_v1/
```

O endpoint utilizado pela aplicação retorna o resumo de um artigo aleatório:

```text
GET /page/random/summary
```

Exemplo de resposta:

```json
{
	"type": "standard",
	"title": "Fortaleza do Morro de São Paulo",
	"displaytitle": "<span lang=\"pt\" dir=\"ltr\"><span class=\"mw-page-title-main\">Fortaleza do Morro de São Paulo</span></span>",
	"namespace": {
		"id": 0,
		"text": ""
	},
	"wikibase_item": "Q10283651",
	"titles": {
		"canonical": "Fortaleza_do_Morro_de_São_Paulo",
		"normalized": "Fortaleza do Morro de São Paulo",
		"display": "<span lang=\"pt\" dir=\"ltr\"><span class=\"mw-page-title-main\">Fortaleza do Morro de São Paulo</span></span>"
	},
	"pageid": 1052266,
	"thumbnail": {
		"source": "https://upload.wikimedia.org/wikipedia/commons/thumb/1/1d/Brazilian_States.PNG/330px-Brazilian_States.PNG?utm_source=pt.wikipedia.org&utm_campaign=api&utm_content=thumbnail",
		"width": 330,
		"height": 333
	},
	"originalimage": {
		"source": "https://upload.wikimedia.org/wikipedia/commons/1/1d/Brazilian_States.PNG?utm_source=pt.wikipedia.org&utm_campaign=api&utm_content=thumbnail_unscaled",
		"width": 387,
		"height": 391
	},
	"lang": "pt",
	"dir": "ltr",
	"revision": "68699364",
	"tid": "ae640041-7b55-11ef-87c0-920ba56bc77a",
	"timestamp": "2024-09-25T15:49:00Z",
	"description": "Bem tombado pelo Instituto do Patrimônio Artístico e Cultural da Bahia na cidade de Cairu",
	"description_source": "central",
	"coordinates": {
		"lat": -13.37444,
		"lon": -38.91583
	},
	"content_urls": {
		"desktop": {
			"page": "https://pt.wikipedia.org/wiki/Fortaleza_do_Morro_de_S%C3%A3o_Paulo",
			"revisions": "https://pt.wikipedia.org/wiki/Fortaleza_do_Morro_de_S%C3%A3o_Paulo?action=history",
			"edit": "https://pt.wikipedia.org/wiki/Fortaleza_do_Morro_de_S%C3%A3o_Paulo?action=edit",
			"talk": "https://pt.wikipedia.org/wiki/Discuss%C3%A3o:Fortaleza_do_Morro_de_S%C3%A3o_Paulo"
		},
		"mobile": {
			"page": "https://pt.wikipedia.org/wiki/Fortaleza_do_Morro_de_S%C3%A3o_Paulo",
			"revisions": "https://pt.wikipedia.org/wiki/Special:History/Fortaleza_do_Morro_de_S%C3%A3o_Paulo",
			"edit": "https://pt.wikipedia.org/wiki/Fortaleza_do_Morro_de_S%C3%A3o_Paulo?action=edit",
			"talk": "https://pt.wikipedia.org/wiki/Discuss%C3%A3o:Fortaleza_do_Morro_de_S%C3%A3o_Paulo"
		}
	},
	"extract": "A Fortaleza do Morro de São Paulo localiza-se na ponta noroeste da ilha de Tinharé, atual distrito de Cairu, no litoral do estado brasileiro da Bahia.",
	"extract_html": "<p>A <b>Fortaleza do Morro de São Paulo</b> localiza-se na ponta noroeste da ilha de Tinharé, atual distrito de Cairu, no litoral do estado brasileiro da Bahia.</p>"
}
```

A aplicação utiliza apenas o título e o resumo retornados pela API.

> A API da Wikipédia é um serviço externo. Portanto, seu funcionamento e disponibilidade dependem do serviço da Wikimedia e de uma conexão ativa com a internet.

 🔐 **User-Agent**

As requisições realizadas pelo aplicativo incluem um `User-Agent` identificando o cliente.

Isso é necessário para o consumo adequado das APIs da Wikimedia e também permite identificar a aplicação que está realizando as requisições.

---

## 👩‍💻 Sobre a palestrante

**Aline Gomes Tolentino da Cruz Luisi**

Engenheira de Software Mobile Sênior, formada em Sistemas de Informação pela Universidade Federal de Ouro Preto (UFOP) e pós-graduada em Desenvolvimento de Sistemas para Dispositivos Móveis pelo Instituto Federal de São Paulo (IFSP).

Atua com desenvolvimento Android nativo, com experiência em Kotlin, arquitetura de aplicações, qualidade de software e práticas de desenvolvimento voltadas a ambientes de grande escala.

Atualmente, trabalha no Itaú Unibanco, atuando em arquitetura e evolução de aplicações mobile.

Ao longo da carreira, também atuou na F1rst Digital Services, empresa do grupo Santander. Além da experiência profissional, atua no compartilhamento de conhecimento por meio de apresentações técnicas, mentorias e atividades de formação de desenvolvedores.

---

## 📄 Licença

Projeto desenvolvido para fins educacionais no minicurso da Semana de Computação do IFSP São Carlos.
