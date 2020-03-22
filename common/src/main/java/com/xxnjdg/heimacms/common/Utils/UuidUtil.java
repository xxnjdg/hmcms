package com.xxnjdg.heimacms.common.Utils;

import java.util.UUID;


public class UuidUtil {

    /**
     * 生成一个32位的UUID
     * @return
     */
    public static String getUUID32(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

    /**
     * 生成指定个数的UUID
     * @param num
     * @return
     */
    public static String[] getUUID(int num){
        if( num <= 0) {
            return null;
        }
        String[] uuidArr = new String[num];
        for (int i = 0; i < uuidArr.length; i++) {
            uuidArr[i] = getUUID32();
        }
        return uuidArr;
    }
}
