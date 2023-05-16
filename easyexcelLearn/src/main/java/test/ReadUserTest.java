package test;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import entity.User;
import java.util.ArrayList;
import java.util.List;

public class ReadUserTest {
    public static void main(String[] args){
        //read("D:\\git\\myJavaLearn\\easyexcelLearn\\src\\excel\\user.xlsx");
        method01("D:\\git\\myJavaLearn\\easyexcelLearn\\src\\excel\\user.xlsx");
    }

    public static void method01(String path) {
        List<User> users = new ArrayList<>();
        EasyExcel.read(path, User.class, new AnalysisEventListener() {
            /*每读取一行就调用一次*/
            @Override
            public void invoke(Object o, AnalysisContext analysisContext) {
                User user = (User) o;
                users.add(user);
            }

            /*全部读取完后调用*/
            @Override
            public void doAfterAllAnalysed(AnalysisContext analysisContext) {
                System.out.println(users);
                for (User user : users) {
                    System.out.println(user);
                }
            }
        }).sheet().doRead();
    }
}
