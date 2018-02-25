// UserManager.aidl
package com.example.administrator.mytestallhere;
import com.example.administrator.mytestallhere.bean.User;
// Declare any non-default types here with import statements

interface UserManager {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void addUser(in User user);
        List<User> getUsers();
}
