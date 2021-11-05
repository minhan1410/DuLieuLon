import java.util.ArrayList;

public class Bai3 {
    private StringBuffer r;
    private int[][] dl;

    public Bai3(StringBuffer c, int[][] dl) {
        this.r = c;
        this.r.append("d");
        this.dl = dl;
    }

    public void run() {
        ArrayList<String> Er = new ArrayList<String>();
        for (int i = 0; i < dl.length - 1; i++) {
            for (int j = i + 1; j < dl.length; j++) {
                System.out.print("E" + (i + 1) + "" + (j + 1) + " = ");
                StringBuilder sb = new StringBuilder();

                for (int k = 0; k < dl[i].length; k++) {
                    // System.out.println("dl[" + i + "][" + k + "]= " + dl[i][k] + " vs dl[" + j +
                    // "][" + k + "]= " + dl[j][k]);
                    if (dl[i][k] == dl[j][k]) {
                        sb.append(r.charAt(k));
                    }
                }
                System.out.print(sb.toString() + "; ");
                Er.add(sb.toString());
            }
            System.out.println();
        }
        System.out.println("==> Er = " + Er);

    }

    public static void main(String[] args) {
        StringBuffer c = new StringBuffer();
        c.append("a");
        c.append("b");
        c.append("c");

        int[][] dl = new int[][] { 
            { 1, 0, 1, 1 }, 
            { 0, 1, 1, 0 }, 
            { 1, 1, 1, 0 }, 
            { 1, 0, 0, 0 }, 
            { 1, 1, 0, 1 } 
        };

        Bai3 bai3 = new Bai3(c, dl);
        bai3.run();
    }
}
