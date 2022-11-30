package beadando;
import javax.persistence.*;

@Entity
@Table(name="huzas")
public class huzas {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int HúzásId;
    @Column(name = "ev")
    public int Év;
    @Column(name = "het")
    public int Hét;

    public huzas(int húzásId, int év, int hét) {
        HúzásId = húzásId;
        Év = év;
        Hét = hét;
    }

    public huzas() {

    }

    public int getHúzásId() {
        return HúzásId;
    }

    public void setHúzásId(int húzásId) {
        HúzásId = húzásId;
    }

    public int getÉv() {
        return Év;
    }

    public void setÉv(int év) {
        Év = év;
    }

    public int getHét() {
        return Hét;
    }

    public void setHét(int hét) {
        Hét = hét;
    }
}
