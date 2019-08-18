package com.common.district.common.constants;

public class ConstantsEnum {
    public enum DictsTypeEnum{
        TYPE1("费用类型","1");
        DictsTypeEnum(String name,String value){
            this.name=name;
            this.value=value;
        }
        private String name;
        private String value;
        public String getName() {
            return name;
        }
        public String getValue() {
            return value;
        }
    }
    public enum IdCreatePreEnum{
        /**前缀--规定必须是两位**/
        /**
         *
         */
        CONTRACT("CT"),
        /**
         *应收金额RBD_Id
         */
        RECEIVE("YS"),
        /**
         * 项目
         */
        PROJECT("XM"),
        /**
         * 位置字典
         */
        LOCATIONINFO("WZ");
        IdCreatePreEnum(String pre) {
            this.pre = pre;
        }

        private String pre;

        public String getPre() {
            return pre;
        }
    }
}
