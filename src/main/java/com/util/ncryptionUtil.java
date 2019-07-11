package com.util;

import com.nyist.entity.Customer;
import com.nyist.entity.SysAuthority;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

/**
 * @author ：为天下溪
 * @date ：Created in 2019/2/28 10:23
 * @description：加密工具
 * @version: $version$
 */
public class ncryptionUtil {
    /**
     * 随机生成 salt 需要指定 它的字符串的长度
     *
     * @param len 字符串的长度
     * @return salt
     */
    public static String generateSalt(int len) {
        //一个Byte占两个字节
        int byteLen = len >> 1;
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        return secureRandom.nextBytes(byteLen).toHex();
    }

    /**
     * 获取加密后的密码，使用默认hash迭代的次数 1 次
     *
     * @param hashAlgorithm hash算法名称 MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512、etc。
     * @param password      需要加密的密码
     * @param salt          盐
     * @return 加密后的密码
     */
    public static String encryptPassword(String hashAlgorithm, String password, String salt) {
        return encryptPassword(hashAlgorithm, password, salt, 1);
    }

    /**
     * 获取加密后的密码，需要指定 hash迭代的次数
     *
     * @param hashAlgorithm  hash算法名称 MD2、MD5、SHA-1、SHA-256、SHA-384、SHA-512、etc。
     * @param password       需要加密的密码
     * @param salt           盐
     * @param hashIterations hash迭代的次数
     * @return 加密后的密码
     */
    public static String encryptPassword(String hashAlgorithm, String password, String salt, int hashIterations) {
        SimpleHash hash = new SimpleHash(hashAlgorithm, password, salt, hashIterations);
        return hash.toString();
    }

    /**
     * 验证修改密码与原密码是否相等
     * @param password
     * @param customer
     * @return
     */
    public static int comparePassword(String password, Customer customer){
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(customer.getCustName());
        //将MD5加密后的密文与数据库中的密文对比,如果正确返回1
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
        if (newPs.equals(customer.getCustCode())){
            return 1;
        }
        return 0;
    }

    public static String createPassword(String password, Customer customer){
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(customer.getCustName());
        //将MD5加密后的密文与数据库中的密文对比,如果正确返回1
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();

        return newPs;
    }

    public static String createPassword1(String password, SysAuthority sysAuthority){
        // 将用户名作为盐值
        ByteSource salt = ByteSource.Util.bytes(sysAuthority.getUsername());
        //将MD5加密后的密文与数据库中的密文对比,如果正确返回1
        String newPs = new SimpleHash("MD5", password, salt, 1024).toHex();
        return newPs;
    }
}
