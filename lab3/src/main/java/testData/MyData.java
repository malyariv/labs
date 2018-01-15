package testData;


import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

public class MyData implements Serializable{
    private String name;
    private int age;
    private Proffesion job;
    private int[] array= new int[500];
    public static long time=0;


    public MyData() {
        long t=System.currentTimeMillis();
        setName();
        age = 20+(int)(Math.random()*35);
        setJob();
        time+=System.currentTimeMillis()-t;
    }

    private void setName() {
        Random random=new Random();
        int i=random.nextInt(Names.values().length);
        for (Names n:Names.values()){
            if (n.ordinal()==i){
                name=""+n;
                return;
            }
        }
    }

    private void setJob(){
        Random random=new Random();
        int i=random.nextInt(Proffesion.values().length);
        for (Proffesion p:Proffesion.values()){
            if (p.ordinal()==i){
                job=p;
                return;
            }
        }
    }

    @Override
    public String toString() {
        return "MyData{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", job=" + job +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyData)) return false;

        MyData data = (MyData) o;

        if (age != data.age) return false;
        if (!name.equals(data.name)) return false;
        if (job != data.job) return false;
        return Arrays.equals(array, data.array);
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + age;
        result = 31 * result + job.hashCode();
        result = 31 * result + Arrays.hashCode(array);
        return result;
    }
}
