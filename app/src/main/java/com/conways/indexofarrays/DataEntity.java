package com.conways.indexofarrays;

import net.sourceforge.pinyin4j.PinyinHelper;

/**
 * Created by Conways on 2017/3/1.
 */

public class DataEntity implements Comparable<DataEntity> {

    private String userName;
    private String nickName;
    private int userId;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getNickName() {
        return nickName;
    }



    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int compareTo(DataEntity o) {
        if (o.getPinYinNickName().compareTo(this.getPinYinNickName()) > 0) {
            return -1;
        } else if (o.getPinYinNickName().compareTo(this.getPinYinNickName()) < 0) {
            return 1;
        }
        return 0;
    }


    public String getPinYinNickName() {
        if (nickName.equals("") || nickName == null) {
            char value = userName.charAt(0);
            if (value > '\u4e00' && value < '\u9fa5') {
                char valueTemp = PinyinHelper.toHanyuPinyinStringArray(value)[0].charAt(0);
                valueTemp -= 32;
                return valueTemp + "";
            }
            if (value >= 97 && value <= 122) {
                value -= 32;
            }
            return value + "";
        }
        char value = nickName.charAt(0);
        if (value > '\u4e00' && value < '\u9fa5') {
            char valueTemp = PinyinHelper.toHanyuPinyinStringArray(value)[0].charAt(0);
            valueTemp -= 32;
            return valueTemp + "";
        }
        if (value >= 97 && value <= 122) {
            value -= 32;
        }
        return value + "";
    }

}