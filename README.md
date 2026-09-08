# Virtual Store

Aplicação de microsserviços para simular o funcionamento básico de uma loja virtual, desenvolvida como trabalho prático para a disciplina de Microsserviços e DevOps. O projeto evolui progressivamente desde a implementação dos microsserviços em Spring Boot até sua execução orquestrada em um cluster Kubernetes, passando por containerização com Docker, comunicação entre serviços e Docker Compose.

Para o passo a passo completo de cada etapa do trabalho, incluindo decisões teóricas, comparações Docker x Kubernetes e evidências de execução, veja [`DOCUMENTACAO.md`](./DOCUMENTACAO.md).

## Arquitetura

O projeto é composto por dois microsserviços independentes, cada um com seu próprio banco de dados:

### `product-service`
Responsável pelo catálogo de produtos.

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/products` | Lista todos os produtos |
| GET | `/products/{id}` | Consulta um produto específico |
| POST | `/products` | Cadastra um novo produto |

### `order-service`
Responsável pelos pedidos. Ao criar um pedido, consulta o `product-service` para validar a existência de cada produto e calcular o valor total automaticamente.

| Método | Endpoint | Descrição |
|---|---|---|
| GET | `/orders` | Lista todos os pedidos |
| GET | `/orders/{id}` | Consulta um pedido específico |
| POST | `/orders` | Cria um novo pedido |

Os dois serviços se comunicam via HTTP (`RestClient`), utilizando o nome do serviço na rede (container ou Service do Kubernetes) em vez de `localhost` ou IPs fixos.

## Stack técnica

- **Java 21**
- **Spring Boot 4** (Spring Web, Spring Data JPA, Spring RestClient)
- **H2 Database** (em memória, um banco independente por serviço)
- **Lombok**
- **Docker** (multi-stage build)
- **Docker Compose**
- **Kubernetes** (Deployment, Service)

## Estrutura do repositório

```
virtual-store/
├── product-service/       # Microsserviço de produtos
├── order-service/          # Microsserviço de pedidos
├── k8s/                     # Manifestos Kubernetes (Deployment e Service)
├── assets/                   # Evidências e prints usados na documentação
├── docker-compose.yml
└── DOCUMENTACAO.md          # Documentação detalhada de todas as etapas do trabalho
```

## Como executar

### Com Docker Compose

Na raiz do projeto:

```bash
docker compose up -d
```

Isso constrói as imagens de ambos os microsserviços, cria a rede e sobe os dois containers. As aplicações ficam disponíveis em:
- `product-service`: `http://localhost:8081`
- `order-service`: `http://localhost:8082`

### Com Kubernetes

Requer um cluster Kubernetes local (ex: Kubernetes embutido no Docker Desktop) com as imagens já construídas localmente (`product-service:1.0` e `order-service:1.0`).

```bash
kubectl apply -f k8s/product-service-deployment.yaml
kubectl apply -f k8s/product-service-service.yaml
kubectl apply -f k8s/order-service-deployment.yaml
kubectl apply -f k8s/order-service-service.yaml
```

Para acessar os serviços localmente:

```bash
kubectl port-forward service/product-service 8081:8081
kubectl port-forward service/order-service 8082:8082
```

## Exemplo de uso

**Cadastrar um produto**
```http
POST /products
Content-Type: application/json

{
    "name": "Notebook Gamer",
    "category": "ELECTRONICS",
    "price": 4500.00,
    "description": "Notebook com placa de vídeo dedicada"
}
```

**Criar um pedido**
```http
POST /orders
Content-Type: application/json

{
    "itens": [
        { "productId": 1, "amount": 2 }
    ]
}
```

O `order-service` consulta o `product-service` para cada item, valida a existência do produto e calcula o `total` automaticamente.
