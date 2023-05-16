package test;

import com.alibaba.excel.EasyExcel;
import entity.User;
import service.UserService;

public class UserTest {
    public static void main(String[] args) {
        test01();
    }

    public static void test01() {
        String filename = "D:\\git\\myJavaLearn\\easyexcelLearn\\src\\excel\\user.xlsx";
        UserService userService = new UserService();
        EasyExcel.write(filename, User.class)
                .sheet("用户信息")
                .doWrite(userService.getUsers());
    }
}
