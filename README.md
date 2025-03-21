# Proyecto de Acceso de Personal con Microservicios

Este proyecto implementa un **sistema centralizado de control de acceso de personal** utilizando una arquitectura de **microservicios** con tecnologías modernas. Se basa en **Java, Python, OpenCV, gRPC, Apache Kafka y SQL Server**, con la intención de ser desplegado en **Docker** en el futuro.

## 🚀 Características
- **Microservicios en Java y Python** para la gestión de acceso.
- **SQL Server** como base de datos centralizada.
- **gRPC** para la comunicación eficiente entre microservicios.
- **Apache Kafka** para el manejo de eventos y mensajería en tiempo real.
- **OpenCV** para reconocimiento facial y procesamiento de imágenes.
- **Docker (Futuro)** para la contenedorización y despliegue escalable.

---

## 📌 Instalación
### **1. Clonar el repositorio**
```bash
 git clone https://github.com/tu-usuario/tu-repo.git
 cd tu-repo
```

### **2. Configurar la Base de Datos (SQL Server)**
Asegúrate de tener **SQL Server** instalado y crea la base de datos necesaria.
Ejecuta los scripts SQL en `db/init.sql` para inicializar las tablas.

### **3. Configurar y Ejecutar los Microservicios**
#### **Microservicio en Java (Backend Principal)**
```bash
cd java-service
./gradlew bootRun
```
#### **Microservicio en Python (Procesamiento de Imágenes con OpenCV)**
```bash
cd python-service
pip install -r requirements.txt
python app.py
```
#### **Ejecutar Apache Kafka**
Si usas Kafka localmente, inicia ZooKeeper y Kafka:
```bash
bin/zookeeper-server-start.sh config/zookeeper.properties
bin/kafka-server-start.sh config/server.properties
```

---

## 🏗️ Arquitectura del Proyecto
El sistema se compone de los siguientes microservicios:

### **1. Microservicio de Gestión de Acceso (Java + Spring Boot + SQL Server)**
- Autenticación de usuarios y validación de accesos.
- Comunicación con otros servicios a través de **gRPC**.
- Almacena datos en **SQL Server**.

### **2. Microservicio de Procesamiento de Imágenes (Python + OpenCV)**
- Captura imágenes desde cámaras conectadas.
- Aplica algoritmos de reconocimiento facial con OpenCV.
- Envía los datos al servicio de acceso a través de **gRPC**.

### **3. Microservicio de Eventos y Notificaciones (Apache Kafka)**
- Gestiona eventos de entrada/salida de personal.
- Publica notificaciones a sistemas externos o dashboards.

---

## 🚀 Ejecución en Producción
### **Ejecutar con Docker (Próximamente)**
Cuando el sistema esté listo para ser desplegado en **Docker**, se podrá ejecutar con:
```bash
docker-compose up -d
```
Esto iniciará todos los servicios automáticamente.

### **Configurar Nginx como Proxy Reverso**
Para exponer los servicios a través de **Nginx**, configura el archivo `/etc/nginx/sites-available/microservicios`:
```nginx
server {
    listen 80;
    server_name tu-dominio.com;

    location /api/ {
        proxy_pass http://127.0.0.1:8080;
    }
}
```

---

## 🔍 📖 Endpoints Disponibles
| Servicio | Método | Endpoint | Descripción |
|----------|--------|---------|-------------|
| Gestión de Acceso | gRPC | `/validateAccess` | Valida el acceso de un usuario |
| Procesamiento de Imágenes | gRPC | `/processImage` | Procesa una imagen para reconocimiento facial |
| Eventos y Notificaciones | Kafka | `acceso-topic` | Publica eventos de acceso |

---

## 📜 Licencia
Este proyecto está bajo la **Licencia MIT**. ¡Siéntete libre de modificar y mejorar el código!

---

## 🤝 Contribuciones
Las contribuciones son bienvenidas. Si quieres mejorar el código o agregar nuevas funciones, por favor abre un **Pull Request** o crea un **Issue**.

**💡 Autor:** _Tu Nombre / Tu Organización_

