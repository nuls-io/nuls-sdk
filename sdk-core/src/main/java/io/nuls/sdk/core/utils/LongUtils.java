/*
 * MIT License
 *
 * Copyright (c) 2017-2018 nuls.io
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 *
 */

package io.nuls.sdk.core.utils;

import java.math.BigInteger;

/**
 * 长整型计算工具类
 * Long integer computing utility class.
 *
 * @author: Niels Wang
 */
public class LongUtils {

    public static long add(long val1, long val2) {
        BigInteger value1 = BigInteger.valueOf(val1);
        BigInteger value2 = BigInteger.valueOf(val2);
        return value1.add(value2).longValue();
    }

    public static long sub(long val1, long val2) {
        BigInteger value1 = BigInteger.valueOf(val1);
        BigInteger value2 = BigInteger.valueOf(val2);
        return value1.subtract(value2).longValue();
    }

    public static long mul(long val1, long val2) {
        BigInteger value1 = BigInteger.valueOf(val1);
        BigInteger value2 = BigInteger.valueOf(val2);
        return value1.multiply(value2).longValue();
    }

    public static double exactDiv(long val1, long val2) {
        return DoubleUtils.div(val1, val2);
    }

    public static long div(long val1, long val2) {
        BigInteger value1 = BigInteger.valueOf(val1);
        BigInteger value2 = BigInteger.valueOf(val2);
        return value1.divide(value2).longValue();
    }

    public static long mod(long val1, long val2) {
        BigInteger value1 = BigInteger.valueOf(val1);
        BigInteger value2 = BigInteger.valueOf(val2);
        return value1.mod(value2).longValue();
    }
}
