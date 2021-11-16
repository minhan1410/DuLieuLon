import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.HashSet;
import java.util.Set;

public class Apriori {
    private ArrayList<Character> I;
    private ArrayList<ArrayList<Character>> giaoTac;
    private double minsup;

    public Apriori(ArrayList<Character> I, ArrayList<ArrayList<Character>> giaoTac, double minsup) {
        this.I = I;
        this.giaoTac = giaoTac;
        this.minsup = minsup;
    }

    public double sup(List<Character> list) {
        double count = 0;
        for (ArrayList<Character> item : giaoTac) {
            int check = 0;
            for (int i = 0; i < list.size(); i++) {
                if (item.contains(list.get(i))) {
                    check++;
                }
            }
            if (check == list.size())
                count++;
        }
        return count / giaoTac.size();
    }

    public ArrayList<ArrayList<Character>> gop(ArrayList<ArrayList<Character>> Xi, ArrayList<Character> A) {
        ArrayList<ArrayList<Character>> result = new ArrayList<>();
        for (int i = 0; i < Xi.size(); i++) {
            for (int j = 0; j < A.size(); j++) {
                Set<Character> set = new HashSet<>(Xi.get(i));
                if (set.add(A.get(j))) {
                    ArrayList<Character> temp = new ArrayList<>(set);
                    for (ArrayList<Character> item : result) {
                        if (item.equals(temp)) {
                            temp = null;
                            break;
                        }
                    }
                    if (temp != null) {
                        result.add(temp);
                    }
                }
            }
        }
        return result;
    }

    public void run() {
        ArrayList<ArrayList<ArrayList<Character>>> X = new ArrayList<>();
        ArrayList<Character> A = new ArrayList<>();

        ArrayList<ArrayList<Character>> Xi = new ArrayList<>();

        int buoc = 0;
        System.out.println("Buoc " + ++buoc);
        for (int i = 0; i < I.size(); i++) {
            double sup = sup(Arrays.asList(I.get(i)));
            if (sup >= minsup) {
                System.out.printf("\n\tsup(%s) = %.2f  >  %.2f\n\t==> {%s} la tap muc thuong xuyen\n", I.get(i), sup,
                        minsup, I.get(i));
                A.add(I.get(i));

                ArrayList<Character> item = new ArrayList<>();
                item.add(I.get(i));
                Xi.add(item);

                System.out.println("\t==> A = " + A);
                System.out.println("\t==> Xi = " + Xi);
            } else {
                System.out.printf("\n\tsup(%s) = %.2f  <  %.2f\n\t==> {%s} khong la tap muc thuong xuyen\n", I.get(i),
                        sup, minsup, I.get(i));
            }
        }
        X.add(Xi);

        int countFalse = 0;
        ArrayList<ArrayList<Character>> temp = gop(Xi, A);
        while (countFalse < temp.size()) {
            Xi = new ArrayList<>();
            System.out.println("Buoc " + ++buoc);
            for (int i = 0; i < temp.size(); i++) {
                double sup = sup(temp.get(i));
                if (sup >= minsup) {
                    System.out.printf("\n\tsup(%s) = %.2f  >  %.2f\n\t==> {%s} la tap muc thuong xuyen\n", temp.get(i),
                            sup, minsup, temp.get(i));

                    Xi.add(temp.get(i));
                    System.out.println("\t==> Xi = " + Xi);
                } else {
                    System.out.printf("\n\tsup(%s) = %.2f  <  %.2f\n\t==> {%s} khong la tap muc thuong xuyen\n",
                            temp.get(i), sup, minsup, temp.get(i));
                    countFalse++;
                }
            }
            if (Xi.size() != 0) {
                X.add(Xi);

                temp = gop(Xi, A);
                countFalse = 0;
            }
        }
        System.out.printf("\nTap muc thuong xuyen vs minsup = %.2f la:\n", minsup);
        for (int i = 0; i < X.size(); i++) {
            System.out.println("==> Tap muc co luc luong la " + (i + 1) + " : " + X.get(i));
        }
    }

    public static void main(String[] args) {

        ArrayList<Character> i = new ArrayList<>();
        i.add('n');
        i.add('p');
        i.add('q');
        i.add('x');
        i.add('y');
        i.add('z');

        ArrayList<ArrayList<Character>> giaoTac = new ArrayList<ArrayList<Character>>();
        giaoTac.add(new ArrayList<Character>(Arrays.asList('p', 'q', 'x', 'y')));
        giaoTac.add(new ArrayList<Character>(Arrays.asList('q', 'x', 'y', 'z')));
        giaoTac.add(new ArrayList<Character>(Arrays.asList('n', 'p', 'q', 'z')));
        giaoTac.add(new ArrayList<Character>(Arrays.asList('p', 'q', 'y', 'z')));

        double minsup = 2.0 / 3;

        Apriori apriori = new Apriori(i, giaoTac, minsup);
        apriori.run();

    }
}
