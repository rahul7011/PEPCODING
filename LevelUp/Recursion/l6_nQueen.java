public class l6_nQueen {
    static boolean[] rows, cols, diags, adiags;

    public static int nqueen_01(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            if (!rows[r] && !cols[c] && !diags[r - c + n - 1] && !adiags[r + c]) {
                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = true;

                count += nqueen_01(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");

                rows[r] = cols[c] = diags[r - c + n - 1] = adiags[r + c] = false;
            }
        }
        return count;
    }

    static int row = 0, col = 0, diag = 0, adiag = 0;

    public static int nqueen_02(int n, int tnq, int bno, String psf) {
        if (tnq == 0) {
            System.out.println(psf);
            return 1;
        }
        int count = 0;
        for (int bidx = bno; bidx < n * n; bidx++) {
            int r = bidx / n, c = bidx % n;
            int rmask = (1 << r);
            int cmask = (1 << c);
            int dmask = (1 << (r - c + n - 1));
            int admask = (1 << (r + c));
            if ((row & rmask) == 0 && (col & cmask) == 0 && (diag & dmask) == 0 && (adiag & admask) == 0) {
                row ^= rmask;
                col ^= cmask;
                diag ^= dmask;
                adiag ^= admask;

                count += nqueen_02(n, tnq - 1, bidx + 1, psf + "(" + r + "," + c + ") ");

                row ^= rmask;
                col ^= cmask;
                diag ^= dmask;
                adiag ^= admask;
            }
        }
        return count;
    }

    public static int nqueen_03(int n, int floor, String psf) {
        if (floor == n) {
            System.out.println(psf);
            return 1;
        }
        int count = 0, r = floor;
        for (int room = 0; room < n; room++) {
            int c = room;
            if ((row & (1 << r)) == 0 && (col & (1 << c)) == 0 && (diag & (1 << (r - c + n - 1))) == 0
                    && (adiag & (1 << (r + c))) == 0) {
                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));

                count += nqueen_03(n, floor + 1, psf + "(" + r + "," + c + ") ");

                row ^= (1 << r);
                col ^= (1 << c);
                diag ^= (1 << (r - c + n - 1));
                adiag ^= (1 << (r + c));
            }
        }
        return count;
    }

    public static void main(String[] args) {
        // int n = 4, tnq = 4;
        // rows = new boolean[n];
        // cols = new boolean[n];
        // diags = new boolean[n + n - 1];
        // adiags = new boolean[n + n - 1];
        // System.out.println(nqueen_02(n, tnq, 0, ""));
        int n=4;
        System.out.println(nqueen_03(n, 0, ""));
    }
}
