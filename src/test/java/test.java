import org.springframework.beans.BeanUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * @Auther: 李景然
 * @Date: 2018/6/12 16:42
 * @Description:
 */
public class test {
    public static void main(String[] args) throws ParseException {
        long start= System.currentTimeMillis();
        UserDTO userDTO=new UserDTO();
        userDTO.setUserId(1);
        userDTO.setSurgeryDatetime("2017-10-10 13:32:40");
        userDTO.setUserName("xiaoming");
        userDTO.setUserPwd("123abc");

        User user=new User();
        String dateStr=userDTO.getSurgeryDatetime();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            user.setSurgeryDatetime(sdf.parse(dateStr));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        user.setUserId(userDTO.getUserId());
        user.setUserName(userDTO.getUserName());
        user.setUserPwd(userDTO.getUserPwd());

        long end= System.currentTimeMillis();
        System.out.println("set get用时"+(start-end)+"毫秒");

        User user1=new User();
        long shijiancha=useCoryProperties(userDTO,user1);
        user1.setSurgeryDatetime(sdf.parse(userDTO.getSurgeryDatetime()));
        System.out.println("BeanUtils.copyProperties用时"+shijiancha+"毫秒。不能转换时间！");
        String dateStr1=sdf.format(user1.getSurgeryDatetime());
        System.out.println("手动处理的时间，结果是："+dateStr1);
    }

    private static Long useCoryProperties(Object a, Object b){
        Long start = System.currentTimeMillis();
        BeanUtils.copyProperties(a, b);
        Long end = System.currentTimeMillis();
        return end - start;
    }
}
