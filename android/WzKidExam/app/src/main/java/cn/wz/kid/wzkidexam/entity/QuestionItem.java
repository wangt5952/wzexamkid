package cn.wz.kid.wzkidexam.entity;

public class QuestionItem {
    private String question;
    private Integer trueAnswer;
    private Integer[] lblAnswers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public Integer getTrueAnswer() {
        return trueAnswer;
    }

    public void setTrueAnswer(Integer trueAnswer) {
        this.trueAnswer = trueAnswer;
    }

    public Integer[] getLblAnswers() {
        return lblAnswers;
    }

    public void setLblAnswers(Integer[] lblAnswers) {
        this.lblAnswers = lblAnswers;
    }
}
