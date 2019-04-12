package com.example.shota.unluckysns.util;

import com.example.shota.unluckysns.constant.AppConst;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

public class AsyncTask extends android.os.AsyncTask<String, String, String> {

  /**
   * バックグラウンド処理
   * @param params
   * @return
   */
  @Override
  protected String doInBackground(String... params) {

    // パラメーター設定
    String url = params[0];
    final RequestParams reqParams = new RequestParams();
    reqParams.put("userId", "test2");
    reqParams.put("email", "test2@mail");
    reqParams.put("password", "test2pass");
    reqParams.put("age", 25);
    reqParams.put("yaku", "厄");
    reqParams.put("nickName", "test2");
    reqParams.put("image", "testImage");
    AsyncHttpClient client = new AsyncHttpClient();


    return "";
  }

  /**
   * UIスレッドに反映
   * @param str
   */
  @Override
  protected void onPostExecute(String str) {
  }

}
