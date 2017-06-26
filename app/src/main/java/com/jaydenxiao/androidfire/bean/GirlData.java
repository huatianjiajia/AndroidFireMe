package com.jaydenxiao.androidfire.bean;

import java.util.List;

/**
 * 项目名称： AndroidFireMe
 * 包名：com.jaydenxiao.androidfire.bean
 * 类描述： describe
 * 创建人： wxw https://github.com/huatianjiajia
 * 创建时间： 2017/6/23 8:55
 * 修改人：
 * 修改时间：
 * 修改备注：
 */
public class GirlData {
    private boolean isError;
    private List<PhotoGirl> results;

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public List<PhotoGirl> getResults() {
        return results;
    }

    public void setResults(List<PhotoGirl> results) {
        this.results = results;
    }
}
