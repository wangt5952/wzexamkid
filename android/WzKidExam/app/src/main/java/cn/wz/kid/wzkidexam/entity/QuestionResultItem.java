package cn.wz.kid.wzkidexam.entity;

public class QuestionResultItem {
    private String question;
    private Integer trueAnswer;
    private Integer userAnswer;

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

    public Integer getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(Integer userAnswer) {
        this.userAnswer = userAnswer;
    }

}
