package OTHER;

import java.util.Scanner;

public class Main_백준_2798_블랙잭 {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int M = sc.nextInt();
		int[] arr = new int[N];

		for (int i = 0; i < N; i++) {
			arr[i] = sc.nextInt();
		}

		int max = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			int a = arr[i];
			for (int j = i + 1; j < N; j++) {
				int b = arr[j];
				for (int k = j + 1; k < N; k++) {
					int c = arr[k];
					if (a + b + c <= M) {
						if (max < a + b + c) {
							max = a + b + c;
						}
					}
				}
			}
		}
		System.out.println(max);

	}
}
