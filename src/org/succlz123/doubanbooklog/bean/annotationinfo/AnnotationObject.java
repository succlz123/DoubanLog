package org.succlz123.doubanbooklog.bean.annotationinfo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by fashi on 2015/4/4.
 */
public class AnnotationObject {

    private List<AnnotationResult> annotationResult;
    private Integer count;
    private Integer start;
    private Integer total;

    public List<AnnotationResult> getAnnotationResult() {
        return annotationResult;
    }

    public void setAnnotationResult(List<AnnotationResult> annotationResult) {
        this.annotationResult = annotationResult;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getTotal() {
        return total;
    }

    public int getItemCount(){
        return annotationResult.size();
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public static AnnotationObject parseJson(JSONObject jsonObject) {

        AnnotationObject annotationObject = new AnnotationObject();

        Integer count = jsonObject.optInt("count");
        Integer start = jsonObject.optInt("start");
        Integer total = jsonObject.optInt("total");

        JSONArray jsonArray = jsonObject.optJSONArray("annotations");
        List<AnnotationResult> annotationResults = new ArrayList<AnnotationResult>();

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject jsonObject1 = jsonArray.optJSONObject(i);
            AnnotationResult annotationResult = AnnotationResult.parseJson(jsonObject1);
            if (annotationResult!=null) {
                annotationResults.add(annotationResult);
            }
        }

        annotationObject.setAnnotationResult(annotationResults);
        annotationObject.setCount(count);
        annotationObject.setStart(start);
        annotationObject.setTotal(total);

        return annotationObject;
    }
}

