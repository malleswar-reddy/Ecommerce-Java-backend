Here’s an improved and more detailed version of your **"How to Run Liquibase"** documentation with corrected spelling,
formatting, and additional context:

---

# ✅ How to Run Liquibase

Liquibase is used for managing database schema changes. This guide explains how to execute Liquibase migrations for your
services using a shell script.

---

## ✅ Prerequisites

Before running the script, ensure the following are set up:

1. **Docker** is installed and running on your machine
2. **PostgreSQL** container is up and running
3. The required **database (e.g., `adminservice`)** is already created inside PostgreSQL
4. Your Liquibase changelog and properties files are correctly configured in the respective service module

---

## 🛠️ File Structure Example

Each service module (e.g., `adminservice`) should contain:

``` 
├── liquibase/
│   ├── changelog-master.xml
│   ├── changesets/
│   │   └── 001-init-schema.xml
│   └── liquibase.properties
├── run-liquibase.sh
```

---

## 🔐 Grant Execute Permission to Script

Run the following command to make the script executable:

```bash
chmod +x run-liquibase.sh
```

---

## 🚀 Run Liquibase

Use the following command to apply Liquibase migrations to the target database:

```bash
sh run-liquibase.sh admin-service dropAll
```

Where:

* `adminservice` is the name of the service/database module you are applying the migrations to.

---

## 🧪 Verifying the Migration

You can verify if the migration was successful by checking:

* The logs printed in the terminal
* The `DATABASECHANGELOG` and `DATABASECHANGELOGLOCK` tables in your database

---

## 📦 Sample `run-liquibase.sh` Script

Here’s a basic template for `run-liquibase.sh` if needed:

```bash
#!/bin/bash

SERVICE_NAME=$1

if [ -z "$SERVICE_NAME" ]; then
  echo "❌ Service name not provided. Usage: sh run-liquibase.sh <service-name>"
  exit 1
fi

echo $SERVICE_NAME/liquibase

mvn liquibase --defaultsFile=liquibase-$SERVICE_NAME.properties --changeLogFile=changelog-$SERVICE_NAME.yml update

```

## troubleshooting shooting steps

If you encounter issues while running Liquibase, consider the following troubleshooting steps:

```shell

./run-li
```