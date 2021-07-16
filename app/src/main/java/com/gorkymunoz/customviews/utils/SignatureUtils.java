package com.gorkymunoz.customviews.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;
import android.util.Base64;
import android.util.Log;

import com.gorkymunoz.customviews.BuildConfig;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by Gorky Muñoz on 6/7/2021.
 * <p>
 * gorkymunoz@hotmail.com
 */
public class SignatureUtils {

    public static final String TAG = SignatureUtils.class.getSimpleName();

    @SuppressLint("PackageManagerGetSignatures")
    public static void getKeyHash(Context context, String hashStrategy) {
        PackageInfo info;
        Signature[] signatures;
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                info = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_SIGNING_CERTIFICATES);
                signatures = info.signingInfo.getApkContentsSigners();
            } else {
                info = context.getPackageManager().getPackageInfo(BuildConfig.APPLICATION_ID, PackageManager.GET_SIGNATURES);
                signatures = info.signatures;
            }
            for (Signature signature : signatures) {
                final MessageDigest md = MessageDigest.getInstance(hashStrategy);
                md.update(signature.toByteArray());

                final byte[] digest = md.digest();
                final StringBuilder toRet = new StringBuilder();
                for (int i = 0; i < digest.length; i++) {
                    if (i != 0) toRet.append(":");
                    int b = digest[i] & 0xff;
                    String hex = Integer.toHexString(b);
                    if (hex.length() == 1) toRet.append("0");
                    toRet.append(hex);
                }

/*
                // Convert to hex
                StringBuilder sb = new StringBuilder();
                for (byte b : digest) {
                    sb.append(Integer.toHexString(0xff & b));
                }*/

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Log.e("Key2", hashStrategy + "-->>>>>>>>>>>>" + java.util.Base64.getEncoder().encodeToString(digest));
                } else {
                    Log.e("Key1", hashStrategy + "-->>>>>>>>>>>>" + new String(Base64.encode(digest, 0)));

                }
                Log.e("KeyHash", hashStrategy + "-->>>>>>>>>>>>" + toRet.toString());
            }
        } catch (PackageManager.NameNotFoundException e1) {
            Log.e(TAG, "name not found::", e1);
        } catch (NoSuchAlgorithmException e) {
            Log.e(TAG, "no such an algorithm::" + e);
        } catch (Exception e) {
            Log.e(TAG, "exception", e);
        }
    }
}
