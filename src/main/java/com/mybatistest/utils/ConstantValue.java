package com.mybatistest.utils;

/**
 * Created by zhao on 2019/4/16.
 */
public final class ConstantValue {
    public static final Integer DATA_ZERO = 0;
    public static final Integer DATA_ONE = 1;
    public static final Integer DATA_TWO = 2;
    public static final Integer DATA_THREE = 3;
    public static final Integer DATA_FOUR = 4;
    public static final Integer DATA_FIVE = 5;
    public static final Integer DATA_SIX = 6;
    public static final Integer DATA_SEVEN = 7;
    public static final Integer DATA_EIGHT = 8;
    public static final Integer DATA_NINE = 9;
    public static final Integer DATA_TEN = 10;

    /**
     * 推送类型 1小程序 2文本卡片
     */
    public static final Integer MINI_TYPE = 1;
    public static final Integer APP_TYPE = 2;

    public static final String NOT_HUAXIA_BANK_CLIENT = "该活动仅限指定客户参与";

    /**
     * 手机加解密密文前缀 $ty$
     */
    public static final String ENCRYPT_PREFIX = "$ty$";

    /**
     *批量加好友 上传方式
     */
    public static final class BatchAddClientUploadType{
        //excel方式
        public static final Integer EXCEL_TYPE = 0;
        //加密文本方式
        public static final Integer ENCRYPT_TYPE = 1;
    }

    /**
     *批量加好友 状态
     */
    public static final class BatchAddClientState{
        //未添加
        public static final Integer NOT_ADDED = 0;
        //已添加
        public static final Integer ADDED = 1;
    }

    /**
     *批量加好友 状态
     */
    public static final class UploadBatchAddClient{
        //表头的行数
        public static final Integer HEAD_ROW = 2;

        public static final String FAIL_FILE_SHEET_NAME = "失败数据";

        public static final String EXCEL_FILE_SHEET_NAME = "sheet1";

        public static final String FAIL_MSG = "表格中核心错误消息%s条，请下载查看";
    }

    public static final class UploadActivityPrize{
        //表头的行数
        public static final Integer HEAD_ROW = 1;
    }

    public static final String STAFF_NAME = "#客户经理名称#";

}
