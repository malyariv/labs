package businesslogic;

import dataclasses.*;

public class Operations {
    private StringBuilder sb=new StringBuilder("We ");

    public void execute(Condition condition) {
        if (condition.getTemperature()!=null&&condition.getTemperature()>25) {
            sb.append("heat the components up to ");
            sb.append(condition.getTemperature());
            sb.append(" C, ");
        }
        if (condition.getTemperature()!=null&&condition.getTemperature()<20) {
            sb.append("cool the components down to ");
            sb.append(condition.getTemperature());
            sb.append(" C, ");
        }
        if (condition.needElectrolysis()) {
            sb.append("use electrolysis, ");
        }
        if (condition.getCatalyst()!=null) {
            sb.append("add ");
            sb.append(condition.getCatalyst());
            sb.append(" as a catalyst, ");
        }
        if (condition.needExhaust()) {
            sb.append("use exhaust to avoid poisoning, ");
        }
        int length =sb.toString().trim().length();
        if (length>2) {
            sb=sb.deleteCharAt(length);
            sb=sb.deleteCharAt(length-1);
            sb.append(".");
            System.out.println(sb.toString().trim());
        }
    }


}