# Spring Boot Redis Implementation 🚀

Hey project ek professional MNC-standard Java application aahe, jyat **Spring Boot**, **Redis Cache**, ani **Docker** cha vapar kela aahe.

## 🛠 Tech Stack
* **Java:** 17
* **Framework:** Spring Boot 3.x
* **Database:** MySQL
* **Caching:** Redis (Spring Data Redis)
* **Build Tool:** Maven
* **Containerization:** Docker & Docker Compose

## 🚀 Key Features
- **CRUD Operations:** Complete Employee management.
- **Caching Strategy:**
  - `@Cacheable`: Faster data retrieval for IDs.
  - `@CacheEvict`: Auto-refreshing cache on data updates/save.
- **Logging:** Slf4j with MNC-standard logging formats.
- **Production Ready:** Includes Dockerfile and Docker Compose setup.

## 📦 How to Run

### 1. Clone the repository
```bash
git clone [https://github.com/vikaspaithane/Redis.git](https://github.com/vikaspaithane/Redis.git)

🔌 API Endpoints

Method  Endpoint                  Description
POST     /api/v1/employees       Save a new employee
 GET     /api/v1/employees        Fetch all employees
GET      /api/v1/employees/{id}   Get employee by ID (Cached)
PUT      /api/v1/employees/{id}   Update employee data
