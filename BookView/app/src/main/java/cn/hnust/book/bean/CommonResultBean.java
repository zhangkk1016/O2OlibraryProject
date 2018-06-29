package cn.hnust.book.bean;

/**
 * Created by tjouyang on 2018/4/16.
 *
 * @author tjouyang
 */
public class CommonResultBean { // 通用的后台返回bean，应付那种只有一个result字段的接口
    private String result;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
