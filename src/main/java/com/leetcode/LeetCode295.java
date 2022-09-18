package com.leetcode;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 中位数是有序列表中间的数。如果列表长度是偶数，中位数则是中间两个数的平均值。
 * <p>
 * 例如，
 * <p>
 * [2,3,4] 的中位数是 3
 * <p>
 * [2,3] 的中位数是 (2 + 3) / 2 = 2.5
 * <p>
 * 设计一个支持以下两种操作的数据结构：
 * <p>
 * void addNum(int num) - 从数据流中添加一个整数到数据结构中。
 * double findMedian() - 返回目前所有元素的中位数。
 * 示例：
 * <p>
 * addNum(1)
 * addNum(2)
 * findMedian() -> 1.5
 * addNum(3)
 * findMedian() -> 2
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/find-median-from-data-stream
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LeetCode295 {
    static class MedianFinder {
        Queue<Integer> minHeap = new PriorityQueue<>();
        Queue<Integer> maxHeap = new PriorityQueue<>((o1, o2) -> o2 - o1);

        public void addNum(int num) {
            if (maxHeap.size() != minHeap.size()) {
                minHeap.add(num);
                maxHeap.add(minHeap.poll());
            } else {
                maxHeap.add(num);
                minHeap.add(maxHeap.poll());
            }
        }

        public double findMedian() {
            if(minHeap.isEmpty()) return -1;
            return maxHeap.size() != minHeap.size() ? minHeap.peek() : (maxHeap.peek() + minHeap.peek()) / 2.0;
        }
    }
}
