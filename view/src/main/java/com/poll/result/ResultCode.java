package com.poll.result;

import lombok.Getter;

@Getter
public enum ResultCode {

    /* Success status code */
    SUCCESS(0, "Success"),
    ERROR(1, "Error"),

    /* Parameter errors: 10001-19999 */
    PARAM_IS_INVALID(10001, "Invalid parameter"),
    PARAM_IS_BLANK(10002, "Parameter is empty"),
    PARAM_TYPE_BIND_ERROR(10003, "Parameter type error"),
    PARAM_NOT_COMPLETE(10004, "Incomplete parameters"),


    /* User errors: 20001-29999 */
    USER_NOT_LOGGED_IN(20001, "User not logged in"),
    USER_LOGIN_ERROR(20002, "Incorrect account or password"),
    USER_ACCOUNT_FORBIDDEN(20003, "Account is disabled"),
    USER_NOT_EXIST(20004, "User does not exist"),
    USER_HAS_EXISTED(20005, "User already exists"),
    USER_REGISTER_ERROR(20006, "User registration error"),

    /* Business errors: 30001-39999 */
    SPECIFIED_QUESTIONED_USER_NOT_EXIST(30001, "A business issue occurred"),
    LIKABLE_NOT_EXIST(30002, "Likeable object does not exist"),
    REPLYABLE_NOT_EXIST(30003, "Replyable object does not exist"),
    COMMENTABLE_NOT_EXIST(30004, "Commentable object does not exist"),

    /* System errors: 40001-49999 */
    SYSTEM_INNER_ERROR(40001, "System is busy, please try again later"),

    /* Data errors: 50001-59999 */
    RESULT_DATA_NONE(50001, "Data not found"),
    DATA_IS_WRONG(50002, "Data is incorrect"),
    DATA_ALREADY_EXISTED(50003, "Data already exists"),

    /* Interface errors: 60001-69999 */
    INTERFACE_INNER_INVOKE_ERROR(60001, "Internal system interface invocation exception"),
    INTERFACE_OUTER_INVOKE_ERROR(60002, "External system interface invocation exception"),
    INTERFACE_FORBID_VISIT(60003, "Interface access forbidden"),
    INTERFACE_ADDRESS_INVALID(60004, "Interface address invalid"),
    INTERFACE_REQUEST_TIMEOUT(60005, "Interface request timeout"),
    INTERFACE_EXCEED_LOAD(60006, "Interface load too high"),

    /* Permission errors: 70001-79999 */
    PERMISSION_NO_ACCESS(70001, "No access permission"),

    /* File upload */
    UPLOAD_ERROR(80001, "Upload failed"),

    SESSION_TIME_OUT(90001, "Session timed out");

    private final Integer code;

    private final String message;

    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}