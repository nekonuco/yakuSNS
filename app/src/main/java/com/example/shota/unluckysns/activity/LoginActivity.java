package com.example.shota.unluckysns.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.shota.unluckysns.R;
import com.example.shota.unluckysns.constant.AppConst;
import com.example.shota.unluckysns.util.Validator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    // text設定
    final EditText loginId = (EditText)findViewById(R.id.edit_id);
    final EditText loginPw = (EditText)findViewById(R.id.edit_pass);

    // Button周りの設定
    findViewById(R.id.button_login).setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {

        // 入力チェック
        final String txtId = loginId.getText().toString();
        final String txtPw = loginPw.getText().toString();
        // 未入力の場合、メッセージの表示
        if (Validator.isNullOrBlank(txtId) && Validator.isNullOrBlank(txtPw)) {

        }

        startVolley(txtId, txtPw);
//        rereadVolley(txtId, txtPw);
      }
    });

  }

  private void startVolley(final String userId, final String password) {

    // Queue
    RequestQueue postQueue = Volley.newRequestQueue(this);

    StringRequest strReq = new StringRequest(Request.Method.POST, AppConst.LOGIN_SERVICE_URL,

            // 通信成功
            new Response.Listener<String>() {
              @Override
              public void onResponse(String response) {
                try {
                  // レスポンス文字列をjsonオブジェクトに変換して解析
                  JSONObject jsonObject = new JSONObject(response);
                  JSONArray count = jsonObject.getJSONArray("result");
                  if (count.length() > 0) {
                    for (int i = 0; i < count.length(); i++) {
                      JSONObject data = count.getJSONObject(i);
                      String nameId = data.getString("id");
                      String password = data.getString("password");
                      String password2 = data.getString("password");
                    }
                    Toast.makeText(LoginActivity.this, "JSONObjectの取得に成功しました", Toast.LENGTH_SHORT).show();
                  }


                } catch (JSONException e) {
                  Toast.makeText(LoginActivity.this, "JSONObjectの取得に失敗しました", Toast.LENGTH_SHORT).show();
                }
              }
            },

            // 通信失敗
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this, "通信に失敗しました", Toast.LENGTH_SHORT).show();
              }
            }) {

      // 送信するデータを設定
      @Override
      protected Map<String, String> getParams() {

         Map<String, String> params = new HashMap<>();
         params.put("user_name", userId);
         params.put("password", password);
         return params;
      }
    };

    postQueue.add(strReq);
  }

  private void rereadVolley(final String userId, final String password) {

    // queue
    RequestQueue getQueue = Volley.newRequestQueue(this);
    // paramの設定
    Map<String, String> params = new HashMap<>();
    params.put("name", userId);
    params.put("password", password);

    // JSONObject作成
    JSONObject reqJsonParam = new JSONObject(params);

    // Volleyによる通信開始
    JsonObjectRequest mRequest = new JsonObjectRequest(Request.Method.GET, AppConst.LOGIN_SERVICE_URL, reqJsonParam,

            // 通信成功
            new Response.Listener<JSONObject>() {
              @Override
              public void onResponse(JSONObject response) {

                Toast.makeText(LoginActivity.this, "通信に成功しました", Toast.LENGTH_SHORT).show();
              }
            },

            // 通信失敗
            new Response.ErrorListener() {
              @Override
              public void onErrorResponse(VolleyError error) {

                Toast.makeText(LoginActivity.this, "通信に失敗しました", Toast.LENGTH_SHORT).show();
              }
            }
            );

    getQueue.add(mRequest);
  }

}
