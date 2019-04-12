package com.example.shota.unluckysns.util;

import android.net.sip.SipSession;
import android.util.Log;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;

import org.json.JSONObject;

import java.util.Map;

/**
 * サーバーリクエストクラス
 */
public class VolleyRequest extends Request<JSONObject> {


  private final Listener<JSONObject> mListener;
  private Map<String, String> mParams;

  public VolleyRequest(int method, String url, Listener<JSONObject> listener,
                       ErrorListener errorListener) {
    super(method, url, errorListener);
    mListener = listener;
  }

  /**
   * パラメータのsetter
   * @param map パラメータマップ
   */
  public void setParams(Map<String, String> map) {
    mParams = map;
  }

  /**
   * パラメータのgetter
   * @return
   */
  @Override
  protected Map<String, String> getParams() {
    return mParams;
  }

  /**
   * データの受け取り
   * @param networkResponse
   * @return
   */
  @Override
  protected Response<JSONObject> parseNetworkResponse(NetworkResponse networkResponse) {

    // responseインスタンス生成
    String resp = new String(networkResponse.data);
    // JSONObject型response
    JSONObject resultJson;
    try {
      resultJson = new JSONObject(resp);
    } catch (Exception e) {
      Log.e("Error", e.getMessage());
      return null;
    }
    return Response.success(resultJson, getCacheEntry());
  }

  @Override
  protected void deliverResponse(JSONObject response) {
    mListener.onResponse(response);
  }

}
