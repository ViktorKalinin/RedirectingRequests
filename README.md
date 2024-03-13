# Redirecting Requests API

Этот проект реализует REST API для перенаправления запросов на https://jsonplaceholder.typicode.com/

## Стек технологий

- Java 17
- Spring Boot
- Spring Security
- Spring Data JPA
- PostgreSQL
- Docker
- Maven

## Функциональности

  **Перенаправление запросов**:
   - Обработчики запросов (GET, POST, PUT, DELETE) проксируют запросы к https://jsonplaceholder.typicode.com/

  **Авторизация и ролевая модель доступа**:
   - Реализована базовая авторизация с несколькими аккаунтами и четырьмя ролями: ROLE_ADMIN, ROLE_POSTS, ROLE_USERS, ROLE_ALBUMS. 
   - ROLE_ADMIN имеет доступ ко всем обработчикам, остальные роли имеют доступ к соответствующим маршрутам.

  **Ведение аудита**:
   - Действия пользователей логируются.

**In-memory кэш**:
   - Временное хранение данных в памяти реализовано с помощью in-memory кэша для уменьшения числа запросов к jsonplaceholder.

