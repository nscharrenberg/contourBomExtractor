# postgresqlToExcell
Export PostgreSQL data to Excell in hierarchical order

# Database Connection
As the persistence.xml contains sensitive information, it's not included in the repository.
You'll have to add the peristence.xml manually.

An example of an persistence.xml is as following:
```xml
<?xml version="1.0" encoding="UTF-8"?>
<persistence version="2.0"
             xmlns="http://java.sun.com/xml/ns/persistence" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
             xsi:schemaLocation="http://java.sun.com/xml/ns/persistence http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd">
    <persistence-unit name="contourDB" transaction-type="RESOURCE_LOCAL">
        <provider>org.eclipse.persistence.jpa.PersistenceProvider</provider>
        <class>com.nscharrenberg.contour.domain.Product</class>
        <class>com.nscharrenberg.contour.domain.Bom</class>
        <class>com.nscharrenberg.contour.domain.BomLine</class>
        <properties>
            <property name="eclipselink.ddl-generation" value="none" />
            <property name="javax.persistence.jdbc.driver" value="org.postgresql.Driver" />
            <property name="javax.persistence.jdbc.url" value="jdbc:postgresql://localhost:15433/contourdb" />
            <property name="javax.persistence.jdbc.user" value="root" />
            <property name="javax.persistence.jdbc.password" value="root" />
        </properties>
    </persistence-unit>
</persistence>
```

The values that need to be changed are:
```xml
<property name="javax.persistence.jdbc.url" value="jdbc:postgresql://<DB IP>:<DB PORT>/<DB NAME>" />
<property name="javax.persistence.jdbc.user" value="<DB USER>" />
<property name="javax.persistence.jdbc.password" value="<DB PASS>" />
```

<DB IP> = IP of your database server. If the db is on the same server you should be able to use localhost. If it's on a remote server use the ip of the server.  
<DB PORT> = the port the database is using.
<DB NAME> = the name of the database.
<DB USER> = the user of the database
<DB PASS> = the password of the user.

