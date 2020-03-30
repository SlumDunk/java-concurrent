package com.slam.dunk.day05.bitwise;

/**
 * @Author: zerongliu
 * @Date: 3/28/20 11:47
 * @Description:
 */
public class Permission {
    /**
     * allow SELECT 0001  = 1
     */
    public static final int ALLOW_SELECT = 1 << 0;

    /**
     * allow INSERT 0010  = 2
     */
    public static final int ALLOW_INSERT = 1 << 1;

    /**
     * allow UPDATE 0100  =4
     */
    public static final int ALLOW_UPDATE = 1 << 2;

    /**
     * allow DELETE 1000  = 8
     */
    public static final int ALLOW_DELETE = 1 << 3;
    /**
     * permission status
     */
    private int flag;

    /**
     * set permission
     *
     * @param per
     */
    public void setPer(int per) {
        flag = per;
    }

    /**
     * add permission
     *
     * @param per
     */
    public void enable(int per) {
        flag = flag | per;
    }

    /**
     * disable permission
     *
     * @param per
     */
    public void disable(int per) {
        flag = flag & ~per;
    }

    /**
     * has permission
     *
     * @param per
     * @return
     */
    public boolean isAllow(int per) {
        return ((flag & per) == per);
    }

    /**
     * has no permission
     *
     * @param per
     * @return
     */
    public boolean isNotAllow(int per) {
        return ((flag & per) == 0);
    }

    public static void main(String[] args) {
        int flag = 15;
        Permission permission = new Permission();
        permission.setPer(flag);
        permission.disable(ALLOW_DELETE | ALLOW_INSERT);
        System.out.println("select = " + permission.isAllow(ALLOW_SELECT));
        System.out.println("update = " + permission.isAllow(ALLOW_UPDATE));
        System.out.println("insert = " + permission.isAllow(ALLOW_INSERT));
        System.out.println("delete = " + permission.isAllow(ALLOW_DELETE));


    }
}
