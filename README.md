
<p>Самофалов Артем Викторович</p>
<p>Курс "Разработчик на Spring Framework"</p>
<p>Группа 2022-08</p>

<p>Проектная работа</p>

Для работы приложений требуется работающий RabbitMQ. За его запуск отвечает docker-compose.yml
Адрес консоли RabbitMQ: http://localhost:15672/
Логин и пароль: guest

Консоль заявок: http://localhost:8080/
Логин и пароль: admin

Логи пишутся в каталог /logs/pw/

Тестовые данные
Существующие паспорта:
1111 222222
1112 222222

Террорист:
1111 222222 или имя terrorist )

Окончательное решение принимается рандомно с вероятностью 75%(одобрена)/25%(отклонена)
Ставка выбирается рандомно в диапазоне из параметров 
percentage_rate_min: 15
percentage_rate_max: 30

Пример запроса подачи заявки:
POST http://localhost:8080/api/requests
Content-Type: application/json
{
"name": "ivan",
"docSeries": "1112",
"docNumber": "222222",
"docDate": "2000-01-01",
"sum": 1
}

Пример запроса состояния заявки:
GET http://localhost:8080/api/requests/a9cb76be-dfa3-4455-9dce-bb4e40d41b57