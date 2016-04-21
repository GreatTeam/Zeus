package com.zeus.web.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class PasswordMD5 {

	public static String getMd5(String username,String password){
		String salt =username+"zeus";
		String mD5 = new Md5Hash(password, salt,2).toString();
		return mD5;
	}
}
