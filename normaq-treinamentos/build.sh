#!/bin/bash
echo "Copiando arquivos estáticos..."
cd backend
mkdir -p target/classes/static
cp -r src/main/resources/static/* target/classes/static/
mvn clean package -DskipTests
echo "Build concluído!"
