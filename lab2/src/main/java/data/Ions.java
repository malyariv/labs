package data;

public enum Ions { H(1,1), OH(-1,2), CO3(-2,3), Cl(-1,1), NO3(-1,3),
                    Na(1,1), K(1,1), Cu(2,2);
    private int charge;
    private int nameLength;
    Ions(int c, int l){
        nameLength=l;
        charge=c;
    }

    public int getCharge() {
        return charge;
    }
    public int nameLength(){
        return nameLength;
    }
}
/*
public enum Ions { H(1,1), OH(-1,2), CO3(-2,3), Cl(-1,1), SO4(-2,3), NO3(-1,3), PO4(-3,3),
                    Na(1,1), Ca(2,2), K(1,1), Mg(2,2), Fe(2,2), Cu(2,2), Ag(1,2), Zn(2,2);


 */
