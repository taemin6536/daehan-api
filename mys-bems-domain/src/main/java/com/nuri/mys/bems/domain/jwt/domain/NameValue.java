/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.
 * @since 2014. 6. 10.
 */

package com.nuri.mys.bems.domain.jwt.domain;

import com.nuri.mys.bems.domain.jwt.util.json.JsonSerializable;
import com.nuri.mys.bems.domain.jwt.util.json.JsonUtil;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;
import java.util.StringTokenizer;

@Getter
@Setter
@NoArgsConstructor
public class NameValue implements JsonSerializable {
    //
    private String name;
    private String value;

    public NameValue(String name, String value) {
        //
        this.setName(name);
        this.setValue(value);
    }

    public static NameValue fromJson(String json) {
        //
        return JsonUtil.fromJson(json, NameValue.class);
    }

    @Override
    public String toString() {
        //
        return toJson();
    }

    @Override
    public boolean equals(Object target) {
        //
        if (this == target) {
            return true;
        }

        if (target == null || getClass() != target.getClass()) {
            return false;
        }

        NameValue nameValue = (NameValue)target;
        return Objects.equals(this.name, nameValue.name)
                && Objects.equals(this.value, nameValue.value);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name+value);
    }

    public String toSimpleString() {
        //
        return String.format("%s:%s", name, value);
    }

    public static NameValue fromSimpleString(String nameValueString) {
        //
        StringTokenizer tokenizer = new StringTokenizer(nameValueString, ":");
        String name = tokenizer.nextToken();
        String value = tokenizer.nextToken();

        return new NameValue(name, value);
    }

    public static NameValue sample() {
        //
        return new NameValue("name", "Cheolsoo Kim");
    }

//    public static void main(String[] args) {
//        //
//        System.out.println(sample());
//        System.out.println(sample().toSimpleString());
//    }
}
