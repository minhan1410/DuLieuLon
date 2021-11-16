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
            int lengS = i.length(), count = 0;
            for (String j : Md) {
                if (!i.equals(j)) {
                    for (int k = 0; k < j.length(); k++) {
                        if (lengS < j.length() || i.indexOf(j.charAt(k)) == -1) {
                            break;
                        } else {
                            count++;
                        }
                    }
                    if (count == j.length()) {
                        removeMd.add(j);
                    }
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
                if (sb.length() > 0) {
                    Md0 = sb.toString();
                    sb = new StringBuilder();
                } else {
                    Md0 = "";
                    break;
                }
            }
            if (Md0.length() > 0) {
                sb.append(Md0);
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

        int[][] dl = new int[][] { 
            { 0, 1, 1, 1, 0 }, 
            { 1, 1, 0, 1, 0 }, 
            { 0, 0, 1, 0, 1 }, 
            { 1, 0, 0, 1, 1 },
            { 0, 1, 0, 1, 0 } 
        };

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

        int[][] dl2 = new int[][] { 
            { 1, 1, 0, 0, 1, 1, 0 }, 
            { 1, 1, 1, 1, 0, 0, 1 }, 
            { 0, 0, 0, 1, 1, 0, 1 },
            { 0, 1, 1, 0, 0, 1, 0 }
        };

        Reat reat2 = new Reat(c2, dl2);
        reat2.run();

        System.out.println();

        StringBuffer c3 = new StringBuffer();
        c3.append("a");
        c3.append("b");
        c3.append("c");
        c3.append("e");
        c3.append("f");
        c3.append("g");

        int[][] dl3 = new int[][] { 
            { 1, 1, 0, 0, 1, 1, 0 }, 
            { 1, 1, 1, 1, 0, 0, 1 }, 
            { 0, 0, 0, 1, 1, 0, 1 },
            { 0, 1, 1, 0, 1, 1, 1 } 
        };

        Reat reat3 = new Reat(c3, dl3);
        reat3.run();
    }
}