# Multi-Tenant Spring Boot Application

This is a sample project to demonstrate the multi-tenant application using Spring Boot.

## DataLoader

Its a generic data loader that can be used to load data from a data source and cache it in memory.
Currently its disabled in the code.

- Since we’re sticking to schema-based multi-tenancy without explicitly adding tenantId in entities, 
we only need to make sure that roles and permissions are created inside the correct schema for each tenant.

- Since your schema automatically switches based on the tenant, we don’t need to manually set the tenantId field.
Instead, we can use the current schema determined by the TenantContext!

steps to run the project:
1. Clone the project
2. Open the project in IntelliJ IDEA
3. Run the project
4. Open the browser in the Postman and hit the URL: http://localhost:8080/tenants
5. Add the tenant using request param: `name`. E.g. `key= name,value=tenant1`
6. Hit the URL: http://localhost:8080/tenants
7. You will see the tenant added in the list.
8. Add the Permission using the URL: http://localhost:8080/permissions
9. Add the role using the URL: http://localhost:8080/roles
10. Add the user using the URL: http://localhost:8080/users
