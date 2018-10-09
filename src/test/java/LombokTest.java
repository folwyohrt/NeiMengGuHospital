import com.alibaba.druid.sql.visitor.functions.Hex;
import lombok.extern.java.Log;

import java.util.Calendar;
import java.util.Date;

/**
 * @Auther: 李景然
 * @Date: 2018/8/20 20:10
 * @Description:
 */
@Log
public class LombokTest {

    public static void main(String[] args) {

        String str5 = String.valueOf(null);

        String temp = "hh".intern();
        String s1 = "a" + temp;
        String s2 = "ahh";
        System.out.println(s1 == s2);    // false

        String a = new String("123") + new String("456"); //常量池中不存在"123456",这就是这么写的目的。
        System.out.println(a == a.intern()); // 1.6及以下是false，1.7以上是true

        Student student = new Student();
        student.setAge(27);
        student.setMale("man");
        student.setName("lance");
        student.setStudentNo("2017");

        System.out.println(student.toString());

        Student student2 = new Student();
        student2.setAge(27);
        student2.setMale("man");
        student2.setName("lance");
        student2.setStudentNo("2017");

        System.out.println(student.equals(student2));

        student2.setStudentNo("2018");

        System.out.println(student.equals(student2));

        log.info("lombok test");

    }
}
