package beadando;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class BeadandoController {
    @FXML
    public int n = 0;
    public TableView parh1;
    public TableView parh2;
    @FXML
    private Label lbdel;
    @FXML
    public ComboBox cb1;
    @FXML
    private ComboBox cb2;
    @FXML
    private TableView tv2;
    @FXML
    private Label lb2;
    @FXML
    private Label lb1;
    @FXML
    private GridPane gp1;
    @FXML
    private GridPane gp2;
    @FXML
    private GridPane gp6;
    @FXML
    GridPane gp7;
    @FXML
    private TextField uNyereményId;
    @FXML
    private TextField uHúzásId;
    @FXML
    private TextField uTalálat;
    @FXML
    private TextField uDarab;
    @FXML
    private TextField uÉrték;
    @FXML
    private TextField tfNyereményId;
    @FXML
    private TextField tfHúzásId;
    @FXML
    private TextField tfTalálat;
    @FXML
    private TextField tfDarab;
    @FXML
    private TextField tfÉrték;
    @FXML
    private TableView tv1;
    @FXML
    private TableColumn<huzas, String> nyereményIdCol;
    @FXML
    private TableColumn<huzas, String> húzásIdCol;
    @FXML
    private TableColumn<huzas, String> találatCol;
    @FXML
    private TableColumn<huzas, String> darabCol;
    @FXML
    private TableColumn<huzas, String> értékCol;
    SessionFactory factory;

    public BeadandoController() {
    }

    @FXML
    void initialize() {
        this.ElemekTörlése();
        Configuration cfg = (new Configuration()).configure("hibernate.cfg.xml");
        this.factory = cfg.buildSessionFactory();
    }

    void ElemekTörlése() {
        this.parh1.setVisible(false);
        this.parh1.setManaged(false);
        this.parh2.setVisible(false);
        this.parh2.setManaged(false);
        this.cb1.setVisible(false);
        this.cb2.setManaged(false);
        this.cb1.setVisible(false);
        this.cb2.setManaged(false);
        this.lb1.setVisible(false);
        this.lb1.setManaged(false);
        this.gp1.setVisible(false);
        this.gp1.setManaged(false);
        this.tv1.setVisible(false);
        this.tv1.setManaged(false);
        this.tv2.setVisible(false);
        this.tv2.setManaged(false);
        this.lb2.setVisible(false);
        this.lb2.setManaged(false);
        this.gp2.setVisible(false);
        this.gp2.setManaged(false);
        this.lbdel.setManaged(false);
        this.lbdel.setVisible(false);
    }

    @FXML
    protected void menuCreateClick() {
        this.ElemekTörlése();
        this.gp1.setVisible(true);
        this.gp1.setManaged(true);
    }

    @FXML
    protected void menuUpdateClick() {
        this.ElemekTörlése();
        this.gp2.setVisible(true);
        this.gp2.setManaged(true);
        this.cb1.setVisible(true);
        this.cb1.setManaged(true);
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        List<huzas> lista = session.createQuery("FROM nyeremeny ").list();
        this.cb1.setPromptText("Válassz egy id-t!");
        Iterator var4 = lista.iterator();

        while(var4.hasNext()) {
            nyeremeny nyer = (nyeremeny) var4.next();
            this.cb1.getItems().add(nyer.NyereményId);
        }

    }

    void Create() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        nyeremeny nyer = new nyeremeny(Integer.parseInt(tfNyereményId.getText()), Integer.parseInt(tfHúzásId.getText()),Integer.parseInt(tfTalálat.getText()),Integer.parseInt( tfDarab.getText()),Integer.parseInt( tfÉrték.getText()));
        session.save(nyer);
        t.commit();
    }

    @FXML
    void bt1Click() {
        this.Create();
        this.ElemekTörlése();
        this.lb1.setVisible(true);
        this.lb1.setManaged(true);
        this.lb1.setText("Adatok beírva az adatbázisba");
    }

    @FXML
    protected void menuReadClick() {
        this.ElemekTörlése();
        this.tv1.setVisible(true);
        this.tv1.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        this.nyereményIdCol = new TableColumn("Nyeremény Id");
        this.húzásIdCol = new TableColumn("Húzás Id");
        this.találatCol = new TableColumn("Találat");
        this.darabCol = new TableColumn("Darab");
        this.értékCol = new TableColumn("Érték");
        this.tv1.getColumns().addAll(new Object[]{this.nyereményIdCol, this.húzásIdCol, this.találatCol, this.darabCol, this.értékCol});
        this.nyereményIdCol.setCellValueFactory(new PropertyValueFactory("Nyeremény Id"));
        this.húzásIdCol.setCellValueFactory(new PropertyValueFactory("Húzás Id"));
        this.találatCol.setCellValueFactory(new PropertyValueFactory("Találat"));
        this.darabCol.setCellValueFactory(new PropertyValueFactory("Darab"));
        this.értékCol.setCellValueFactory(new PropertyValueFactory("Érték"));
        this.tv1.getItems().clear();
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
        Iterator var4 = lista.iterator();

        while(var4.hasNext()) {
            nyeremeny nyer = (nyeremeny) var4.next();
            this.tv1.getItems().add(nyer);
        }

        System.out.println();
        t.commit();
    }

    @FXML
    void Update() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        System.out.println("Update..");
        int n = this.cb1.getVisibleRowCount();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, n);
        nyer.setNyereményId(Integer.parseInt(this.uNyereményId.getText()));
        nyer.setHúzásId(Integer.parseInt(this.uHúzásId.getText()));
        nyer.setTalálat(Integer.parseInt(this.uTalálat.getText()));
        nyer.setDarab(Integer.parseInt(this.uDarab.getText()));
        nyer.setÉrték(Integer.parseInt(this.uÉrték.getText()));
        session.update(nyer);
        t.commit();
    }

    public void bt2Click(ActionEvent actionEvent) {
        this.ElemekTörlése();
        this.Update();
        this.lb2.setVisible(true);
        this.lb2.setManaged(true);
        this.lb2.setText("Update lefutott!");
    }

    @FXML
    protected void menuDeleteClick() {
        this.ElemekTörlése();
        this.lbdel.setVisible(true);
        this.lbdel.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        Session session = this.factory.openSession();
        System.out.println("Delete..");
        Transaction t = session.beginTransaction();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, 1);
        session.delete(nyer);
        t.commit();
        this.lbdel.setText("Az 1.-es id-val rendelkező sor törlésre került!");
    }

    @FXML
    protected void menuReadClick_2() {
        this.ElemekTörlése();
        this.tv1.setVisible(true);
        this.tv1.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        this.nyereményIdCol = new TableColumn("Nyeremény Id");
        this.húzásIdCol = new TableColumn("Húzás Id");
        this.találatCol = new TableColumn("Találat");
        this.darabCol = new TableColumn("Darab");
        this.értékCol = new TableColumn("Érték");
        this.tv1.getColumns().addAll(new Object[]{this.nyereményIdCol, this.húzásIdCol, this.találatCol, this.darabCol, this.értékCol});
        this.nyereményIdCol.setCellValueFactory(new PropertyValueFactory("Nyeremény Id"));
        this.húzásIdCol.setCellValueFactory(new PropertyValueFactory("Húzás Id"));
        this.találatCol.setCellValueFactory(new PropertyValueFactory("Találat"));
        this.darabCol.setCellValueFactory(new PropertyValueFactory("Darab"));
        this.értékCol.setCellValueFactory(new PropertyValueFactory("Érték"));
        this.tv1.getItems().clear();
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
        Iterator var4 = lista.iterator();

        while(var4.hasNext()) {
            nyeremeny nyer = (nyeremeny) var4.next();
            this.tv1.getItems().add(nyer);
        }

        System.out.println();
        t.commit();
    }

    @FXML
    protected void Rest1Create1() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        nyeremeny nyer = new nyeremeny(Integer.parseInt(this.tfNyereményId.getText()), Integer.parseInt(this.tfHúzásId.getText()),Integer.parseInt(this.tfTalálat.getText()),Integer.parseInt(this.tfDarab.getText()),Integer.parseInt(this.tfÉrték.getText()));
        session.save(nyer);
        t.commit();
    }

    @FXML
    protected void Rest1Read1() {
        this.ElemekTörlése();
        this.tv1.setVisible(true);
        this.tv1.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        this.nyereményIdCol = new TableColumn("Nyeremény Id");
        this.húzásIdCol = new TableColumn("Húzás Id");
        this.találatCol = new TableColumn("Találat");
        this.darabCol = new TableColumn("Darab");
        this.értékCol = new TableColumn("Érték");
        this.tv1.getColumns().addAll(new Object[]{this.nyereményIdCol, this.húzásIdCol, this.találatCol, this.darabCol, this.értékCol});
        this.nyereményIdCol.setCellValueFactory(new PropertyValueFactory("Nyeremény Id"));
        this.húzásIdCol.setCellValueFactory(new PropertyValueFactory("Húzás Id"));
        this.találatCol.setCellValueFactory(new PropertyValueFactory("Találat"));
        this.darabCol.setCellValueFactory(new PropertyValueFactory("Darab"));
        this.értékCol.setCellValueFactory(new PropertyValueFactory("Érték"));
        this.tv1.getItems().clear();
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
        Iterator var4 = lista.iterator();

        while(var4.hasNext()) {
            nyeremeny nyer = (nyeremeny) var4.next();
            this.tv1.getItems().add(nyer);
        }

        System.out.println();
        t.commit();
        this.lb2.setText("Rest, Read lefutott!!");
    }

    @FXML
    protected void Rest1Update1() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        System.out.println("Update..");
        int n = this.cb1.getVisibleRowCount();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, n);
        nyer.setNyereményId(Integer.parseInt(this.uNyereményId.getText()));
        nyer.setHúzásId(Integer.parseInt(this.uHúzásId.getText()));
        nyer.setTalálat(Integer.parseInt(this.uTalálat.getText()));
        nyer.setDarab(Integer.parseInt(this.uDarab.getText()));
        nyer.setÉrték(Integer.parseInt(this.uÉrték.getText()));
        session.update(nyer);
        t.commit();
    }

    @FXML
    protected void Rest1Delete1() {
        this.ElemekTörlése();
        this.lbdel.setVisible(true);
        this.lbdel.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        Session session = this.factory.openSession();
        System.out.println("Delete..");
        Transaction t = session.beginTransaction();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, 2);
        session.delete(nyer);
        t.commit();
        this.lbdel.setText("Rest_Delete, 2-es id törlése került!");
    }

    @FXML
    protected void Rest2Create() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        nyeremeny nyer = new nyeremeny(Integer.parseInt(this.tfNyereményId.getText()), Integer.parseInt(this.tfHúzásId.getText()),Integer.parseInt(this.tfTalálat.getText()),Integer.parseInt(this.tfDarab.getText()),Integer.parseInt(this.tfÉrték.getText()));
        session.save(nyer);
        t.commit();
    }

    @FXML
    protected void Rest2Read1() {
        this.ElemekTörlése();
        this.tv1.setVisible(true);
        this.tv1.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        this.nyereményIdCol = new TableColumn("Nyeremény Id");
        this.húzásIdCol = new TableColumn("Húzás Id");
        this.találatCol = new TableColumn("Találat");
        this.darabCol = new TableColumn("Darab");
        this.értékCol = new TableColumn("Érték");
        this.tv1.getColumns().addAll(new Object[]{this.nyereményIdCol, this.húzásIdCol, this.találatCol, this.darabCol, this.értékCol});
        this.nyereményIdCol.setCellValueFactory(new PropertyValueFactory("Nyeremény Id"));
        this.húzásIdCol.setCellValueFactory(new PropertyValueFactory("Húzás Id"));
        this.találatCol.setCellValueFactory(new PropertyValueFactory("Találat"));
        this.darabCol.setCellValueFactory(new PropertyValueFactory("Darab"));
        this.értékCol.setCellValueFactory(new PropertyValueFactory("Érték"));
        this.tv1.getItems().clear();
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
        Iterator var4 = lista.iterator();

        while(var4.hasNext()) {
            nyeremeny nyer = (nyeremeny) var4.next();
            this.tv1.getItems().add(nyer);
        }

        System.out.println();
        t.commit();
    }

    @FXML
    protected void Rest2Update1() {
        Session session = this.factory.openSession();
        Transaction t = session.beginTransaction();
        System.out.println("Update..");
        int n = this.cb1.getVisibleRowCount();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, n);
        nyer.setNyereményId(Integer.parseInt(this.uNyereményId.getText()));
        nyer.setHúzásId(Integer.parseInt(this.uHúzásId.getText()));
        nyer.setTalálat(Integer.parseInt(this.uTalálat.getText()));
        nyer.setDarab(Integer.parseInt(this.uDarab.getText()));
        nyer.setÉrték(Integer.parseInt(this.uÉrték.getText()));
        session.update(nyer);
        t.commit();
    }

    @FXML
    protected void Rest2Delete1() {
        this.ElemekTörlése();
        this.lbdel.setVisible(true);
        this.lbdel.setManaged(true);
        this.tv1.getColumns().removeAll(this.tv1.getColumns());
        Session session = this.factory.openSession();
        System.out.println("Delete..");
        Transaction t = session.beginTransaction();
        nyeremeny nyer = (nyeremeny) session.load(nyeremeny.class, 3);
        session.delete(nyer);
        t.commit();
        this.lbdel.setText("Rest2_Delete! 3-as id törlésre került!!");
    }

    @FXML
    protected void Soapletoltes() {
    }

    @FXML
    protected void Soapletoltes2() {
    }

    @FXML
    protected void Soapgrafikon() {
    }

    @FXML
    protected void Adatb_Dontesi_Fa() {
    }

    @FXML
    protected void Adatb_tobbalgo() {
    }

    @FXML
    protected void Adatb_tobbalgo2() {
    }

    @FXML
    protected void Parhuzamos() throws InterruptedException {
        this.ElemekTörlése();
        this.parh1.setVisible(true);
        this.parh1.setManaged(true);
        this.parh1.getColumns().removeAll(this.parh1.getColumns());
        this.találatCol = new TableColumn("Találat");
        this.értékCol = new TableColumn("Érték");
        this.parh1.getColumns().addAll(new Object[]{this.találatCol, this.értékCol});
        this.találatCol.setCellValueFactory(new PropertyValueFactory("Találat"));
        this.értékCol.setCellValueFactory(new PropertyValueFactory("Érték"));
        this.parh1.getItems().clear();
        final Session session = this.factory.openSession();
        final Transaction t = session.beginTransaction();
        (new Thread() {
            public void run() {
                while(true) {
                    try {
                        System.out.println("Egyik szál");
                        List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
                        lista.stream().forEach((a) -> {
                            PrintStream var10000 = System.out;
                            String var10001 = Integer.toString(a.getTalálat());
                            var10000.println(var10001 + ": " +  a.getÉrték());
                        });
                        Iterator var2 = lista.iterator();

                        while(var2.hasNext()) {
                            nyeremeny ujnyer = (nyeremeny) var2.next();
                            BeadandoController.this.parh1.getItems().add(ujnyer);
                        }

                        Thread.sleep(1000L);
                        t.commit();
                    } catch (InterruptedException var4) {
                        var4.printStackTrace();
                    }
                }
            }
        }).start();

        while(true) {
            System.out.println("Másik szál");
            List<nyeremeny> lista = session.createQuery("FROM nyeremeny ").list();
            lista.stream().forEach((a) -> {
                PrintStream var10000 = System.out;
                String var10001 = Integer.toString(a.getTalálat());
                var10000.println(var10001 + ": " + a.getÉrték());
            });
            Iterator var4 = lista.iterator();

            while(var4.hasNext()) {
                nyeremeny ujnyer = (nyeremeny) var4.next();
                this.parh1.getItems().add(ujnyer.getTalálat());
            }

            Thread.sleep(2000L);
            t.commit();
        }
    }

    @FXML
    protected void Stream() {
    }
}
