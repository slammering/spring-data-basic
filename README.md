# Spring Basic
This repository has two branches
- main: With H2 in memory database, can be startet without any additional ressources
- mssql: With MSSQL Database and schema creation with liquibase. The MSSQL server can be started via docker:

```
docker run --rm -e 'ACCEPT_EULA=Y' -e 'SA_PASSWORD=Geheim!1234' -p 1433:1433 -d mcr.microsoft.com/mssql/server:2017-latest
```
