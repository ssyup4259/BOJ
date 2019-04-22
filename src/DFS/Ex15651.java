package DFS;

import java.util.Scanner;

public class Ex15651 {
	static int N, M;
	static int[] arr;

	static void dfs(int idx) {
		if (idx == M) {
			for (int i = 0; i < M; i++) {
				if (i == M - 1) {
					System.out.println(arr[i]);
				} else {
					System.out.print(arr[i] + " ");
				}
			}
			return;
		}

		for (int i = 1; i <= N; i++) {
			arr[idx] = i;
			dfs(idx + 1);
		}
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		arr = new int[M];
		dfs(0);
	}

}
