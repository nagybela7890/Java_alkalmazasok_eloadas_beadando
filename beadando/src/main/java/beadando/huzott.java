package beadando;
import javax.persistence.*;
@Entity
@Table(name = "huzott")
public class huzott {
    @Id
    @GeneratedValue
    @Column(name = "id")
    public int Id;
    @Column(name = "huzasid")
    public int HúzásId;
    @Column(name = "szam")
    public int Szám;

    public huzott(int id, int húzásId, int szám) {
        Id = id;
        HúzásId = húzásId;
        Szám = szám;
    }

    public huzott() {

    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getHúzásId() {
        return HúzásId;
    }

    public void setHúzásId(int húzásId) {
        HúzásId = húzásId;
    }

    public int getSzám() {
        return Szám;
    }

    public void setSzám(int szám) {
        Szám = szám;
    }
}
