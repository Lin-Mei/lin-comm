package com.lin.comm.validation;

import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.*;

/**
 * @Author meilin
 * @Date 2018/12/314:39
 * @Version 1.0
 **/
public class DemModel {
    @Null(message = "必须为空")   //被注释的元素必须为 null
    private String vNull;
    @NotNull(message = "不能为空") //被注释的元素必须不为 null
    private String vNotNull;
    @NotNull(message = "不能为空")
    @AssertTrue(message = "必须真")     //被注释的元素必须为 true
    private Boolean vTrue;
    @NotNull(message = "不能为空")
    @AssertFalse(message = "必须为假")     //被注释的元素必须为 false
    private Boolean vFalse;
    @NotNull(message = "不能为空")
    @Email(message = "邮箱格式错误") //被注释的元素必须是电子邮箱地址
    private String email;
    @Valid
    private Person person;
//    @Min(value)     被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @Max(value)     被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @DecimalMin(value)  被注释的元素必须是一个数字，其值必须大于等于指定的最小值
//    @DecimalMax(value)  被注释的元素必须是一个数字，其值必须小于等于指定的最大值
//    @Size(max=, min=)   被注释的元素的大小必须在指定的范围内
//    @Digits (integer, fraction)     被注释的元素必须是一个数字，其值必须在可接受的范围内
//    @Past   被注释的元素必须是一个过去的日期
//    @Future     被注释的元素必须是一个将来的日期
//    @Pattern(regex=,flag=)  被注释的元素必须符合指定的正则表达式
//    Hibernate Validator提供的校验注解：
//    @NotBlank(message =)   验证字符串非null，且trim后长度必须大于0
//    @Email  被注释的元素必须是电子邮箱地址
//    @Length(min=,max=)  被注释的字符串的大小必须在指定的范围内
//    @NotEmpty   被注释的字符串的必须非空
//    @Range(min=,max=,message=)  被注释的元素必须在合适的范围内


    public String getvNull() {
        return vNull;
    }

    public void setvNull(String vNull) {
        this.vNull = vNull;
    }

    public String getvNotNull() {
        return vNotNull;
    }

    public void setvNotNull(String vNotNull) {
        this.vNotNull = vNotNull;
    }

    public Boolean getvTrue() {
        return vTrue;
    }

    public void setvTrue(Boolean vTrue) {
        this.vTrue = vTrue;
    }

    public Boolean getvFalse() {
        return vFalse;
    }

    public void setvFalse(Boolean vFalse) {
        this.vFalse = vFalse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    @Override
    public String toString() {
        return "DemModel{" +
                "vNull='" + vNull + '\'' +
                ", vNotNull='" + vNotNull + '\'' +
                ", vTrue=" + vTrue +
                ", vFalse=" + vFalse +
                ", email='" + email + '\'' +
                ", person=" + person +
                '}';
    }
}
