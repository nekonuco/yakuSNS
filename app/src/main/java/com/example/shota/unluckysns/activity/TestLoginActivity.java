package com.example.shota.unluckysns.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.RequestQueue;
import com.android.volley.Request;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.example.shota.unluckysns.R;
import com.example.shota.unluckysns.bean.UserInfoBean;
import com.example.shota.unluckysns.constant.AppConst;
import com.example.shota.unluckysns.util.Validator;
import com.example.shota.unluckysns.util.VolleyRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static com.android.volley.toolbox.Volley.newRequestQueue;

/**
 * ログイン処理
 * こちらの処理を使用
 *
 * @author shota
 *
 */
public class TestLoginActivity extends AppCompatActivity {

  private static RequestQueue mQueue;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_login);

    mQueue = newRequestQueue(getApplicationContext());
    // text設定
    final EditText loginId = findViewById(R.id.edit_id);
    final EditText loginPw = findViewById(R.id.edit_pass);

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

        // server接続
        testStringPost(txtId, txtPw);
      }
    });
  }

  /**
   * idとpasswordをpost
   * @param userId ユーザーID
   * @param password パスワード
   */
  private void testStringPost(final String userId, final String password) {

    // 送信するパラメータを設定
    Map<String, String> params = new HashMap<>();
    params.put("user_name", userId);
    params.put("password", password);

    // リクエスト初期設定
    VolleyRequest volleyReq =
            new VolleyRequest(Request.Method.POST, AppConst.LOGIN_SERVICE_URL,
                    volleyListener, volleyErrorListener);

    // リクエストのタイムアウト
    volleyReq.setRetryPolicy(new DefaultRetryPolicy(
            10000,
            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    volleyReq.setParams(params);
    mQueue.add(volleyReq);

  }

  /**
   * ログイン情報の取得
   */
  private Listener<JSONObject> volleyListener = new Listener<JSONObject>() {
    @Override
    public void onResponse(JSONObject response) {
      try {
        JSONArray count = response.getJSONArray("result");
        if (count.length() == 1) {
          for (int i = 0; i < count.length(); i++) {
            UserInfoBean uiBean = new UserInfoBean();
            JSONObject data = count.getJSONObject(i);
            String nameId = data.getString("id");
            String password = data.getString("password");
            uiBean.setUserId(data.getString("id"));
            uiBean.setPassword(data.getString("password"));
            // TODO:main画面intent

          }
        } else {
          // TODO:エラー処理

        }
      } catch (Exception e) {
        Log.e("Error", e.getMessage());
      }
      Toast.makeText(TestLoginActivity.this, "JSONObjectの取得に成功しました", Toast.LENGTH_SHORT).show();
    }
  };

  // 通信エラー
  private ErrorListener volleyErrorListener = new ErrorListener() {
    @Override
    public void onErrorResponse(VolleyError error) {
      Toast.makeText(TestLoginActivity.this, "JSONObjectの取得に失敗しました", Toast.LENGTH_SHORT).show();
    }
  };

}
