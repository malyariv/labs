package data;

public enum Marker{
    SALT(4), WATER(0), SOLUTION(1), ACID(2),ALKALI(3);
    int priority;
    Marker(int i){
        priority=i;
    }

    public int getPriority() {
        return priority;
    }
    public void setPriority(int p){
        priority=p;
    }
}
