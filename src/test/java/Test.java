import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Test {
    static int[] arr = {0, 1, 2};

    public static int[] sort(int[] nums) {
        if (nums == null || nums.length == 0) return nums;
        Map<Integer, Integer> cnt = new HashMap<>();
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }

        for (int i = 0; i < nums.length; ) {
            for (int j : arr) {
                int c = cnt.get(j);
                if (c > 0) {
                    nums[i] = j;
                    cnt.put(j, c - 1);
                    i++;
                }
            }
        }

        return nums;
    }

    public static void main(String[] args) {
        int[] nums = {2, 1, 2, 0, 1, 0};
        System.out.println(Arrays.toString(sort(nums)));
//        System.out.println(Arrays.toString(sort2(nums)));
    }
}
