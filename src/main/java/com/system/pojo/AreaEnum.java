package com.system.pojo;

/**
 * @Auther: 李景然
 * @Date: 2018/6/11 17:07
 * @Description:
 */
public enum AreaEnum {

    Area_ENUM_ONE("病区1",1),//病区1
    Area_ENUM_TWO("病区2",2),//病区2
    Area_ENUM_THREE("病区3",3),//病区3
    Area_ENUM_FOUR("病区4",4);//病区4

    private String key;
    private int value;

    public String getKey(){
        return this.key;
    }

    public int getValue(){
        return this.value;
    }

    //构造函数必须为private的,防止意外调用
    private AreaEnum(String key, int value){
        this.key = key;
        this.value = value;
    }
}
