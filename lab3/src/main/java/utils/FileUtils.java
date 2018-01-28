package utils;
/**
 * The class {@code FileUtils} implements methods to work with a file system
 * and to read/write objects on disk using serialization.
 */

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileUtils<T> {

    private final String adr;
    private Logger loger;

    public FileUtils(String folder){
        adr=folder;
        File f=new File(adr);
        if (!f.exists()) {
            f.mkdir();
        }
    }

    public Object readObject(int fileIndex, int elementIndex) {
        String filename=adr+fileIndex+".ser";
        String indexname=adr+fileIndex+".txt";
        byte[] buffer=null;
        try(RandomAccessFile raf=new RandomAccessFile(filename,"r")){
            int[] offsetLength=getOffsetLength(indexname, elementIndex);
            if (offsetLength==null){
                return null;
            }
            raf.seek(offsetLength[0]);
            buffer=new byte[offsetLength[1]];
            raf.read(buffer);
        }catch (IOException e){
            loger.log(Level.SEVERE,"Exception ",e);
        }

        return getObject(buffer);
    }

    public Object getObject(byte[] bytes){
        Object t=null;
        try(ObjectInputStream oos=new ObjectInputStream(new ByteArrayInputStream(bytes))){
            t= oos.readObject();
        }catch (IOException|ClassNotFoundException e){
            loger.log(Level.SEVERE,"Exception ",e);
        }
        return t;
    }

    private int[] getOffsetLength(String filename, int elementIndex){
        int[] offsetLength=new int[2];
        try (RandomAccessFile raf=new RandomAccessFile(filename, "r")){
            if (elementIndex>1) {
                offsetLength[0]=4*(elementIndex-2);
                if (new File(filename).length()<=offsetLength[0]+4) {
                    return null;
                }
                raf.seek(offsetLength[0]);
                offsetLength[0]=raf.readInt();
            }
            offsetLength[1]=(raf.readInt()-offsetLength[0]);
        }catch (IOException e){
            loger.log(Level.SEVERE,"Exception ",e);
        }
        return offsetLength;
    }

    public void write(T data, int writtenFilesIndex){
        String filename=adr+writtenFilesIndex+".ser";
        String indexname=adr+writtenFilesIndex+".txt";
        write(data, filename,indexname);
    }

    public void write(Object data, String filename, String indexname){
        int length=(int)new File(filename).length();
        try (DataOutputStream dis=new DataOutputStream(new FileOutputStream(filename, true));
             ObjectOutputStream oos=new ObjectOutputStream(dis);
             DataOutputStream indexStream=new DataOutputStream(new FileOutputStream(indexname,true))){
            oos.writeObject(data);
            length+=dis.size();
            indexStream.writeInt(length);
        }catch (IOException e){
            loger.log(Level.SEVERE,"Exception ",e);
             }
    }



    public void write(Object obj, String filename){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(filename))){
            oos.writeObject(obj);
        }catch (IOException e){
            loger.log(Level.SEVERE,"Exception ",e);
        }
    }

    public void writeBlock(T[] data, int writtenFilesIndex){
        String filename=adr+writtenFilesIndex+".ser";
        String indexname=adr+writtenFilesIndex+".txt";
        write(data, filename,indexname);
    }

    public int getFileSize(T t){
        String filename=adr+"test.txt";
        write(t,filename);
        File file=new File(filename);
        int size=(int) file.length();
        file.delete();
        return size;
    }

    public void clearDirectory(){
        File file=new File(adr);
        for(File f:file.listFiles()) f.delete();
    }

    public void fileShift(int fileIndex, int max){
        for (int i=fileIndex;i<max;i++){
            String filename=adr+i+".ser";
            String indexFilename=adr+i+".txt";
            String filename2=adr+(i+1)+".ser";
            String indexFilename2=adr+(i+1)+".txt";
            new File(filename).delete();
            new File(indexFilename).delete();
            new File(filename2).renameTo(new File(filename));
            new File(indexFilename2).renameTo(new File(indexFilename));
        }
        new File(adr+max+".ser").delete();
        new File(adr+max+".txt").delete();
    }

    public void fileMerge(int fileIndex, int[] indices1, int[] indices2){
        String buffer=adr+"bufferMerge"+".ser";
        String buffertxt=adr+"bufferMerge"+".txt";
        for (int ind:indices1){
            T t= (T) readObject(fileIndex,ind+1);
            write(t, buffer, buffertxt);
        }
        for (int ind:indices2){
            T t= (T) readObject(fileIndex+1,ind+1);
            write(t, buffer, buffertxt);
        }
        new File(adr+fileIndex+".ser").delete();
        new File(adr+fileIndex+".txt").delete();
        new File(buffer).renameTo(new File(adr+fileIndex+".ser"));
        new File(buffertxt).renameTo(new File(adr+fileIndex+".txt"));
    }

    public ConfigClass readConfiguration(String filename){
        ConfigClass h=null;
        try (ObjectInputStream ois=new ObjectInputStream(new FileInputStream(adr+filename+"Config.txt"))){
            h=(ConfigClass)ois.readObject();
        }catch (IOException|ClassNotFoundException e){
            loger.log(Level.SEVERE,"Exception ",e);}
        return h;
    }

    public void saveConfiguration(ConfigClass conf, String name){
        try (ObjectOutputStream oos=new ObjectOutputStream(new FileOutputStream(adr+name+"Config.txt"))){
            oos.writeObject(conf);
        }catch (IOException e){loger.log(Level.SEVERE,"Exception ",e);}
    }

    public void setLoger(Logger loger){
        this.loger=loger;
    }

}
