package beadando;

import java.io.PrintStream;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

class BeadandoController1 extends Thread {
    private final BeadandoController this$0;
    private final Session val$session;
    private final Transaction val$t;

    BeadandoController1(BeadandoController this$0, Session var2, Transaction var3) {
        this.this$0 = this$0;
        this.val$session = var2;
        this.val$t = var3;
    }

    public void run() {
        while(true) {
            try {
                System.out.println("Egyik szál");
                List<nyeremeny> lista = this.val$session.createQuery("FROM nyeremeny ").list();
                lista.stream().forEach((a) -> {
                    PrintStream var10000 = System.out;
                    String var10001 = Integer.toString(a.getTalálat());
                    var10000.println(var10001 + ": " + a.getÉrték());
                });
                Iterator var2 = lista.iterator();

                while(var2.hasNext()) {
                    nyeremeny ujnyer = (nyeremeny) var2.next();
                    this.this$0.parh1.getItems().add(ujnyer);
                }

                Thread.sleep(1000L);
                this.val$t.commit();
            } catch (InterruptedException var4) {
                var4.printStackTrace();
            }
        }
    }
}
