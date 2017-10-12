package cn.gridlife.bzblibrary.d_voice_to_text;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aispeech.AIError;
import com.aispeech.AIResult;
import com.aispeech.common.AIConstant;
import com.aispeech.common.JSONResultParser;
import com.aispeech.common.Util;
import com.aispeech.export.engines.AICloudASREngine;
import com.aispeech.export.listeners.AIASRListener;

import cn.gridlife.bzblibrary.R;

/**
 * Created by BZB on 2017/10/10.
 */

public class VoiceToTextActivity extends Activity {

    private EditText editText;
    private ImageView ibShow;
    private ImageView ibhide;
    private TextView resultText;
    private ImageView ivStart;
    AICloudASREngine mEngine;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.d_voice_to_text_activity);
        editText = (EditText) findViewById(R.id.et_input);
        resultText = (TextView) findViewById(R.id.tv_state);
        ivStart = (ImageView) findViewById(R.id.iv_start_translate);
        ivStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mEngine = AICloudASREngine.createInstance();
                mEngine.setVadResource("aicar_vad_v0.8.bin");
                mEngine.setDeviceId(Util.getIMEI(getApplicationContext()));
                mEngine.setHttpTransferTimeout(10);
                mEngine.init(getApplicationContext(), new AICloudASRListenerImpl(), "15066096518596cc", "b3eddbc28fb0086e0412543e2f16f365");
                mEngine.setNoSpeechTimeOut(0);

            }
        });
        editText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                showInputMethod(getApplicationContext(), view);
            }
        });
        ibShow = (ImageView) findViewById(R.id.ib_show);
        ibhide = (ImageView) findViewById(R.id.ib_hide_keybord);
        ibShow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showInputMethod(getApplicationContext(), editText);
            }
        });
        ibhide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HideKeyboard(editText);
            }
        });
    }

    /**
     * 显示键盘
     *
     * @param context
     * @param view
     */
    public static void showInputMethod(Context context, View view) {
        InputMethodManager im = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        im.showSoftInput(view, 0);
    }

    //隐藏虚拟键盘
    public static void HideKeyboard(View v) {
        InputMethodManager imm = (InputMethodManager) v.getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive()) {
            imm.hideSoftInputFromWindow(v.getApplicationWindowToken(), 0);
        }
    }

    private class AICloudASRListenerImpl implements AIASRListener {
        TextView resultText=findViewById(R.id.tv_state);
        @Override
        public void onInit(int status) {
            Log.i(Tag, "Init result " + status);
            if (status == AIConstant.OPT_SUCCESS) {
                resultText.setText("初始化成功!");
                mEngine.start();
            } else {
                resultText.setText("初始化失败!code:" + status);
            }
        }

        final String Tag = "VoiceToTextActivity";

        @Override
        public void onError(AIError aiError) {

            Log.e(Tag, "error:" + aiError.toString());
            resultText.setText(aiError.toString());
        }

        @Override
        public void onResults(AIResult aiResult) {
            if (aiResult.isLast()) {
                if (aiResult.getResultType() == AIConstant.AIENGINE_MESSAGE_TYPE_JSON) {
                    Log.i(Tag, "result JSON = " + aiResult.getResultObject().toString());
// 可以使⽤JSONResultParser来解析识别结果
// 结果按概率由⼤到⼩排序
                    JSONResultParser parser = new JSONResultParser(aiResult.getResultObject()
                            .toString());
                    resultText.append("识别结果为 : " + parser.getRec());
                }
            }
        }

        @Override
        public void onRmsChanged(float v) {
            Toast.makeText(getApplicationContext(), v + "", Toast.LENGTH_LONG).show();
        }

        @Override
        public void onReadyForSpeech() {
            resultText.setText("请说话");
        }

        @Override
        public void onBeginningOfSpeech() {
            resultText.setText("检测到说话");
        }

        @Override
        public void onEndOfSpeech() {
            resultText.append("检测到语⾳停⽌，开始识别...\n");
        }

        @Override
        public void onRecorderReleased() {
            resultText.append("检测到录⾳机停⽌\n");
        }

        @Override
        public void onBufferReceived(byte[] bytes) {

        }

        @Override
        public void onNotOneShot() {

        }
    }
}