package com.xu;

import org.jetbrains.annotations.NotNull;

/**
 * zhenjun.xu
 * 2016/12/30
 */
class Common {

    /**
     *  根据文件路径，生成 Yii::t 所需的分类名称
     * @param filePath 路径
     * @return 分类名称
     */
    static String getYiiTName(String filePath){
        String[] pathArray = filePath.split("/");
        String module = "";
        String controller = "";
        String name = "site";
        int modulePosition = 0;
        /*
         遍历字符串数组，查找module，controller
         */
        for (int i=0;i<pathArray.length;i++){
            if(pathArray[i].contentEquals("modules")){
                module = pathArray[i+1];
                modulePosition = i;
            }
            if(pathArray[i].contentEquals("controllers")){
                controller = pathArray[pathArray.length-1].replace("Controller.php","");
            }
            if(pathArray[i].contentEquals("views")){
                controller = toUpperCaseFirstOne(pathArray[i+1]);
            }
        }
        if(module.length() >0  && controller.length()>0){
            name = module+controller;
        }
        if(module.length()==0 && controller.length()>0){
            name = toLowerCaseFirstOne(controller);
        }
        if(module.length()>0 && controller.length()==0){
            name = pathArray[modulePosition+3];
        }
        if(module.length()==0 && controller.length()==0){
            name = reverse(pathArray)[1];
        }
        return name;
    }

    /**
     * 首字母大写
     * @param name 字符串
     * @return 首字母大写
     */
    private static String toUpperCaseFirstOne(String name) {
        char[] cs=name.toCharArray();
        cs[0]-=32;
        return String.valueOf(cs);
    }
    /**
     * 首字母转小写
     * @param s  字符串
     * @return 首字母大写
     */
    private static String toLowerCaseFirstOne(String s){
        if(Character.isLowerCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toLowerCase(s.charAt(0))).append(s.substring(1)).toString();
    }

    /**
     *
     * 返回一个单元顺序相反的数组
     * 当 arr 为 null 时返回 new String[0] 。
     *
     * @param arr 数组
     * @return 数组
     */
    private static String[] reverse(String[] arr) {
        String[] rarr = new String[0];
        if (arr != null) {
            rarr = new String[arr.length];
            int j = 0;
            for (int i = arr.length - 1; i >= 0; i--) {
                rarr[j++] = arr[i];
            }
        }
        return rarr;
    }

}
