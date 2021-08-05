/*
 * @Date: 04/30/2021 09:25:35
 * @LastEditTime: 04/30/2021 09:26:24
 * @Description: Math
 */
public class _970_PowerfulIntegers {
    // 列举可能的情况，并且x^i + y^j <= bound.
    public List<Integer> powerfulIntegers(int x, int y, int bound) {
        Set<Integer> res = new HashSet<>();
        for (int i = 1; i < bound; i *= x) {
            for (int j = 1; i + j <= bound; j *= y) {
                res.add(i + j);
                if (y == 1)
                    break;
            }
            if (x == 1)
                break;
        }
        return new ArrayList<>(res);
    }
}
