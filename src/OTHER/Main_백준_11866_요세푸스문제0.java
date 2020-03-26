package OTHER;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Scanner;

public class Main_백준_11866_요세푸스문제0 {
	public static Queue<Integer> queue = new LinkedList<>();
	public static Queue<Integer> rQueue = new LinkedList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] inputStr = reader.readLine().split(" ");

		int N = Integer.parseInt(inputStr[0]);
		int K = Integer.parseInt(inputStr[1]);

		for (int i = 1; i <= N; i++) {
			queue.add(i);
		}

		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= K; j++) {
				if (j % K == 0) {
					rQueue.add(queue.poll());
				} else {
					queue.add(queue.poll());
				}
			}
		}

		printResult(N);
	}

	private static void printResult(int N) {
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for (int i = 1; i <= N; i++) {
			sb.append(rQueue.poll());
			if (i != N) {
				sb.append(", ");
			}
		}
		sb.append(">");
		System.out.println(sb.toString());
	}

}
