package com.automation;



import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * json 数据格式的响应
 * @author hj
 *
 */

public class Result {
    
    /**
     * 类型
     */
    public enum Type {

        /** 成功 */
        success,

        /** 警告 */
        warn,

        /** 错误 */
        error
    }
    
    
    Type result;
    
    /** 结果码*/
    Long code;
    
    /** 原因描述 */
    String reason;    
    

    
    /** 携带的其他数据 */
    Object data;
        
    private Result(Type result, Long code,String reason) {
        this.result = result;
        this.code = code;
        this.reason = reason;
    }
        
    private Result(Type result,Long code, String reason, Object data) {
        this.result = result;
        this.code = code;
        this.reason = reason;
        this.data = data;
    }
    
    
    public static  Result error(Long code,String reason){
        return new Result(Type.error,code,reason);
    }
    
    public static  Result error(Long code, String reason, Object data){
        return new Result(Type.error,code,reason,data);
    }
        
    
    public static  Result warn(Long code,String msg){
        return new Result(Type.warn,code ,msg);
    }
        
    public static  Result warn(Long code,String msg,Object data){
        return new Result(Type.warn,code,msg,data);
    }    
    
    public static  Result success(){
        return new Result(Type.success,0L,"success");
    }
        
    public static  Result success(Object data){
        return new Result(Type.success,0L,"success",data);
    }

    
    @JsonProperty
    public Type getResult() {
        return result;
    }

    @JsonProperty
    public Long getCode() {
        return code;
    }
    
    @JsonProperty
    public String getReason() {
        return reason;
    }
    
    @JsonProperty
    public Object getData() {
        return data;
    }    
    
    
}
