package com.slam.dunk.day07.safeclass;

/**
 * @Author: zerongliu
 * @Date: 3/29/20 18:16
 * @Description:
 */
public class ImmutableFinalRef {
    private final int a;
    private final int b;
    /**
     * here, it is thread unsafe
     */
    private final User user;

    public ImmutableFinalRef(int a, int b) {
        super();
        this.a = a;
        this.b = b;
        this.user = new User(2);
    }

    public int getA() {
        return a;
    }

    public int getB() {
        return b;
    }

    public User getUser() {
        return user;
    }

    public static class User {
        private int age;

        public User(int age) {
            super();
            this.age = age;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

    }

    public static void main(String[] args) {
        ImmutableFinalRef ref = new ImmutableFinalRef(12, 23);
        User u = ref.getUser();
        //u.setAge(35);
    }
}
