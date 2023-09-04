package com.invisibleprogrammer.invisibletirapi.users;

import java.util.ArrayList;
import java.util.List;

public class InMemoryUserRepository {
    private List<User> users;

    public InMemoryUserRepository() {
        this.users = new ArrayList<>();
    }
}
