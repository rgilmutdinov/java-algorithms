class Solution {

    public static class Point {
        public int x;
        public int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int distSquaredTo(Point p) {
            return (x - p.x) * (x - p.x) + (y - p.y) * (y - p.y);
        }
    }

    public static enum Orientation {
        CLOCKWISE,
        COUNTER_CLOCKWISE,
        COLLINEAR
    }

    // https://e-maxx.ru/algo/oriented_area
    private int triangleArea(Point p1, Point p2, Point p3) {
        // return p1.x * (p2.y - p3.y) + p2.x * (p3.y - p1.y) + p3.x * (p1.y - p2.y);
        return (p2.x - p1.x) * (p3.y - p1.y) - (p3.x - p1.x) * (p2.y - p1.y);
    }

    private Orientation getOrientation(Point p1, Point p2, Point p3) {
        int area = triangleArea(p1, p2, p3);
        if (area < 0)
            return Orientation.CLOCKWISE;
        if (area > 0)
            return Orientation.COUNTER_CLOCKWISE;
        return Orientation.COLLINEAR;
    }

    private void reverse(List<Point> points, int from, int to) {
        for (int i = from, j = to; i < j; i++, j--) {
            Collections.swap(points, i, j);
        }
    }

    private boolean clockwise(Point p1, Point p2, Point p3, boolean includeCollinear) {
        Orientation o = getOrientation(p1, p2, p3);
        return o == Orientation.CLOCKWISE || (includeCollinear && o == Orientation.COLLINEAR);
    }

    // Graham Scan algorithm (n log n)
    private List<Point> convexHull(List<Point> points, boolean includeCollinear) {
        int n = points.size();
        if (n == 0)
            return List.of();

        final Point p0 = Collections.min(points, (p1, p2) -> {
            int cmp = Integer.compare(p1.y, p2.y);
            if (cmp == 0) {
                cmp = Integer.compare(p1.x, p2.x);
            }

            return cmp;
        });

        Collections.<Point>sort(points, (p1, p2) -> {
            Orientation o = getOrientation(p0, p1, p2);
            if (o == Orientation.COLLINEAR) {
                return Integer.compare(p0.distSquaredTo(p1), p0.distSquaredTo(p2));
            }

            return o == Orientation.CLOCKWISE ? -1 : 1;
        });

        if (includeCollinear) {
            int i = n - 1;
            while (i >= 0 && getOrientation(p0, points.get(i), points.get(n - 1)) == Orientation.COLLINEAR) {
                i--;
            }

            reverse(points, i + 1, n - 1);
        }

        List<Point> list = new ArrayList<>();
        for (int i = 0; i < points.size(); i++) {
            while (list.size() > 1 && !clockwise(list.get(list.size() - 2), list.get(list.size() - 1), points.get(i), includeCollinear)) {
                list.remove(list.size() - 1);
            }
            list.add(points.get(i));
        }

        return list;
    }
}