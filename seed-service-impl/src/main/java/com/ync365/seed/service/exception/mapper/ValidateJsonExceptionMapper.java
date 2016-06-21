package com.ync365.seed.service.exception.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.dubbo.rpc.protocol.rest.RestConstraintViolation;
import com.alibaba.dubbo.rpc.protocol.rest.RpcExceptionMapper;
import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.alibaba.fastjson.JSON;
import com.ync365.seed.dto.base.ResponseCode;
import com.ync365.seed.dto.base.ResponseDTO;

public class ValidateJsonExceptionMapper extends RpcExceptionMapper {
    Logger log = LoggerFactory.getLogger(ValidateJsonExceptionMapper.class);

    @Override
    protected Response handleConstraintViolationException(ConstraintViolationException cve) {
        Set<ConstraintViolation<?>> sets = cve.getConstraintViolations();
        ResponseDTO dto = new ResponseDTO();
        dto.setCode(ResponseCode.CommonCode.PARAM_WRONG.v());
        dto.setMsg(ResponseCode.CommonCode.PARAM_WRONG.c());
        List<RestConstraintViolation> list = new ArrayList<>();
        for (ConstraintViolation<?> cv : sets) {
            list.add(new RestConstraintViolation(cv.getPropertyPath().toString(), cv.getMessage(),
                    cv.getInvalidValue() == null ? "null" : cv.getInvalidValue().toString()));
        }
        dto.setData(list);
        log.warn("Message:{},返回值:{}", cve.getMessage(), JSON.toJSON(dto));
        return Response.status(200).entity(dto).type(ContentType.APPLICATION_JSON_UTF_8).build();
    }

}
