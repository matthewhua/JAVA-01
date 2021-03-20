package io.matthew.rpcfx.demo.provider;

import io.matthew.rpcfx.demo.api.User;
import io.matthew.rpcfx.demo.api.UserService;

public class UserServiceImpl implements UserService
{

    @Override
    public User findById(int id) {
        return new User(id, "Matthew" + System.currentTimeMillis());
    }
}
