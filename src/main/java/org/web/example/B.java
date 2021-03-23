package org.web.example;

public class B {

    public static void main(String[] args) {
        System.out.println(CachePrefixEvent.CachePrefixEnum.WEB.getEvent());
        System.out.println(CachePrefixEvent.CachePrefixEnum.WEB.getDescription());
        System.out.println(CachePrefixEvent.CachePrefixEnum.APP.getEvent());
        System.out.println(CachePrefixEvent.CachePrefixEnum.APP.getDescription());
        String name = "";
        System.out.println(CachePrefixEvent.CachePrefixEnum.CHANGE.getEvent()+name);
    }
}
