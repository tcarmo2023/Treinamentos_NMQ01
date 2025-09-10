#!/bin/bash
echo "Iniciando build..."
cd backend
mvn clean package -DskipTests
