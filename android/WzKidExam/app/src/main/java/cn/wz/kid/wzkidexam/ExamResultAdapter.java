package cn.wz.kid.wzkidexam;

import android.graphics.Color;
import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

import cn.wz.kid.wzkidexam.entity.QuestionResultItem;

public class ExamResultAdapter extends BaseQuickAdapter<QuestionResultItem, BaseViewHolder> {

    public ExamResultAdapter(@Nullable List<QuestionResultItem> data) {
        super(R.layout.wz_recyclerview_item, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, QuestionResultItem item) {
        if (null == item) {
            return;
        }
        if (null == item.getUserAnswer() || item.getTrueAnswer().intValue() != item.getUserAnswer().intValue()) {
            helper.setText(R.id.tvRvItem, item.getQuestion() + " " + item.getUserAnswer() + " (" + item.getTrueAnswer() + ")").setTextColor(R.id.tvRvItem, Color.RED);
        } else {
            helper.setText(R.id.tvRvItem, item.getQuestion() + " " + item.getUserAnswer() + " (" + item.getTrueAnswer() + ")").setTextColor(R.id.tvRvItem, Color.BLACK);
        }
    }
}
