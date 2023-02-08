#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE ps_db;
	CREATE USER powersub WITH LOGIN PASSWORD 'powersub@123';
	GRANT ALL PRIVILEGES ON DATABASE ps_db TO powersub;
	ALTER DATABASE ps_db OWNER TO powersub;
EOSQL