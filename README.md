# Instrukcja uruchomienia
Wymagania wstępne
Zainstalowane środowisko Docker oraz Docker Compose.

Prawidłowo skonfigurowany plik .env w katalogu głównym projektu.

Konfiguracja środowiska
Przed uruchomieniem należy upewnić się, że plik .env zawiera niezbędne dane uwierzytelniające dla bazy danych:

DB_USER – nazwa użytkownika bazy danych.

DB_PASSWORD – hasło użytkownika.

DB_NAME – nazwa bazy danych.

Proces uruchomienia
W celu zbudowania i uruchomienia całego stosu technologicznego należy wykonać poniższą komendę w terminalu:

docker-compose up --build
Komenda ta automatycznie przeprowadzi proces budowania aplikacji (Multi-stage build), pobierze niezbędne zależności i uruchomi kontener z bazą danych PostgreSQL oraz kontener z aplikacją backendową.

Dokumentacja API
Aplikacja udostępnia interfejs REST API pod bazowym adresem /api.

Dostępne endpointy
Metoda	Adres URL	Opis
- GET	/api/tasks	Pobiera listę wszystkich zapisanych zadań.
- GET	/api/tasks/{id}	Pobiera szczegółowe informacje o zadaniu o wskazanym identyfikatorze.
- POST	/api/tasks	Tworzy nowe zadanie na podstawie przesłanego obiektu JSON.
- PUT	/api/tasks/{id}	Aktualizuje dane istniejącego zadania o podanym identyfikatorze.
- DELETE	/api/task/{id}	Usuwa zadanie o wskazanym identyfikatorze z systemu.
Format danych wejściowych (TaskCreationDto)

Funkcjonalności systemu
Automatyczne tworzenie tabel: Dzięki integracji Hibernate, struktura bazy danych jest automatycznie aktualizowana przy starcie aplikacji.

Walidacja danych: System weryfikuje poprawność przesyłanych danych wejściowych przy użyciu adnotacji @Valid.

Trwałość danych: Wykorzystanie wolumenów Dockera (postgres_data) zapewnia zachowanie danych nawet po usunięciu kontenerów.

Obsługa błędów: W przypadku próby dostępu do nieistniejącego zasobu, aplikacja rzuca dedykowany wyjątek TaskNotFoundException zwracający status HTTP 404.

Dokumentacja interaktywna (Swagger UI): Po poprawnym uruchomieniu aplikacji w kontenerze Docker, interfejs Swagger UI jest dostępny pod następującym adresem:
`http://localhost:8080/swagger-ui/index.html`
