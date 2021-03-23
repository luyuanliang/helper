package org.web.example;

public class CachePrefixEvent {

    public static String CACHE_PREFIX = "HSBROKER_UF3.0_CRT_";

    enum CachePrefixEnum {

        WEB(CACHE_PREFIX+"WEB_","first web"),
        APP(CACHE_PREFIX+"APP_","first App"),
        ONE(CACHE_PREFIX+"ONE_","ffafd App"),
        CHANGE(CACHE_PREFIX+"CHANGE_","ffafd App");

        CachePrefixEnum(String event, String description){
            this.event = event;
            this.description = description;
        }

        // 缓存事件描述
        private String description;

        // 缓存事件
        private String event;

        public String getEvent() {
            return event;
        }

        public void setEvent(String event) {
            this.event = event;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }

}
