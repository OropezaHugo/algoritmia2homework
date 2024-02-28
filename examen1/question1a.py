private static int function(int n, int [] dp) {
    if (n == 0 ) {
      return 0;
    }

    if (n == 1 || n == 2) {
      return 1;
    }

    dp[n] = function(n - 1, dp) + function(n - 2, dp);

    return dp[n];
  }