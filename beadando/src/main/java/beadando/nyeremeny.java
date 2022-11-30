package beadando;

import javax.persistence.*;

@Entity
@Table(name="nyeremeny")
public class nyeremeny {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int NyereményId;
    @Column(name = "huzasid")
    public int HúzásId;
    @Column(name = "talalat")
    public int Találat;
    @Column(name = "darab")
    public int Darab;
    @Column(name = "ertek")
    public int Érték;

    public nyeremeny(int nyereményId, int húzásId, int találat, int darab, int érték) {
        NyereményId = nyereményId;
        HúzásId = húzásId;
        Találat = találat;
        Darab = darab;
        Érték = érték;
    }

    public nyeremeny() {

    }


    public int getNyereményId() {
        return NyereményId;
    }

    public void setNyereményId(int nyereményId) {
        NyereményId = nyereményId;
    }

    public int getHúzásId() {
        return HúzásId;
    }

    public void setHúzásId(int húzásId) {
        HúzásId = húzásId;
    }

    public int getTalálat() {
        return Találat;
    }

    public void setTalálat(int találat) {
        Találat = találat;
    }

    public int getDarab() {
        return Darab;
    }

    public void setDarab(int darab) {
        Darab = darab;
    }

    public int getÉrték() {
        return Érték;
    }

    public void setÉrték(int érték) {
        Érték = érték;
    }
}
