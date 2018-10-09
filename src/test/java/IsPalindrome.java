import com.system.util.tools.DateFormatHelper;
import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Auther: 李景然
 * @Date: 2018/6/12 16:42
 * @Description:
 */
public class IsPalindrome {
    /*
    * 思路：i，j分别指向字符串的首和尾
    * 从首找到第一个字母或者数字，从未找到第一个字母或数字，然后比较这两个字符是否一样，不一样就直接return false；否则循环，直到i不小于j
    * */
    public static boolean isPalindrome(String s) {
        int i = 0;
        int j = s.length() - 1;
        while (i < j) {
            while (i < j && ! (Character.isLetterOrDigit(s.charAt(i))))
                i ++;
            while (i < j && ! (Character.isLetterOrDigit(s.charAt(j))))
                j --;
            if(i < j && Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) return false;
            i ++;
            j --;
        }
        return true;
    }
}
