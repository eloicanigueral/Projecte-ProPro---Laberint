/**
 * @class Pair
 * @brief Parell d'objectes.
 *
 * @author mbofill
 * @version 2012.4.16
 */

public class Pair<S,T> {
    public S first;
    public T second;

    public Pair(S x, T y) {
	first = x;
	second = y;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair p = (Pair) o;
        return first.equals(p.first);
    }
}
