import java.util.Scanner;

public class Main_백준_9944_NxM보드완주하기 {
	static final int[][] dir = { { 1, 0 }, { 0, 1 }, { -1, 0 }, { 0, -1 } };
	static int N, M;
	static String map[][];
	static boolean visit[][]; // [1][2][3] 1번칸 방향
	static int required;
	static int min;
	static int count;

	static class Info {
		int r, c, d, cnt;

		public Info(int r, int c, int d, int cnt) {
			super();
			this.r = r;
			this.c = c;
			this.d = d;
			this.cnt = cnt;
		}
	}

	static boolean isRange(int r, int c) {
		if (r < 0 || r >= N || c < 0 || c >= N) {
			return false;
		}
		return true;
	}

	static Info go(int r, int c, int d) {
		do {
			visit[r][c] = true;
			r = r + dir[d][0];
			c = c + dir[d][1];
			count++;
		} while (isRange(r, c) && map[r][c].equals(".") && !visit[r][c]);
		return new Info(r - dir[d][0], c - dir[d][1], d, 0);
	}

	static void rollback(int r, int c, int d, int real) {
		visit[r][c] = false;
		for (int i = 1; i < real; i++) {
			visit[r + dir[d][0] * i][c + dir[d][1] * i] = false;
		}
		count -= real;
	}

	static void solve(int r, int c, int cc) {
		if (count == required) {
			if (cc < min) {
				min = cc;
			}
			return;
		}
		for (int d = 0; d < 4; d++) {
			int nextR = r + dir[d][0];
			int nextC = c + dir[d][1];
			Info next = go(r, c, d); // 일단 가
			if (isRange(nextR, nextC) && map[nextR][nextC].equals(".") && !visit[nextR][nextC]) {
				int real = count;
				real = count - real;
				solve(next.r, next.c, cc + 1);
				rollback(r, c, d, real);
			}
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int tc = 0;
		while (sc.hasNextInt()) {
			N = sc.nextInt();
			M = sc.nextInt();
			map = new String[N][M];
			required = 0;
			tc++;
			for (int i = 0; i < N; i++) {
				String str = sc.next();
				for (int j = 0; j < M; j++) {
					map[i][j] = str.substring(j, j + 1);
					if (map[i][j].equals(".")) {
						required++;
					}
				}
			}

			min = Integer.MAX_VALUE;
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < M; j++) {
					if (map[i][j].equals(".")) {
						visit = new boolean[N][M];
						count = 0;
						solve(i, j, 1);
					}
				}
			}
			if (min >= Integer.MAX_VALUE) {
				min = -1;
			}
			System.out.println("Case " + tc + ": " + min);
		}
		return;

	}
}
