public class Main {

    private static boolean mutex = false; // Variável de estádo de disponibilidade de recursos
                                          // quando FALSE = desocupado/livre | quando TRUE = ocupado/indisponível

    private static void estadoThread1(boolean estadoTHR1)
    { if (estadoTHR1 == true) { System.out.println("Thread 1 EM USO"); }}

    private static void estadoThread2(boolean estadoTHR2)
    { if (estadoTHR2 == true) { System.out.println("Thread 2 EM USO"); }}

    private static void espera(int tempo)
    {
        try {
            System.out.println("Aguardando...\n");
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static boolean estadoMutex(char thread)
    {
        System.out.println("\n\nThread " + thread + " quer ter prioridade!\n");

        if (mutex == false)
        {
            System.out.println("Mutex detectado LIVRE");

            mutex = true;
            System.out.println("Estado Mutex alterado para OCUPADO | Valor [" + mutex + "]\n");

            return true;
        }
        else
        {
            System.out.println("Mutex detectado OCUPADO | Valor [" + mutex + "]");
            System.out.println("Aguarde o fim da execução!\n");

            return false;
        }
    }

    public static void main(String[] args) {

        Thread thr1 = new Thread(() ->
        {
            boolean usoTHR1 = false;
            System.out.println("Iniciando Thread 1\n");

            espera(3000);
            usoTHR1 = estadoMutex('1');
            estadoThread1(usoTHR1);
        });

        Thread thr2 = new Thread(() ->
        {
            boolean usoTHR2 = false;
            System.out.println("Iniciando Thread 2\n");

            espera(1000);
            usoTHR2 = estadoMutex('2');
            estadoThread2(usoTHR2);
        });

        thr1.start();
        thr2.start();
    }
}