package com.leach.advanced.enums;

interface Charactor{
    void describle();
}

/**
 * 枚举类实现接口
 */
public enum City implements Charactor{

    // 枚举类实例
    Beijin("北京","110000000000",1){
        @Override
        public void describle(){
            System.out.println("中国首都"+this.getName()+",是全国的政治、文化中心。" +
                    "北京地处中国华北地区，中心位于东经116°20′、北纬39°56′，东与天津毗连，其余均与河北相邻，北京市总面积16410.54平方千米。");
        }
    },
    Shanghai("上海","310000000000",1){
        @Override
        public void describle(){
            System.out.println(this.getName()+",简称“沪”或“申”，是中国共产党的诞生地，是中华人民共和国省级行政区、直辖市，全国的科技、贸易、信息、金融和航运中心。" +
                    "上海位于中国华东地区，界于东经120°52′-122°12′，北纬30°40′-31°53′之间，地处长江入海口，东隔东中国海与日本九州岛相望，南濒杭州湾，" +
                    "北、西与江苏、浙江两省相接，上海市总面积6340.5平方千米。");
        }
    },
    Guangzhou("广州","440100000000",1){
        @Override
        public void describle(){
            System.out.println(this.getName()+",简称穗，别称羊城、花城，是广东省省会、副省级市、国家中心城市、超大城市、国际大都市、国际商贸中心、" +
                    "国际综合交通枢纽、国家综合性门户城市，首批沿海开放城市，是南部战区司令部驻地 。广州地处广东省中南部，珠江三角洲北缘，濒临南海，" +
                    "邻近香港、澳门，是中国通往世界的南大门，是粤港澳大湾区、泛珠江三角洲经济区的中心城市以及“一带一路”的枢纽城市。");
        }
    },
    Shenzhen("深圳","440300000000",1){
        @Override
        public void describle(){
            System.out.println(this.getName()+",简称“深”，别称“鹏城”，是中国四大一线城市之一，广东省省辖市、计划单列市、副省级市、国家区域中心城市、" +
                    "超大城市，国务院定位的全国性经济中心城市和国际化城市、国家创新型城市、国际科技产业创新中心、全球海洋中心城市、国际性综合交通枢纽，" +
                    "粤港澳大湾区四大中心城市之一，中国三大全国性金融中心之一。");
        }
    };

    // 属性
    private String name;
    private String zone;
    private int grade;

    // 构造方法
    City(String name, String zone, int grade){
        this.name = name;
        this.zone = zone;
        this.grade = grade;
    }

    // getter 方法
    public String getName(){
        return name;
    }
    public String getZone(){
        return zone;
    }
    public int getGrade(){
        return grade;
    }
}
