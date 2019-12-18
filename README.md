# Fitness center

### Used design patterns:

* MVC
* Factory
* Command
* DAO
* Singleton

### Application functionality

#### General
* View fitness center information
* Display of a table with trainers
* Sending messages to email using a special form
* Language change Russian/English

#### Client
* Registration
* Login/logout
* View/Update profile information
* Make order
* View list of orders
* Pay/Delete order

#### Trainer
* Registration
* Login/logout
* View/Update profile information
* View his clients with their orders
* Appoint equipment and description for selected client

#### Admin
* Login/logout
* View list of users
* Block/Unblock users
* View information about all users

---
###### Фитнес центр. Общая постановка условия. Клиент осуществляет заказ на цикл тренировок. Тренер определяет нагрузки и режим, делает назначение клиенту (упражнения, снаряды). Назначение может выполнить личный тренер. Клиент может отказаться и/или заменить часть назначений. Постоянным и корпоративным клиентам назначаются скидки.