# Dentro da pasta do seu projeto
mkdir backend
mv pom.xml backend/
mkdir -p backend/src/main/java/com/normaq/treinamentos/
mkdir -p backend/src/main/resources/static/

# Mover arquivos Java
mv *.java backend/src/main/java/com/normaq/treinamentos/

# Mover arquivos frontend
mv index.html style.css backend/src/main/resources/static/

# Mover application.properties
mv application.properties backend/src/main/resources/

# Verificar estrutura
find . -type f
