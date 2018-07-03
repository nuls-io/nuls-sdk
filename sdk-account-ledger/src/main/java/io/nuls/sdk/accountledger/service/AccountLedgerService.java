package io.nuls.sdk.accountledger.service;

import io.nuls.sdk.accountledger.model.InputDto;
import io.nuls.sdk.accountledger.model.OutputDto;
import io.nuls.sdk.core.model.Result;

import java.util.List;

/**
 * @author: Charlie
 */
public interface AccountLedgerService {

    /**
     * Get transaction details based on transaction hash
     *
     * @param hash Transaction hash
     * @return If the operation is successful, 'success' is true, and data is TransactionDto;
     * If the operation fails, "success" is false and the result has error information
     */
    Result getTxByHash(String hash);

    /**
     * Transfer
     *
     * @param address   Remittance account address
     * @param toAddress Beneficiary account Address
     * @param password  Remittance account password
     * @param amount    Transfer amount
     * @param remark remark
     * @return If the operation is successful, 'success' is true
     * If the operation fails, "success" is false and the result has error information
     */
    Result transfer(String address, String toAddress, String password, long amount, String remark);

    /**
     * Transfer
     *
     * @param address   Remittance account address
     * @param toAddress Beneficiary account Address
     * @param amount    Transfer amount
     * @param remark remark
     * @return If the operation is successful, 'success' is true
     * If the operation fails, "success" is false and the result has error information
     */
    Result transfer(String address, String toAddress, long amount, String remark);

    /**
     * Get account balance
     *
     * @param address address
     * @return If the operation is successful, 'success' is true, and data is BalanceDto
     * If the operation fails, "success" is false and the result has error information
     */
    Result getBalance(String address);

    /**
     * 创建交易
     * Create Transaction
     *
     * @param inputs inputs
     * @param outputs outputs
     * @param remark remark
     * @return Result
     */
    Result createTransaction(List<InputDto> inputs, List<OutputDto> outputs, String remark);

    /**
     * 签名交易
     * Sign Transaction
     *
     * @param txHex txHex
     * @param priKey  priKey
     * @param address address
     * @param password password
     * @return Result
     */
    Result signTransaction(String txHex, String priKey, String address, String password);

    /**
     * 广播交易
     * Broadcast Transaction
     *
     * @param txHex txHex
     * @return Result
     */
    Result broadcastTransaction(String txHex);
}
