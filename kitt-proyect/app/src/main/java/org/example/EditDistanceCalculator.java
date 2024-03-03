package org.example;

public class EditDistanceCalculator {
  static int min(int x, int y, int z)
  {
    if (x <= y && x <= z)
      return x;
    if (y <= x && y <= z)
      return y;
    else
      return z;
  }
  static int editDist(String str1, String str2, int lengthStr1,
                      int lengthStr2)
  {
    if (lengthStr1 == 0)
      return lengthStr2;

    if (lengthStr2 == 0)
      return lengthStr1;

    if (str1.charAt(lengthStr1 - 1) == str2.charAt(lengthStr2 - 1))
      return editDist(str1, str2, lengthStr1 - 1, lengthStr2 - 1);

    return 1
            + min(editDist(str1, str2, lengthStr1, lengthStr2 - 1), // Insert
            editDist(str1, str2, lengthStr1 - 1, lengthStr2), // Remove
            editDist(str1, str2, lengthStr1 - 1,
                    lengthStr2 - 1) // Replace
    );
  }
}
