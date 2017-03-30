package solved.questions.hackerearth;

public class NthBinaryPalindrome {
  
  public static void main(String[] args) {

    for (long i = 1; i <= 1000; i++) {
      long start = System.currentTimeMillis();
      System.out.println("Number: " + nthBooleanPalindrome(i));
      long stop = System.currentTimeMillis();
      System.out.println("Time Taken: " + (stop - start));
    }
  }



  public static long nthBooleanPalindrome(long n) {
    n = n + 1;
    if (n == 1)
      return 0;
    if (n == 2)
      return 1;
    long m = (long) Math.floor(Math.log(n) / Math.log(2));
    long c = 1 << (m - 1);
    long answer;
    if (n >= 3 * c) {
      long a = n - 3 * c;
      long d = 2 * c * c;
      answer = d + 1;
      long k2 = 1;
      for (long i = 1; i < m; i++) {
        k2 <<= 1;
        answer += a * k2 / c % 2 * (k2 + d / k2);
      }
    } else {
      long a = n - 2 * c;
      long d = c * c;
      answer = d + 1 + (n % 2 * c);
      long k2 = 1;
      for (long i = 1; i < m - 1; i++) {
        k2 <<= 1;
        answer += a * k2 / c % 2 * (k2 + d / k2);
      }
    }
    return answer;
  }
}
