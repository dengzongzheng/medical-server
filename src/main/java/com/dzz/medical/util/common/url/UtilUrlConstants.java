package com.dzz.medical.util.common.url;

/**
 * 公共接口地址信息
 *
 * @author dzz
 * @version 1.0.0
 * @since 2018年02月02 下午2:20
 */
public class UtilUrlConstants {

    /** 生成图像验证码 */
    public static final String GENERATE_IMG_CODE = "/common/generateImgCode";

    /** 生成短信验证码 */
    public static final String GENERATE_SMS_CODE = "/common/sendSmsCode";

    /** 生成邮箱验证码 */
    public static final String GENERATE_EMAIL_CODE = "/common/sendEmailCode";

    /**文件上传路径*/
    public static final String FILE_UPLOAD = "/file/upload";

    /**文件下载路径*/
    public static final String FILE_DOWN = "/file/down";

    /**图片获取路径*/
    public static final String FILE_PIC_DOWN = "/file/pic";


    /**区域信息*/
    public static final String AREA_LIST = "/area/listArea";

    /**区域详细信息*/
    public static final String AREA_DETAIL = "/area/getAreaByCode";

    /**区域下级信息*/
    public static final String AREA_SUB = "/area/subArea";


}