package SAMSUNG;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Ex3190 {
	static int N, K, L;
	static int[][] map;// map은 사과 있는지
	static char[] arr; // 시간에 따른 회전
	static int time; // 지난 시간
	static Info warm;// 지렁이
	// 상 우 하 좌
	static int[][] dir = { { -1, 0 }, { 0, 1 }, { 1, 0 }, { 0, -1 } };
	static Queue<Rc> que;

	static class Info { // 지렁이 정보
		int hr, hc, tr, tc, dir;

		Info(int hr, int hc, int tr, int tc, int dir) {
			this.hr = hr; // 머리좌표
			this.hc = hc;
			this.tr = tr; // 꼬리좌표
			this.tc = tc;
			this.dir = dir; // 머리 방향
		}

	}

	static class Rc { // 좌표 저장용
		int r;
		int c;

		Rc(int r, int c) {
			this.r = r;
			this.c = c;
		}
	}

	static boolean isRange(int r, int c) {
		if (r <= 0 || r > N || c <= 0 || c > N) {
			return false;
		}
		return true;
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		map = new int[N + 1][N + 1];
		K = sc.nextInt();
		for (int i = 0; i < K; i++) { // 사과 자리에 1을입력
			int r = sc.nextInt();
			int c = sc.nextInt();
			map[r][c] = 1;
		}

		L = sc.nextInt();
		arr = new char[10001]; // 문제에서 X는 최대 10000
		for (int i = 0; i < L; i++) {
			int x = sc.nextInt();
			arr[x] = sc.next().charAt(0);
		}
		warm = new Info(1, 1, 1, 1, 1);
		map[1][1] = 2; // 뱀이 지나간 자리를 2부터 시작해서 2,3,4... 이런식으로 사과는 1을 사용할거니ㄲ깐
		time = 0;
		que = new LinkedList<>(); // que는 뱀의 머리가 가는곳 무조건 큐에 넣는다.
		que.add(new Rc(1, 1));

		while (true) {
			int nowD = warm.dir;
			int nextR = warm.hr + dir[nowD][0];
			int nextC = warm.hc + dir[nowD][1];
			time++;
			// 벽을 만나지 않았따면
			if (isRange(nextR, nextC)) {
				// 다음 칸 에 사과 없고 자기 몸 아니라면
				if (map[nextR][nextC] == 0) {
					map[nextR][nextC] = time + 2;
					warm.tr = que.peek().r;
					warm.tc = que.poll().c;
					map[warm.tr][warm.tc] = 0; // 꼬리칸에 0을 만들어줘야 하는데 그 꼬리는 제일 먼저 들어온 큐
				} else if (map[nextR][nextC] == 1) {// 다음 칸에 사과가 있다면
					map[nextR][nextC] = time + 2;
				} else { // 다음 칸이 자기몸이라면
					break;
				}
			} else { // 벽을 만났다면
				break;
			}

			// 방향 설정
			int nextD = nowD;
			if (arr[time] == 'D') {
				nextD = (nowD + 1) % 4;
			} else if (arr[time] == 'L') {
				nextD = (nowD + 4 - 1) % 4;
			}

			// 머리 부분 바꾸기
			warm.hr = nextR;
			warm.hc = nextC;
			warm.dir = nextD;
			que.add(new Rc(nextR, nextC));
		}

		System.out.println(time);
	}
}
