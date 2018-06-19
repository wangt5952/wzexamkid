package cn.wz.kid.wzkidexam;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import cn.wz.kid.wzkidexam.common.WzConstants;

public class MainActivity extends AppCompatActivity {

    /** 题库. */
    private Integer questionType = 2;
    /** 考题量. */
    private Integer questionCount = 30;
    /** 每题时间（秒）. */
    private Integer answerTimeBySecond = 10;
    /** 题库选择框. */
    private Spinner spnQueTyp;
    /** 题量选择框. */
    private Spinner spnQueCnt;
    /** 每题回答时间选择框. */
    private Spinner spnAnsTime;
    /** 开考按钮. */
    private Button btnStartExam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 题库选择
        spnQueTyp = (Spinner) findViewById(R.id.spnQuestionType);
        ArrayAdapter<String> adapterQueTyp = new ArrayAdapter<String>(this, R.layout.wz_spinner_text_item, initQuestionType());
        spnQueTyp.setAdapter(adapterQueTyp);
        spnQueTyp.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (null == WzConstants.QUESTION_TYPE_MAP.get(i)
                        || "".equals(WzConstants.QUESTION_TYPE_MAP.get(i))) {
                    questionType = null;
                } else {
                    questionType = i;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                questionType = null;
            }
        });
        spnQueTyp.setSelection(2);
        // 考题数量选择
        spnQueCnt = (Spinner) findViewById(R.id.spnQuestionCnt);
        ArrayAdapter<Integer> adapterQueCnt = new ArrayAdapter<Integer>(this, R.layout.wz_spinner_text_item, initQuestionCnt());
        spnQueCnt.setAdapter(adapterQueCnt);
        spnQueCnt.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                questionCount = (Integer) spnQueCnt.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                questionCount = null;
            }
        });
        spnQueCnt.setSelection(1);
        // 每道考题答题时间
        spnAnsTime = (Spinner) findViewById(R.id.spnAnswerTimeByQuestion);
        ArrayAdapter<Integer> adapterAnsTime = new ArrayAdapter<Integer>(this, R.layout.wz_spinner_text_item, initAnswerTimeEveryQuestion());
        spnAnsTime.setAdapter(adapterAnsTime);
        spnAnsTime.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                answerTimeBySecond = (Integer) spnAnsTime.getItemAtPosition(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                answerTimeBySecond = null;
            }
        });
        spnAnsTime.setSelection(2);
        // 开始
        btnStartExam = (Button) findViewById(R.id.btnPrepareExam);
        btnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (null == questionType || null == questionCount || null == answerTimeBySecond) {
                    Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).show();
                } else {
                    Intent examIntent = new Intent(MainActivity.this, ExamActivity.class);
                    examIntent.putExtra("paramQueType", questionType);
                    examIntent.putExtra("paramQueCnt", questionCount);
                    examIntent.putExtra("paramAnsTime", answerTimeBySecond);
                    startActivity(examIntent);
                }
            }
        });
    }

    /**
     * 获得题库列表.
     * @return 题库列表
     */
    private List<String> initQuestionType() {
        List<String> qtLs = new ArrayList<String>();
        qtLs.add("十以内加法");
        qtLs.add("十以内减法");
        qtLs.add("十以内加减法");
        qtLs.add("二十以内加法");
        qtLs.add("二十以内减法");
        qtLs.add("二十以内加减法");
        qtLs.add("乘法口诀");
        qtLs.add("除法口诀");
        return qtLs;
    }

    /**
     * 获得考题数量列表.
     * @return 考题数量列表
     */
    private List<Integer> initQuestionCnt() {
        List<Integer> qcLs = new ArrayList<Integer>();
        qcLs.add(20);
        qcLs.add(30);
        qcLs.add(50);
        return qcLs;
    }

    /**
     * 获得单题回答时间列表.
     * @return 单题回答时间列表
     */
    private List<Integer> initAnswerTimeEveryQuestion() {
        List<Integer> atLs = new ArrayList<Integer>();
        atLs.add(3);
        atLs.add(5);
        atLs.add(10);
        atLs.add(15);
        atLs.add(20);
        return atLs;
    }
}
