package com.grabski.authapi.common.dto;

public record GenericRestResponse<T>(int status, T response) {
}
