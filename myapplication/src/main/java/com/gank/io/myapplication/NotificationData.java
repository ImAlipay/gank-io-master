package com.gank.io.myapplication;


/**
 * NotificationData:推送实体类
 *
 * @author zpl
 * @date 2018/6/4
 */
public class NotificationData {

    /**
     * 类型
     * "1","trade"
     * "2","system"
     * "3","ad"
     */
    public static final String TYPE_TRADE = "1";
    public static final String TYPE_SYSTEM = "2";
    public static final String TYPE_AD = "3";
    private int Id;
    /**
     * 类型：trade交易 system系统 ad广告
     */
    private String DataType;
    private String TradeNo;
    private String Timestamp;
    private String Content;

    public String getDataType() {
        return DataType;
    }

    public void setDataType(String dataType) {
        DataType = dataType;
    }

    public String getTradeNo() {
        return TradeNo;
    }

    public void setTradeNo(String tradeNo) {
        TradeNo = tradeNo;
    }

    public String getTimestamp() {
        return Timestamp;
    }

    public void setTimestamp(String timestamp) {
        Timestamp = timestamp;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    @Override
    public String toString() {
        return "NotificationData{" +
                "Id=" + Id +
                ", DataType='" + DataType + '\'' +
                ", TradeNo='" + TradeNo + '\'' +
                ", Timestamp='" + Timestamp + '\'' +
                ", Content='" + Content + '\'' +
                '}';
    }
}
