package utils;

public class FileUtilsExt<T> extends FileUtils<T> {

    private final String adr;
    private Object[] cache;
    private int indI=-1,indJ=-1;

    public FileUtilsExt(String folder) {
        super(folder);
        adr=folder;
    }

    public Object readFromBlock(int i, int j, int elementIndex) {
        if (indI!=i&&indJ!=j) {
            cache = (Object[]) readObject(i, j);
            indI=i;
            indJ=j;
        }
        Object result=cache[elementIndex];
        return result;
    }
}
