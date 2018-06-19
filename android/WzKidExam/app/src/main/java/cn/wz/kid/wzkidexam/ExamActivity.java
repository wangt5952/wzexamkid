package cn.wz.kid.wzkidexam;

import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.CountDownTimer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;

import cn.wz.kid.wzkidexam.common.WzQuestionLib;
import cn.wz.kid.wzkidexam.entity.QuestionItem;
import cn.wz.kid.wzkidexam.entity.QuestionResultItem;
import cn.wz.kid.wzkidexam.utils.WzUtil;

public class ExamActivity extends AppCompatActivity {

    private TextView txtAnswerTime;
    private TextView txtQuestion;
    private Button btnAns1;
    private Button btnAns2;
    private Button btnAns3;
    private Button btnAns4;
    private TextView txtMessage;
    private Button btnStartExam;
    private Button btnExamResult;
    private Button btnBackToMain;

    private PopupWindow wPopupExamResult;
    private View vPopupExamResult;
    private RecyclerView rvExamResult;
    private ExamResultAdapter examResultAdapter;
    private Button btnPopupClose;

    private Integer usedQuestionType;
    private Integer usedQuestionCount;
    private Integer usedAnswerTimeWithSecond;

    private List<QuestionItem> usedQuestions;
    private List<QuestionResultItem> usedAnswers;
    private int currectQuestionIdx = 0;

    private CountDownTimer cdTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exam);

        // 接收参数
        usedQuestionType = getIntent().getIntExtra("paramQueType", 2);
        usedQuestionCount = getIntent().getIntExtra("paramQueCnt", 30);
        usedAnswerTimeWithSecond = getIntent().getIntExtra("paramAnsTime", 10);

        // 控件初始化
        txtAnswerTime = (TextView) findViewById(R.id.txtTime);
        txtQuestion = (TextView) findViewById(R.id.txtQuestion);

        btnAns1 = (Button) findViewById(R.id.btnAnswer_1);
        btnAns1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currectQuestionIdx < usedQuestionCount) {
                    answer(currectQuestionIdx, usedQuestions.get(currectQuestionIdx).getLblAnswers()[0]);
                    currectQuestionIdx++;
                    nextQuestion();
                }
            }
        });
        btnAns2 = (Button) findViewById(R.id.btnAnswer_2);
        btnAns2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currectQuestionIdx < usedQuestionCount) {
                    answer(currectQuestionIdx, usedQuestions.get(currectQuestionIdx).getLblAnswers()[1]);
                    currectQuestionIdx++;
                    nextQuestion();
                }
            }
        });
        btnAns3 = (Button) findViewById(R.id.btnAnswer_3);
        btnAns3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currectQuestionIdx < usedQuestionCount) {
                    answer(currectQuestionIdx, usedQuestions.get(currectQuestionIdx).getLblAnswers()[2]);
                    currectQuestionIdx++;
                    nextQuestion();
                }
            }
        });
        btnAns4 = (Button) findViewById(R.id.btnAnswer_4);
        btnAns4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (currectQuestionIdx < usedQuestionCount) {
                    answer(currectQuestionIdx, usedQuestions.get(currectQuestionIdx).getLblAnswers()[3]);
                    currectQuestionIdx++;
                    nextQuestion();
                }
            }
        });

        txtMessage = (TextView) findViewById(R.id.txtMessage);

        btnStartExam = (Button) findViewById(R.id.btnStartExam);
        btnStartExam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initQuestions();
                currectQuestionIdx = 0;
                btnStartExam.setEnabled(false);
                btnExamResult.setEnabled(false);
                usedAnswers = new ArrayList<QuestionResultItem>();
                QuestionItem currQuestion = nextQuestion();
                if (null == currQuestion) {
                    return;
                }
            }
        });

        btnExamResult = (Button) findViewById(R.id.btnExamResult);
        btnExamResult.setEnabled(false);
        btnExamResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popupExamResult();
            }
        });

        btnBackToMain = (Button) findViewById(R.id.btnBackToMain);
        btnBackToMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder adb = new AlertDialog.Builder(ExamActivity.this);
                adb.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        finish();
                    }
                });
                adb.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                adb.setTitle("提示");
                adb.setMessage("确定退出考试吗？");
                adb.show();
            }
        });
    }

    /**
     * 初始化考题.
     */
    private void initQuestions() {
        txtAnswerTime.setText("");
        txtQuestion.setText("");
        txtMessage.setText("");
        Random qrd = new Random();
        WzQuestionLib qlib = new WzQuestionLib();
        List<QuestionItem> allQuestions = qlib.getExamQuestions(usedQuestionType);
        if (null == allQuestions || 0 == allQuestions.size()) {
            txtMessage.setText("本套考题无法使用，请点击返回重新选择题库类型");
            return;
        }
        usedQuestions = new ArrayList<QuestionItem>();
        if (usedQuestionCount > allQuestions.size()) {
            usedQuestionCount = allQuestions.size();
        }
        List<Integer> idxLs = new ArrayList<Integer>();
        int idx = 0;
        for (int i = 0; i < usedQuestionCount; i++) {
            while (true) {
                idx = qrd.nextInt(allQuestions.size());
                if (WzUtil.contain(idxLs, idx)) {
                    continue;
                } else {
                    idxLs.add(idx);
                    usedQuestions.add(allQuestions.get(idx));
                    break;
                }
            }
        }
    }

    /**
     * 下一题.
     * @return 下一题
     */
    private QuestionItem nextQuestion() {
        if (null != cdTimer) {
            cdTimer.cancel();
            cdTimer = null;
        }
        if (currectQuestionIdx < usedQuestions.size()) {
            QuestionItem qi = usedQuestions.get(currectQuestionIdx);
            QuestionResultItem qri = new QuestionResultItem();
            qri.setQuestion(qi.getQuestion());
            qri.setTrueAnswer(qi.getTrueAnswer());
            usedAnswers.add(qri);
            txtQuestion.setText(qi.getQuestion());
            btnAns1.setText(String.valueOf(qi.getLblAnswers()[0]));
            btnAns2.setText(String.valueOf(qi.getLblAnswers()[1]));
            btnAns3.setText(String.valueOf(qi.getLblAnswers()[2]));
            btnAns4.setText(String.valueOf(qi.getLblAnswers()[3]));
            txtMessage.setText("考试中");
            cdTimer = new CountDownTimer(usedAnswerTimeWithSecond * 1000, 1000) {
                @Override
                public void onTick(long l) {
                    int lastTime = (int) (l / 1000);
                    txtAnswerTime.setText(String.valueOf(lastTime));
                    if (4 > lastTime) {
                        txtAnswerTime.setTextColor(Color.RED);
                    }
                }

                @Override
                public void onFinish() {
                    if (null != cdTimer) {
                        cdTimer.cancel();
                        cdTimer = null;
                    }
                    currectQuestionIdx++;
                    nextQuestion();
                }
            }.start();
            return qi;
        } else {
            txtMessage.setText("考试结束");
            if (null != cdTimer) {
                cdTimer.cancel();
                cdTimer = null;
            }
            btnExamResult.setEnabled(true);
            btnStartExam.setEnabled(true);
            btnAns1.setEnabled(false);
            btnAns2.setEnabled(false);
            btnAns3.setEnabled(false);
            btnAns4.setEnabled(false);
            return null;
        }
    }

    /**
     * 回答.
     * @param idx 问题
     * @param uAns 用户回答
     */
    private void answer(int idx, Integer uAns) {
        usedAnswers.get(idx).setUserAnswer(uAns);
    }

    private void popupExamResult() {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wmManager=(WindowManager) ExamActivity.this.getSystemService(Context.WINDOW_SERVICE);
        wmManager.getDefaultDisplay().getMetrics(dm);
        vPopupExamResult = View.inflate(ExamActivity.this, R.layout.activity_exam_result_popup, null);
        wPopupExamResult = new PopupWindow(vPopupExamResult, dm.widthPixels, dm.heightPixels, true);
        wPopupExamResult.setOutsideTouchable(true);
        wPopupExamResult.setTouchable(true);
        ColorDrawable cdw = new ColorDrawable(0xb0000000);
        wPopupExamResult.setBackgroundDrawable(cdw);
        rvExamResult = (RecyclerView) vPopupExamResult.findViewById(R.id.rvExamResult);
        rvExamResult.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
                outRect.left = 0;
                outRect.right = 0;
                outRect.bottom = (int) (5 * ExamActivity.this.getResources().getDisplayMetrics().density + 0.5f);
            }
        });
        rvExamResult.setHasFixedSize(true);
        rvExamResult.setLayoutManager(new LinearLayoutManager(this));
        examResultAdapter = new ExamResultAdapter(null);
        examResultAdapter.openLoadAnimation();
        rvExamResult.setAdapter(examResultAdapter);
        examResultAdapter.setNewData(usedAnswers);
        btnPopupClose = (Button) vPopupExamResult.findViewById(R.id.btnPopupClose);
        btnPopupClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wPopupExamResult.dismiss();
            }
        });
//        wPopupExamResult.update();
        wPopupExamResult.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {

            }
        });
        wPopupExamResult.showAtLocation(ExamActivity.this.findViewById(R.id.btnExamResult), Gravity.TOP | Gravity.START, 0, 0);
    }
}
