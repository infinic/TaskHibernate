package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        UserServiceImpl us = new UserServiceImpl();

        // 1. Создание таблицы User(ов)
        us.createUsersTable();

        // 2. Добавление 4 User(ов) в таблицу с данными на свой выбор.
        //    После каждого добавления должен быть вывод в консоль ( User с именем – name добавлен в базу данных )
        us.saveUser("Иван", "Соколов", (byte) 37);
        us.saveUser("Сергей", "Петров", (byte) 21);
        us.saveUser("Андрей", "Ласточкин", (byte) 27);
        us.saveUser("Алексей", "Манн", (byte) 42);

        // 3. Получение всех User из базы и вывод в консоль ( должен быть переопределен toString в классе User )
        us.getAllUsers().forEach(System.out::println);

        // 4. Очистка таблицы User(ов)
        us.cleanUsersTable();

        // 5. Удаление таблицы
        us.dropUsersTable();
    }
}
