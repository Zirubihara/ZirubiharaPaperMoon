# Photo Traveller

## Informacje ogólne Photo Traveller

Aplikacja Photo Traveller pozwala użytkownikom na tworzenie ogłoszeń i publikowanie zdjęć. Aplikacja wziąć jest rozwijana
ponieważ wydaje się być ciekawą alternatywą dla portalów typu shutterstock. Jesteś muzykiem i potrzebujesz do swojego 
teledysku nagrania z miejsca które znajduje się daleko od Ciebie? Lub potrzebujesz nagrania jakiegoś przedmiotu który
jest bardzo rzadki? Aplikacja znajduje swoje zastosowanie w miejscach gdzie potrzebujesz pewnych ujęć lub zdjęć których
nie możesz sam wykonać. Nie musisz już szukać dokładnie takiego ujęcia w sieci i wybierać tego które najmniej wybiega od
twojej wizji. Możesz teraz to w łatwy sposób komuś zlecić! Ponadto możesz oceniać oferty.

Aplikacja zostanie w przyszłości rozwinięta o:
Powiadomienia gdy ktoś doda post w interesujących Cię podkategoriach
Zdjęcia oraz filmy będą przechowywane w Bucket-cie w Google Cloude - aktualnie zrezygnowano z tego podejścia ze
względu na wczesną fazę aplikacji i opłaty związane z tą usługą
Podpięcie PayPala
Dokończenie prac nad m.in. HATEOAS 
FrontEnd



## Endpoints

### Uwierzytelnianie
#### Rejestracja 

`POST /api/auth/signup`

**Dane wejściowe**:
```json
{
    "username": "[nazwa użytkownika]",
    "email": "[adres email]",
    "password": "[hasło]"
}
```
**Po rejestracji**:
Po zarejestrowaniu należy otworzyć otrzymany adres email i kliknąć w link aktywacyjny - ze wzgledu na wczesną wersję aplikacji
zaleca się korzystanie z np. mail trapa.

<br/>



#### Logowanie

`POST /api/auth/login`

**Dane wejściowe**:
```json
{
    "username": "[nazwa użytkownika]",
    "password": "[hasło]"
}
```
  
**Odpowiedź**:

Jeśli udało Ci siępoprawnie zalogować zostaną wygenerowane kody authenticationToken, refreshToken oraz data wygaśnięcia tokenu.
Gdy token autoryzacyjny wygaśnie, przy użyciu tokenu odświeżającego można wygenerować nowy kod dla użytkownika.

<br/>
  
### Photos (Zbiorcze kategorie)

#### Pokaż wszystkie Photos

`GET /api/photos`

<br/>

#### Pokaż jedno Photos

`GET /api/photos/{id}`

<br/>

#### Dodaj Photos
 
`POST /api/photos`
 
**Dane wejściowe**:
```json
{
	"name": "[nazwa Photos]",
	"description": "[opis Photos]"
}
```

<br/>



### Posty

#### Pokaż wszystkie Posty

`GET /api/posts`

<br/>

#### Pokaż jeden Post

`GET /api/posts/{id}`

<br/>

#### Pokaż Post po Photos

`GET /api/categories/by-photos/{id}`

<br/>

#### Pokaż Post po Użytkowniku

`GET /api/categories/by-user/{id}`

<br/>

#### Dodaj Post

`POST /api/posts`

**Dodaj Post**:
```json
{
    "photosName":"[nazwa isniejacego photos]",
    "postNAme":"[nazwa posta]",
    "url":"[link]",
    "description":"[opis]"
}
```
<br/>

### Komentarze

#### Dodaj Komentarz

`POST /api/comments`

```json
{
	    "postId":"[ id posta jak liczba]",
        "text":"[treść komentarza]",
        "userName":"[ nazwa użytkownika ]"
}
```

<br/>

#### Pokaż wszystkie Komentarze

`GET /api/comments`

<br/>

#### Pokaż wszystkie Poście

`GET /api/comments/by-post/{postId}`

<br/>


#### Pokaż Komentarz po Użytkowniku

`GET /api/comments/by-user/{userName}`

<br/>

### Głosowanie

#### Zagłosuj oceniając post

`POST /api/votes`

```json
{
	      "voteType":"[DOWNVOTE / UPVOTE]",
          "postId":"[postId jako liczba]"
}
```

<br/>
