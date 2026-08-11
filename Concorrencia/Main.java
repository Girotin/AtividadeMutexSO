public class Main {

    private static boolean mutex = false; // Variável de estado de disponibilidade de recursos
                                          // quando FALSE = desocupado/livre | quando TRUE = ocupado/indisponível

    private static int recursoDisputado = 0;

    private static void espera(int tempo)
    {
        try {
            Thread.sleep(tempo);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static void travaMutex(char thread)
    {
        System.out.println("Thread " + thread + " tentando adquirir o Mutex...");

        while (mutex == true)
        {
            System.out.println("Thread " + thread + " encontrou o Mutex OCUPADO. Aguardando...");
            espera(1000);
        }

        mutex = true;

        System.out.println("Thread " + thread + " adquiriu o Mutex.");
    }

    private static void liberaMutex(char thread)
    {
        mutex = false;

        System.out.println("Thread " + thread + " liberou o Mutex.\n");
    }

    private static void praticandoConcorrencia(char thread)
    {
        travaMutex(thread);

        System.out.println("Thread " + thread + " entrou na seção crítica.");

        int valorAtual = recursoDisputado;

        espera(2500);

        recursoDisputado = valorAtual + 1;

        System.out.println("Thread " + thread + " alterou o recurso para: " + recursoDisputado);

        System.out.println("Thread " + thread + " saiu da seção crítica.");

        liberaMutex(thread);
    }

    public static void main(String[] args) {

        Thread thr1 = new Thread(() ->
        {
            System.out.println("Iniciando Thread 1\n");

            for (int i = 0; i < 5; i++)
            {
                praticandoConcorrencia('1');
            }
        });

        Thread thr2 = new Thread(() ->
        {
            System.out.println("Iniciando Thread 2\n");

            for (int i = 0; i < 5; i++)
            {
                praticandoConcorrencia('2');
            }
        });

        thr1.start();
        thr2.start();
    }
}
