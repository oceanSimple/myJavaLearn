package service;

import entity.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UserService {
    private List<User> users;

    public UserService() {
        users = new ArrayList<>();
        User user1 = new User(1, "ocean", "male", 10000.0, new Date(), "");
        User user2 = new User(2, "ocean", "female", 20000.0, new Date(), "");
        User user3 = new User(3, "ocean", "male", 30000.0, new Date(), "");
        users.add(user1);
        users.add(user2);
        users.add(user3);
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
        System.out.println();
    }
}
