Die Tests brauchen einen extra Constructor für Board:

```java
public Board(int board[][]) {
    this.n = board.length;
    this.board = board;

    int Felder = 0;
    for (int[] x : board) {
        for (int y : x) {
            if (y == 0) {
                Felder++;
            }
        }
    }
    this.freeFields = Felder;
}
```
