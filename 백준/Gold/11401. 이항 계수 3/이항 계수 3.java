import java.io.*;

public class Main {
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final int MOD = 1_000_000_007;

	static int N, K;
	static long[] factorial;

	public static void main(String[] args) throws IOException {
		String[] tokens = br.readLine().split(" ");
		N = Integer.parseInt(tokens[0]);
		K = Integer.parseInt(tokens[1]);

		// 팩토리얼 미리 계산
		factorial = new long[N + 1];
		factorial[0] = 1;
		for (int i = 1; i <= N; i++) {
			factorial[i] = factorial[i - 1] * i % MOD;
		}

		// 이항계수 계산: N! / (K! * (N-K)!) mod MOD
		long numerator = factorial[N];
		long denominator = factorial[K] * factorial[N - K] % MOD;

		long result = numerator * modInverse(denominator) % MOD;
		System.out.println(result);
	}

	// 역원: a^(MOD-2) % MOD (페르마의 소정리)
	static long modInverse(long a) {
		return pow(a, MOD - 2);
	}

	// 모듈러 거듭제곱 (분할 정복)
	static long pow(long base, long exp) {
		long result = 1;
		while (exp > 0) {
			if ((exp & 1) == 1) result = result * base % MOD;
			base = base * base % MOD;
			exp >>= 1;
		}
		return result;
	}
}
