package io.nuls.sdk.accountledger.service.impl;

import io.nuls.sdk.accountledger.model.Input;
import io.nuls.sdk.accountledger.model.Output;
import io.nuls.sdk.accountledger.model.Transaction;
import io.nuls.sdk.accountledger.service.AccountLedgerService;
import io.nuls.sdk.core.contast.AccountErrorCode;
import io.nuls.sdk.core.contast.SDKConstant;
import io.nuls.sdk.core.contast.TransactionErrorCode;
import io.nuls.sdk.core.crypto.AESEncrypt;
import io.nuls.sdk.core.crypto.ECKey;
import io.nuls.sdk.core.crypto.Hex;
import io.nuls.sdk.core.exception.NulsException;
import io.nuls.sdk.core.model.Coin;
import io.nuls.sdk.core.model.Na;
import io.nuls.sdk.core.model.Result;
import io.nuls.sdk.core.model.dto.BalanceInfo;
import io.nuls.sdk.core.script.P2PKHScriptSig;
import io.nuls.sdk.core.utils.*;
import org.spongycastle.util.Arrays;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Charlie
 */
public class AccountLedgerServiceImpl implements AccountLedgerService {

    private static final AccountLedgerService INSTANCE = new AccountLedgerServiceImpl();

    private AccountLedgerServiceImpl() {

    }

    public static AccountLedgerService getInstance() {
        return INSTANCE;
    }

    private RestFulUtils restFul = RestFulUtils.getInstance();

    @Override
    public Result getTxByHash(String hash) {
        if (StringUtils.isBlank(hash)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }

        Result result = restFul.get("/accountledger/tx/" + hash, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        //重新组装input
        List<Map<String, Object>> inputMaps = (List<Map<String, Object>>) map.get("inputs");
        List<Input> inputs = new ArrayList<>();
        for (Map<String, Object> inputMap : inputMaps) {
            Input inputDto = new Input(inputMap);
            inputs.add(inputDto);
        }
        map.put("inputs", inputs);

        //重新组装output
        List<Map<String, Object>> outputMaps = (List<Map<String, Object>>) map.get("outputs");
        List<Output> outputs = new ArrayList<>();
        for (Map<String, Object> outputMap : outputMaps) {
            Output outputDto = new Output(outputMap);
            outputs.add(outputDto);
        }
        map.put("outputs", outputs);
        Transaction transactionDto = new Transaction(map);
        result.setData(transactionDto);
        return result;
    }

    @Override
    public Result transfer(String address, String toAddress, String password, long amount, String remark) {
        if (!AddressTool.validAddress(address) || !AddressTool.validAddress(toAddress)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        if (!StringUtils.validPassword(password)) {
            return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
        }
        if (!validTxRemark(remark)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR);
        }
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("address", address);
        parameters.put("toAddress", toAddress);
        parameters.put("password", password);
        parameters.put("amount", amount);
        parameters.put("remark", remark);
        Result result = restFul.post("/accountledger/transfer", parameters);
        return result;
    }

    @Override
    public Result transfer(String address, String toAddress, long amount, String remark) {
        return transfer(address, toAddress, null, amount, remark);
    }

    private boolean validTxRemark(String remark) {
        if (StringUtils.isBlank(remark)) {
            return true;
        }
        try {
            byte[] bytes = remark.getBytes(SDKConstant.DEFAULT_ENCODING);
            if (bytes.length > 100) {
                return false;
            }
            return true;
        } catch (UnsupportedEncodingException e) {
            return false;
        }
    }

    @Override
    public Result getBalance(String address) {
        if (!AddressTool.validAddress(address)) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }
        Result result = restFul.get("/accountledger/balance/" + address, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        map.put("balance", ((Map) map.get("balance")).get("value"));
        map.put("usable", ((Map) map.get("usable")).get("value"));
        map.put("locked", ((Map) map.get("locked")).get("value"));
        BalanceInfo balanceDto = new BalanceInfo(map);
        return result.setData(balanceDto);
    }

    @Override
    public Result broadcastTransaction(String txHex) {
        Map<String, Object> map = new HashMap<>();
        map.put("txHex", txHex);
        Result result = restFul.post("/accountledger/transaction/broadcast", map);
        return result;
    }

    @Override
    public Result createTransaction(List<Input> inputs, List<Output> outputs, String remark) {
        if (inputs == null || inputs.isEmpty()) {
            return Result.getFailed("inputs error");
        }
        if (outputs == null || outputs.isEmpty()) {
            return Result.getFailed("outputs error");
        }
        byte[] remarkBytes = null;
        if (!StringUtils.isBlank(remark)) {
            try {
                remarkBytes = remark.getBytes(SDKConstant.DEFAULT_ENCODING);
            } catch (UnsupportedEncodingException e) {
                return Result.getFailed("remark error");
            }
        }

        List<Coin> outputList = new ArrayList<>();
        for (int i = 0; i < outputs.size(); i++) {
            Output outputDto = outputs.get(i);
            Coin to = new Coin();
            try {
                to.setOwner(AddressTool.getAddress(outputDto.getAddress()));
            } catch (Exception e) {
                return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
            }

            try {
                to.setNa(Na.valueOf(outputDto.getValue()));
            } catch (Exception e) {
                return Result.getFailed(AccountErrorCode.DATA_PARSE_ERROR);
            }
            if (outputDto.getLockTime() < 0) {
                return Result.getFailed("lockTime error");
            }

            to.setLockTime(outputDto.getLockTime());
            outputList.add(to);
        }

        List<Coin> inputsList = new ArrayList<>();

        for (int i = 0; i < inputs.size(); i++) {
            Input inputDto = inputs.get(i);
            byte[] key = Arrays.concatenate(Hex.decode(inputDto.getFromHash()), new VarInt(inputDto.getFromIndex()).encode());
            Coin coin = new Coin();
            coin.setOwner(key);
            coin.setNa(Na.valueOf(inputDto.getValue()));
            coin.setLockTime(inputDto.getLockTime());
            inputsList.add(coin);
        }

        io.nuls.sdk.core.model.transaction.Transaction tx = TransactionTool.createTransferTx(inputsList, outputList, remarkBytes);
        if (!TransactionTool.isFeeEnough(tx, 1)) {
            return Result.getFailed(TransactionErrorCode.FEE_NOT_RIGHT);
        }

        try {
            String txHex = Hex.encode(tx.serialize());
            Map<String, String> map = new HashMap<>();
            map.put("value", txHex);
            return Result.getSuccess().setData(map);
        } catch (IOException e) {
            Log.error(e);
            return Result.getFailed(e.getMessage());
        }
    }

    @Override
    public Result signTransaction(String txHex, String priKey, String address, String password) {
        if (StringUtils.isBlank(priKey)) {
            return Result.getFailed("priKey error");
        }
        if (StringUtils.isBlank(txHex)) {
            return Result.getFailed("txHex error");
        }
        if (!AddressTool.validAddress(address)) {
            return Result.getFailed("address error");
        }

        if (StringUtils.isNotBlank(password)) {
            if (StringUtils.validPassword(password)) {
                //decrypt
                byte[] privateKeyBytes = null;
                try {
                    privateKeyBytes = AESEncrypt.decrypt(Hex.decode(priKey), password);
                } catch (Exception e) {
                    return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
                }
                priKey = Hex.encode(privateKeyBytes);
            } else {
                return Result.getFailed(AccountErrorCode.PASSWORD_IS_WRONG);
            }
        }
        if (!ECKey.isValidPrivteHex(priKey)) {
            return Result.getFailed(AccountErrorCode.PARAMETER_ERROR, "priKey error");
        }

        ECKey key = ECKey.fromPrivate(new BigInteger(Hex.decode(priKey)));
        try {
            String newAddress = AccountTool.newAddress(key).getBase58();
            if (!newAddress.equals(address)) {
                return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
            }
        } catch (NulsException e) {
            return Result.getFailed(AccountErrorCode.ADDRESS_ERROR);
        }

        try {
            byte[] data = Hex.decode(txHex);
            io.nuls.sdk.core.model.transaction.Transaction tx = TransactionTool.getInstance(new NulsByteBuffer(data));
            tx = TransactionTool.signTransaction(tx, key);

            txHex = Hex.encode(tx.serialize());
            Map<String, String> map = new HashMap<>();
            map.put("value", txHex);
            return Result.getSuccess().setData(map);
        } catch (Exception e) {
            Log.error(e);
            return Result.getFailed(AccountErrorCode.DATA_PARSE_ERROR);
        }
    }

}
