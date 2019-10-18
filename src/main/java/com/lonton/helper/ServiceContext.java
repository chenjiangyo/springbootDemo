package com.lonton.helper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.lonton.exception.BusinessException;
import com.lonton.exception.ExceptionMessage;

import lombok.Data;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 数据总线
 * @author 雷磊
 */
@Data
public class ServiceContext {
    private final static String REQ_MESSAGE = "REQ_MESSAGE";

    private final static String REQ_BODY = "REQ_BODY";

    private final static String REQ_PARAM = "REQ_PARAM";

    private final static String REQ_PAGE = "REQ_PAGE";

    private final static String REQ_SORT = "REQ_SORT";

    private final static String TRAN_SUCCESS = "TRAN_SUCCESS";

    private final static String ERROR_MESSAGE = "ERROR_MESSAGE";

    private final static String RSP_HEAD = "RSP_HEAD";

    private final static String RSP_BODY = "RSP_BODY";

    private final static String RSP_ELEMENTS = "elements";

    private final static String PAGE = "page";
    private final static String PAGE_TOTAL = "total";
    private final static String PAGE_NUM = "current";
    private final static String PAGE_TOTAL_PAGE = "totalPages";
    private final static String PAGE_SIZE = "pageSize";


    private JSONObject reqParam = new JSONObject() ;

    private ReqPageInfo reqPage;

    private List<ReqSort> reqSorts;

    private Map<String, Object> rspData = new HashMap<>();

    public ServiceContext(){

    }

    public ServiceContext(Map<String, Object> requestJsonObj){
        this.initRequestDataFromJson(requestJsonObj);
    }

    private void initRequestDataFromJson(Map<String, Object> requestJsonObj){
        final Object reqMsgStr = requestJsonObj.get(REQ_MESSAGE);
        if(reqMsgStr == null){
            throw new BusinessException(ExceptionMessage.WEB_REQ_EXCEPTION);
        }
        JSONObject reqMsgObj = JSON.parseObject((String)reqMsgStr);

        JSONObject reqBodyObj = reqMsgObj.getJSONObject(REQ_BODY);
        if(reqBodyObj == null){
            throw new BusinessException(ExceptionMessage.WEB_REQ_EXCEPTION);
        }

        JSONObject reqParamObj = reqBodyObj.getJSONObject(REQ_PARAM);
        if(reqParamObj != null){
            this.reqParam = reqParamObj;
        }

        JSONObject reqPageObj = reqBodyObj.getJSONObject(REQ_PAGE);
        if(reqPageObj != null){
            this.reqPage = reqPageObj.toJavaObject(ReqPageInfo.class);
        }

        JSONArray reqSortArray = reqBodyObj.getJSONArray(REQ_SORT);
        if(reqSortArray != null){
            this.reqSorts = reqSortArray.toJavaList(ReqSort.class);
        }
    }

    public Object getParam(String key){
        Object value = this.reqParam.get(key);
        if(value == null){
            throw new BusinessException(ExceptionMessage.WEB_REQ_EXCEPTION);
        }
        return value;
    }

    public Object getParam(String paramKey, Class T){
        JSONObject value = this.reqParam.getJSONObject(paramKey);
        if(value==null){
            throw new BusinessException(ExceptionMessage.WEB_REQ_EXCEPTION);
        }
        return value.toJavaObject(T);
    }

    public Object getParam(Class T){
        return this.reqParam.toJavaObject(T);
    }

    public List getListParam(String paramKey, Class T){
        JSONArray value = this.reqParam.getJSONArray(paramKey);
        if(value==null){
            throw new BusinessException(ExceptionMessage.WEB_REQ_EXCEPTION);
        }
        return value.toJavaList(T);
    }

    public void putRspValue(String key, Object value){
        rspData.put(key, value);
    }

    public void putRpsPage(PageInfo pageInfo){
        JSONObject pageInfoJson = new JSONObject();
        pageInfoJson.put(PAGE_TOTAL, pageInfo.getTotal());
        pageInfoJson.put(PAGE_NUM, pageInfo.getPageNum());
        pageInfoJson.put(PAGE_SIZE, pageInfo.getPageSize());
        pageInfoJson.put(PAGE_TOTAL_PAGE, pageInfo.getPages());
        rspData.put(PAGE, pageInfoJson);
        rspData.put(RSP_ELEMENTS, pageInfo.getList());
    }

    public JSONObject getJsonRsp(){
        JSONObject rspJson = new JSONObject();

        JSONObject rspHeadJson = new JSONObject();
        rspHeadJson.put(TRAN_SUCCESS, true);
        rspJson.put(RSP_HEAD, rspHeadJson);

        JSONObject rspBodyJson = new JSONObject(this.rspData);
        rspJson.put(RSP_BODY, rspBodyJson);

        return rspJson;
    }

    public String getSortStr(){
        if(this.reqSorts == null){
            return null;
        }
        return this.reqSorts.stream().map(ReqSort::getSortBy).reduce(null, (a,b) ->  a != null ? a + ", "+ b : b);
    }

    public JSONObject handlerException(Exception e){
        JSONObject rspJson = new JSONObject();

        JSONObject rspHeadJson = new JSONObject();
        rspHeadJson.put(TRAN_SUCCESS, false);
        rspHeadJson.put(ERROR_MESSAGE, e.getMessage());
        rspJson.put(RSP_HEAD, rspHeadJson);

        return rspJson;
    }

    public static void main(String[] args) {
        Map<String, Object> paramObj = new HashMap<>();
        paramObj.put(REQ_MESSAGE,
                "{'REQ_HEAD':{" +
                        "'reqTme':'2019-10-08T3:18:00'," +
                        "'userInfo':{'name':'zzm'}," +
                        "'token':'devToken'" +
                        "}," +
                 "'REQ_BODY':{" +
                        "'REQ_PARAM':{'param1':'value1', 'param2':2, 'param3': 10.56}," +
                        "'REQ_PAGE':{'pageNum':1, 'pageSize':'10'}," +
                        "'REQ_SORT':[{'field':'COL_A', 'order':'desc'},{'field':'COL_B'}]" +
                  "}" +
                "}");

        ServiceContext context = new ServiceContext(paramObj);
    }

}
