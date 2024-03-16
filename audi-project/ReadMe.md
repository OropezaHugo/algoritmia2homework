2 a)
Greedy algorithms used:
- Kruskal MST(for groups)
    I used it for the K entry of the problem were we are searching separated groups
    with the strongest relation, only inverting the sorted list to be from higher to lower edge's weight
    and because we already formed a MST before it we do not care about possible cycles
- Prim MST(for who can entry to the party)
    I used it for the X entry, where we need no cycles, I use it because that way I know that the MST will
    be totally connected even with the minimum relation strength
- Group separator(to form all the possible groups)
    I create a method which can form as much groups as possible, using some conditionals and iterating the 
    MST graph formed before
https://en.wikipedia.org/wiki/Greedy_algorithm#Applications 
(2nd paragraph affirm that kruskal and prim are greedy algorithms)

3
complexity:
- for the X case part:
    O(n²) n = number of nodes
    because of the sorting(O(n log n)), and the prim algorithm
- for the K case part:
    O(n²) n = number of nodes
    because of the sorting(O(n log n)), and the prim algorithm for the MST,
    the iterators just add each other so the MST algorithm still dominates the complexity
    