package com.slam.dunk.day03.atomic;

import java.util.concurrent.atomic.AtomicReference;

/**
 * @Author: zerongliu
 * @Date: 3/27/20 15:16
 * @Description:
 */
public class UseAtomicReference {
    static AtomicReference<UserInfo> userRef = new AtomicReference<UserInfo>();

    public static void main(String[] args) {
        //user instance
        UserInfo user = new UserInfo("Mark", 15);
        userRef.set(user);

        //update user instance, the val point to the reference of the update user
        UserInfo updateUser = new UserInfo("Bill", 17);
        userRef.compareAndSet(user, updateUser);
        System.out.println(userRef.get().getName());
        System.out.println(userRef.get().getAge());
        System.out.println(user.getName());
        System.out.println(user.getAge());
    }

    /**
     * user class
     */
    static class UserInfo {
        private String name;
        private int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }
    }
}
