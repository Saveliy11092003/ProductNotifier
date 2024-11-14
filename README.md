<p><b>Микросервисная система на основе event driven архитектуры с использованием Apache Kafka</b></p>

<p> Есть микросервис producer - ProductMicroservice и микросервис consumer - EmailNotification.
<p> Доставка сообщений осуществляется с помощью Kafka Cluster поднятого в Docker.

На стороне ProductMicroservice реализованы:
1. Создание топика, в который отправляются сообщения
2. Механизм отправки сообщений в Kafka Cluster
3. Java config в KafkaConfig.java с настройками сериализации, acknowlegment config, idempotence, timeouts, linger.

На стороне EmailNotification реализованы:
1. Механизм получения сообщений
2. Обработка ошибок с использованием DLT, Not Retryable и Retryable Exception.
3. Java config в KafkaConfig.java с настройками десериализации, trusted packages, group id.

Действия перед запуском:
1. cd deploy - перейти в папку deploy;
2. docker-compose up -d - поднять Kafka Cluster;

В процессе выполнения проекта были изучены следующие темы:
1. Topic, Partition, Message, Event, Kafka Broker, Kafka Controller
2. Конфигурация и запуск кластера
3. Kafka Topic CLI, Kafka Producer CLI, Kafka Consumer CLI
