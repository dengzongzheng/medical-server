package com.dzz.medical.common.enums;

import com.dzz.medical.util.common.bean.ConstantBean;
import com.google.common.collect.Lists;
import java.util.List;
import java.util.Objects;


/**
 * 微信相关枚举类
 * @author dzz
 * @version 1.0.0
 * @since 2018年03月22 下午6:12
 */
public interface WxManageEnums {

    /** 消息 */
    enum MessageEvent {

        COMPLAINT("V10001_I_WANT_COMPLAINT", "我要投诉"),
        GUIDE("V10001_WORK_GUIDE","办事指南")
        ;

        /** 商品编号 */
        private String code;

        /** 商品名称 */
        private String name;

        MessageEvent(String code, String name) {
            this.code = code;
            this.name = name;
        }

        public String getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getNameByCode(String code) {
            for (MessageEvent info : MessageEvent.values()) {
                if (Objects.equals(code, info.getCode())) {
                    return info.getName();
                }
            }
            return null;
        }
    }

    enum MedicalStatusEnums{

        NOMOR(1, "正常"),
        OFFLINE(2,"已下线"),
        CONFIG(0,"配置中"),
        ;

        /** code */
        private Integer code;

        /** 名称 */
        private String name;

        MedicalStatusEnums(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getNameByCode(Integer code) {
            for (MedicalStatusEnums info : MedicalStatusEnums.values()) {
                if (Objects.equals(code, info.getCode())) {
                    return info.getName();
                }
            }
            return null;
        }

        /**
         * 获取当前枚举的属性列表
         *
         * @return 属性列表
         */
        public static List<ConstantBean> getElementList() {
            List<ConstantBean> list = Lists.newArrayList();
            for (MedicalStatusEnums property : MedicalStatusEnums.values()) {
                ConstantBean constantBean = new ConstantBean();
                constantBean.setCode(property.code);
                constantBean.setName(property.name);
                list.add(constantBean);
            }
            return list;
        }
    }

    enum ToppingEnums{

        YES(1, "是"),
        NO(0,"否");

        /** code */
        private Integer code;

        /** 名称 */
        private String name;

        ToppingEnums(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getNameByCode(Integer code) {
            for (ToppingEnums info : ToppingEnums.values()) {
                if (Objects.equals(code, info.getCode())) {
                    return info.getName();
                }
            }
            return null;
        }

        /**
         * 获取当前枚举的属性列表
         *
         * @return 属性列表
         */
        public static List<ConstantBean> getElementList() {
            List<ConstantBean> list = Lists.newArrayList();
            for (ToppingEnums property : ToppingEnums.values()) {
                ConstantBean constantBean = new ConstantBean();
                constantBean.setName(property.name);
                constantBean.setCode(property.code);
                list.add(constantBean);
            }
            return list;
        }
    }


    enum MessageTypeEnums{

        TEXT(1, "text"),
        EVENT(2,"event");

        /** code */
        private Integer code;

        /** 名称 */
        private String name;

        MessageTypeEnums(Integer code, String name) {
            this.code = code;
            this.name = name;
        }

        public Integer getCode() {
            return code;
        }

        public String getName() {
            return name;
        }

        public static String getNameByCode(Integer code) {
            for (MessageTypeEnums info : MessageTypeEnums.values()) {
                if (Objects.equals(code, info.getCode())) {
                    return info.getName();
                }
            }
            return null;
        }

        /**
         * 获取当前枚举的属性列表
         *
         * @return 属性列表
         */
        public static List<ConstantBean> getElementList() {
            List<ConstantBean> list = Lists.newArrayList();
            for (MessageTypeEnums property : MessageTypeEnums.values()) {
                ConstantBean constantBean = new ConstantBean();
                constantBean.setName(property.name);
                constantBean.setCode(property.code);
                list.add(constantBean);
            }
            return list;
        }
    }
}
