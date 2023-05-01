# Java Test Task

**Task:
Create an API. It should contain two methods:**
1. Request for a list of exchange rates for all sources, with average
   market rates
2. Request to issue a list of average exchange rates for all sources for
   the period
   On a regular basis, the web application loads data from a third-party service
   into an internal database. Implemented API requests must operate on a
   local database (i.e. retrieve data from a database).
   What to use:
3. Use public API
    https://api.monobank.ua/docs/
    https://minfin.com.ua/ua/developers/api/
    https://api.privatbank.ua/#p24/exchange

 For each of the providers, implement a common interface and
   separate implementations

4. Cron job - to implement data synchronization with api providers.
5. Java/Kotlin, Tomcat, Spring Boot, Hibernate, PostgresQL; GIT
6. Requests should be documented using Swagger 
7. Use Gradle as project builder


### API

Localhost URL: https://localhost:8080/swagger-ui/index.html