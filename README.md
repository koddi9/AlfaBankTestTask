# Alfa Bank Test Task
## Настройка подключения к API
- Для задания параметров запуска приложения используются ресурсы в директории ***./src/main/resources/***
- Изменить параметры подключения к внешним сервисам можно в файле ***application.properties*** у атрибутов с префиксом ***api.***
### Настройка API сервиса валют
- Под префиксом ***api.currency.*** находятся параметры для подключения к сервису [OpenExchangeRates](https://docs.openexchangerates.org/docs/latest-json).

|Параметр|Описание|
| -----|------|
|app_id|Уникальный "App ID" личного аккаунта (required). В данный момент используется триал версия ключа|
|base|Валюта, относительно которой рассматривается искомый курс. Для смены необходимо иметь аккаунт с более высоким приоиритетом|
|target|Дефолтное значение искомой валюты. Использутеся, если не было задано в качестве параметра при GET запросе (target)|
|value|Название сервиса Feign клиента внутри приложения|
|url|Адрес внешнего сервиса|
### Настройка API сервиса гиф файлов
- Под префиксом ***api.giphy.*** к сервису гиф файлов [Giphy](https://developers.giphy.com/docs/api/endpoint#random)

|Параметр|Описание|
| -----|------|
|api_key|Уникальный "App ID" личного аккаунта (required). В данный момент используется триал версия ключа|
|tag.positive|Тег, используемый при запросе к внешнему сервису для фильтрации гиф файлов при увеличении текущего значения курса, относительно вчерашнего|
|tag.negative|Тег для фильтрации при отрицательном изменении|
|rating|Параметр для фильрации гиф файлов по [указанному рейтингу](https://developers.giphy.com/docs/optional-settings/#rating). Если не указывать, результаты будут получены из всех возможных рейтингов|
|format|Весь контент GIPHY поставляется в нескольких версиях, описывающих размеры и форматы GIF файлов.Некоторые GIF файлы могут не иметь полного набора свойств. [Подробнее](https://developers.giphy.com/docs/optional-settings/#rendition-guide)|
|value|Название сервиса Feign клиента внутри приложения|
|url|Адрес внешнего сервиса|
## Запуск
После запуска приложения запросы будут приниматься на единственный endpoint по адресу **http://localhost:8080/** с Http методом GET.
В качестве параметра передается единственное значение кода валюты **"target"**, которая будет рассматриваться относительно базовой валюты **"base"**.

 ** Если параметр не указан, по умолчанию будет использоваться соответствующее значениe, указанное в настройках приложения. 