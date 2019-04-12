package com.example.shota.unluckysns.util;

public class Validator {

  /**
   * null判定
   * stringの場合、空文字判定
   * @param obj
   * @return
   */
  public static boolean isNullOrBlank(Object obj) {
    if (obj == null){
      return true;
    }
    if(obj instanceof String){
      String str = obj.toString();
      return(str == "");
    }
    return false;
  }


}
