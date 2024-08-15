package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
    public static void main(String[] args) throws SQLException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        User user1 = new User("User1", "Lastname1", "user1@mail.ru");
        User user2 = new User("User2", "Lastname2", "user2@mail.ru");
        User user3 = new User("User3", "Lastname3", "user3@mail.ru");
        User user4 = new User("User4", "Lastname4", "user4@mail.ru");

        Car audi = new Car("bmw", 1);
        Car bmw = new Car("volvo", 2);
        Car opel = new Car("mercedes", 3);
        Car ferrari = new Car("toyota", 4);

        userService.add(user1.setCar(audi));
        userService.add(user2.setCar(bmw));
        userService.add(user3.setCar(opel));
        userService.add(user4.setCar(ferrari));


        List<User> users = userService.listUsers();
        System.out.println(users.size());
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            if (user.getCar() != null) {
                System.out.println("Car Model = " + user.getCar().getModel());
                System.out.println("Car Series = " + user.getCar().getSeries());
            } else {
                System.out.println("No car assigned");
            }
            System.out.println();
        }
        User foundUser = userService.getUserByCar("bmw", 1);
        if (foundUser != null) {
            System.out.println("Найден пользователь: " + foundUser.getFirstName() +
                    " с автомобилем модели " + foundUser.getCar().getModel());
        } else {
            System.out.println("Пользователь с указанным автомобилем не найден.");
        }
        User foundUser2 = userService.getUserByCar("mercedes", 3);
        if (foundUser2 != null) {
            System.out.println("Найден пользователь: " + foundUser.getFirstName() +
                    " с автомобилем модели " + foundUser2.getCar().getModel());
        } else {
            System.out.println("Пользователь с указанным автомобилем не найден.");
        }
        User foundUser3 = userService.getUserByCar("volvo", 2);
        if (foundUser3 != null) {
            System.out.println("Найден пользователь: " + foundUser.getFirstName() +
                    " с автомобилем модели " + foundUser3.getCar().getModel());
        } else {
            System.out.println("Пользователь с указанным автомобилем не найден.");
        }

        context.close();
    }
}
