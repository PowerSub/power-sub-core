#!/bin/bash
set -e

psql -v ON_ERROR_STOP=1 --username "$POSTGRES_USER" --dbname "$POSTGRES_DB" <<-EOSQL
	CREATE DATABASE powersub;
	CREATE USER powersub WITH LOGIN PASSWORD 'powersub@123';
	GRANT ALL PRIVILEGES ON DATABASE powersub TO powersub;
	ALTER DATABASE powersub OWNER TO powersub;
EOSQL
