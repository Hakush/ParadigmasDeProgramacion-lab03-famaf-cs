# Lab 3 - Programación distribuida

## Dependencias

Necesitan Java 17, tanto el JRE como el JDK. En Ubuntu, pueden instalarlo con:

```bash
apt install openjdk-17-jdk openjdk-17-jre
```
Tambien se necesita maven, se puede instalar con:
```bash
apt install maven
```

## Compilación y ejecución (make que por dentro usa mvn) (Sólo importa compilar, la ejecución la hace spark-submit)

- Para compilar el código ejecutamos `make compile`, lo cual crea todos los archivos compilados en el directorio `target/`

- Para crear el archivo .jar de nuestro proyecto, ejecutamos `make package`

- Para correr el código ejecutamos `make run ARGS="<flags>"` donde `<flags>` son las flags que corresponden a los args toma la función principal del software.

- `make clean` borra los archivos `.class` que se generan en la compilación

## Compilación y ejecución nueva con Maven (Solo importa compilar, la ejecución la hace spark-submit)

- `mvn compile` crea todos los archivos compilados en el directorio target/classes/

- `mvn clean` borra los archivos `.class` que se generan en la compilación

- `mvn clean package` borra y descarga las dependencias necesarias, creando el archivo .jar correspondiente al proyecto

- `mvn package` crea el archivo .jar correspondiente al proyecto

- `mvn exec:java -Dexec.mainClass="com.group_30_lab3_2024.App" -Dexec.args="<flags>"` Ejecuta el código donde `<flags>` son las flags que corresponden a los args toma la función principal del software.

## Seteo de workers en spark

- Montar un Cluster de Spark

    - Descargar Apache Spark ( spark-3.5.1-bin-hadoop3.tgz) del sitio https://spark.apache.org/downloads.html y descomprimir el archivo descargado en algún directorio de su disco (${SPARK_FOLDER}).

    - Dentro del directorio ${SPARK_FOLDER}/sbin, lanzar una instancia de master: 
```bash
 ./start-master.sh
```
Si está todo ok en la url http://localhost:8080 podemos observar el estado actual del cluster y la <b>URL</b> en la que se iniciarán los Workers, con esta URL ejecutaremos los hijos

- Crear un script para ejecutar N-workers, por ejemplo:

start-2-workers.sh:
```bash
#!/bin/bash

# Set environment variables
export SPARK_WORKER_INSTANCES=2       # Number of worker instances
export SPARK_WORKER_PORT=7078         # Starting port for workers
export SPARK_WORKER_WEBUI_PORT=8081   # Starting port for worker web UI

# Start the workers
./start-worker.sh spark://URL:7077 -m 1G -c 1
```

- Dentro del directorio ${SPARK_FOLDER}/sbin, lanzar N instancias de workers con este script: 
```bash
./start-2-workers.sh
```
Con los scripts de shell anteriores, hemos creado un cluster de Spark con un master y un worker.

Para detener los workers, configurar un archivo workers en ${SPARK_FOLDER}/conf/ con el hostname de cada worker.
Si no, intentar correr el script de crear workers de nuevo y dado el mensaje de error detener los procesos de los hijos

## Correr laboratorio con spark

- Para darle nuestro programa a spark y que este se encargue de ejecutarlo necesitamos correr el siguiente comando <b>en el directorio de spark</b>:
`bin/spark-submit  --master $SPARK_MASTER_URL $APP_HOME/target/FeedsReader-0.1.jar  "<flags>"`

- $APP_HOME es el directorio de nuestra pc en donde este el lector de feeds

- $SPARK_MASTER_URL es la url donde este seteado nuestro master, por ejemplo : `spark://pollo-desktop:7077`

- "< flags >" Son las flags previamente utilizadas en el laboratorio 2

- Nota: en vez de darle $APP_HOME + /archivoTexto.txt lo que hago es darle por defecto tomar como big data el archivo BigData.txt el cual se encuentra en $APP_HOME. 

- Es necesario modificar una línea de código en donde se inicia la sesión de spark para indicarle nuestra URL del master de spark 
    ``` 
    SparkSession spark = SparkSession
        .builder()
        .appName("FeedsReader")
        .master("Aca va la URL de tu Master") 
        .getOrCreate(); 
    ```
 



