package io.nuls.sdk.protocol.service.impl;

import io.nuls.sdk.accountledger.model.InputDto;
import io.nuls.sdk.accountledger.model.OutputDto;
import io.nuls.sdk.accountledger.model.TransactionDto;
import io.nuls.sdk.core.contast.KernelErrorCode;
import io.nuls.sdk.core.model.Result;
import io.nuls.sdk.core.utils.RestFulUtils;
import io.nuls.sdk.core.utils.StringUtils;
import io.nuls.sdk.protocol.model.*;
import io.nuls.sdk.protocol.service.BlockService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author: Charlie
 */
public class BlockServiceImpl implements BlockService {

    private static BlockService instance = new BlockServiceImpl();

    private BlockServiceImpl() {

    }

    public static BlockService getInstance() {
        return instance;
    }

    private RestFulUtils restFul = RestFulUtils.getInstance();

    @Override
    public Result getNewestBlockHight() {
        Result result = restFul.get("/block/newest/hight", null);
        if (result.isFailed()) {
            return result;
        }
        return result;
    }

    @Override
    public Result getNewestBlockHash() {
        Result result = restFul.get("/block/newest/hash", null);
        if (result.isFailed()) {
            return result;
        }
        return result;
    }

    @Override
    public Result getNewestBlockHeader() {
        Result result = restFul.get("/block/newest", null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        BlockHeaderDto blockHeaderDto = new BlockHeaderDto(map);
        return result.setData(blockHeaderDto);
    }

    @Override
    public Result getBlockHeader(int height) {
        if (height < 0) {
            return Result.getFailed(KernelErrorCode.PARAMETER_ERROR);
        }
        Result result = restFul.get("/block/header/height/" + height, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        BlockHeaderDto blockHeaderDto = new BlockHeaderDto(map);
        return result.setData(blockHeaderDto);
    }

    @Override
    public Result getBlockHeader(String hash) {
        if (StringUtils.isBlank(hash)) {
            return Result.getFailed(KernelErrorCode.PARAMETER_ERROR);
        }
        Result result = restFul.get("/block/header/hash/" + hash, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        BlockHeaderDto blockHeaderDto = new BlockHeaderDto(map);
        return result.setData(blockHeaderDto);
    }

    @Override
    public Result getBlock(int height) {
        if (height < 0) {
            return Result.getFailed(KernelErrorCode.PARAMETER_ERROR);
        }
        Result result = restFul.get("/block/height/" + height, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        return result.setData(assembleBlockDto(map));
    }

    @Override
    public Result getBlock(String hash) {
        if (StringUtils.isBlank(hash)) {
            return Result.getFailed(KernelErrorCode.PARAMETER_ERROR);
        }
        Result result = restFul.get("/block/hash/" + hash, null);
        if (result.isFailed()) {
            return result;
        }
        Map<String, Object> map = (Map) result.getData();
        return result.setData(assembleBlockDto(map));
    }

    private BlockDto assembleBlockDto(Map<String, Object> map) {
        List<Map<String, Object>> txMapList = (List<Map<String, Object>>) map.get("txList");
        List<TransactionDto> txList = new ArrayList<>();
        for (Map<String, Object> txMap : txMapList) {
            //重新组装input
            List<Map<String, Object>> inputMaps = (List<Map<String, Object>>) txMap.get("inputs");
            List<InputDto> inputs = new ArrayList<>();
            for (Map<String, Object> inputMap : inputMaps) {
                InputDto inputDto = new InputDto(inputMap);
                inputs.add(inputDto);
            }
            txMap.put("inputs", inputs);
            //重新组装output
            List<Map<String, Object>> outputMaps = (List<Map<String, Object>>) txMap.get("outputs");
            List<OutputDto> outputs = new ArrayList<>();
            for (Map<String, Object> outputMap : outputMaps) {
                OutputDto outputDto = new OutputDto(outputMap);
                outputs.add(outputDto);
            }
            txMap.put("outputs", outputs);
            TransactionDto transactionDto = new TransactionDto(txMap);
            txList.add(transactionDto);
        }
        map.put("txList", txList);
        return new BlockDto(map);
    }

}
