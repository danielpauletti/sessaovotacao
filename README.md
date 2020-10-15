# sessaovotacao
**Endpoints**
- POST /pauta 
```
{
  "descricao":"Pauta nova 4"
}    
```
//201 CREATED
```
{
  "status": 201,
  "mensagem": "Pauta criada com sucesso!",
  "entity": {
    "id": 8,
    "descricao": "Pauta nova 4"
  }
}
```
===========================================
- POST /sessao/nova-sessao 
```
{
  "descricao":"Sessão da 'Pauta nova 4'",
  "id_pauta":8,
  "tempo":5
}    
```
//201 CREATED 
```
{
  "status": 201,
  "mensagem": "Sessão criada com sucesso!",
  "entity": {
    "id": 17,
    "tempo_total": 5,
    "descricao": "Sessão da 'Pauta nova 4'",
    "data_abertura": "15/10/2020 18:37:32",
    "data_fechamento": null
   }
}    
```
===========================================
- POST /sessao/computar-voto 
```
{
  "descricao":"Sessão da 'Pauta nova 2'",
  "id_pauta":2,
  "id_sessao":18,
  "cpf_cnpj_associado":"02972314005",
  "voto":"S"
}   
```
//201 CREATED 
```
{
  "status": 201,
  "mensagem": "Voto computado com sucesso!",
  "entity": {
    "id": 19,
      "pauta": {
        "id": 8,
        "descricao": "Pauta nova 4"
      },
      "sessao": {
        "id": 18,
        "descricao": "Sessão da 'Pauta nova 4'",
          "pauta": {
            "id": 8,
            "descricao": "Pauta nova 4"
          },
          "duracao": 5,
          "dataAbertura": 1602793798776,
          "dataFechamento": null
        },
        "voto": "S",
        "cpfCnpjAssociado": "01982304006"
      }
    }  
 ```
===========================================
- PUT sessao/fechar-sessao/{idSessao}   
//200 OK 
```
  {
               "status": 200,
               "mensagem": "Sessão fechada com sucesso!",
               "entity": {
                   "sessao": {
                       "id": 20,
                       "descricao": "Sessão da 'Pauta nova 99'",
                       "pauta": {
                           "id": 8,
                           "descricao": "Pauta nova 4"
                       },
                       "duracao": 5,
                       "dataAbertura": 1602794146833,
                       "dataFechamento": 1602794161533
                   },
                   "votos_sim": 0,
                   "votos_nao": 0
               }
           }
```
