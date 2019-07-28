package homework4;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

/*
 * 思路 :
 *
 * 首先我们把距离定义为无穷大，然后用两个索引指向 lo = 0 和 hi = max
 * 猜一次 lo, 再猜一次 hi, 因为后面这次猜了 hi，所以假如结果是冷，说明神秘数字在 mid 的左边，更靠近 lo，
 * 那么就令 hi = mid
 * 如果结果是热，说明神秘数字在 mid 的右边，更靠近 hi，那么就令 lo = mid
 * 然后重复上述步骤，直到猜中
 *
 */
public class ex34 {
    static class GuessGame {

        /**
         * CORRECT 正确
         * HOT 离得近
         * COLD 离得远
         */
        public enum Result {CORRECT, HOT, COLD}

        private int secretNumber;
        private int prevDistance;
        private int maximum; // [0, maximum]

        public GuessGame(int limit) {
            maximum = limit;
        }

        private void play() {
            prevDistance = Integer.MAX_VALUE;
            //生成的随机数
            secretNumber = StdRandom.uniform(0, maximum + 1);
            StdOut.printf("范围是 %d ~ %d, 要猜测的数字是 %d\n", 0, maximum, secretNumber);
        }

        private Result guess(int guessNumber) {
            int curDistance = Math.abs(guessNumber - secretNumber);
            if (curDistance == 0) return Result.CORRECT;
            //判断离得远还是近
            Result r = curDistance < prevDistance ? Result.HOT : Result.COLD;
            //缩小间隔
            prevDistance = curDistance;
            return r;
        }
    }

    private static void playGame_2lgN(GuessGame game) {
        game.play();
        // lo 下界 hi 上界 guessTimes 游戏次数
        int lo = 0, hi = game.maximum, guessTimes = 0;
        GuessGame.Result result;
        while (lo <= hi) {
            // 猜一个下界
            result = game.guess(lo);
            guessTimes++;
            if (result == GuessGame.Result.CORRECT)
                break;

            // 猜一个上界
            result = game.guess(hi);
            guessTimes++;
            if (result == GuessGame.Result.CORRECT)
                break;

            // 开始二分查找
            int mid = (lo + hi) >> 1;
            switch (result) {
                case COLD:
                    hi = mid;
                    break;
                case HOT:
                    lo = mid;
                    break;
                default:
                    break;
            }
        }
        StdOut.printf("一共猜了 %d 次\n", guessTimes);
    }

    private static void playGame_lgN(GuessGame game) {
        game.play();
        // lo 下界 hi 上界 guessTimes 游戏次数
        int lo = 0, hi = game.maximum, guessTimes = 0;
        GuessGame.Result result;
        while (lo <= hi) {
            // 开始二分查找
            int mid = (lo + hi) >> 1;
            result = game.guess(mid);
            guessTimes++;
            if (result == GuessGame.Result.CORRECT)
                break;
            switch (result) {
                case COLD:
                    hi = mid - 1;
                    break;
                case HOT:
                    lo = mid + 1;
                    break;
                default:
                    break;
            }
        }
        StdOut.printf("一共猜了 %d 次\n", guessTimes);
    }

    public static void main(String[] args) {
        GuessGame game = new GuessGame(8);
        playGame_2lgN(game);
        playGame_lgN(game);
    }
}