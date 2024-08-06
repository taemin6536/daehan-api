/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package com.nuri.mys.bems.domain.jwt.util.json;

public interface JsonSerializable {
    //
    default String toJson() {
        //
        return JsonUtil.toJson(this);
    }

    default String toPrettyJson() {
        //
        return JsonUtil.toPrettyJson(this);
    }
}