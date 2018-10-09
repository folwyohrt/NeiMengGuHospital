/**
 * @Auther: 李景然
 * @Date: 2018/7/26 20:35
 * @Description:
 */
public class thread2 {
    public static void main(String[] args) throws InterruptedException {
        Count_i ci = new Count_i();
        Count_j cj = new Count_j();
        Count_k ck = new Count_k();
        Mul_thread_i aa = new Mul_thread_i(ci);
        Mul_thread_j bb = new Mul_thread_j(cj);
        Mul_thread_k cc = new Mul_thread_k(ck);

        aa.start();
        bb.start();
        cc.start();
        aa.join();
        cc.join();
        bb.join();

    }
}

class Count_i {
    public synchronized void count() {
        for (int k = 0; k < 300; k++) {
            System.out.println("Count_i");
        }
    }
}

class Count_j {
    public synchronized void count() {
        for (int k = 0; k < 300; k++) {
            System.out.println("Count_j");
        }
    }
}

class Count_k {
    public synchronized void count() {
        for (int k = 0; k < 300; k++) {
            System.out.println("Count_k");
        }
    }
}

class Mul_thread_i extends Thread {
    public Count_i c_i;

    public Mul_thread_i(Count_i acc) {
        this.c_i = acc;
    }

    public void run() {
        c_i.count();
    }
}

class Mul_thread_j extends Thread {
    public Count_j c_j;

    public Mul_thread_j(Count_j acc) {
        this.c_j = acc;
    }

    public void run() {
        c_j.count();
    }
}

class Mul_thread_k extends Thread {
    public Count_k c_k;

    public Mul_thread_k(Count_k acc) {
        this.c_k = acc;
    }

    public void run() {
        c_k.count();
    }
}
