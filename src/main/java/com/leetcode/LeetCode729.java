package com.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * 实现一个 MyCalendar 类来存放你的日程安排。如果要添加的日程安排不会造成 重复预订 ，则可以存储这个新的日程安排。
 * <p>
 * 当两个日程安排有一些时间上的交叉时（例如两个日程安排都在同一时间内），就会产生 重复预订 。
 * <p>
 * 日程可以用一对整数 start 和 end 表示，这里的时间是半开区间，即 [start, end), 实数 x 的范围为，  start <= x < end 。
 * <p>
 * 实现 MyCalendar 类：
 * <p>
 * MyCalendar() 初始化日历对象。
 * boolean book(int start, int end) 如果可以将日程安排成功添加到日历中而不会导致重复预订，返回 true 。否则，返回 false 并且不要将该日程安排添加到日历中。
 *  
 * <p>
 * 示例：
 * <p>
 * 输入：
 * ["MyCalendar", "book", "book", "book"]
 * [[], [10, 20], [15, 25], [20, 30]]
 * 输出：
 * [null, true, false, true]
 * <p>
 * 解释：
 * MyCalendar myCalendar = new MyCalendar();
 * myCalendar.book(10, 20); // return True
 * myCalendar.book(15, 25); // return False ，这个日程安排不能添加到日历中，因为时间 15 已经被另一个日程安排预订了。
 * myCalendar.book(20, 30); // return True ，这个日程安排可以添加到日历中，因为第一个日程安排预订的每个时间都小于 20 ，且不包含时间 20 。
 *  
 * <p>
 * 提示：
 * <p>
 * 0 <= start < end <= 109
 * 每个测试用例，调用 book 方法的次数最多不超过 1000 次。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/my-calendar-i
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode729 {
    static class MyCalendar {
        Set<Integer> tree;
        Set<Integer> lazy;

        public MyCalendar() {
            tree = new HashSet<>();
            lazy = new HashSet<>();
        }

        public boolean book(int start, int end) {
            if (query(start, end - 1, 0, 1000000000, 1)) {
                return false;
            }
            update(start, end - 1, 0, 1000000000, 1);
            return true;
        }

        public boolean query(int start, int end, int left, int right, int idx) {
            if (start > right || end < left) {
                return false;
            }
            /* 如果该区间已被预订，则直接返回 */
            if (lazy.contains(idx)) {
                return true;
            }
            if (start <= left && right <= end) {
                return tree.contains(idx);
            } else {
                int mid = (left + right) >> 1;
                if (end <= mid) {
                    return query(start, end, left, mid, 2 * idx);
                } else if (start > mid) {
                    return query(start, end, mid + 1, right, 2 * idx + 1);
                } else {
                    return query(start, end, left, mid, 2 * idx)
                            || query(start, end, mid + 1, right, 2 * idx + 1);
                }
            }
        }

        public void update(int start, int end, int left, int right, int idx) {
            if (right < start || left > end) {
                return;
            }

            if (start <= left && right <= end) {
                tree.add(idx);
                lazy.add(idx);
            } else {
                int mid = (left + right) / 2;
                update(start, end, left, mid, 2 * idx);
                update(start, end, mid + 1, right, 2 * idx + 1);
                tree.add(idx);
            }
        }
    }

    public static void main(String[] args) {
        MyCalendar myCalendar = new MyCalendar();
        int[][] plans = {{10, 20}, {15, 25}, {20, 30}, {30, 40}};
        for (int[] plan : plans) {
            System.out.println(myCalendar.book(plan[0], plan[1]));
        }
    }
}
