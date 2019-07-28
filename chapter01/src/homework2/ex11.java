package homework2;

import edu.princeton.cs.algs4.StdOut;

/**
 * 通关自定义类，来实现日期不合法的时候，抛出异常
 */

public class ex11 {

    //定义一个自定义的异常类
    static class DateFormatInvalidException extends Exception {
        DateFormatInvalidException(String s) {
            super(s);
        }
    }

    static class SmartDate {
        private final int month;
        private final int day;
        private final int year;

        SmartDate(int month, int day, int year) {
            if (year < 0) {
                DateFormatInvalidException e = new DateFormatInvalidException("year should not be negtive number");
                throw new RuntimeException(e);
            }
            switch (month) {
                //大月
                case 1:
                case 3:
                case 5:
                case 7:
                case 8:
                case 10:
                case 12: {
                    if (day < 1 || day > 31) {
                        DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
                        throw new RuntimeException(e);
                    }
                }
                break;
                //小月
                case 4:
                case 6:
                case 9:
                case 11: {
                    if (day < 1 || day > 30) {
                        DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
                        throw new RuntimeException(e);
                    }
                }
                case 2: {
                    /*
                     *  if  (year能被4整除  and  不能被100整除)  or  year能被400整除
                     *  		闰年
                     *  else
                     *  		平年
                     */
                    boolean invalid = false;
                    // 闰年日期应该在 [1, 29]
                    //能被4整除但不能被100整除，或者能给400整除
                    if ((year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)) {
                        if (day > 29 || day < 1) invalid = true;
                    } else
                        // 平年日期应该在 [1, 28]
                        if (day > 28 || day < 1) invalid = true;
                    if (invalid) {
                        DateFormatInvalidException e = new DateFormatInvalidException("the date " + year + "/" + month + "/" + day + "is not exist");
                        throw new RuntimeException(e);
                    }
                }
                break;
                default: {
                    DateFormatInvalidException e = new DateFormatInvalidException("the date " + month + "/" + day + "is not exist");
                    throw new RuntimeException(e);
                }
            }
            this.month = month;
            this.day = day;
            this.year = year;
        }

        int day() {
            return day;
        }

        int month() {
            return month;
        }

        int year() {
            return year;
        }

        public String toString() {
            return String.format("日期 : %d/%d/%d", year, month, day);
        }
    }

    public static void main(String[] args) {
        SmartDate date = new SmartDate(2, 28, 2018);
        StdOut.println(date);
    }
}
