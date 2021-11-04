import java.util.ArrayList;
import java.util.stream.Collectors;

public class Reat {
    private StringBuffer r;
    private int[][] dl;

    public Reat(StringBuffer c, int[][] dl) {
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

        ArrayList<String> Md = Er.stream().filter(i -> i.length() > 0 && i.indexOf("d") == -1)
                .collect(Collectors.toCollection(ArrayList::new));

        ArrayList<String> removeMd = new ArrayList<String>();
        for (String i : Md) {
            for (String j : Md) {
                if (!i.equals(j) && j.indexOf(i) != -1) {
                    removeMd.add(i);
                }
            }
        }
        Md.removeAll(removeMd);
        System.out.println("==> Md = " + Md);

        StringBuilder sb = new StringBuilder();
        if (Md.size() > 1) {
            String Md0 = Md.get(0);
            for (int i = 1; i < Md.size(); i++) {
                for (int j = 0; j < Md0.length(); j++) {
                    if (Md.get(i).indexOf(Md0.charAt(j)) != -1) {
                        sb.append(Md0.charAt(j));
                    }
                }
                Md0 = sb.toString();
            }
        } else {
            sb.append(Md.get(0));
        }

        StringBuilder v = new StringBuilder();
        for (int i = 0; i < r.length(); i++) {
            if (sb.indexOf(r.charAt(i) + "") == -1) {
                v.append(r.charAt(i) + ", ");
            }
        }
        System.out.println("==> V = R - {" + sb + "} = " + v);

        System.out.println(
                "Vay " + v.toString().replace(", d, ", "") + " la tap cac thuoc tinh rut gon cua bang quyet dinh tren");
    }

    public static void main(String[] args) {
        StringBuffer c = new StringBuffer();
        c.append("p");
        c.append("q");
        c.append("r");
        c.append("s");

        int[][] dl = new int[][] { { 0, 1, 1, 1, 0 }, { 1, 1, 0, 1, 0 }, { 0, 0, 1, 0, 1 }, { 1, 0, 0, 1, 1 },
                { 0, 1, 0, 1, 0 } };

        Reat reat = new Reat(c, dl);
        reat.run();

        System.out.println();

        StringBuffer c2 = new StringBuffer();
        c2.append("a");
        c2.append("b");
        c2.append("c");
        c2.append("e");
        c2.append("f");
        c2.append("g");

        int[][] dl2 = new int[][] { { 1, 1, 0, 0, 1, 1, 0 }, { 1, 1, 1, 1, 0, 0, 1 }, { 0, 0, 0, 1, 1, 0, 1 },
                { 0, 1, 1, 0, 1, 1, 1 }, };

        Reat reat2 = new Reat(c2, dl2);
        reat2.run();

    }
}
