package io.nuls.sdk.core.model;

import io.nuls.sdk.core.crypto.Hex;
import io.nuls.sdk.core.utils.AddressTool;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangkun23 on 2018/10/8.
 */
public class ContractResult {

    /**
     * 交易创建者
     */
    private byte[] sender;

    /**
     * 合约地址
     */
    private byte[] contractAddress;

    /**
     * 合约执行结果
     */
    private String result;
    /**
     * 已使用Gas
     */
    private long gasUsed;
    /**
     * 单价
     */
    private long price;
    /**
     * 状态根
     */
    private byte[] stateRoot;

    /**
     * 调用者向合约转入的资金
     */
    private long value;

    /**
     * 有错误，还原状态
     */
    private boolean revert;

    /**
     * 有错误，状态改变
     */
    private boolean error;

    /**
     *
     */
    private String errorMessage;

    /**
     *
     */
    private String stackTrace;

    /**
     *
     */
    private BigInteger balance;

    private BigInteger preBalance;

    /**
     *
     */
    private BigInteger nonce;

    private boolean isNrc20;

    /**
     * 合约转账交易
     */
    private List<ContractTransfer> transfers = new ArrayList<>();

    /**
     * 消息事件
     */
    private List<String> events = new ArrayList<>();

    private String remark;

    public byte[] getSender() {
        return sender;
    }

    public void setSender(byte[] sender) {
        this.sender = sender;
    }

    public boolean isSuccess() {
        return !error && !revert;
    }

    public byte[] getContractAddress() {
        return contractAddress;
    }

    public void setContractAddress(byte[] contractAddress) {
        this.contractAddress = contractAddress;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public byte[] getStateRoot() {
        return stateRoot;
    }

    public void setStateRoot(byte[] stateRoot) {
        this.stateRoot = stateRoot;
    }

    public long getGasUsed() {
        return gasUsed;
    }

    public void setGasUsed(long gasUsed) {
        this.gasUsed = gasUsed;
    }

    public long getPrice() {
        return price;
    }

    public void setPrice(long price) {
        this.price = price;
    }

    public List<String> getEvents() {
        return events;
    }

    public void setEvents(List<String> events) {
        this.events = events;
    }

    public List<ContractTransfer> getTransfers() {
        return transfers;
    }

    public void setTransfers(List<ContractTransfer> transfers) {
        this.transfers = transfers;
    }

    public boolean isRevert() {
        return revert;
    }

    public void setRevert(boolean revert) {
        this.revert = revert;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getStackTrace() {
        return stackTrace;
    }

    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }

    public BigInteger getBalance() {
        return balance;
    }

    public void setBalance(BigInteger balance) {
        this.balance = balance;
    }

    public BigInteger getPreBalance() {
        return preBalance;
    }

    public void setPreBalance(BigInteger preBalance) {
        this.preBalance = preBalance;
    }

    public BigInteger getNonce() {
        return nonce;
    }

    public void setNonce(BigInteger nonce) {
        this.nonce = nonce;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public boolean isNrc20() {
        return isNrc20;
    }

    public void setNrc20(boolean nrc20) {
        isNrc20 = nrc20;
    }

    @Override
    public String toString() {
        return "ContractResult{" +
                "contractAddress=" + AddressTool.getStringAddressByBytes(contractAddress) +
                ", result='" + result + '\'' +
                ", gasUsed=" + gasUsed +
                ", stateRoot=" + Hex.encode(stateRoot) +
                ", value=" + value +
                ", revert=" + revert +
                ", error=" + error +
                ", errorMessage='" + errorMessage + '\'' +
                ", stackTrace='" + stackTrace + '\'' +
                ", balance=" + (balance != null ? balance.toString() : null) +
                ", nonce=" + nonce +
                ", transfersSize=" + (transfers != null ? transfers.size() : 0) +
                ", eventsSize=" + (events != null ? events.size() : 0) +
                ", remark='" + remark + '\'' +
                '}';
    }
}
