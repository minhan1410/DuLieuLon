import java.util.ArrayList;
import java.util.LinkedHashSet;

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

        ArrayList<String> M = new ArrayList<String>(new LinkedHashSet<String>(Er));
        System.out.println("==> M = " + M);

        ArrayList<String> L = new ArrayList<String>();
        for (int i = 0; i < r.length(); i++) {
            if (i == 0) {
                L.add(r.substring(0, r.length() - 1));
                System.out.println("==> L(0) = " + L.get(0));
            } else {
                String l = L.get(i - 1);
                char a = r.charAt(i - 1);
                StringBuffer substring = new StringBuffer();

                for (int j = 0; j < l.length(); j++) {
                    if (l.charAt(j) != a) {
                        substring.append(l.charAt(j));
                    }
                }

                ArrayList<String> find = new ArrayList<String>();
                for (String s : M) {
                    int lengS = s.length(), count = 0;
                    for (int j = 0; j < substring.length(); j++) {
                        if (lengS < substring.length() || s.indexOf(substring.charAt(j)) == -1) {
                            break;
                        } else {
                            count++;
                        }
                    }
                    if (count == substring.length()) {
                        find.add(s);
                    }
                }

                String strFind = "";
                if (find.size() == 0) {
                    strFind = r.toString();
                    L.add(substring.toString());
                } else {
                    if (find.size() == 1) {
                        strFind = find.get(0);
                    } else {
                        StringBuffer giaoFind = new StringBuffer();
                        String find0 = find.get(0);
                        for (int j = 1; j < find.size(); j++) {
                            for (int k = 0; k < find0.length(); k++) {
                                if (find.get(j).indexOf(find0.charAt(k)) != -1) {
                                    giaoFind.append(find0.charAt(k));
                                }
                            }
                            if (giaoFind.length() > 0) {
                                find0 = giaoFind.toString();
                                giaoFind = new StringBuffer();
                            } else {
                                break;
                            }
                        }
                        strFind = find0;
                    }
                    if (strFind.indexOf("d") != -1) {
                        L.add(substring.toString());
                    } else {
                        L.add(L.get(L.size() - 1));
                    }
                }

                System.out.printf("==> L(%d) = %s vi {d} tap con {%s}+ = %s\n", i, L.get(i), substring.toString(),
                        strFind);
            }
        }
        String result = "";
        for (int i = 0; i < L.get(L.size() - 1).length(); i++) {
            result += L.get(L.size() - 1).charAt(i);
            if (i != L.get(L.size() - 1).length() - 1) {
                result += ",";
            }
        }
        System.out.println("Nhu vay tap {" + result + "} la mot rut gon cua bang quyet dinh tren");
    }

    public static void main(String[] args) {
        StringBuffer c = new StringBuffer();
        c.append("a");
        c.append("b");
        c.append("c");

        int[][] dl = new int[][] { { 1, 0, 1, 1 }, { 0, 1, 1, 0 }, { 1, 1, 1, 0 }, { 1, 0, 0, 0 }, { 1, 1, 0, 1 } };

        Bai3 bai3 = new Bai3(c, dl);
        bai3.run();

        System.out.println();

        StringBuffer c2 = new StringBuffer();
        c2.append("a");
        c2.append("b");
        c2.append("c");
        c2.append("e");
        c2.append("f");

        int[][] dl2 = new int[][] { { 1, 1, 0, 0, 1, 0 }, { 1, 0, 0, 0, 0, 1 }, { 0, 1, 1, 0, 1, 1 },
                { 1, 1, 1, 0, 0, 1 } };

        Bai3 bai32 = new Bai3(c2, dl2);
        bai32.run();
    }
}