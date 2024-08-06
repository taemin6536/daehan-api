package com.nuri.mys.bems.service.common;

import com.nuri.mys.bems.domain.entity.common.CommonPageDto;

public class PagingParamService {

    public static CommonPageDto setPagingParam(CommonPageDto params) {
        int pageNum = 1;
        int rowLength = 10;
        int startNumber = 0;
        
        
//        if(params.getRowLength() != 0) {
//        	rowLength = params.getRowLength();
//        }
//
//        if(params.getPageNum() != 0)
//        {
//            pageNum = params.getPageNum();
//            startNumber = (pageNum * rowLength)-rowLength;
//
//        }
//
//        params.setStartNumber(startNumber);
//        params.setRowLength(rowLength);

        if(params.getOrderColNum() == null) {
            params.setOrderColNum("1");
        }
        if(params.getOrderType() == null) {
            params.setOrderType("DESC");
        }
        return params;
    }
}
