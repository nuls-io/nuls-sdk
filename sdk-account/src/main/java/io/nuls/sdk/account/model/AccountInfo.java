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

package io.nuls.sdk.account.model;


import io.nuls.sdk.core.crypto.Hex;
import io.nuls.sdk.core.model.Account;
import io.nuls.sdk.core.utils.StringUtils;

import java.util.Map;

/**
 * @author: Charlie
 */
public class AccountInfo {

    private String address;

    private String alias;

    private String pubKey;

    private String extend;

    private Long createTime;
    /**
     * isEncrypted
     */
    private boolean encrypted;

    /**
     * only offline account
     */
    private String priKey;

    /**
     * only offline account
     */
    private String encryptedPriKey;

    public AccountInfo() {
    }

    public AccountInfo(Map<String, Object> map) {
        this.address = (String) map.get("address");
        this.alias = (String) map.get("alias");
        this.pubKey = (String) map.get("pubKey");
        this.extend = (String) map.get("extend");
        this.createTime = StringUtils.parseLong(map.get("createTime"));
        this.encrypted = (boolean) map.get("encrypted");
    }

    public AccountInfo(Account account){
        this.address = account.getAddress().toString();
        this.alias = account.getAlias();
        this.pubKey =  Hex.encode(account.getPubKey());
        this.createTime = account.getCreateTime();
        if (account.getExtend() != null) {
            this.extend = Hex.encode(account.getExtend());
        }
        this.encrypted = account.isEncrypted();
        this.priKey = Hex.encode(account.getPriKey());
        this.encryptedPriKey = Hex.encode(account.getEncryptedPriKey());
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPubKey() {
        return pubKey;
    }

    public void setPubKey(String pubKey) {
        this.pubKey = pubKey;
    }

    public String getExtend() {
        return extend;
    }

    public void setExtend(String extend) {
        this.extend = extend;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public boolean isEncrypted() {
        return encrypted;
    }

    public void setEncrypted(boolean encrypted) {
        this.encrypted = encrypted;
    }

    public String getPriKey() {
        return priKey;
    }

    public void setPriKey(String priKey) {
        this.priKey = priKey;
    }

    public String getEncryptedPriKey() {
        return encryptedPriKey;
    }

    public void setEncryptedPriKey(String encryptedPriKey) {
        this.encryptedPriKey = encryptedPriKey;
    }
}
