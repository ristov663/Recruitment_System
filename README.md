![image](https://github.com/user-attachments/assets/354bc31f-1352-47e6-8901-f11573d615d6)

<h1>Дипломска работа (Diploma thesis)<h1/>
  
Дипломска работа на тема РАЗВОЈ НА ВЕБ-БАЗИРАН СИСТЕМ ЗА УПРАВУВАЊЕ СО РЕГРУТАЦИСКИ ПРОЦЕСИ СО ИНТЕЛИГЕНТЕН АСИСТЕНТ

Diploma thesis on the topic DEVELOPMENT OF A WEB-BASED SYSTEM FOR MANAGEMENT OF RECRUITMENT PROCESSES WITH INTELLIGENT ASSISTANT



## Опис

Овој проект претставува **веб-базиран систем за управување со регрутациски процеси**, кој овозможува автоматизација, анализа и оптимизација на процесот на вработување.

Системот интегрира **интелигентен асистент базиран на вештачка интелигенција**, кој овозможува:
* семантичко споредување на кандидати и огласи
* автоматско рангирање на кандидати
* објаснување на одлуките (Explainable AI)
* препораки за работни позиции

## Цели на системот
* Централизирано управување со кандидати и огласи
* Подобрување на процесот на селекција
* Намалување на времето за вработување
* Поддршка при донесување одлуки преку AI
---

## Главни функционалности

### Управување со корисници
* Регистрација и најава (JWT Authentication)
* Role-based пристап (ADMIN, HR, CANDIDATE)

### Управување со огласи
* Креирање, ажурирање и бришење на огласи
* Пребарување и филтрирање

### Апликации за работа
* Аплицирање на кандидати
* Следење на статус (pipeline)
* Управување од страна на HR

### CV Management
* Upload на CV (PDF)
* Парсирање на CV
* Чување на податоци за кандидат
---

## AI функционалности

### Semantic Matching (Spring AI)
* Споредување на CV и job description со embeddings
* Пресметка на match score

### Explainable AI
* Објаснување зошто кандидатот е соодветен
* Транспарентно донесување одлуки

### Ranking System
* Рангирање на кандидати според AI score
* Избор на најдобар кандидат

### CV Summarization
* Автоматско генерирање краток опис на кандидат

### Job Recommendations
* Персонализирани препораки за кандидати
---

## Архитектура

Системот е изграден според **Clean / Onion Architecture**:
* Domain Layer
* Repository Layer
* Service Layer
* Controller Layer
---

## Технологии

### Backend
* Kotlin
* Spring Boot
* Spring Security (JWT)
* Spring AI
* PostgreSQL
* JPA / Hibernate

### Frontend
* React

### AI
* Spring AI
* OpenAI (embeddings + chat models)
---

## Безбедност
* JWT Authentication
* Role-based Authorization
* Secure endpoints
---

## Како да се стартува
```bash
# Clone repository
git clone https://github.com/your-username/recruitment-system

# Navigate
cd recruitment-system

# Run backend
./gradlew bootRun
```

---

## API

Base URL:

```
/api/v1
```

* `/users`
* `/jobs`
* `/applications`
* `/applications/{id}/explain`
* `/analytics`

---

## Заклучок

Овој систем претставува **модерно решение за дигитализација на HR процесите**, со фокус на:

* автоматизација
* интелигентна анализа
* подобрување на одлуките

---
---


## Overview

This project is a **web-based recruitment management system** designed to optimize and automate the hiring process using modern technologies and artificial intelligence.

It integrates an **AI-powered assistant** that enhances decision-making through semantic analysis and intelligent recommendations.

---

## Objectives
* Centralized recruitment management
* Faster hiring process
* Data-driven decision making
* Intelligent candidate evaluation

---

## Core Features

### User Management
* JWT Authentication
* Role-based access (ADMIN, HR, CANDIDATE)

### Job Management
* Create, update, delete job postings
* Search and filtering

### Applications
* Candidate job applications
* Recruitment pipeline tracking
* HR management tools

### CV Handling
* CV upload (PDF)
* CV parsing
* Candidate profile management

---

## AI Capabilities

### Semantic Matching (Spring AI)
* Embedding-based similarity between CV and job
* Intelligent match scoring

### Explainable AI
* AI-generated explanations for decisions
* Transparent candidate evaluation

### Candidate Ranking
* Ranking based on AI score
* Best candidate selection

### CV Summarization
* AI-generated candidate summaries

### Job Recommendations
* Personalized job suggestions for candidates

---

## Architecture

Built using **Clean / Onion Architecture**:

* Domain Layer
* Repository Layer
* Service Layer
* Controller Layer
---

## Tech Stack

### Backend
* Kotlin
* Spring Boot
* Spring Security (JWT)
* Spring AI
* PostgreSQL
* JPA / Hibernate

### Frontend
* React

### AI
* Spring AI
* OpenAI APIs
---

## Security
* JWT Authentication
* Role-based Authorization
* Secure REST APIs
---

## Getting Started
```bash
# Clone repository
git clone https://github.com/your-username/recruitment-system

# Navigate
cd recruitment-system

# Run backend
./gradlew bootRun
```

---

## API

Base URL:

```
/api/v1
```

* `/users`
* `/jobs`
* `/applications`
* `/applications/{id}/explain`
* `/analytics`

---

## Conclusion

This project demonstrates a **modern AI-powered recruitment system** that combines:

* intelligent automation
* semantic understanding
* scalable architecture

to significantly improve hiring efficiency.

---
