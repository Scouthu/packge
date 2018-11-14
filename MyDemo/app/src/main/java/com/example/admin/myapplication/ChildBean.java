package com.example.admin.myapplication;

public class ChildBean {

    //这是ceckbox底下的选项
    private String ans;
    //这是判断是不是勾选了
    private boolean isChecked;

    public String getAns() {
        return ans;
    }

    public void setAns(String ans) {
        this.ans = ans;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
