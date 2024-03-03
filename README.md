# algoritmia2homework
Proyect Information:

In this project I decided to use edit distance algorithm(which uses DP) for the misspelling words and then the text simmilarity percentage, and for this one I used an adaptation of the knapsack problem where we are searching the most similar word to every one, so we can see them in the EditDistance class and in the mostSimilarWord(w1, wlist) method respectively, and using this 2 we are able to check if a word is in both texts, or if it has a misspelling or it does not exist.
(Edit distance algorithm was obtained and minimally changed from https://www.geeksforgeeks.org/edit-distance-dp-5/)

the complexity of the solution is divided in 3 parts:
1. calculate text similarity:
  O(i*j)
  where n is the word1 length and m is the word2 length
2. readFile:
   O(l)
   where n is the number of lines of the file.txt
3. Edit Distance:
   O(3^n)
   where n is the longest length of the 2 words
this 3 parts give us a complexity of:
O(i*j*l*(3^n))
aprox: O(m^n)

Is there a non-dynamic programming solution?
yes, there is another solution for the problem, which is just iterate the words of text one comparing with the words in the second text using a different approach instead of the editdistance we can use just a comparator char by char(str[0] == str2[0]?) and then we just add all the coincidence of it by using other iterator, this implementation would be heavier than the first solution in complexity by the use of different cicles and inherated functions, but focussing in the edit distance part, this other solution which just would use O(n^2) complexity could be an improvement 

